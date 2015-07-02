package com.kadir.xml.earthquakes.utilities;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kadir.xml.earthquakes.model.Quakes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
	
	private Network() {
		}
	
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isAvailable();
	}
	
	public static ArrayList<Quakes> DownloadData(String url) {
		ArrayList<Quakes> result = new ArrayList<Quakes>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements newsHeadLines = doc.select("earhquake");
			for (int i = 0; i < newsHeadLines.size(); i++) {
				Quakes quake = new Quakes();
				Element tagA = newsHeadLines.get(i);
				quake.setName(tagA.attr("name").trim());
				quake.setLokasyon(tagA.attr("lokasyon").trim());
				quake.setMag((Float.parseFloat(tagA.attr("mag"))));
				quake.setDepth((Float.parseFloat(tagA.attr("depth"))));
				quake.setLat((Float.parseFloat(tagA.attr("lat"))));
				quake.setLng((Float.parseFloat(tagA.attr("lng"))));
				result.add(quake);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
