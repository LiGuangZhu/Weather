package com.Tusiji.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtils {
	public static void SendHttpRequset(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpsURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpsURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					connection.setConnectTimeout(8000);
					InputStream stream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(stream));
					StringBuffer Reponse = new StringBuffer();
					String line;
					if ((line = reader.readLine()) != null) {
						Reponse.append(line);
					}
					if (listener != null) {
						listener.onFinish(Reponse.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
