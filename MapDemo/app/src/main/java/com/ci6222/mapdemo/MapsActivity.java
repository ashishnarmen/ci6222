package com.ci6222.mapdemo;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.widget.Toast;
import android.content.pm.PackageManager;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.maps.UiSettings;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_REQUEST_CODE = 101;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

        if (mMap != null) {
            int permission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (permission == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                requestPermission(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        LOCATION_REQUEST_CODE);
            }
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng BPARK = new LatLng(1.318653, 103.706442);
        Marker bpark = mMap.addMarker(new MarkerOptions()
                .position(BPARK)
                .title("Park")
                .snippet("Jurong Bird Park"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BPARK, 14));
        UiSettings mapUiSettings = mMap.getUiSettings();
        mapUiSettings.setZoomControlsEnabled(true);
    }

    protected void requestPermission(String permissionType,
                                     int requestCode) {

        ActivityCompat.requestPermissions(this,
                new String[]{permissionType}, requestCode
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Unable to show location - permission required",
                            Toast.LENGTH_LONG).show();
                } else {

                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
            }
        }
    }

}
