package hu.bme.aut.hf.customchat2;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

/**
 * Created by Balázs on 2015.05.17..
 */
public class LocMan {

        private Context context;
        private LocationListener listener;
        private LocationManager locMan;

        public LocMan(Context aContext, LocationListener listener) {
            context = aContext;
            this.listener = listener;
            locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }

        public void startLocationMonitoring() {
            locMan.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    100, 100, listener);
            // EMULÁTORON A NETWORK PROVIDER NEM ÉRHETŐ EL!
            locMan.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0, 0, listener);
        }

        public void stopLocationMonitoring() {
            if (locMan != null) {
                locMan.removeUpdates(listener);
            }
        }
}
