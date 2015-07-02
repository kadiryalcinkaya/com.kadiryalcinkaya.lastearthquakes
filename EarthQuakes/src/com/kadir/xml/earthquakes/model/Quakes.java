package com.kadir.xml.earthquakes.model;

import java.io.Serializable;

public class Quakes implements Serializable{

	private static final long serialVersionUID = 8877047302548867637L;
	
	private String name;
	private String lokasyon;
	private float lat;
	private float lng;
	private float mag;
	private float Depth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLokasyon() {
		return lokasyon;
	}
	public void setLokasyon(String lokasyon) {
		this.lokasyon = lokasyon;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getMag() {
		return mag;
	}
	public void setMag(float mag) {
		this.mag = mag;
	}
	public float getDepth() {
		return Depth;
	}
	public void setDepth(float depth) {
		Depth = depth;
	}
	
	@Override
	public String toString() {
		return "Quakes [name=" + name + ", lokasyon=" + lokasyon + ", lat="
				+ lat + ", lng=" + lng + ", mag=" + mag + ", Depth=" + Depth
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quakes other = (Quakes) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
