package Clases;

import java.net.SocketException;
import java.util.Scanner;

public class MainCliente {

    public static void main(String[] args) throws SocketException {
        String ip;
        int puerto;
        System.out.println("Digite la ip del servidor: ");
        Scanner en = new Scanner(System.in);
        ip=en.next();
        System.out.println("Digite el puerto del servidor: ");
        puerto = en.nextInt();
        Cliente c = new Cliente(ip,puerto);
    }

}

