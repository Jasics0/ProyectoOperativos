package Clases;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.Enumeration;

public class Servidor extends Thread {

    public static InetAddress obtenerIp() throws UnknownHostException, SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        InetAddress i = null;
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                i = (InetAddress) ee.nextElement();

            }
            try {
                if (puertaDeEnlace().contains(i.getHostAddress())) {
                    break;
                }
            } catch (Exception a){

            }
        }
        return i;
    }


    private static String puertaDeEnlace() throws IOException {
        ProcessBuilder b = new ProcessBuilder().command(("hostname -I").split(" "));
        Process p = b.start();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String gateWay = entrada.readLine();
        return gateWay;
    }

    public String sacarPorcentajeMemoria() throws IOException {
        String salida = "";

        ProcessBuilder b = new ProcessBuilder().command(("ps aux --sort pmem").split(" "));
        Process p = b.start();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea = "";
        while ((salida = entrada.readLine()) != null) {
            if (salida.contains(pid() + "")) {
                linea = salida;
                break;
            }
        }
        int i = 0;
        int i2 = 0;
        String porcentaje = "";
        while (i < 3) {
            if (linea.charAt(i2) != ' ') {
                while (linea.charAt(i2) != ' ') {
                    i2++;
                }
                i++;
            }
            i2++;
            if (i == 3) {
                while (linea.charAt(i2) == ' ') {
                    i2++;
                }
            }
        }

        while (linea.charAt(i2) != ' ') {
            porcentaje += linea.charAt(i2);
            i2++;
        }
        return porcentaje + "%";
    }

    public void run() {
        ServerSocket sServer;
        try {
            System.out.println("Servidor# Creando el socket en el puerto 2010...");
            sServer = new ServerSocket(2010);
            System.out.println("Servidor# Esperando conexiones...");
            Socket sConexion = sServer.accept();
            System.out.println("Servidor# Se conectó un cliente " + sConexion.getInetAddress());
            String mensaje;
            String mensajeE = "";
            DataInputStream entrada;
            DataOutputStream salida;
            do {
                entrada = new DataInputStream(sConexion.getInputStream());
                mensaje = entrada.readUTF();
                System.out.println("Servidor# Porcentaje de disco del cliente: " + mensaje);
                Thread.sleep(1000);
                mensajeE = sacarPorcentajeMemoria();
                salida = new DataOutputStream(sConexion.getOutputStream());
                salida.writeUTF(mensajeE);
            } while (true);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int pid() {
        String id = ManagementFactory.getRuntimeMXBean().getName();
        String[] ids = id.split("@");
        return Integer.parseInt(ids[0]);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(obtenerIp().getHostAddress());
    }


}
