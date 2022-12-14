package com.example.pgsr_daegu_safety;

import static com.example.pgsr_daegu_safety.MainActivity.tag;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Geocoder geocoder;

    private WebView webView;
    private com.example.pgsr_daegu_safety.ProgressDialog progressDialog;

    private EditText address_editText;
    private ImageButton search_button;
    private ImageButton cur_posi_button;

    private GpsTracker gpsTracker;
    static String Tag;
    static String adr;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.second_main);

        geocoder = new Geocoder(this);

        if (checkLocationServicesStatus()) {
            checkRunTimePermission();
        } else {
            showDialogForLocationServiceSetting();
        }
        gpsTracker = new GpsTracker(this);


        progressDialog = new com.example.pgsr_daegu_safety.ProgressDialog(this);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        webView = (WebView)findViewById(R.id.my_webView);
        address_editText = (EditText)findViewById(R.id.address_search_editText);
        search_button = (ImageButton) findViewById(R.id.search_button);
        cur_posi_button = (ImageButton)findViewById(R.id.cur_posi_button);

        adr = "https://www.google.com/maps/d/edit?hl=ko&mid=1YEjQZk8j00Fg89FfeDDQgJzTdFyLouM&ll=";
        if(tag==1) {
            adr = "https://www.google.com/maps/d/edit?hl=ko&mid=1lqSAQkHjJY0FgjpIbZo3Rbv_yQgdMe8&ll=";
        }
        else if(tag==2){
            adr = "https://www.google.com/maps/d/edit?hl=ko&mid=1WEU7f7hTzOJMvSvChRpR0SQYV0CZhLk&ll=";
        }
        else if(tag==3){
            adr = "https://www.google.com/maps/d/edit?hl=ko&mid=1UZMSV-RbqfkRQJqPzweVVF8QrWHCo-k&ll=";
        }
        else if(tag==4){
            adr = "https://www.google.com/maps/d/edit?hl=ko&mid=1YEjQZk8j00Fg89FfeDDQgJzTdFyLouM&ll=";
        }

        cur_posi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cur_posi();
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move_to_address(address_editText.getText().toString());
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                progressDialog.show();
            }

            //???????????? ?????? ????????? ??????
            @Override
            public void onPageFinished(WebView view, String url){
                progressDialog.dismiss();
            }
        });

        cur_posi();
    }

    private void move_to_address(String address) {
        List<Address> list = null;
        String url = "";

        try {
            list = geocoder.getFromLocationName
                    (address, // ?????? ??????
                            10); // ?????? ??????
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","????????? ?????? - ???????????? ??????????????? ????????????");
        }

        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(this, "?????? ????????? ????????????.", Toast.LENGTH_SHORT);
            } else {
                // ???????????? ????????? ????????? ?????????
                Address addr = list.get(0);
                double latitude = addr.getLatitude();
                double longitude = addr.getLongitude();

                System.out.println("////////////?????? ??? ????????? : "+latitude+","+longitude);
                url = adr + latitude + "%2C" + longitude + "&z=14";
                webView.loadUrl(url);
                Toast.makeText(this, Tag, Toast.LENGTH_SHORT);
            }
        }
    }

    private void cur_posi() {
        Log.d("cur_posi", "cur_posi() starts");
        Toast.makeText(this, "cur_posi() starts", Toast.LENGTH_SHORT);


        System.out.println(gpsTracker);

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();
        System.out.println("latitude: " + latitude + ",    longitude: " + longitude);

        String url = adr + latitude + "%2C" + longitude + "&z=14";

        webView.loadUrl(url);

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // ?????? ????????? PERMISSIONS_REQUEST_CODE ??????, ????????? ????????? ???????????? ??????????????????

            boolean check_result = true;


            // ?????? ???????????? ??????????????? ???????????????.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {
            } else {


                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(this, "???????????? ?????????????????????. ?????? ?????? ???????????? ???????????? ??????????????????.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(this, "???????????? ?????????????????????. ??????(??? ??????)?????? ???????????? ???????????? ?????????. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {


        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(this, "??? ?????? ??????????????? ?????? ?????? ????????? ???????????????.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("?????? ????????? ????????????");
        builder.setMessage("?????? ???????????? ???????????? ?????? ???????????? ???????????????.\n"
                + "?????? ????????? ???????????????????");
        builder.setCancelable(true);
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS ????????? ?????????");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}