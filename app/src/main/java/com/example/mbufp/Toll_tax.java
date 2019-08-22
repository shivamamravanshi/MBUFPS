package com.example.mbufp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.LocationListener;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Toll_tax extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 1000;
    double latitude,longitude;
    private static final LatLng Pagara_toll_booth = new LatLng(24.507181, 77.186022);
    private static final LatLng Damoh_Jabalpur_Toll = new LatLng(23.796215, 79.456919);
    private static final LatLng Bhopal_Vidisha_Toll = new LatLng(23.371173, 77.502863);
    private static final LatLng Choundha_Toll = new LatLng(26.517987, 77.969003);
    private static final LatLng Ramnagar_Toll = new LatLng(25.278885, 77.614493);
    private static final LatLng Yamuna_ExpressWay = new LatLng(28.114414, 77.574935);
    private static final LatLng Gunna_Bypass_Toll = new LatLng(24.620360, 77.261483);
    private static final LatLng Madhya_Pradesh_PUC_Board = new LatLng(23.209767, 77.422503);
    private static final LatLng MAHAKAAL_PUC_CENTRE = new LatLng(23.226743, 77.423217);
    private static final LatLng Maa_Krapa_PUC_Center = new LatLng(23.338611, 77.474172);
    private static final LatLng SUVIDHA_POLLUTION_TESTING_CENTER = new LatLng(23.195819, 77.417034);
    private static final LatLng PUC_Pollution_Testing_Centre = new LatLng(23.236524, 77.432663);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll_tax);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checklocationpermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission is Granted
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else //Permission is Denied
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                return;
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.clear();
            TollList();
        }
    }

    protected void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();

    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.nearbypuc: {
                /*mMap.clear();
                String nearbypuc = "NearbyPUC";
                String url = getUrl(latitude,longitude,nearbypuc);
                dataTransfer[0]=mMap;
                dataTransfer[1]=url;
                Object dataTransfer[] = new Object[2];
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(Toll_tax.this,"Showing Nearby PUC's",Toast.LENGTH_LONG).show();
                */
                mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(Madhya_Pradesh_PUC_Board);
                markerOptions.title("Madhya Pradesh Pollution Control Board");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(Madhya_Pradesh_PUC_Board));

                markerOptions.position(SUVIDHA_POLLUTION_TESTING_CENTER);
                markerOptions.title("SUVIDHA POLLUTION TESTING CENTER");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(SUVIDHA_POLLUTION_TESTING_CENTER));

                markerOptions.position(Maa_Krapa_PUC_Center);
                markerOptions.title("Maa Krapa PUC Center");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(Maa_Krapa_PUC_Center));

                markerOptions.position(MAHAKAAL_PUC_CENTRE);
                markerOptions.title("MAHAKAAL PUC CENTRE");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(MAHAKAAL_PUC_CENTRE));

                markerOptions.position(PUC_Pollution_Testing_Centre);
                markerOptions.title("PUC Pollution Testing Centre");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(PUC_Pollution_Testing_Centre));
            }
            break;
            case R.id.search: {

                EditText search_for_location = findViewById(R.id.search_for_location);
                String location = search_for_location.getText().toString();
                List<Address> addressList = null;
                MarkerOptions markerOptions = new MarkerOptions();
                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 5);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < addressList.size(); i++) {
                        Address myAddress = addressList.get(i);
                        LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                        markerOptions.position(latLng);
                        markerOptions.title("Your Search Result");
                        mMap.addMarker(markerOptions);
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    }
                }
            }
            break;

        }
    }

    private String getUrl(double latitude,double longitude,String nearbyPlace)
    {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?");
        googlePlaceUrl.append("location"+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyC9Lt6E__xgcGpP0HwC0J6WAbjLpE7j%P0");
        return googlePlaceUrl.toString();
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    public boolean checklocationpermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;

        } else
            return true;

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    void TollList()
    {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Pagara_toll_booth);
        markerOptions.title("Pagara toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        markerOptions.position(Damoh_Jabalpur_Toll);
        markerOptions.title("Damoh Jabalpur Toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        markerOptions.position(Bhopal_Vidisha_Toll);
        markerOptions.title("Bhopal Vidisha Toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        markerOptions.position(Choundha_Toll);
        markerOptions.title("Choundha Toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        markerOptions.position(Ramnagar_Toll);
        markerOptions.title("Ramnagar Toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        markerOptions.position(Yamuna_ExpressWay);
        markerOptions.title("Yamuna ExpressWay Toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        markerOptions.position(Gunna_Bypass_Toll);
        markerOptions.title("Gunna Bypass Toll Booth");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(Gunna_Bypass_Toll));
        mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }
}
