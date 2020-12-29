package mas.emma.services.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import mas.emma.data.statictypes.constants.ApplicationConstants;
import mas.emma.services.Application;

/**
 *
 * @author nlmsc
 */
public class AES implements AutoCloseable {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public AES(String appKey) {
        MessageDigest sha = null;
        try {
            key = appKey.getBytes(ApplicationConstants.UTF8);
            sha = MessageDigest.getInstance(ApplicationConstants.SHA1);
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ApplicationConstants.AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            ExceptionHelper.logException(e.getMessage(), null, Application.getAppType());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ExceptionHelper.logException(e.getMessage(), null, Application.getAppType());
        }
    }

    public String encrypt(String strToEncrypt) {
        try {

            Cipher cipher = Cipher.getInstance(ApplicationConstants.PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(ApplicationConstants.UTF8)));
        } catch (Exception e) {
            ExceptionHelper.logException(e.getMessage(), null, Application.getAppType());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance(ApplicationConstants.PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            ExceptionHelper.logException(e.getMessage(), null, Application.getAppType());
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        close();
    }

}
