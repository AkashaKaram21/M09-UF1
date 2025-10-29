package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    private final String ALGORISME_HASH = "SHA-256";
    private final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private final int MIDA_IV = 16;
    private final String CLAU = "MiClaveSecreta_2025!";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] msgBytes = msg.getBytes("UTF-8");
            
            byte[] ivBytes = new byte[MIDA_IV];
            SecureRandom random = new SecureRandom();
            random.nextBytes(ivBytes);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hashBytes = digest.digest(clau.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(hashBytes, "AES");
            
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] msgXifrat = cipher.doFinal(msgBytes);
            
            byte[] bIViMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
            System.arraycopy(ivBytes, 0, bIViMsgXifrat, 0, MIDA_IV);
            System.arraycopy(msgXifrat, 0, bIViMsgXifrat, MIDA_IV, msgXifrat.length);
            
            return new TextXifrat(bIViMsgXifrat);
        } catch (Exception e) {
            System.err.println("Error de xifrat AES: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] bIvIMsgXifrat = xifrat.getBytes();
            
            byte[] ivBytes = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            
            byte[] textXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
            
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hashBytes = digest.digest(clau.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(hashBytes, "AES");
            
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] textBytes = cipher.doFinal(textXifrat);
            
            return new String(textBytes, "UTF-8");
        } catch (Exception e) {
            System.err.println("Error de desxifrat AES: " + e.getLocalizedMessage());
            return null;
        }
    }

    // MAIN mantenido
    public static void main(String[] args) {
        XifradorAES xifrador = new XifradorAES();
        String msgs[] = {"Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Agora lila Ótto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            TextXifrat bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifrador.xifra(msg, xifrador.CLAU);
                desxifrat = xifrador.desxifra(bXifrats, xifrador.CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            
            System.out.println("-------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats.getBytes())); 
            System.out.println("DEC: " + desxifrat);
        }
    }
}