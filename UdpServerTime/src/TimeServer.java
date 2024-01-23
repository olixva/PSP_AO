import java.net.InetAddress;
import java.net.UnknownHostException;

public class TimeServer {
    public static void main(String[] args) {
        final String inetAddress = "224.0.0.1";
        final int port = 7777;

        try {
            int hh, mm, ss;
            InetAddress serverIP = InetAddress.getByName(inetAddress);

        }catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}