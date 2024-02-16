package main;

import domain.MataServer;

public class Main {
    public static void main(String[] args) {
        // Creamos y iniciamos el servidor
        MataServer server = new MataServer(2);
        server.iniciar();
    }
}
