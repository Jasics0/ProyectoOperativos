package Clases;

import javax.swing.*;
import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) throws SocketException {
        int port = Integer.parseInt(JOptionPane.showInputDialog("Digite el puerto desde el cual quiere que escuche el servidor."));
        Servidor s = new Servidor(port);
    }
}
