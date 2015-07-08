package com.kadir.xml.earthquakes.task;

import java.util.ArrayList;

import android.os.AsyncTask;

import com.kadir.xml.earthquakes.Activity_Main;
import com.kadir.xml.earthquakes.model.Quakes;
import com.kadir.xml.earthquakes.utilities.Network;

public class DownloadDataASYNC extends
		AsyncTask<String, Integer, ArrayList<Quakes>> {

	private final Activity_Main activity_Main;

	public DownloadDataASYNC(Activity_Main activity_Main) {
		this.activity_Main = activity_Main;
	}

	@Override
	protected void onPreExecute() {
		activity_Main.setProgressBarIndeterminate(true);
		activity_Main.setProgressBarIndeterminateVisibility(true);
	}

	@Override
	protected void onPostExecute(ArrayList<Quakes> result) {
		activity_Main.setProgressBarIndeterminateVisibility(false);
	}

	@Override
	protected ArrayList<Quakes> doInBackground(String... url) {
		ArrayList<Quakes> QuakeList = new ArrayList<Quakes>();
		QuakeList = Network.DownloadData(url[0]);
		return QuakeList;
	}
}
