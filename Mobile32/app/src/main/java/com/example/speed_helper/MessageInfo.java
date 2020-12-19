package com.example.speed_helper;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MessageInfo extends AppCompatActivity {

    private Button btnmodify, messageinfoCan;
    private TextView messagetext;
    private TextView usermessage;
    private GpsTracker gpsTracker;
    String gps;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageinfo);

        btnmodify = (Button) findViewById(R.id.btnmodify);
        messageinfoCan = (Button) findViewById(R.id.messageinfoCan);
        messagetext = (TextView) findViewById(R.id.messagetext);
        usermessage = (TextView) findViewById(R.id.usermessage);

        SharedPreferences sf = getSharedPreferences("text", MODE_PRIVATE);
        SharedPreferences gps = getSharedPreferences("GPS", MODE_PRIVATE);
        SharedPreferences name = getSharedPreferences("Uinfo", MODE_PRIVATE);

        String message = sf.getString("message", "없음");
        String Uname = name.getString("uname", "없음");

        if (Uname != "없음") {
            messagetext.setText("현재위치 : " + gps.getString("address", "GPS 연결 상태 혹은 메인창에서 현위치 정보를 확인하세요") +
                    "도움요청자 : " + Uname);
        }

        if (message != "없음") {
            usermessage.setText(message);
        } else {
            usermessage.setText("메세지를 입력하세요.");
        }


        messageinfoCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MessageModify.class);
                startActivity(intent);

                finish();
            }
        });

    }

}
