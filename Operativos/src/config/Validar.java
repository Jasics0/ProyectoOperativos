/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author henry
 */
public class Validar {

    static Hash hs = new Hash();

    public static boolean exists(String user, String pass) throws FileNotFoundException, IOException, Exception {
        File archivo = new File("C:\\Users\\henry\\Documents\\NetBeansProjects\\Grafiqueishons\\src\\config\\datos.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        String[] data = linea.split(" ");
        String userF = data[0];
        String passF = hs.desencriptar(data[1]);
        return pass.equals(passF) && user.equals(userF);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        if (exists("henry", "123")) {
            System.out.println("Si mi rey jeje");
        } else {
            System.out.println("No pibe :)");
        }
    }
}
