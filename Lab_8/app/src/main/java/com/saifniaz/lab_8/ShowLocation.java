package com.saifniaz.lab_8;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShowLocation extends Activity implements LocationListener {
    private LocationManager locationManager;

    private double latitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        // get access to geolocation services
        setupGeolocation();
    }

    private void setupGeolocation() {
        // check to ensure I have permission
        verifyGeolocationPermission();

        // check to ensure that geolocation is enabled

        // request updates

    }

    private static final int REQUEST_GEOLOCATION_PERMS = 1;

    private void verifyGeolocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // TODO:  Show the user rationale and ask for permission
            }

            String[] perms = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
            requestPermissions(perms, REQUEST_GEOLOCATION_PERMS);
        } else {
            // geolocation permission granted, so request location updates
            verifyGeolocationEnabled();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] perms,
                                           int[] results) {
        if (requestCode == REQUEST_GEOLOCATION_PERMS) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                // geolocation permission granted, so request location updates
                verifyGeolocationEnabled();
            }
        }
    }

    private void verifyGeolocationEnabled() {
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        // check if geolocation is enabled in settings
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // TODO:  Request location updates
            requestLocationUpdates();
        } else {
            // show the settings app to let the user enable it
            String locationSettings = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGeoloc = new Intent(locationSettings);
            startActivity(enableGeoloc);

            // Note:  startActivityForResult() may be better here
        }
    }

    private void requestLocationUpdates() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String recommendedProvider = locationManager.getBestProvider(criteria, true);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(recommendedProvider,
                    5000, 10, this);
            Log.i("MapsDemo", "requestLocationUpdates()");
        }
    }

    private String[] geocode(double latitude, double longitude) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> results = geocoder.getFromLocation(latitude, longitude, 1);

                if (results.size() > 0) {
                    String[] temp = new String[8];
                    temp[0] = results.get(0).getAddressLine(0);
                    temp[1] = results.get(0).getAddressLine(1);
                    temp[2] = results.get(0).getLocality();
                    temp[3] = results.get(0).getAdminArea();
                    temp[4] = results.get(0).getCountryName();
                    temp[5] = results.get(0).getPostalCode();
                    temp[6] = results.get(0).getPhone();
                    temp[7] = results.get(0).getUrl();

                    return temp;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void onLocationChanged(Location location) {
        Log.i("MapsDemo", "Location changed: " + location);

        // remember the coordinates for the map activity's backup location
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        // geocode the result - get the location name
        String[] result = geocode(location.getLatitude(), location.getLongitude());

        // show the location in the search UI
        EditText addr1 = (EditText)findViewById(R.id.addr1);
        EditText addr2 = (EditText)findViewById(R.id.addr2);
        EditText city = (EditText)findViewById(R.id.cityField);
        EditText provice = (EditText)findViewById(R.id.provField);
        EditText country = (EditText)findViewById(R.id.countryField);
        EditText postal = (EditText)findViewById(R.id.postal);
        EditText phone = (EditText)findViewById(R.id.phone);
        EditText url = (EditText)findViewById(R.id.urlField);


        addr1.setText(result[0]);
        addr2.setText(result[1]);
        city.setText(result[2]);
        provice.setText(result[3]);
        country.setText(result[4]);
        postal.setText(result[5]);
        phone.setText(result[6]);
        url.setText(result[7]);

    }

    public void onProviderEnabled(String provider) {
        Log.i("MapsDemo", "Provider enabled: " + provider);
    }

    public void onProviderDisabled(String provider) {
        Log.i("MapsDemo", "Provider disabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("MapsDemo", "Provider ("+provider+") status changed: " + status);
    }
}
