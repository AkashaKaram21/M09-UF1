/*
 * Programa que xifra i desxifra textos amb xifratge mono-alfabètic.
 * Utilitza una permutació fixa de l'alfabet per substituir les lletres.
 * Manté majúscules, minúscules, espais i símbols.
 */
import java.util.*; 
public class Monoalfabetic {

    // Array de majùscules
    static char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();

    // Aquest array es guardarà la permutació generada per poder desxifrar després
    static char[] permutat;

    //En aquest mètode tenim que crear un array permutat
    public static char[] permutaAlfabet(char[] alfabet) {

        // Convertim el char[] en una llista de caràcters
        List<Character> llista = new ArrayList<>();

        // Afegim cada lletra de l’alfabet a la llista
        for (char c : alfabet) {
            llista.add(c);
        }

        // Barregem l’ordre dels caràcters amb Collections.shuffle
        Collections.shuffle(llista);

        // Creem un nou array de caràcters amb la mateixa mida
        char[] arrayChar = new char[llista.size()];

        // Tornem a passar cada lletra de la llista al nou array
        for (int i = 0; i < llista.size(); i++) {
            arrayChar[i] = llista.get(i);
        }

        // Retornem el nou alfabet permutat
        return arrayChar;
    }

    //Comparem el array majùscules amb el permutat
    public static String xifraMonoAlfa(String cadena) {

        // Generem la permutació només una vegada
        permutat = permutaAlfabet(majuscules);

        // Creem una cadena buida per anar afegint les lletres xifrades
        String resultat = "";

        // Recorrem cada caràcter de la cadena original
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false; 

            // Recorrem l’alfabet per buscar la lletra (en majúscula o minúscula)
            for (int j = 0; j < majuscules.length; j++) {
                // Si la lletra és majúscula
                if (c == majuscules[j]) {
                    resultat += permutat[j];
                    trobat = true;
                    break;
                }
                // Si la lletra és minúscula, convertim a majúscula per trobar la seva posició
                else if (Character.toUpperCase(c) == majuscules[j]) {
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
                    resultat += majuscules[j];
                    trobat = true;
                    break;
                }
                // Si és minúscula
                else if (Character.toUpperCase(c) == permutat[j]) {
                    resultat += Character.toLowerCase(majuscules[j]);
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

    // --- MÈTODE MAIN per provar el programa ---
    public static void main(String[] args) {

        String text = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        System.out.println("Text original: " + text);

        String xifrat = xifraMonoAlfa(text);
        System.out.println("Text xifrat:  " + xifrat);

        String desxifrat = desxifraMonoAlfa(xifrat);
        System.out.println("Text desxifrat: " + desxifrat);
    }
}
