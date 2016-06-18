package com.accumulatorx.jget;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
		URL remoteUrl = new URL(url);
		InputStream in = remoteUrl.openStream();
		try {
			byte[] buffer = new byte[8192];
			int count;
			while ((count = in.read(buffer)) > 0) {
				System.out.write(buffer, 0, count);
			}
		} finally {
			close(in);
		}
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
