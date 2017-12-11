package com.cinemaapp.views.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.cinemaapp.R;
import com.cinemaapp.models.cinemas.Cinemas;
import com.cinemaapp.presenters.MapsPresenter;
import com.cinemaapp.views.Bases.BaseFragment;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends BaseFragment<MapsPresenter> implements OnMapReadyCallback,IMapsActivity {
    //extends BaseViews<BillboardDetailPresenter> implements IBillboardDetail
    //extends FragmentActivity implements OnMapReadyCallback
    private GoogleMap mMap;
    private ArrayList<Cinemas> cinemasArrayList = new ArrayList<>();
    private ArrayList<LatLng> routeList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setPresenter(new MapsPresenter());
        getPresenter().inject(this,getValidateInternet());
        getPresenter().getThreadCinemasList();
        //receiveCinemas();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(checkPlayServices()) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }


    @Override
    public void getCinemasMaps(ArrayList<Cinemas> cinemas) {
        Log.e("CinemaArrayList 80 ", "" + cinemas.size());
        this.cinemasArrayList = cinemas;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createMarkers();
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Comment: The loading of the map becomes slow
        /*boolean success = googleMap.setMapStyle(
                MapStyleOptions
                        .loadRawResourceStyle(this, R.raw.maps_dark_style));*/

        changeStateControls();

    }



    private void createMarkers() {

        Log.e("CinemaArrayList 127 ", "" + cinemasArrayList.size());

        if (cinemasArrayList != null){

            for (int i = 0; i< cinemasArrayList.size();i++) {

                for (int j = 0; j< cinemasArrayList.get(i).getLocationsList().size();j++) {

                    Log.e("CinemaArrayList 133 ", "" + cinemasArrayList.size());

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(
                                    cinemasArrayList.get(i).getLocationsList().get(j).getLocation().getCoordinates()[1],
                                    cinemasArrayList.get(i).getLocationsList().get(j).getLocation().getCoordinates()[0]))
                            .title(cinemasArrayList.get(i).getName()+"\n")
                            .snippet(cinemasArrayList.get(i).getLocationsList().get(j).getName())
                            .icon(bitPapDescriptorFromVector(this,R.drawable.places_vector)));

                    routeList.add(new LatLng(
                            cinemasArrayList.get(i).getLocationsList().get(j).getLocation().getCoordinates()[1],
                            cinemasArrayList.get(i).getLocationsList().get(j).getLocation().getCoordinates()[0]));



                }
            }
        }

        //method to calculate routes on webservice
        if (routeList.size()==1){
            Log.e("RouteList Size 157 ", "" + routeList.size());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(routeList.get(0),13));
        }else if(routeList.size()>1){
            Log.e("RouteList Size 160 ", "" + routeList.size());
            centerRoutes(routeList);
        }else {
            Toast.makeText(this, "jas", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    private void changeStateControls() {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);//Brujula
        uiSettings.setMyLocationButtonEnabled(true);//Ubicación
    }

    private boolean checkPlayServices(){

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS){
            if(googleAPI.isUserResolvableError(result)){
                googleAPI.getErrorDialog(this, result, 9000).show();
            }
            return false;
        }
        return true;
    }

    RoutingListener routingListener = new RoutingListener() {
        @Override
        public void onRoutingFailure(RouteException e) {
            Log.e("MapsActivity",e.getMessage());
        }

        @Override
        public void onRoutingStart() {
            Log.i("MapsActivity","Starting route");
        }

        @Override
        public void onRoutingSuccess(ArrayList<Route> routes, int shortestRouteIndex) {
            ArrayList polyLines = new ArrayList<>();


            for (int i = 0; i < routes.size(); i++){
                PolylineOptions polyLineOptions = new PolylineOptions();
                polyLineOptions.color(ContextCompat.getColor(getApplicationContext(),colorSelector(i)));
                polyLineOptions.width(10);
                polyLineOptions.addAll(routes.get(i).getPoints());

                Polyline polyLine = mMap.addPolyline(polyLineOptions);
                polyLines.add(polyLine);

                int distance = routes.get(i).getDistanceValue();
                int duration = routes.get(i).getDurationValue();

                /*Polyline polyline = mMap.addPolyline(new PolylineOptions()
                .add(myHome)
                .width(4)
                .color(Color.BLUE)
        );*/






                //Toast.makeText(MapsActivity.this, "Distance: "+distance+" \nDuration: "+duration, Toast.LENGTH_LONG).show();

            }
        }

        @Override
        public void onRoutingCancelled() {
            Log.i("MapsActivity","Canceled route");
        }
    };

    private void calculateRoute(LatLng myHome, LatLng myOffice) {
        ArrayList<LatLng> points= new ArrayList<>();
        points.add(myHome);
        points.add(myOffice);


        Routing routing = new Routing.Builder()
                //.alternativeRoutes(true)
                //.travelMode(AbstractRouting.TravelMode.DRIVING)
                .waypoints(points)
                .key(getString(R.string.google_maps_key))
                //.optimize(false)
                .withListener(routingListener)
                .build();
        routing.execute();
        centerRoutes(points);
    }


    //METHOD FOR CALCULATE ROUTE FROM ARRAY LIST OR SERVICES
    public void calculateRouteList(ArrayList<LatLng> arrayRoutes) {

        Routing routing = new Routing.Builder()
                //.alternativeRoutes(true)
                //.travelMode(AbstractRouting.TravelMode.DRIVING)
                .waypoints(arrayRoutes)
                .key(getString(R.string.google_maps_key))
                //.optimize(false)
                .withListener(routingListener)
                .build();
        routing.execute();
        centerRoutes(arrayRoutes);
    }

    public void centerRoutes(ArrayList<LatLng> points){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(LatLng latLng : points){
            builder.include(latLng);
        }
        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen
        mMap.animateCamera((CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)));
    }

    @NonNull
    private BitmapDescriptor bitPapDescriptorFromVector(Context context, int vectorId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public int colorSelector(int sw){
        switch (sw){
            case 0:
                return R.color.colorAccent;
            case 1:
                return R.color.colorWhite;
            case 2:
                return R.color.colorPrimaryDark;
            default:
                return R.color.colorGray;
        }
    }

}
