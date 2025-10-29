package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    private final char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    private char[] permutat;
    
    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(majuscules);
    }
    
    private char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista);
        
        char[] arrayChar = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            arrayChar[i] = llista.get(i);
        }
        return arrayChar;
    }
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        
        String resultat = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean trobat = false;
            
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    resultat += permutat[j];
                    trobat = true;
                    break;
                } else if (Character.toUpperCase(c) == majuscules[j]) {
                    resultat += Character.toLowerCase(permutat[j]);
                    trobat = true;
                    break;
                }
            }
            
            if (!trobat) {
                resultat += c;
            }
        }
        
        return new TextXifrat(resultat.getBytes());
    }
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        
        if (xifrat == null) {
            return null;
        }
        
        String cadena = new String(xifrat.getBytes());
        String resultat = "";
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;
            
            for (int j = 0; j < permutat.length; j++) {
                if (c == permutat[j]) {
                    resultat += majuscules[j];
                    trobat = true;
                    break;
                } else if (Character.toUpperCase(c) == permutat[j]) {
                    resultat += Character.toLowerCase(majuscules[j]);
                    trobat = true;
                    break;
                }
            }
            
            if (!trobat) {
                resultat += c;
            }
        }
        
        return resultat;
    }
}