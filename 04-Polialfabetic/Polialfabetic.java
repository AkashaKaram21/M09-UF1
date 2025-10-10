/*
 * 
 */
   import java.util.*; 

public class Polialfabetic {

    // Array de majùscules
    final static char[] alfabet = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();

    // Aquest array es guardarà la permutació generada per poder desxifrar després
    static char[] permutat;

    //En aquest mètode tenim que crear un array permutat
    public static char[] permutaAlfabet(char[] alfabet) {
        char[] arrayChar = alfabet.clone();
        Random random = new Random();

        for (int i = 0; i < arrayChar.length; i++) {
            int j = random.nextInt(arrayChar.length);
            // Intercanviem arrayChar[i] i arrayChar[j]
            char temp = arrayChar[i];
            arrayChar[i] = arrayChar[j];
            arrayChar[j] = temp;
        }

        return arrayChar;
    }


    public static String xifraMonoAlfa(String cadena) {

        // Generem la permutació només una vegada
        permutat = permutaAlfabet(alfabet);

        // Creem una cadena buida per anar afegint les lletres xifrades
        String resultat = "";

        // Recorrem cada caràcter de la cadena original
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false; 

            // Recorrem l’alfabet per buscar la lletra (en majúscula o minúscula)
            for (int j = 0; j < alfabet.length; j++) {
                // Si la lletra és majúscula
                if (c == alfabet[j]) {
                    resultat += permutat[j];
                    trobat = true;
                    break;
                }
                // Si la lletra és minúscula, convertim a majúscula per trobar la seva posició
                else if (Character.toUpperCase(c) == alfabet[j]) {
                    resultat += Character.toLowerCase(permutat[j]);
                    trobat = true;
                    break;
                }
            }

            // Si el caràcter no és una lletra (espai, número, etc.), el deixem igual
            if (!trobat) {
                resultat += c;
            }
        }

        return resultat;
    }

    // Fem al revès 
    public static String desxifraMonoAlfa(String cadena) {

        // Creem una cadena buida per al resultat
        String resultat = "";

        // Recorrem cada lletra del text xifrat
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;

            // Recorrem l'alfabet permutat per trobar la posició original
            for (int j = 0; j < permutat.length; j++) {
                // Si és majúscula
                if (c == permutat[j]) {
                    resultat += alfabet[j];
                    trobat = true;
                    break;
                }
                // Si és minúscula
                else if (Character.toUpperCase(c) == permutat[j]) {
                    resultat += Character.toLowerCase(alfabet[j]);
                    trobat = true;
                    break;
                }
            }

            // Si el caràcter no és lletra, es deixa igual
            if (!trobat) {
                resultat += c;
            }
        }

        return resultat;
    }

    public static void main(String[] args) {
        
        String text = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        System.out.println("Text original: " + text);

        String xifrat = xifraMonoAlfa(text);
        System.out.println("Text xifrat:  " + xifrat);

        String desxifrat = desxifraMonoAlfa(xifrat);
        System.out.println("Text desxifrat: " + desxifrat);
        
    }


}
