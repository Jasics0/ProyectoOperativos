package Clases;

public class Main {

    public static void main(String[] args) {
        //Noseatrollperrito
        Cliente c = new Cliente();
        Servidor s = new Servidor();
        s.start();
        c.start();
    }

}
