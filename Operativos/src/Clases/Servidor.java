package Clases;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.Enumeration;

public class Servidor {

    public Servidor(int port) {
        ServerSocket sServer;
        try {
            System.out.println("Servidor# Creando el socket en el puerto " + port + "...");
            sServer = new ServerSocket(port);
            System.out.println("Servidor# Esperando conexiones...");
            Socket sConexion = sServer.accept();
            System.out.println("Servidor# Se conectó un cliente " + sConexion.getInetAddress());
            String mensaje;
            String mensajeE = "";
            DataInputStream entrada;
            DataOutputStream salida;
            do {
//                entrada = new DataInputStream(sConexion.getInputStream());
//                mensaje = entrada.readUTF();
//                System.out.println("Servidor# Porcentaje de disco del cliente: " + mensaje);
                mensajeE = "Disco usado:" + sacarPorcentajeDf("sda") + " | Memoria Disponible:" + sacarPorcentajeMf("Mem") +" | CPU: "+sacarCPU()+ "\n\n";
                mensajeE += sacarProcesos();
                salida = new DataOutputStream(sConexion.getOutputStream());
                salida.writeUTF(mensajeE);
                Thread.sleep(1000);
            } while (true);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private InetAddress obtenerIp() throws UnknownHostException, SocketException {
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
            } catch (Exception a) {

            }
        }
        return i;
    }


    private String puertaDeEnlace() throws IOException {
        ProcessBuilder b = new ProcessBuilder().command(("hostname -I").split(" "));
        Process p = b.start();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String gateWay = entrada.readLine();
        return gateWay;
    }

    public  String sacarProcesos() throws IOException {
        String salida = "";

        ProcessBuilder b = new ProcessBuilder().command(("ps aux --sort pmem").split(" "));
        Process p = b.start();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea = "";
        String administrador = "";
        int i = 0, i2 = 0;
        while ((salida = entrada.readLine()) != null) {
            while (i < salida.length()) {
                i2++;
                while (salida.charAt(i) != ' ') {
                    if (i2 <= 4 || i2 == 11) {
                        administrador += salida.charAt(i);
                    }

                    if (i==(salida.length()-1)){
                        break;
                    }
                    i++;
                }

                if (i==(salida.length()-1)){
                    break;
                }


                while (salida.charAt(i) == ' ') {
                    if (i==(salida.length()-1)){
                        break;
                    }
                    if (i2 <= 4) {
                        administrador += salida.charAt(i);
                    }
                    i++;

                }

            }
            administrador += "\n";
            linea += salida + "\n";

            i = i2 = 0;
        }
        return administrador;
    }

    public String sacarCPU() throws IOException {
        String salida = "";

        ProcessBuilder b = new ProcessBuilder().command(("ps aux --sort pmem").split(" "));
        Process p = b.start();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea = "";
        double cpu = 0;
        String cupS="";
        int i = 0, i2 = 0;
        entrada.readLine();
        while ((salida = entrada.readLine()) != null) {
            while (i < salida.length()) {
                i2++;
                while (salida.charAt(i) != ' ') {
                    if (i2 == 3) {
                        cupS += salida.charAt(i);
                    }

                    if (i==(salida.length()-1)){
                        break;
                    }
                    i++;
                }

                if (i==(salida.length()-1) || i2==3){
                    break;
                }


                while (salida.charAt(i) == ' ') {
                    if (i==(salida.length()-1) || i2==3){
                        break;
                    }
                    i++;

                }

            }
            if (!"0.0".equals(cupS)) {
                cpu += Double.parseDouble(cupS);

            }
            i = i2 = 0;
            cupS="";
        }
        return cpu+"";
    }

    public int pid() {
        String id = ManagementFactory.getRuntimeMXBean().getName();
        String[] ids = id.split("@");
        return Integer.parseInt(ids[0]);
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

            return porcentaje + "%";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error.";
        }
    }


    public String sacarPorcentajeMf(String palabra) {
        String salida = "";
        try {
            ProcessBuilder b = new ProcessBuilder().command("free -m".split(" "));
            Process p = b.start();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((salida = entrada.readLine()) != null) {
                if (salida.contains(palabra)) {
                    break;
                }
            }

            int i = 0, i2 = 0;
            String porcentaje = "";
            String aux[] = new String[2];
            while (salida.charAt(i) != ' ') {
                i++;
            }

            while (i < salida.length()) {

                while (salida.charAt(i) == ' ') {
                    i++;
                }
                i2++;
                aux[i2 - 1] = "";
                while (salida.charAt(i) != ' ') {
                    aux[i2 - 1] += salida.charAt(i);
                    i++;
                }
                if (i2 == 2) {
                    break;
                }

            }
            Double libre = Double.parseDouble(aux[0]) - Double.parseDouble(aux[1]);
            porcentaje = (Math.round((libre * 100) / Double.parseDouble(aux[0]))) + "";
            return porcentaje + "%";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error.";
        }
    }

}
