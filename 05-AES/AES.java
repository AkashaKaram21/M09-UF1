import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class AES {

    //Algoritmo de cifrado
    public static final String ALGORSIME_XIFRAT = "AES";
    //Algoritmo de hash, si xifra ja no es podra desxifrar
    public static final String ALGORISME_HASH = "SHA-256";
    //Formato de cifrado
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    //Tamaño del IV
    private static final byte[] MIDA_IV = 16;
    //Crea el IV
    private static byte[] iv = new byte[MIDA_IV];
    //Clave secreta
    private static final String CLAU = "123456789";

        public static byte[] xifratAES(String msg, String clau) throws Exception {
        //Existeix un mètode .getBytes() que serveix per obtenir el bytes
        byte[] msgBytes = msg.getBytes("UTF-8");

        //Hem de fer l'Initialization Vector però amb el random perqu+e no comencem sempre de la mateixa manera
        byte[] ivBytes = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);

        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        //Crear el digest amb l'agoritme de la consta 
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);

        // Calcular el hash
        byte[] hashBytes = digest.digest(clau.getBytes("UTF-8"));

        // Convertir a hexadecimal per veure-ho
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        // Crear la clau AES a partir del hash
        SecretKeySpec keySpec = new SecretKeySpec(hashBytes, "AES");

        // Inicialitzar el cipher AES/CBC/PKCS5Padding
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Retornar el text xifrat
        return cipher.doFinal(msgBytes);
    }

    public static String desxifraAES(byte[] blvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV (els primers 16 bytes)
        byte[] ivBytes = new byte[MIDA_IV];
        System.arraycopy(blvIMsgXifrat, 0, ivBytes, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // Extreure la part xifrada
        int xifratLength = blvIMsgXifrat.length - MIDA_IV;
        byte[] textXifrat = new byte[xifratLength];
        System.arraycopy(blvIMsgXifrat, MIDA_IV, textXifrat, 0, xifratLength);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashBytes = digest.digest(clau.getBytes("UTF-8"));

        // Crear la clau AES a partir del hash
        SecretKeySpec keySpec = new SecretKeySpec(hashBytes, "AES");

        // Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] textBytes = cipher.doFinal(textXifrat);

        // return String desxifrat
        return new String(textBytes, "UTF-8");
    }

        

      public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Agora lila Ótto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("---");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
    
}
