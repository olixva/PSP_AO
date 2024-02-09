import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestURL {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://ansat.es:80/index.htm");
            InputStreamReader entrada = new InputStreamReader(url.openStream());
            BufferedReader flujoE = new BufferedReader(entrada);

            String linea;
            while ((linea = flujoE.readLine()) != null) {
                System.out.println(linea);
            }
            flujoE.close();

            System.out.println("\n-------------------------------------------------------\n");

            URLConnection urlCon = url.openConnection();
            InputStreamReader entrada2 = new InputStreamReader(urlCon.getInputStream());
            BufferedReader flujoE2 = new BufferedReader(entrada2);

            String linea2;
            while ((linea2 = flujoE2.readLine()) != null) {
                System.out.println(linea2);
            }
            flujoE2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
