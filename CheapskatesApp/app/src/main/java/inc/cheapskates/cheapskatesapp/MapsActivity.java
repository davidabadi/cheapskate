package inc.cheapskates.cheapskatesapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String lon;
    private String lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gets the lat and lon from the last intent.
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            lat = bundle.getString("lat");
            lon = bundle.getString("lon");
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used. Do not change.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker at the Restaurant.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at Restaurant and move the camera
        LatLng restaurant = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));//puts the latitude and longitude of the restaurant in restaurant
        mMap.addMarker(new MarkerOptions().position(restaurant).title(""));//adds a marker at location restaurant
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant, 18));// moves the camera at location restaurant and sets the zoom level

    }
}
