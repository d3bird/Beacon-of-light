package com.example.firebasetest;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static double lo;
    public static double la;

    public static Vector<Entry> events = new Vector<Entry>(5);

    public static String getcord(){
        String temp ="";
        temp+=la+":"+lo;
        return temp;
    }
    public static void importplaces(Entry e){
        events.add(e);
    }

    private GoogleMap mMap;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //check to see if the netwr provider is working
        if(locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lat = location.getLatitude();
                    la= lat;
                    double log = location.getLongitude();
                    lo =log;
                    LatLng latLng = new LatLng(lat,log);
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {
                        List<Address> addressList =  geocoder.getFromLocation(lat,log,1);
                        String ad = addressList.get(0).getAddressLine(0);
                        String[] r = ad.split(",");
                        ad = r[0]+r[1]+" (you) ";
                        mMap.addMarker(new MarkerOptions().position(latLng).title(ad));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng) );


                        LatLng temploc;
                        double LA;
                        double LO;
                        String[] split;
                        String title ="";
                        for (int i = 0; i < events.size();i++){
                            split = events.get(i).getGPS().split(":");
                            title = events.get(i).getID();
                            LA =Double.parseDouble(split[0]);
                            LO=Double.parseDouble(split[1]);
                            temploc = new LatLng(LA,LO);
                            mMap.addMarker(new MarkerOptions().position(temploc).title(title));
                            title = "";
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }else if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lat = location.getLatitude();
                    double log = location.getLongitude();
                    LatLng latLng = new LatLng(lat,log);
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {
                        List<Address> addressList =  geocoder.getFromLocation(lat,log,1);
                        String ad = addressList.get(0).getAddressLine(0);
                        String[] r = ad.split(",");
                        ad = r[0]+r[1]+" (you) ";

                        mMap.addMarker(new MarkerOptions().position(latLng).title(ad));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                        LatLng temploc;
                        double LA;
                        double LO;
                        String[] split;
                        String title ="";
                        for (int i = 0; i < events.size();i++){
                            split = events.get(i).getGPS().split(":");
                            title = events.get(i).getID();
                            LA =Double.parseDouble(split[0]);
                            LO=Double.parseDouble(split[1]);
                            temploc = new LatLng(LA,LO);
                            mMap.addMarker(new MarkerOptions().position(temploc).title(title));
                            title = "";
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
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
     //   LatLng sydney = new LatLng(-34, 151);
     //   mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
