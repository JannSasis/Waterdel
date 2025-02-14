package com.example.waterdelivery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class ActivityOneMap1 extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentuserLocationMarker;
    private static final int Request_USer_Location_code = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_one_map1 );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );

    }


    @Override
    public void onMapReady(GoogleMap googleMap)


    {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap = googleMap;
        LatLng urdaneta = new LatLng(15.975810, 120.570688);
        mMap.addMarker(new MarkerOptions().position(urdaneta).title("Urdaneta City"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng( urdaneta));

        LatLng mat = new LatLng(15.9752199,120.5601097);
        mMap.addMarker(new MarkerOptions().position(mat).title("OZONE PURE WATER STATION"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mat , 10));





        if (ContextCompat.checkSelfPermission( this,Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED){


            builGoogleApiClient();
            mMap.setMyLocationEnabled( true );
        }

    }

    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission( this,Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,Manifest.permission.ACCESS_FINE_LOCATION ))
            {
                ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_USer_Location_code );

            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_USer_Location_code);
            }
            return false;
        }
        else
        {
            return true;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode){
            case Request_USer_Location_code:
                if (grantResults.length > 0 &&grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            builGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled( true );
                    }
                    else
                    {
                        Toast.makeText( this,"Peemission Denien",Toast.LENGTH_SHORT ).show();
                    }
                    return;
                }
        }
    }


    protected synchronized void builGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder( this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener( this )
                .addApi( LocationServices.API )
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

        lastLocation = location;

        if (currentuserLocationMarker !=null)
        {
            currentuserLocationMarker.remove();
        }
        LatLng latLng = new LatLng( location.getLatitude(),location.getLongitude() );
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position( latLng );
        markerOptions.title( "User Current Location" );
        markerOptions.icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN) );
        currentuserLocationMarker = mMap.addMarker( markerOptions );

        mMap.moveCamera( CameraUpdateFactory.newLatLng( latLng ) );
        mMap.animateCamera( CameraUpdateFactory.zoomBy( 5 ) );

        if (googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates( googleApiClient, this );

        }




    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval( 1100 );
        locationRequest.setFastestInterval( 1100 );
        locationRequest.setFastestInterval( 1100 );
        locationRequest.setPriority( LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY );

        if (ContextCompat.checkSelfPermission( this,Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates( googleApiClient, locationRequest,this );
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}


