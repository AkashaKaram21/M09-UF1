public class Rot13 {

    //Array amb minúscules
    static char[] minuscules = {
        'a','à','á','ä','b','c','ç','d','e','è','é','ë',
        'f','g','h','i','ì','í','ï','j','k','l','m','n','ñ',
        'o','ò','ó','ö','p','q','r','s','t','u','ù','ú','ü',
        'v','w','x','y','z'
    };

    //Array amb majúscules
    static char[] majuscules = {
        'A','À','Á','Ä','B','C','Ç','D','E','È','É','Ë',
        'F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ',
        'O','Ò','Ó','Ö','P','Q','R','S','T','U','Ù','Ú','Ü',
        'V','W','X','Y','Z'
    };

    public static String xifraRot13(String cadena){
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            // Recorremos array de minúsculas
            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    resultat += minuscules[(j + 13) % minuscules.length];
                }
            }

            // Recorremos array de mayúsculas
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    resultat += majuscules[(j + 13) % majuscules.length];
                }
            }
        }
        return resultat;
    }

    public static String desxifraRot13(String cadena){
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            // Recorremos array de minúsculas
            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    resultat += minuscules[(j - 13 + minuscules.length) % minuscules.length];
                }
            }

            // Recorremos array de mayúsculas
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    resultat += majuscules[(j - 13 + majuscules.length) % majuscules.length];
                }
            }
        }
        return resultat;
    }

    public static void main(String[] args) {
        String cadena = "Holii";
        String xifrat = xifraRot13(cadena);
        String desxifrat = desxifraRot13(xifrat);

        System.out.println("Original: " + cadena);
        System.out.println("Xifrat: " + xifrat);
        System.out.println("Desxifrat: " + desxifrat);
    }
}
