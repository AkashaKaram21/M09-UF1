/*
 * Name and surname : Akasha Karam
 * Problem: In this activity whe have to convert the normal text into permutat 
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class Monoalfabetic {

    static char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();

    public static char[] permutaAlfabet(char[] alfabet ){

        //Convertim el charArray en list
        List<Character> llista = new ArrayList()<>;

       //Guardem cada caracter en la llista 
       for(charcater c : llista){
         llista.add(c);
       }

       //Desprès el desordem amb el 
       Collections.shuffle(llista);

       // Convertir a array de char amb el bucle 
        char[] arrayChar = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            arrayChar[i] = llista.get(i);
        }

        return arrayChar[i];
    }

    public static char[] xifraMonoAlfa(String cadena){
        
        //Cridem al metòde anterior amb el char
        char[] permutat = permutaAlfabet(majuscules);
        //onvertim la cadena en array
        char[] array = new char[cadena.length()];


       // recorrem cada caràcter de la cadena
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;

            // busquem el caràcter a l'alfabet original
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    array[i] = permutat[j]; 
                    trobat = true;
                }
            }

            if (!trobat) {
                array[i] = c; 
            }
        }

        return array;
    }

    public static char[] desxifraMonoAlfa(String cadena){
        
        char[] permutat = permutaAlfabet(majuscules);

        char[] array = new char[cadena.length()];

        // recorrem cada caràcter de la cadena
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;

            // busquem el caràcter a l'alfabet original
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                      permutat[j] = array[i];
                        trobat = true;
                }
            }

            if (!trobat) {
                array[i] = c; 
            }
        }

        return array;
    }

    }

    public static void main(String[] args) {

        String cadena = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        System.out.println(cadena);
        System.out.println("| | | | | | | | | | | | | | | | | | | | | | | | | |");
    }

