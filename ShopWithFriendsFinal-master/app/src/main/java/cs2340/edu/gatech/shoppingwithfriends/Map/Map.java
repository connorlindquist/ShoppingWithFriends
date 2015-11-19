package cs2340.edu.gatech.shoppingwithfriends.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs2340.edu.gatech.shoppingwithfriends.R;

public class Map extends FragmentActivity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        createMap();
    }

    private void createMap() {
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        map.addMarker(new MarkerOptions().position(new LatLng(33.7925, -84.3963)).title("Deal at Atlantic Station!"));
    }

}