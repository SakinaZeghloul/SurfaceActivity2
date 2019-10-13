package openclass.tp.nfa024.cnam.fr.surfaceactivity.Contrôleur;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.preference.PowerPreference;

import java.util.HashMap;
import java.util.Map;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.AlertReceiver;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.LocalisationManager;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LONGITUDE_RAPHAEL;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.POINT_RADIUS;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.PROX_ALERT_EXPIRATION;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.RAPHAEL;

public class MapActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Location location;

    private HashMap<String, POI> mPOIHashMap, hashMapPOI;
    private HashMap<Marker, POI> mMarkerMap;
    private POI mPOI;


    Button retourAccueil;
    String loadGame, newGame;

    public static final String TAG = "MapActivity";
    private static final String PROX_ALERT_INTENT =
            "openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.AlertReceiver";
    private static final String SHARED_PREFS = "SharedPref";

    private String ENIGME, choix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        final LocalisationManager g = new LocalisationManager(this);

        Location l = g.getLocation();

   //     mPOIHashMap = POIHash.getPOIHash();
    //    mPOIHashMap=mScanner.loadDataPOI(this);
        mPOIHashMap=PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);

        mMarkerMap = new HashMap<>();
        hashMapPOI = new HashMap<>();

        mPOI = new POI();
        mScanner:new Scanner();


        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {
                loadGame = (String) extras.get("Load Game");

                choix=extras.getString(ENIGME);
                Log.d(TAG, "Choix: " + choix);

                if(loadGame!=null){
                    hashMapPOI=PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);
                    for (Map.Entry<String, POI> e : hashMapPOI.entrySet()) {
                        mPOI = e.getValue();
                        Log.d(TAG, "mPOI: " + mPOI.isCapture());
                    }
                }
            }
        }


        retourAccueil = (Button) findViewById(R.id.buttonRetour);

        retourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accueil = new Intent(view.getContext(), Enigme.class);
                startActivity(accueil);
            }
        });
    }

    /**
     @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
     savedInstanceState){
     return inflater.inflate(R.layout.activity_maps, container.class);
     }
     */

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


        ajoutMarkerHash();

        markerBleu(choix);


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                try {
                    if (marker.getSnippet().contains("Bleu")) {

                        for (Map.Entry<Marker, POI> e : mMarkerMap.entrySet()) {

                            mPOI = e.getValue();

                            if (marker.getTitle().equals(mPOI.getId())) {
                                mPOI.setTag(2);
                                PowerPreference.getDefaultFile().setMap("POIHash", mPOIHashMap);
                                proximityAlert(mPOI.getLatitude(), mPOI.getLongitude());
                                Log.d(TAG, "mPOI: " + mPOI.getId());
                            }
                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }

                return false;
                }
            });


        tresorTrouve(mMarkerMap);
        PowerPreference.getDefaultFile().setMap("POIHash", mPOIHashMap);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(RAPHAEL));
        LatLng latLng = new LatLng(Constantes.LATITUDE_RAPHAEL, LONGITUDE_RAPHAEL);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11), 5000, null);

    }

    private void ajoutMarkerHash() {

        for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {

            mPOI=e.getValue();
            String g=e.getKey();

            Marker marker=ajoutMarker(mPOI.getLatitude(), mPOI.getLongitude(), g);

            mMarkerMap.put(marker, mPOI);
        }
    }

    private Marker ajoutMarker(double latitude, double longitude, String nom) {

        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(nom));
    }


    public void markerBleu(String recup) {

        if(recup!=null){

        for (Map.Entry<Marker, POI> e : mMarkerMap.entrySet()) {

            mPOI = e.getValue();
            Marker marker = e.getKey();


            if (mPOI.getId().equals(recup)) {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                marker.setSnippet("Bleu");
                }
            }
        }
    }


    private void tresorTrouve(HashMap<Marker, POI> hashMap) {

        for (Map.Entry<Marker, POI> e : hashMap.entrySet()) {

            mPOI = e.getValue();
            Marker g=e.getKey();

            if(mPOI.isCapture()==1) {
                g.setTag(e.getValue());
                g.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }
        }
    }

    private void proximityAlert(double latitude, double longitude) {


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Bundle extras = new Bundle();
        extras.putDouble("Lat", latitude);
        extras.putDouble("Lon", longitude);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        final LocalisationManager g = new LocalisationManager(this);
        Location l = g.getLocation();

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra(PROX_ALERT_INTENT, extras);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.addProximityAlert(latitude, longitude, POINT_RADIUS, PROX_ALERT_EXPIRATION, pendingIntent);

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new AlertReceiver(), filter);

    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}


