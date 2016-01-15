package com.example.pauliner.myzoo;

/**
 * Created by Stéphane.e on 15/01/2016.
 */
public class LocationService {

    private Double latitude;
    private Double longitude;

    public LocationService(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
