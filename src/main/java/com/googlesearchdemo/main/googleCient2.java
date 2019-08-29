package com.googlesearchdemo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class googleCient2 {

	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {

	  try {

		URL url = new URL("https://www.googleapis.com/auth/cloud_search.query");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		//String input = "{\"web\":{\"client_id\":\"908594821341-tfis2puk5p6ppan4ic5f9t0b5d2bmkoj.apps.googleusercontent.com\",\"project_id\":\"pnb-poc-gcs-website\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"cwDycgBA5eqsxGUwrdKbQnhq\",\"redirect_uris\":[\"https://qa.pnbmetlife.com/search/\"]}}";
		String input = "{\"web\":{\"client_id\":\"908594821341-9tug9a4rtce4aap0f58rqv0juevgcgpk.apps.googleusercontent.com\",\"project_id\":\"pnb-poc-gcs-website\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"64jRQL90b-n-0J5GoegSovDL\",\"redirect_uris\":[\"https://qa.pnbmetlife.com/search/\"],\"javascript_origins\":[\"https://qa.pnbmetlife.com\"]}}";
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpsURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }

	}

}
