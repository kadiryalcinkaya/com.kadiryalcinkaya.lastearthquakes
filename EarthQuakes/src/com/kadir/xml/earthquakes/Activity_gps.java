package com.kadir.xml.earthquakes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.kadir.xml.earthquakes.adapter.ListAdapter;
import com.kadir.xml.earthquakes.adapter.QuakeListSerializable;

public class Activity_gps extends Activity {

	private ListView gpsList;
	private QuakeListSerializable quakesList;
	private TextView order_by;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_list_view);
		initializeComponents();
		registerEvents();
	}

	private void initializeComponents() {
		gpsList = (ListView) findViewById(R.id.gps_list);
		int result = getIntent().getIntExtra("order_by", 0);
		order_by = (TextView) findViewById(R.id.order_by);
		if (result>0) {
			order_by.setText(R.string.order_by_location);
		} else order_by.setText(R.string.order_by_time);
	}

	private void registerEvents() {
		quakesList = (QuakeListSerializable) getIntent().getSerializableExtra(
				"list_quakes");
		ListAdapter adapter = new ListAdapter(this, quakesList.getList());
		gpsList.setAdapter(adapter);
	}
}
