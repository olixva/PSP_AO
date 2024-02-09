package PruebasProcesos;

import java.io.File;
import java.io.IOException;

public class RunRedirect {

    public static void main(String[] args) {
        String[] comando = { "cmd", "/c", "disdrs" };
        ProcessBuilder pB = new ProcessBuilder(comando);

        File fOut = new File("PSP_AO\\salida.txt");
        File fErr = new File("PSP_AO\\error.txt");

        pB.redirectOutput(fOut);
        pB.redirectError(fErr);

        try {
            pB.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
