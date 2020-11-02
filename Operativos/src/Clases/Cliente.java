package Clases;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

import javax.swing.JOptionPane;

public class Cliente extends Thread {
    private static final String DEFAULT_GATEWAY = "Default Gateway";


    public static InetAddress obtenerIp() throws UnknownHostException, SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        InetAddress i = null;
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                i = (InetAddress) ee.nextElement();
                if (i.getHostAddress().contains("192.168")) {
                    break;
                }
            }

        }
        return i;
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

    public static String puertaEnlace() {
        if (Desktop.isDesktopSupported()) {
            try {
                Process process = Runtime.getRuntime().exec("ipconfig");
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.trim().startsWith(DEFAULT_GATEWAY)) {
                            String ipAddress = line.substring(line.indexOf(":") + 1).trim(), routerURL = String.format("http://%s", ipAddress); // opening router setup in browser Desktop.getDesktop().browse(new URI(routerURL)); } System.out.println(line); } } } catch (Exception e) { e.printStackTrace(); } } } }
                            String s = ipAddress;
                            if (!s.equals("")) {
                                return s;
                            }
                        }
                    }
                } catch (Exception e) {

                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    public void run() {
        Socket cCliente;
        int puerto = 2010;
        try {
            Thread.sleep(1000);
            cCliente = new Socket();
            System.out.println("Cliente# Estableciendo conexión con el servidor");
            cCliente = new Socket(obtenerIp(), puerto);
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

    public static void main(String[] args) {
        try {
            System.out.println(puertaEnlace());

        } catch (Exception e) {
            System.out.println("ERROR XDDXD");
        }
    }
}
