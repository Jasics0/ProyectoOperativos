package RemoteShell;

import grafiqueishons.Chart;
import grafiqueishons.Graficas;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private static final String DEFAULT_GATEWAY = "Default Gateway";
    private static double nd, nm, nc;
    private static String[] list;
    private Socket cCliente;

    public static double getNd() {
        return nd;
    }

    public static void setNd(double nd) {
        Cliente.nd = nd;
    }

    public static double getNm() {
        return nm;
    }

    public static void setNm(double nm) {
        Cliente.nm = nm;
    }

    public static double getNc() {
        return nc;
    }

    public static void setNc(double nc) {
        Cliente.nc = nc;
    }

    public static String[] getList() {
        return list;
    }

    public static void setList(String[] list) {
        Cliente.list = list;
    }

//    @Override
//    public void run() {
//
//        //DataOutputStream salida;
//        DataInputStream entrada = null;
//        String mensaje = "";
//        //String mensajeS = "";
//        do {
//            try {
//                entrada = new DataInputStream(cCliente.getInputStream());
//            } catch (IOException ex) {
//                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                mensaje = entrada.readUTF();
//            } catch (IOException ex) {
//                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            list = mensaje.split("\r\n|\r|\n");
//            sacarNumeros();
//            System.out.print("Cliente# ");
//            System.out.print(nd + " ");
//            System.out.print(nm + " ");
//            System.out.println(nc + " ");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } while (true);
//    }
    public void sacarNumeros() {
        String[] list2 = list[0].split(" ");
        nd = Double.parseDouble(list2[1]);
        nm = Double.parseDouble(list2[2]);
        nc = Double.parseDouble(list2[3]);
    }

    public Cliente(String ip, int port) {
        try {
            Thread.sleep(1000);
            Graficas g = new Graficas();
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
                list = mensaje.split("\r\n|\r|\n");
                sacarNumeros();
                System.out.print("Cliente# ");
                System.out.print(nd + " ");
                System.out.print(nm + " ");
                System.out.println(nc + " ");
                //g.actualizarCpu(nc);
                //g.actualizarMem(nm);
                g.actualizarDisk(nd);
                Thread.sleep(500);
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
