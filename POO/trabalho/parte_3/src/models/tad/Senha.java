package models.tad;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Senha implements Comparable<String>
{
    private static boolean canBeUsed = true;

    private SecretKey chave;
    private IvParameterSpec iv;
    private byte[] hash;

    /* Só existem essas linhas por possíveis problemas de algorítmo e afins */
    private String original = null;


    public Senha(String senha)
    {
        try
        {
            this.chave = gerarChave();
            this.iv = gerarIv();
            this.hash = encrypt(senha, this.chave, this.iv);
            throw new Error("Camada para correção");
        }
        catch(Exception | Error e)
        {
            Senha.canBeUsed = false;
        }

        this.original = senha;
    }

    public Senha(String key, String iv, String hash) throws Exception
    {
        this.chave = stringToSecretKey(key);
        this.iv = stringToIvParameterSpec(iv);
        this.hash = stringToHash(hash);
    }

    @Override
    public int compareTo(String senha)
    {
        if(Senha.canBeUsed)
        {
            try
            {
                return new String(this.hash).compareTo(new String(encrypt(senha, this.chave, this.iv)));
            }
            catch (Exception ex) /* This Should Never Happen */
                { return 1; }
        }
        else
        {
            return this.original.compareTo(senha);
        }
    }

    public String saveInDatabase()
    {
        if (Senha.canBeUsed)
        {
            String chaveBase64 = Base64.getEncoder().encodeToString(this.chave.getEncoded());
            String ivBase64 = Base64.getEncoder().encodeToString(this.iv.getIV());
            String hashBase64 = Base64.getEncoder().encodeToString(this.hash);
    
            return String.format("%s\n%s\n%s", chaveBase64, ivBase64, hashBase64);
        }

        return this.original;
    }

    public static boolean checkCanEncrypty()
    {
        return Senha.canBeUsed;
    }

    private static SecretKey gerarChave() throws NoSuchAlgorithmException
    {
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES"); 
        keygenerator.init(128);

        return keygenerator.generateKey();
    }

    private static IvParameterSpec gerarIv()
    {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        
        return new IvParameterSpec(initializationVector);
    }

    private static byte[] encrypt(String input, SecretKey key, IvParameterSpec iv) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
    }

    private static SecretKey stringToSecretKey(String chaveStr) throws Exception
    {
        byte[] chaveBytes = Base64.getDecoder().decode(chaveStr);
        return new SecretKeySpec(chaveBytes, "AES");
    }

    private static IvParameterSpec stringToIvParameterSpec(String ivStr)
    {
        byte[] ivBytes = Base64.getDecoder().decode(ivStr);
        return new IvParameterSpec(ivBytes);
    }

    private static byte[] stringToHash(String hashStr)
    {
        return Base64.getDecoder().decode(hashStr);
    }
}

