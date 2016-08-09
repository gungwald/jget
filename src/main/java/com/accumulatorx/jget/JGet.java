package com.accumulatorx.jget;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class JGet {

    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_FAILURE = 1;

    /**
     * Executions
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        JGet jget = new JGet();
        for (String url : args) {
            try {
                jget.get(url);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.err.println("EXIT_FAILURE");
                System.exit(EXIT_FAILURE);
            }
        }
        System.err.println("EXIT_SUCCESS");
        System.exit(EXIT_SUCCESS);
    }

    private void get(String url) throws IOException, URISyntaxException {
        if (! url.matches("^\\w+\\:\\/.*$")) {
            // No protocol. Add default HTTP protocol.
            url = "http://" + url;
        }
        get(new URL(url));
    }
    
    private File buildDownloadName(URL url) throws URISyntaxException {
        String s = url.toString();
        if (s.endsWith("/")) {
            s = s.substring(0, s.length()-1);
        }
        return new File(s.substring(s.lastIndexOf('/')+1));
    }

    private void get(URL url) throws IOException, URISyntaxException {
        URLConnection conn = url.openConnection();
        if (conn.getContentType().startsWith("text/")) {
            BufferedReader textResponseReader = null;
            PrintWriter out = null;
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter(buildDownloadName(url))));
                textResponseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = textResponseReader.readLine()) != null) {
                    out.println(line);
                }
            }
            finally {
                close(textResponseReader);
                close(out);
            }
        }
        else {
            BufferedOutputStream out = null;
            BufferedInputStream binaryResponseReader = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(buildDownloadName(url)));
                binaryResponseReader = new BufferedInputStream(conn.getInputStream());
                byte[] buffer = new byte[8192]; // OS buffer is usually 2048.
                int count;
                while ((count = binaryResponseReader.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
            }
            finally {
                close(binaryResponseReader);
                close(out);
            }
        }
        System.out.flush();
    }

    public void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
