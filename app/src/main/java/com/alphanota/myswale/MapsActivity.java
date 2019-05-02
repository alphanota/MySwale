package com.alphanota.myswale;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alphanota.myswale.model.Swale;
import com.alphanota.myswale.network.Resource;
import com.alphanota.myswale.viewmodel.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private MapViewModel mapViewModel;

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


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //loadSwalesfromGeoJson(mMap);


        initViewModel();


    }


    private void initViewModel(){
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);

        mapViewModel.getAllSwales().observe(this, listResource -> {
            if(listResource.status == Resource.Status.SUCCESS) {
                mMap.clear();
                for (Swale swale: listResource.data) {
                    addSwaleToMap(swale);
                }

            }
            else if (listResource.status == Resource.Status.ERROR) {
                Toast.makeText(this,listResource.message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addSwaleToMap(Swale swale) {

        LatLng latLng = GeoFactory.GeomToLatLng(swale.the_geom);

        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(String.valueOf(swale.asset_id)));

        marker.setTag(swale);

    }


    private void loadSwalesfromGeoJson(GoogleMap map){
        try {
            GeoJsonLayer layer = new GeoJsonLayer(map, R.raw.dep_gi, this);
            layer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
