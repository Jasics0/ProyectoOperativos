package Clases;

public class clienteprueba extends Thread {

    int i=0;
    @Override
    public void run() {
        do {
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static void main(String[] args) {
        int i=0;
        Panel a=new Panel();
        do {
            i++;
            try {
                a.pintar(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (true);
    }
}
