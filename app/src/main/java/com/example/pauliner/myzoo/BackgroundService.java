package com.example.pauliner.myzoo;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by Stéphane.e on 15/01/2016.
 */
public class BackgroundService extends Service implements LocationListener {

    private LocationManager locMng = null;

    public void onCreate() {
        super.onCreate();

        locMng = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locMng.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locMng.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        }

        if (locMng.getAllProviders().contains(LocationManager.GPS_PROVIDER)) {
            locMng.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
        }
        MapActivity.myApplication.getEventBus().post(this);
    }

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        final Double latitude = location.getLatitude();
        final Double longitude = location.getLongitude();
        MapActivity.loc = longitude + "." + latitude;
        if (MapActivity.map) {
            Toast.makeText(getApplicationContext(), MapActivity.loc, Toast.LENGTH_SHORT).show();
        }

        MapActivity.myApplication.getEventBus().post(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
