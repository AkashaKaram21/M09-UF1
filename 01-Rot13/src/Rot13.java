//Agafem de llibreria el Scanner 
import java.util.Scanner;

public class Rot13 {

    //.toCharArray() amb majúscules
    static char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    
    //.toCharArray() amb minúscules
    static char[] minuscules = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();

    //Hem de crear dues funcions

    //1.- xifraRot13( cadena ): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la
    //lletra que està 13 posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).
    
    public static String xifraRot13(String cadena) {
    
    // Fem una variable per poder guardar el resultat
    String resultat = "";
    
    // Recorem la cadena
    for (int i = 0; i < cadena.length(); i++) {
        // Agafem cada lletra de la cadena
        char c = cadena.charAt(i);
        
        // Variable que indica si hem trobat la lletra dins dels arrays de minuscules o majuscules
        // Serveix per saber si hem fet la conversió ROT13. Si segueix sent false, 
        // la lletra no és alfabètica i es copia tal qual al resultat.
        boolean trobat = false;
        
        // Recorrem l'array de minuscules
        for (int j = 0; j < minuscules.length; j++) {
            // Comprovem si la lletra coincideix amb la del array de minuscules
            if (c == minuscules[j]) {
                // Avancem 13 posicions utilitzant % per no passar-nos de l'array
                resultat += minuscules[(j + 13) % minuscules.length];
                
                // Marquem que hem trobat la lletra i hem fet la conversió
                trobat = true;
            }
        }
        
        // Recorrem l'array de majúscules
        for (int j = 0; j < majuscules.length; j++) {
            if (c == majuscules[j]) {
                resultat += majuscules[(j + 13) % majuscules.length];
                trobat = true; // marquem que hem trobat la lletra
            }
        }
        
        // Si no hem trobat la lletra (no és ni majúscula ni minúscula),
        // simplement la copiem tal com és
        if (!trobat) {
            resultat += c;
        }
    }
    
        // Retornem la cadena xifrada
        return resultat;
    }

    //2.- desxifraRot13( cadena ): Ha de fer el procés invers de la funció anterior.
    //Aqui no tenim la variable per guardar ya que estem tornat i els espais i punt ya estaran
    //En cas que el guardem es torna voig :(

    //Es el mateix proccès però en inversa :)
    public static String desxifraRot13(String cadena){
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            
            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    resultat += minuscules[(j - 13 + minuscules.length) % minuscules.length];
                }
            }

            
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    resultat += majuscules[(j - 13 + majuscules.length) % majuscules.length];
                }
            }
        }
        
        return resultat;
    }


   public static void main(String[] args) {
        
        System.out.println("---------");
        //Hem de guardar la variable que es dexifran 
        String e1 = "ABC";
        String e2 = "XYZ";
        String e3 = "Hola, Mr. calçot";
        String e4 = "Perdó, per tu què és?";

        // En el main la sortida ha de ser la mateixa al exemple
        System.out.println(e1 + " => " + xifraRot13(e1));
        System.out.println(e2 + " => " + xifraRot13(e2));
        System.out.println(e3 + " => " + xifraRot13(e3));
        System.out.println(e4 + " => " + xifraRot13(e4));

        System.out.println("Desxifrat");
        System.out.println("---------");

        System.out.println(xifraRot13(e1) + " => " + desxifraRot13(xifraRot13(e1)));
        System.out.println(xifraRot13(e2) + " => " + desxifraRot13(xifraRot13(e2)));
        System.out.println(xifraRot13(e3) + " => " + desxifraRot13(xifraRot13(e3)));
        System.out.println(xifraRot13(e4) + " => " + desxifraRot13(xifraRot13(e4)));
    }
}