package com.davies.goeuro;

public class Location {
	private static final String COMMA = ",";

	private long _id;
	private String key;
	private String name;
	private String fullName;
	private String iata_airport_code;
	private String type;
	private String country;
	private GeoPosition geo_position;
	private long locationId;
	private boolean inEurope;
	private String countryCode;
	private boolean coreCountry;
	private double distance;

	public static class GeoPosition {
		private double latitude;
		private double longitude;
		
		public double getLatitude() { return latitude; }
		public void setLatitude(double latitude) { this.latitude = latitude; }
		public double getLongitude() { return longitude; }
		public void setLongitude(double longitude) { this.longitude = longitude; }
	}

	public long get_id() { return _id; }
	public void set_id(long id) { _id = id; }
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getFullName() { return fullName; }
	public void setFullName(String fullName) { this.fullName = fullName; }
	public String getIata_airport_code() { return iata_airport_code; }
	public void setIata_airport_code(String iataAirportCode) { iata_airport_code = iataAirportCode; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }
	public GeoPosition getGeo_position() { return geo_position; }
	public void setGeo_position(GeoPosition geoPosition) { geo_position = geoPosition; }
	public long getLocationId() { return locationId; }
	public void setLocationId(long locationId) { this.locationId = locationId; }
	public boolean isInEurope() { return inEurope; }
	public void setInEurope(boolean inEurope) { this.inEurope = inEurope; }
	public String getCountryCode() { return countryCode; }
	public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
	public boolean isCoreCountry() { return coreCountry; }
	public void setCoreCountry(boolean coreCountry) { this.coreCountry = coreCountry; }
	public double getDistance() { return distance; }
	public void setDistance(double distance) { this.distance = distance; }
	
	/* 
	 * This method will return a CSV String of specific Location attributes.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(_id).append(COMMA)
		  .append(name).append(COMMA)
		  .append(type).append(COMMA)
		  .append(geo_position.latitude).append(COMMA)
		  .append(geo_position.longitude);
		return sb.toString();
	}
	
	public static String stringHeader() {
		StringBuffer sb = new StringBuffer();
		sb.append("_id").append(COMMA)
		  .append("name").append(COMMA)
		  .append("type").append(COMMA)
		  .append("latitude").append(COMMA)
		  .append("longitude");
		return sb.toString();
	}
}
