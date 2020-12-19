package com.example.speed_helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;

public class UserInfo extends AppCompatActivity {

    private TextView username, takename, takenumber;
    private Button modify, userinfoCan, takeinfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        modify = (Button) findViewById(R.id.modify);
        userinfoCan = (Button) findViewById(R.id.userinfoCan);
        username = (TextView) findViewById(R.id.username);
        takename = (TextView) findViewById(R.id.takename);
        takenumber = (TextView) findViewById(R.id.takenumber);
        takeinfo = (Button) findViewById(R.id.takeinfo);

        SharedPreferences sf = getSharedPreferences("Uinfo", MODE_PRIVATE);

        username.setText(sf.getString("uname",""));
        takename.setText(sf.getString("tname",""));
        takenumber.setText(sf.getString("tnumber",""));

        userinfoCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserModify.class);
                startActivity(intent);

                finish();
            }
        });

        takeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                startActivity(intent);

                finish();
            }
        });

    }
}
