package PruebasProcesos;

import java.io.File;
import java.io.IOException;

public class LanzadorFicheros {

    public void lanzarSumador(Integer n1, Integer n2, String fichero) {
        
        String[] comando = {"java", "Sumador.java", n1.toString(), n2.toString() };
        ProcessBuilder pB = new ProcessBuilder(comando);
        pB.directory(new File("C:\\DAM\\Proyectos VSC\\Java\\PSP\\PSP_AO\\src\\Pruebas"));

        File fOut = new File("PSP_AO\\" + fichero);
        File fErr = new File("PSP_AO\\err_"+ fichero);

        pB.redirectOutput(fOut);
        pB.redirectError(fErr);

        try {
            pB.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
