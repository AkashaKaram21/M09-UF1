package iticbcn.xifratge;

/*
 * En aquest programa tenim que dexifrar i xifarar amb mètode.A més, hi ha de crear
 * un funcion forcaBrutaRotX que doni la posibiles resultat.
 */

public class XifradorRotX implements xifarar{

       //.toCharArray() amb majúscules
    static char[] majuscules = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    
    //.toCharArray() amb minúscules
    static char[] minuscules = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();

    //Hem de crear dues funcions

    //1.- xifraRotX(cadena,desplacament): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la
    //lletra que està X posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).
    
    public static String xifraRotX(String cadena,int desplacament) {
    
        // Fem una variable per poder guardar el resultat
        String resultat = "";
        
        // Recorem la cadena
        for (int i = 0; i < cadena.length(); i++) {
            // Agafem cada lletra de la cadena
            char c = cadena.charAt(i);
            
            // Variable que indica si hem trobat la lletra dins dels arrays de minuscules o majuscules
            // Serveix per saber si hem fet la conversió ROTX. Si segueix sent false, 
            // la lletra no és alfabètica i es copia tal qual al resultat.
            boolean trobat = false;
            
            // Recorrem l'array de minuscules
            for (int j = 0; j < minuscules.length; j++) {
                // Comprovem si la lletra coincideix amb la del array de minuscules
                if (c == minuscules[j]) {
                    // Avancem X*2 posicions utilitzant % per no passar-nos de l'array
                    resultat += minuscules[(j + desplacament * 2) % minuscules.length];
                    
                    
                    trobat = true;
                }
            }
            
            // Recorrem l'array de majúscules
            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    resultat += majuscules[(j + desplacament * 2) % majuscules.length];
                    trobat = true; 
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

    //2.- desxifraRotX(cadena,desplacament )Ha de fer el procés invers de la funció anterior.
    //Aqui no tenim la variable per guardar ya que estem tornat i els espais i punt ya estaran
    //En cas que el guardem es torna voig :(

    //Es el mateix proccès però en inversa :)
    public static String desxifraRotX(String cadena, int deplacament){
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            boolean trobat = false;

            for (int j = 0; j < minuscules.length; j++) {
                if (c == minuscules[j]) {
                    int pos = (j - deplacament * 2) % minuscules.length;
                    if (pos < 0) pos += minuscules.length;
                    resultat += minuscules[pos];
                    trobat = true;
                }
            }

            for (int j = 0; j < majuscules.length; j++) {
                if (c == majuscules[j]) {
                    int pos = (j - deplacament * 2) % majuscules.length;
                    if (pos < 0) pos += majuscules.length;
                    resultat += majuscules[pos];
                    trobat = true;
                }
            }

            if (!trobat) {
                resultat += c;
            }
        }
        
        return resultat;
    }
    
    //Aquest mètode s'encarrega de donar resultat possibles fins que no hagi més combinació 
    public static void forcaBrutaRotX(String cadena){
        //Fem una variable per guardar el número de l'array de majúscules i minúcules que tenen el mateix 
        int max = majuscules.length; 

        //Reacorrem el número de array ja que son la posibilitat de combinació
        for (int d = 0; d < max; d++) {
            //Dexifrem la cadena les vegades del número d'array 
            String resultat = desxifraRotX(cadena, d);
            //Mostrem el resultat 
            System.out.println("(" + d + ")->" + resultat);
        }
    }

    public static void main(String[] args) {
        
        System.out.println("Xifrat");
        System.out.println("------");
        System.out.println("(0)-ABC => " + xifraRotX("ABC",0));
        System.out.println("(2)-XYZ => " + xifraRotX("XYZ",2));
        System.out.println("(4)-Hola, Mr. calçot => " + xifraRotX("Hola, Mr. calçot",4));
        System.out.println("(6)-Perdó, per tu què és? => " + xifraRotX("Perdó, per tu què és?",6));

        System.out.println("Desxifrat");
        System.out.println("---------");
        System.out.println("(0)ABC => " + desxifraRotX("ABC",0));
        System.out.println("(2)ZAÁ => " + desxifraRotX("ZAÁ",2));
        System.out.println("(4)Ïqoc, Óú. écoèqü => " + desxifraRotX("Ïqoc, Óú. écoèqü",4));
        System.out.println("(6)Úiüht, úiü wx ùxì ív? => " + desxifraRotX("Úiüht, úiü wx ùxì ív?",6));

        System.out.println("Missatge xifrat: Úiüht, úiü wx ùxì ív?");
        System.out.println("----------------");
        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }
    
}
