package Clases;
import javax.swing.*;
import java.net.SocketException;

public class MainCliente {

    public static void main(String[] args) throws SocketException {
        String ip;
        int puerto;
        ip = JOptionPane.showInputDialog("Digite la ip del servidor");
        puerto = Integer.parseInt(JOptionPane.showInputDialog("Digite el puerto del servidor"));
        Cliente c = new Cliente(ip, puerto);
    }

}

