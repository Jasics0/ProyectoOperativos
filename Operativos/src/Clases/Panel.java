package Clases;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class Panel {
    JPanel a=new JPanel();
    JFrame j=new JFrame();
    JTextField as=new JTextField();
    public Panel(){
        j.setSize(300,300);

        j.setLocationRelativeTo(null);j.setVisible(true);

        a.setSize(200,200);
        as.setSize(50,50);
    }
    public void pintar(int i){
        if (i%2==0){

            a.setBackground(Color.black);
            as.setText(i+"");
            a.add(as);
            j.add(a);
        } else {
            a.setBackground(Color.white);
            as.setText(i+"");
            a.add(as);

            j.add(a);
        }
    }

}
