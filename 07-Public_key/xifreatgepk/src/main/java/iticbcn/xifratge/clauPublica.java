package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import javax.crypto.Cipher;

public class clauPublica {

    public static void main(String[] args) {
        // Código del main aquí si es necesario
    }
        
    public KeyPair generaParellClausRSA() throws Exception {
        //Per generar la clau publica i privada s'utiliza el KeyPairGenerator que utiliza el RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //Canviem el tamaño de bits por 2048 que és más seguro
        keyGen.initialize(2048);
        //Retornem la clau publica i privada 
        return keyGen.generateKeyPair();
    }

    public byte[] xifratRSA(String msg, PublicKey clauPublica) throws Exception {
        //Usamo el chiper per xifrar i agafem el RSA creat abans 
        Cipher cipher = Cipher.getInstance("RSA");
        //Inialitzem el la clau publica 
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        //Convertim la clau en bytes i el cifrem amb el RSA 
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifratRSA(byte[] missatgeXifrat, PrivateKey clauPrivada) throws Exception {
        //Agafem el RSA 
        Cipher cipher = Cipher.getInstance("RSA");
        //Inialitzem el clauPrivada 
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        //El convertim en bytes i el xifrem amb RSA 
        byte[] bytesDesxifrats = cipher.doFinal(missatgeXifrat);
        //El retornem amb String 
        return new String(bytesDesxifrats, "UTF-8");
    }
}