package com.kadir.xml.earthquakes.adapter;

import java.io.Serializable;
import java.util.ArrayList;

import com.kadir.xml.earthquakes.model.Quakes;

public class QuakeListSerializable implements Serializable{
	
	private static final long serialVersionUID = 3844058495613403932L;
	private ArrayList<Quakes> list;
	
	public QuakeListSerializable(ArrayList<Quakes> list) {
		this.list = list;
	}

	public ArrayList<Quakes> getList() {
		return list;
	}

	
}
