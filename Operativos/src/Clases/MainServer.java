package Clases;

import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) throws SocketException {
        Servidor c = new Servidor(2010);
    }
}
