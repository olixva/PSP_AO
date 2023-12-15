import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class Inet {
    public static void main(String[] args) throws Exception {
        InetAddress equipov4;
        InetAddress um;

        equipov4 = InetAddress.getLocalHost();
        um = InetAddress.getByName("www.um.es");

        System.out.println("Nombre del equipo:  > " + equipov4);
        System.out.println("Dirección IP:       > " + equipov4.getHostAddress());
        System.out.println("Nombre del equipo:  > " + equipov4.getHostName());
        System.out.println("Nombre canónico:    > " + equipov4.getCanonicalHostName());

        System.out.println("\nNombre del equipo:  > " + um);
        System.out.println("Dirección IP:       > " + um.getHostAddress());
        System.out.println("Nombre del equipo:  > " + um.getHostName());
        System.out.println("Nombre canónico:    > " + um.getCanonicalHostName());

        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        // Las recorremos
        System.out.println("\nInterfaces de red: ");
        for (NetworkInterface netint : Collections.list(nets)) {
            
            System.out.println("Nombre: " + netint.getDisplayName());
            System.out.println("Nombre: " + netint.getName());
            
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                System.out.println("InetAddress: " + inetAddress);
            }
        }
    }
}
