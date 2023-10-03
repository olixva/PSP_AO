package PruebasProcesos;

import java.io.IOException;

public class RunEdit {
    public static void main(String[] args) throws IOException {
        String comando = "Notepad.exe";
        Runtime procesoActual = Runtime.getRuntime();
        Process p;

        p = procesoActual.exec(comando);

    }
}
