package com.example.carpoolclan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;


public class GoogleMapsInterface extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FrameLayout map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_interface);

        map = findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;
        String line2 = "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@";

        List<LatLng> line1 = PolyUtil.decode(line2);
        gMap.addPolyline(new PolylineOptions()
                .addAll(line1)
                .color(Color.CYAN));

        LatLng startPnt = line1.get(0);
        this.gMap.addMarker(new MarkerOptions().position(startPnt).title("Start point"));
        LatLng endPnt = line1.get(line1.size()-1);
        this.gMap.addMarker(new MarkerOptions().position(endPnt).title("End point"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPnt, 14));
    }
}