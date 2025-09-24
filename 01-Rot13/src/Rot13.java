import java.util.Scanner;

public class Rot13 {

    static char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    static char[] minuscules = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();

    public static String xifraRot13(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;
            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    resultat += minuscules[(j + 13) % minuscules.length];
                    trobat = true;
                }
            }
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    resultat += majuscules[(j + 13) % majuscules.length];
                    trobat = true;
                }
            }
            if (!trobat) {
                resultat += c;
            }
        }
        return resultat;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.print("Escribe un texto: ");
        String texto = sc.nextLine();        

        String cifrado = xifraRot13(texto);  
        System.out.println("Texto cifrado: " + cifrado);

        sc.close(); 
    }
}
