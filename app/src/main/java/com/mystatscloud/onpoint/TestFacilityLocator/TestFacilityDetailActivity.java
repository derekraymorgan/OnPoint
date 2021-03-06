package com.mystatscloud.onpoint.TestFacilityLocator;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mystatscloud.onpoint.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricky Yu on 4/22/2016.
 */
public class TestFacilityDetailActivity extends FragmentActivity
    implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView name;
    private TextView address;
    private TextView city;
    private TextView state;
    private TextView zip;
    private TextView phone;
    private TextView fax;

    private TestFacility facility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_facility_locator_detail);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detail_map);
        // Obtain other views
        name = (TextView) findViewById(R.id.detail_name);
        address = (TextView) findViewById(R.id.detail_address);
        city = (TextView) findViewById(R.id.detail_city);
        state = (TextView) findViewById(R.id.detail_state);
        zip = (TextView) findViewById(R.id.detail_zip);
        phone = (TextView) findViewById(R.id.detail_phone);
        fax = (TextView) findViewById(R.id.detail_fax);

        // Retrieve facility data from extra
        facility = (TestFacility)getIntent().getSerializableExtra("facility");
        // Set textViews
        address.setText(facility.getAddress());
        city.setText(facility.getCity());
        state.setText(facility.getState());
        zip.setText(Integer.toString(facility.getZipCode()));
        phone.setText(facility.getPhone());
        fax.setText(facility.getFax());



        mapFragment.getMapAsync(this);
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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String uri = null;
                try { // Open a maps application with the corresponding location
                    // must encode address as a URL
                    uri = String.format("geo:0,0?q=%s", URLEncoder.encode(facility.getAddress() + ", "
                            + facility.getCity() + ", "
                            + facility.getState() + ", "
                            + facility.getZipCode(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                return true;
            }
        });

        LatLng address = new LatLng(facility.getLat(), facility.getLng());

        if (address != null) {
            mMap.addMarker(new MarkerOptions().position(address).title("Test Facility"));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            CameraPosition cp = new CameraPosition.Builder()
                    .target(address)
                    .zoom(17)
                    .build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        }
    }
}
