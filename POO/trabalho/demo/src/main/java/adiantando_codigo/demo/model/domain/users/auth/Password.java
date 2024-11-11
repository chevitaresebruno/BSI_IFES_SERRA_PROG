package adiantando_codigo.demo.model.domain.users.auth;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class Password
{
    private final SecretKey key;
    private final IvParameterSpec iv;
    private final byte[] hash;

    public Password(String password) throws Exception
    {
        this.key = generateKey();
        this.iv = generateIv();
        this.hash = encrypt(password, this.key, this.iv);
    }

    public boolean compare(String password) throws Exception
    {
        return new String(this.hash).compareTo(new String(encrypt(password, this.key, this.iv))) == 0;
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException
    {
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES"); 
        keygenerator.init(128);

        return keygenerator.generateKey();
    }

    private static IvParameterSpec generateIv()
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
