package Clases;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Windows {
    private static final String DEFAULT_GATEWAY = "Default Gateway";

    private Windows() {
    }

    public static void main(String[] args) {
        if (Desktop.isDesktopSupported()) {
            try {
                Process process = Runtime.getRuntime().exec("ipconfig");
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.trim().startsWith(DEFAULT_GATEWAY)) {
                            String ipAddress = line.substring(line.indexOf(":") + 1).trim(), routerURL = String.format("http://%s", ipAddress); // opening router setup in browser Desktop.getDesktop().browse(new URI(routerURL)); } System.out.println(line); } } } catch (Exception e) { e.printStackTrace(); } } } }
                        }
                    }
                } catch (Exception e) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}