package iticbcn.xifratge;

import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyzàèéíïòóúüçñ".toCharArray();
    private char[] alfabetPermutat = new char[alfabet.length];
    private Random random;
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        
        long llavor = Long.parseLong(clau);
        initRandom(llavor);
        permutaAlfabet();
        
        String resultat = "";
        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            char lletraXifrada = lletra;
            
            if (Character.isLetter(lletra)) {
                boolean esMajuscula = Character.isUpperCase(lletra);
                char lletraMinuscula = Character.toLowerCase(lletra);
                
                int posicio = -1;
                for (int j = 0; j < alfabet.length; j++) {
                    if (alfabet[j] == lletraMinuscula) {
                        posicio = j;
                        break;
                    }
                }
                
                if (posicio != -1) {
                    lletraXifrada = alfabetPermutat[posicio];
                    if (esMajuscula) {
                        lletraXifrada = Character.toUpperCase(lletraXifrada);
                    }
                }
            }
            
            resultat += lletraXifrada;
            permutaAlfabet();
        }
        
        return new TextXifrat(resultat.getBytes());
    }
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        
        long llavor = Long.parseLong(clau);
        initRandom(llavor);
        permutaAlfabet();
        
        String msgXifrat = new String(xifrat.getBytes());
        String resultat = "";
        
        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletraXifrada = msgXifrat.charAt(i);
            char lletraOriginal = lletraXifrada;
            
            if (Character.isLetter(lletraXifrada)) {
                boolean esMajuscula = Character.isUpperCase(lletraXifrada);
                char lletraMinuscula = Character.toLowerCase(lletraXifrada);
                
                int posicio = -1;
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (alfabetPermutat[j] == lletraMinuscula) {
                        posicio = j;
                        break;
                    }
                }
                
                if (posicio != -1) {
                    lletraOriginal = alfabet[posicio];
                    if (esMajuscula) {
                        lletraOriginal = Character.toUpperCase(lletraOriginal);
                    }
                }
            }
            
            resultat += lletraOriginal;
            permutaAlfabet();
        }
        
        return resultat;
    }
    
    private void initRandom(long llavor) {
        random = new Random(llavor);
    }
    
    private void permutaAlfabet() {
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i] = alfabet[i];
        }
        
        for (int i = 0; i < alfabetPermutat.length; i++) {
            int posicioAleatoria = random.nextInt(alfabetPermutat.length);
            char temp = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[posicioAleatoria];
            alfabetPermutat[posicioAleatoria] = temp;
        }
    }
}