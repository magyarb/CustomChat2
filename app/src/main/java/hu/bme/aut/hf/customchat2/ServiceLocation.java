package hu.bme.aut.hf.customchat2;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import hu.bme.aut.hf.customchat2.LocMan;

/**
 * Created by Balázs on 2015.05.17..
 */
public class ServiceLocation extends Service implements LocationListener {
    public static final String BR_NEW_LOCATION = "BR_NEW_LOCATION";
    public static final String KEY_LOCATION = "KEY_LOCATION";

    private LocMan ldLocationManager = null;
    private boolean locationMonitorRunning = false;

    private Location firstLocation = null;
    private Location lastLocation = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        firstLocation = null;

        if (!locationMonitorRunning) {
            locationMonitorRunning = true;
            ldLocationManager = new LocMan(getApplicationContext(), this);
            ldLocationManager.startLocationMonitoring();
        }

        return START_STICKY;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ldLocationManager != null) {
            ldLocationManager.stopLocationMonitoring();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (firstLocation == null) {
            firstLocation = location;
        }
        lastLocation = location;

        Intent intent = new Intent(BR_NEW_LOCATION);
        intent.putExtra(KEY_LOCATION, location);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TBD
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TBD
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TBD
    }
}
