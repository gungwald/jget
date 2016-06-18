package com.accumulatorx.jget;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JGet {

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
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	private void get(String url) throws IOException {
        get(new URL(url));
	}
    
	private void get(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        if (conn.getContentType().startsWith("text/")) {
            BufferedReader textResponseReader = null;
            try {
            	textResponseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = textResponseReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            finally {
            	close(textResponseReader);
            }
        }
        else {
            BufferedInputStream binaryResponseReader = null;
            try {
            	binaryResponseReader = new BufferedInputStream(conn.getInputStream());
                byte[] buffer = new byte[8192]; // OS buffer is usually 2048.
                int count;
                while ((count = binaryResponseReader.read(buffer)) != -1) {
                    System.out.write(buffer, 0, count);
                }
            }
            finally {
            	close(binaryResponseReader);
            }
        }
        System.out.flush();
	}

	public void close(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
