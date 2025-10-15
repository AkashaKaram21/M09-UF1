import java.util.Random;

public class Polialfabetic {
    // Alfabeto completo con letras normales, acentos y caracteres especiales
    final static char[] alfabet = "abcdefghijklmnopqrstuvwxyzàèéíïòóúüçñ".toCharArray();
    
    // Array donde guardaremos el alfabeto mezclado
    static char[] alfabetPermutat = new char[alfabet.length];
    
    // Objeto para generar números aleatorios
    static Random random;
    
    // Clave secreta para inicializar el generador de números aleatorios
    static long clauSecreta = 12345L;
    
    // Método para inicializar el generador de números aleatorios con una semilla
    public static void initRandom(long llavor) {
        random = new Random(llavor);
    }
    
    // Método que genera una permutación aleatoria del alfabeto
    public static void permutaAlfabet() {
        // Copiamos el alfabeto original
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i] = alfabet[i];
        }
        
        // Mezclamos el alfabeto usando intercambios aleatorios
        for (int i = 0; i < alfabetPermutat.length; i++) {
            int posicioAleatoria = random.nextInt(alfabetPermutat.length);
            char temp = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[posicioAleatoria];
            alfabetPermutat[posicioAleatoria] = temp;
        }
    }
    
    // Método para cifrar un mensaje usando cifrado polialfabético
    public static String xifraPoliAlfa(String msg) {
        if (msg == null || msg.length() == 0) {
            return msg;
        }
        
        String resultat = "";
        
        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            char lletraXifrada = lletra;
            
            if (Character.isLetter(lletra)) {
                boolean esMajuscula = Character.isUpperCase(lletra);
                char lletraMinuscula = Character.toLowerCase(lletra);
                
                // Buscar la posición en el alfabeto original
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
            permutaAlfabet(); // ¡IMPORTANTE! Nueva permutación para cada letra
        }
        
        return resultat;
    }
    
    // Método para descifrar un mensaje cifrado
    public static String desxifraPoliAlfa(String msgXifrat) {
        if (msgXifrat == null || msgXifrat.length() == 0) {
            return msgXifrat;
        }
        
        String resultat = "";
        
        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletraXifrada = msgXifrat.charAt(i);
            char lletraOriginal = lletraXifrada;
            
            if (Character.isLetter(lletraXifrada)) {
                boolean esMajuscula = Character.isUpperCase(lletraXifrada);
                char lletraMinuscula = Character.toLowerCase(lletraXifrada);
                
                // Buscar la posición en el alfabeto permutado
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
            permutaAlfabet(); // ¡IMPORTANTE! Nueva permutación para cada letra
        }
        
        return resultat;
    }
    
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perimetre",
                        "Test 02 Taüll, DÍA, año",
                        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n---");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n---");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}