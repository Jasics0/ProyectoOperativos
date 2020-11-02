package Clases;

import java.net.SocketException;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) throws SocketException {
        Scanner e = new Scanner(System.in);
        System.out.println("Digite el puerto desde el cual quiere estar escuchando: ");
        int port = e.nextInt();
        Servidor c = new Servidor(port);
    }
}
