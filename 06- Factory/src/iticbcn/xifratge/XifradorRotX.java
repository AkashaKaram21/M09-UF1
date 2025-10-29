package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private final char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    private final char[] minuscules = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        String resultat = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean trobat = false;
            
            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    resultat += minuscules[(j + desplacament) % minuscules.length];
                    trobat = true;
                    break;
                }
            }
            
            if (!trobat) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (c == majuscules[j]) {
                        resultat += majuscules[(j + desplacament) % majuscules.length];
                        trobat = true;
                        break;
                    }
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
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        String cadena = new String(xifrat.getBytes());
        String resultat = "";
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;
            
            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    int pos = (j - desplacament) % minuscules.length;
                    if (pos < 0) pos += minuscules.length;
                    resultat += minuscules[pos];
                    trobat = true;
                    break;
                }
            }
            
            if (!trobat) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (c == majuscules[j]) {
                        int pos = (j - desplacament) % majuscules.length;
                        if (pos < 0) pos += majuscules.length;
                        resultat += majuscules[pos];
                        trobat = true;
                        break;
                    }
                }
            }
            
            if (!trobat) {
                resultat += c;
            }
        }
        
        return resultat;
    }
}