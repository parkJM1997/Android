package com.example.speed_helper;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MessageModify extends AppCompatActivity {

    private Button btnup, messageupCan;
    private EditText messageup;
    private TextView messagetext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageup);

        btnup = (Button) findViewById(R.id.btnup);
        messageupCan = (Button) findViewById(R.id.messageupCan);
        messageup = (EditText) findViewById(R.id.messageup);
        messagetext = (TextView) findViewById(R.id.messagetext);

        final int SMS_RECEIVE_PERMISSON=1;

        //권한이 부여되어 있는지 확인
        int permissonCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();

            //권한설정 dialog에서 거부를 누르면
            //ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다.
            //단, 사용자가 "Don't ask again"을 체크한 경우
            //거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                //이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "SMS권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            }
        }



        SharedPreferences sf = getSharedPreferences("text", MODE_PRIVATE);
        SharedPreferences gps = getSharedPreferences("GPS", MODE_PRIVATE);
        SharedPreferences name = getSharedPreferences("Uinfo", MODE_PRIVATE);

        String message = sf.getString("message", "없음");
        String Uname = name.getString("uname", "없음");

        if (Uname != "없음") {
            messagetext.setText("현재위치 : " + gps.getString("address", "GPS 연결이 끊어졌습니다.") + "\n" +
                    "도움요청자 : " + Uname);
        }

        if(message != "없음"){
            messageup.setText(message);
        }
        else{
            messageup.setText("메세지를 입력하세요.");
        }

        messageupCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MessageInfo.class);
                startActivity(intent);

                finish();
            }
        });

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MessageInfo.class);
                startActivity(intent);

                SharedPreferences sf = getSharedPreferences("text", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();

                editor.putString("message", messageup.getText().toString());

                editor.commit();

                finish();
            }
        });
    }
}
