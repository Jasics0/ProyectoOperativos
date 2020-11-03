/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiqueishons;


import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author henry
 */
public class Graficas {

    static class PieRenderer {

        private Color[] color;

        public PieRenderer(Color[] color) {
            this.color = color;
        }

        public void setColor(PiePlot plot, DefaultPieDataset dataset) {
            List<Comparable> keys = dataset.getKeys();
            int aInt;
            for (int i = 0; i < keys.size(); i++) {
                aInt = i % this.color.length;
                plot.setSectionPaint(keys.get(i), this.color[aInt]);
            }
        }
    }

    Color[] colors = {Color.green, Color.red, Color.yellow
            /* size of data set */
    };
    PieRenderer renderer = new PieRenderer(colors);

    static double aux1, aux2, aux3, aux4, aux5, aux6;
    static JFrame jf = new JFrame();
    static JPanel jpcpu = new JPanel();
    static JPanel jpmem = new JPanel();
    static JPanel jpdisk = new JPanel();

    public Graficas() {
        jf.setTitle("Proyecto Operativos.");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1500, 700);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setResizable(false);
        jpcpu.setLayout(new java.awt.BorderLayout());
        jpcpu.setSize(400, 400);
        jpcpu.setLocation(50, 100);
        jpmem.setLocation(490, 100);
        jpmem.setSize(400, 400);
        jpmem.setLayout(new java.awt.BorderLayout());
        jpdisk.setLocation(925, 100);
        jpdisk.setSize(400, 400);
        jpdisk.setLayout(new java.awt.BorderLayout());

    }

    public void actualizarCpu(double x) {
        XYSeries oSeriesCpu = new XYSeries("Consumo del Procesador+" + (int) x + "%");
        XYSeriesCollection oDataSet = new XYSeriesCollection();
        Double cpu4 = x;
        Double cpu3 = aux3;
        Double cpu2 = aux2;
        Double cpu1 = aux1;
        oSeriesCpu.add(1, cpu1);
        oSeriesCpu.add(2, cpu2);
        oSeriesCpu.add(3, cpu3);
        oSeriesCpu.add(4, cpu4);
        oDataSet.addSeries(oSeriesCpu);
        JFreeChart oChart = ChartFactory.createXYAreaChart("Procesador (CPU)", "", "Cantidad", oDataSet, PlotOrientation.VERTICAL, true, false, false);
        ChartPanel oPanelCpu = new ChartPanel(oChart);
        jpcpu.setLayout(new java.awt.BorderLayout());
        jpcpu.add(oPanelCpu);
        jpcpu.validate();
        jf.add(jpcpu);
        aux1 = cpu2;
        aux2 = cpu3;
        aux3 = cpu4;
    }

    public void actualizarMem(double x) {
        XYSeries oSeriesMem = new XYSeries("Memoria RAM+" + (int) x + "%");
        XYSeriesCollection o1DataSet = new XYSeriesCollection();
        Double mem4 = x;
        Double mem3 = aux6;
        Double mem2 = aux5;
        Double mem1 = aux4;
        oSeriesMem.add(1, mem1);
        oSeriesMem.add(2, mem2);
        oSeriesMem.add(3, mem3);
        oSeriesMem.add(4, mem4);
        o1DataSet.addSeries(oSeriesMem);
        JFreeChart oChart = ChartFactory.createXYAreaChart("Memoria RAM", "", "Consumo:", o1DataSet, PlotOrientation.VERTICAL, true, false, false);
        ChartPanel oPanelCpu = new ChartPanel(oChart);
        jpmem.setLayout(new java.awt.BorderLayout());
        jpmem.add(oPanelCpu);
        jpmem.validate();
        jf.add(jpmem);
        aux4 = mem2;
        aux5 = mem3;
        aux6 = mem4;

    }

    public void actualizarDisk(Double x) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Disco Usado", new Double(x + ""));
        dataset.setValue("Disco Libre", new Double((100 - x) + ""));
        JFreeChart chart = ChartFactory.createPieChart3D("Disco", dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        renderer.setColor(plot, dataset);
        plot.setLabelOutlinePaint(Color.yellow);
        ChartPanel panel = new ChartPanel(chart);
        jpdisk.setLayout(new java.awt.BorderLayout());
        jpdisk.add(panel);
        jpdisk.validate();
        jf.add(jpdisk);
    }

    public static void main(String[] args) throws InterruptedException {
        double i = 0.0;
        Graficas g = new Graficas();
        while (true) {
            i++;
            g.actualizarCpu(i);
            g.actualizarMem(i);
            g.actualizarDisk(i);
            Thread.sleep(500);
        }
    }

}
