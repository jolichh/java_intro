/*
 *
 *
 * Programa que fa veure que hi ha una pilota caient en diagonal per la pantalla
 * La velocitat varia segons la rapidesa que premi l'usuari el botó 'enter'
 *
 * El programa finalitza quan introduïm qualsevol cosa diferent a 'enter'
 *
 * Aquesta versió de la pilota permet que reboti quan arriba a final de 'pantalla'
 *
 *
 */
public class Pilota {
    public static final int N_FILES = 9;
    public static final int N_COLS = 14;

    public static void netejaPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void mostraCamp(char[][] camp) {
        //printeja camp amb la bola
        for (int i = 0; i < N_FILES; i++) {
            for (int j = 0; j < N_COLS; j++) {
                System.out.print(camp[i][j]);
            }
            System.out.println();
        }
    }
    public static void netejaCamp(char[][] camp) {
        /* pone todo el campo en · */
        for (int i = 0; i < N_FILES; i++) {
            for (int e = 0; e < N_COLS; e++) {
                camp[i][e] = '·';
            }
        }
    }
    public static void netejaPosicio(char[][] camp, int[] posicio) {
        /* convierte la posicion de la pelota en · */
        camp[posicio[0]][posicio[1]] = '·';
    }
    public static void posicionaPilota(char[][] camp, int[] posicio) {
        /* posiciona la pilota */
        camp[posicio[0]][posicio[1]] = 'O';   
    }
    public static int obteFila(int[] posicio) {
        return posicio[0];
    }
    public static int obteCol(int[] posicio) {
        /* retorna la posició de la columna que está en el index 1 del array*/
        return posicio[1];
    }
    public static int obteIncrFila(int[] increment) {
        /* incrementa nomes la fila i retorna el seu increment */
        return increment[0];
    }
    public static int obteIncrCol(int[] increment) {
        /* incrementa columna i retorna valor */
        return increment[1];
    }
    public static void canviaPosicio(int[] posicio, int novaFila, int novaCol) {
        /* canvia posició de la pilota */
        posicio[0] = novaFila;
        posicio[1] = novaCol;
    }
    public static void canviaIncrement(int[] increment, int nouIncFila, int nouIncCol) {
        /* canvia increment */
        increment[0] =  nouIncFila;
        increment[1] =  nouIncCol;
    }
    public static void seguentPosicio(int[] posicio, int[] increment) {
        int fila = obteFila(posicio);
        int col = obteCol(posicio);
        int incFila = obteIncrFila(increment);
        int incCol = obteIncrCol(increment);

        // actualitza la fila
        fila = fila + incFila;
        if (fila < 0) {                     // es passa per sobre
            fila = 1;                       // torna a la primera fila
            incFila = 1;                    // toca baixar
        } else if (fila > N_FILES -1) {     // es passa per sota
            fila = N_FILES-2;
            incFila = -1;                   //puja bola
        }

        // actualitza la columna
        col = col + incCol;
        if (col < 0) {                     // es passa per sobre
            col = 1;                       // torna a la primera columna
            incCol = 1;                    // toca baixar
        } else if (col > N_COLS -1) {      // es passa per sota
            col = N_COLS-2;
            incCol = -1;                   //puja bola
        }

        // actualitza la posició i l'increment
        canviaPosicio(posicio, fila, col);
        canviaIncrement(increment, incFila, incCol);
    }
    public static void main(String[] args)  {
        char[][] camp = new char[N_FILES][N_COLS];
        netejaCamp(camp);

        int[] posicio = new int[2];         // fila, col
        canviaPosicio(posicio, 0, 0);       // posició inicial (0, 0)

        int[] increment = new int[2];       // incFila, incCol
        canviaIncrement(increment, 1, 1);   // desplaçament inicial: 1 fila 1 columna

        do {
            posicionaPilota(camp, posicio);
            netejaPantalla();
            mostraCamp(camp);
            netejaPosicio(camp, posicio);
            seguentPosicio(posicio, increment);
            System.out.printf("%nEnter per continuar");
        } while (Entrada.readLine().isEmpty());

    }
}
