package com.cardinalhealth.jget;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JGet {

    public JGet() {
        super();
    }

    public static void main(String[] args) {
        JGet jget = new JGet();
        for (String url : args) {
            try {
                jget.get(url);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void get(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        if (conn.getContentType().startsWith("text/")) {
            try (BufferedReader textResponseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = textResponseReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
        else {
            try (BufferedInputStream binaryResponseReader = new BufferedInputStream(conn.getInputStream())) {
                byte[] buffer = new byte[2048]; // Usually matches size of OS buffer, which is efficient.
                int count;
                while ((count = binaryResponseReader.read(buffer)) != -1) {
                    System.out.write(buffer, 0, count);
                }
            }
        }
        System.out.flush();
    }
}
