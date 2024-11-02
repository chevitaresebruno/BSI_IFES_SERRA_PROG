package scripts.adt;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Senha
{
    private SecretKey chave;
    private IvParameterSpec iv;
    private byte[] hash;

    public Senha(String senha) throws Exception
    {
        this.chave = gerarChave();
        this.iv = gerarIv();
        this.hash = encrypt(senha, this.chave,this.iv);
    }

    public boolean validar(String senha) throws Exception
    {
        return new String(this.hash).compareTo(new String(encrypt(senha, this.chave, this.iv))) == 0;
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

}
