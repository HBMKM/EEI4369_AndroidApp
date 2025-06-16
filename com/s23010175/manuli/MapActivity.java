package com.s23010175.manuli;

import androidx.appcompat.app.AppCompatActivity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;



public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private EditText etAddress;
    private Button btnShowLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        etAddress = findViewById(R.id.etAddress);
        btnShowLocation = findViewById(R.id.btnShowLocation);

        Button btnGoToSensor = findViewById(R.id.btnGoToSensor);
        btnGoToSensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, SensorActivity.class);
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = etAddress.getText().toString();
                if (!location.isEmpty()) {
                    showLocationOnMap(location);
                } else {
                    Toast.makeText(MapActivity.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    private void showLocationOnMap(String address) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> locations = geocoder.getFromLocationName(address, 1);
            if (locations != null && !locations.isEmpty()) {
                Address location = locations.get(0);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                map.clear();
                map.addMarker(new MarkerOptions().position(latLng).title(address));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error finding location", Toast.LENGTH_SHORT).show();
        }
    }
}
