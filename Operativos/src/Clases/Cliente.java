package Clases;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;


public class Cliente {
    private static final String DEFAULT_GATEWAY = "Default Gateway";

    public Cliente(String ip, int port) {
        Socket cCliente;
        try {
            Thread.sleep(1000);
            cCliente = new Socket();
            System.out.println("Cliente# Estableciendo conexión con el servidor");
            cCliente = new Socket(InetAddress.getByName(ip), port);
            System.out.println("Cliente# Conexión establecida con el servidor :D");
//            DataOutputStream salida;
            DataInputStream entrada;
            String mensaje = "";
//            String mensajeS = "";
            do {
                entrada = new DataInputStream(cCliente.getInputStream());
                mensaje = entrada.readUTF();
                System.out.println("Cliente# Porcentaje de memoria de este proceso: " + mensaje);
                Thread.sleep(1000);

            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InetAddress obtenerIp() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        InetAddress i = null;
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                i = (InetAddress) ee.nextElement();
                if (i.getHostAddress().contains(puertaEnlace())) {
                    return i;
                }
            }

        }
        return i;
    }


    public String puertaEnlace() {
        if (Desktop.isDesktopSupported()) {
            try {
                Process process = Runtime.getRuntime().exec("ipconfig");
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.trim().startsWith(DEFAULT_GATEWAY)) {
                            String ipAddress = line.substring(line.indexOf(":") + 1).trim(), routerURL = String.format("http://%s", ipAddress); // opening router setup in browser Desktop.getDesktop().browse(new URI(routerURL)); } System.out.println(line); } } } catch (Exception e) { e.printStackTrace(); } } } }
                            if (!ipAddress.equals("")) {
                                return ipAddress;
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

}
