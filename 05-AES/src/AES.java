package src;
/**
 * Classe que implementa el xifrat i desxifrat utilitzant 
 * l'estàndard AES en mode CBC amb PKCS5Padding.
 * * S'utilitza SHA-256 per generar una clau de 256 bits 
 * a partir d'una contrasenya (clau) i un IV (Initialization Vector) 
 * generat aleatòriament per a cada missatge.
 * * Els mètodes d'encriptació i desencriptació gestionen la 
 * combinació i extracció de l'IV del missatge xifrat.
 */

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays; 

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec; 

public class AES {

    // Algoritme de xifrat
    public static final String ALGORSIME_XIFRAT = "AES";
    // Algoritme de hash per a la clau
    public static final String ALGORISME_HASH = "SHA-256";
    // Format de xifrat: AES amb mode CBC i padding PKCS5
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    // Mida de l'IV (Initialization Vector) en bytes, 16 bytes per a AES
    private static final int MIDA_IV = 16;
    // La clau es fa una constant privada ja que es fa servir al main
    private static final String CLAU = "MiClaveSecreta_2025!"; 

    // Mètode per xifrar un missatge (String msg) amb una clau (String clau)
    public static byte[] xifraAES(String msg, String clau) throws Exception {

        // 1. Obtindre els bytes de l'String
        byte[] msgBytes = msg.getBytes("UTF-8");

        // 2. Genera IvParameterSpec
        // Generar l'IV de forma aleatòria per seguretat
        byte[] ivBytes = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        // Crear l'especificació de paràmetre de l'IV
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 3. Genera hash (per la clau de 256 bits)
        // Inicialitzar MessageDigest amb l'algoritme de hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        // Calcular el hash (bytes) de la clau
        byte[] hashBytes = digest.digest(clau.getBytes("UTF-8"));
        // Crear l'especificació de la clau secreta AES a partir del hash
        SecretKeySpec keySpec = new SecretKeySpec(hashBytes, "AES");

        // 4. Encrypt.
        // Inicialitzar el xifrador amb el format especificat
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        // Configurar el xifrador en mode ENCRYPT amb la clau i l'IV
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        // Xifrar el missatge
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        // 5. Combinar IV i part xifrada.
        // Crear un nou array de bytes per a IV + missatge xifrat
        byte[] bIViMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        // Copiar l'IV a l'inici del nou array
        System.arraycopy(ivBytes, 0, bIViMsgXifrat, 0, MIDA_IV);
        // Copiar la part xifrada després de l'IV
        System.arraycopy(msgXifrat, 0, bIViMsgXifrat, MIDA_IV, msgXifrat.length);

        // 6. return iv+msgxifrat
        return bIViMsgXifrat;
    }

    // Mètode per desxifrar un missatge (byte[] bIvIMsgXifrat) amb una clau (String clau)
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // 1. Extreure l'IV. (Els primers MIDA_IV bytes)
        byte[] ivBytes = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        // Crear l'especificació de paràmetre de l'IV
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 2. Extreure la part xifrada.
        // La resta dels bytes
        byte[] textXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // 3. Fer hash de la clau
        // Inicialitzar MessageDigest amb l'algoritme de hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        // Calcular el hash (bytes) de la clau
        byte[] hashBytes = digest.digest(clau.getBytes("UTF-8"));
        // Crear l'especificació de la clau secreta AES a partir del hash
        SecretKeySpec keySpec = new SecretKeySpec(hashBytes, "AES");

        // 4. Desxifrar.
        // Inicialitzar el xifrador amb el format especificat
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        // Configurar el xifrador en mode DECRYPT amb la clau i l'IV
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        // Desxifrar el missatge
        byte[] textBytes = cipher.doFinal(textXifrat);

        // 5. return String desxifrat
        return new String(textBytes, "UTF-8");
    }

    public static void main(String[] args) {
        // Missatges de prova
        String msgs[] = {"Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Agora lila Ótto"};

        // Iterar sobre els missatges
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                // Xifrar el missatge
                bXifrats = xifraAES(msg, CLAU);
                // Desxifrar el missatge xifrat
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                // Capturar errors de xifrat/desxifrat
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            
            // Mostrar els resultats
            System.out.println("-------------------------");
            System.out.println("Msg: " + msg);
            // new String(bXifrats) s'utilitza només per a la visualització, 
            // el resultat no és text llegible, són bytes
            System.out.println("Enc: " + new String(bXifrats)); 
            System.out.println("DEC: " + desxifrat);
        }
    }

}