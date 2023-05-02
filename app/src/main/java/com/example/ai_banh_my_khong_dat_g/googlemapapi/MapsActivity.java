package com.example.ai_banh_my_khong_dat_g.googlemapapi;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.ai_banh_my_khong_dat_g.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {
    GoogleMap mMap;
    SupportMapFragment mapFragment;
    Spinner spinner;
    SearchView searchView;
    Button btnShowDuongDi;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;
    Polyline currentPolyline;
    MarkerOptions place1, place2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints = new ArrayList<>();
        addControls();
        addEvents();
        String location = searchView.getQuery().toString();
        List<Address> addressList = null;
        LatLng diaChiTrenBill = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(MapsActivity.this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
                Address address = addressList.get(0);
                diaChiTrenBill = new LatLng(address.getLatitude(), address.getLongitude());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        place1 = new MarkerOptions().position(new LatLng(20.981073778264847, 105.78745935350383)).title("CỬA HÀNG BÁNH KEM");
        place2 = new MarkerOptions().position(diaChiTrenBill).title("ĐỊA ĐIỂM GIAO HÀNG");

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(diaChiTrenBill, 10));
        //show đường đi
        btnShowDuongDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(place2.getPosition());
//                if (listPoints.size() == 1) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                mMap.addMarker(place2);
                new FetchURL(MapsActivity.this)
                        .execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"),
                                "driving");
            }
        });

    }

    private void addEvents() {

//        //chỗ này là nút search theo query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    new FetchURL(MapsActivity.this)
                            .execute(getUrl(place1.getPosition(), latLng, "driving"),
                                    "driving");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
        //chỗ này để set style bản đồ
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 1:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                        break;
                    case 2:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                        break;
                    case 3:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                        break;
                    default:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    //chỗ này để ánh xạ các control
    private void addControls() {
        btnShowDuongDi = findViewById(R.id.btnShowDirection);
        spinner = findViewById(R.id.spinnerMapStyle);
        searchView = findViewById(R.id.txtSearchAddress);
        String diaChiGiaoHang = getIntent().getStringExtra("diaChiGiaoHang");


        searchView.setQuery(diaChiGiaoHang, false);
        ArrayList<String> ds_StyleMap = new ArrayList<>();
        ds_StyleMap.add("chế độ xem kết hợp");
        ds_StyleMap.add("chế độ xem địa hình");
        ds_StyleMap.add("chế độ xem vệ tinh");
        ds_StyleMap.add("chế độ xem thông thường");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ds_StyleMap);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        //lấy vị trí hiện tại
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        //bật nuts location
        mMap.setMyLocationEnabled(true);
        //bật nút zoom in zoom out
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.addMarker(place1);

        mMap.addMarker(place2);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place2.getPosition(), 15));
        Circle circle = mMap.addCircle(new CircleOptions().center(place1.getPosition()).radius(40).fillColor(Color.GREEN).strokeColor(Color.BLUE));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                //reset marker if array size = 2
                if (listPoints.size() == 1) {
                    listPoints.clear();
                    mMap.clear();
                    mMap.addMarker(place1);
                }
                //save first point
                listPoints.add(latLng);
                //create marker
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
//                if (listPoints.size() == 1) {
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

//                } else {
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

//                }
                mMap.addMarker(markerOptions);
//                //TODO
//                if (listPoints.size() == 2) {
                    //create the url to get the request from first marker to second marker
                    String url = getUrl(place1.getPosition(), listPoints.get(0), "driving");
                    new FetchURL(MapsActivity.this).execute(url, "driving");
//                }
            }
        });
////        bật nút zoom in zoom out

        String location = searchView.getQuery().toString();
//        List<Address> addressList = null;
//        LatLng diaChiGiaoHang = null;
//        if (location != null || !location.equals("")) {
//            Geocoder geocoder = new Geocoder(MapsActivity.this);
//            try {
//                addressList = geocoder.getFromLocationName(location, 1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Address address = addressList.get(0);
//            diaChiGiaoHang = new LatLng(address.getLatitude(), address.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(diaChiGiaoHang).title(location));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(diaChiGiaoHang, 10));
//        }

//        LatLng latingHocVienBuuChinh = new LatLng(20.981073778264847, 105.78745935350383);
//        LatLng latingChungCuVinaconex1 = new LatLng(21.00176343528554, 105.79255508233963);

//        PolylineOptions polylineOptions = new PolylineOptions().add(latingHocVienBuuChinh).add(diaChiGiaoHang).color(Color.RED).width(10);
//        Polyline polyline = mMap.addPolyline(polylineOptions);
//        vẽ hình tròn tại các vị trí và tô màu cho nó
//        Circle circle = mMap.addCircle(new CircleOptions().center(latingHocVienBuuChinh).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
//        Circle circle1 = mMap.addCircle(new CircleOptions().center(diaChiGiaoHang).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
//        phóng to bản đồ tại vị trí đó
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(diaChiGiaoHang, 15));


    }

    //hàm tạo request để gửi đi
    // private String getRequestUrl(LatLng origin, LatLng dest) {
    ////        //value of origin
    ////        String str_org = "origin=" + origin.latitude + "," + origin.longitude;
    ////        //value of destination
    ////        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
    ////        //set value enable sensor
    //////        String sensor = "sensor=false";
    ////        //mode for find direction
    ////        String mode = "mode=driving";
    ////        //build the full param
    ////        String param = str_org + "&" + str_dest + "&" + mode;
    ////        //output format
    ////        String output = "json";
    ////        //create url to request
    ////        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param + getString(R.string.google_maps_key);
    ////        return url;
    ////    }
//
    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            //get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);

                }
                break;
        }

    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null) {
            currentPolyline.remove();
        }
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //parse json here
        }
    }
}