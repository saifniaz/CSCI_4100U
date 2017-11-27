package com.saifniaz.lab_9;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShowLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private double latitude, longitude;
    private String add1, add2, city, province, country, postal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // here I do *not* have a map

        // collect the input values (location to show)
        Intent callingIntent = getIntent();
        add1 = callingIntent.getStringExtra("addr1");
        add2 = callingIntent.getStringExtra("addr2");
        city = callingIntent.getStringExtra("city");
        province = callingIntent.getStringExtra("province");
        country = callingIntent.getStringExtra("country");
        postal = callingIntent.getStringExtra("postal");


        Address locationAddress = forwardGeocode(add1);
        if (locationAddress != null) {
            latitude = locationAddress.getLatitude();
            longitude = locationAddress.getLongitude();
        }
    }

    private Address forwardGeocode(String locationName) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> results = geocoder.getFromLocationName(locationName, 1);

                if (results.size() > 0) {
                    return results.get(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng position = new LatLng(latitude, longitude);

        // centre the map around the specified location
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position));

        // add a marker at the specified location
        MarkerOptions options = new MarkerOptions();
        mMap.addMarker(options.position(position).title(add1));

        // configure the map settings
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // enable the zoom controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}
