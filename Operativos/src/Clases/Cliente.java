package Clases;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends Thread {

    private InetAddress obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip;
    }

    public String sacarPorcentajeDf(String palabra) {
        String salida = "";
        try {
            ProcessBuilder b = new ProcessBuilder().command("df".split(" "));
            Process p = b.start();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((salida = entrada.readLine()) != null) {
                if (salida.contains(palabra)) {
                    break;
                }
            }

            int i = 0;

            while (salida.charAt(i) != '%') {
                i++;
            }
            String porcentaje = "";
            int i2 = i;

            while (salida.charAt(i2) != ' ') {
                i2--;
            }
            for (int j = i2; j < (i + 1); j++) {
                porcentaje += salida.charAt(j);
            }

            return porcentaje;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error.";
        }
    }

    public void run() {
        Socket cCliente;
        int puerto = 2010;
        try {
            Thread.sleep(1000);
            cCliente = new Socket();
            System.out.println("Cliente# Estableciendo conexión con el servidor");
            cCliente = new Socket( obtenerIP(), puerto);
            System.out.println("Cliente# Conexión establecida con el servidor :D");
            DataOutputStream salida;
            DataInputStream entrada;
            String mensaje = sacarPorcentajeDf("sda");
            String mensajeS = "";
            do {
                salida = new DataOutputStream(cCliente.getOutputStream());
                salida.writeUTF(mensaje);
                entrada = new DataInputStream(cCliente.getInputStream());
                mensajeS = entrada.readUTF();
                System.out.println("Cliente# Porcentaje de memoria de este proceso: " + mensajeS);
                Thread.sleep(1000);

            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
