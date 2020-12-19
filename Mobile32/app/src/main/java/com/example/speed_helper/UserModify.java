package com.example.speed_helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class UserModify extends AppCompatActivity{

    private Button update, modifyCan, resetuser;
    private EditText infousername, infotakename, infotakenumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        update = (Button) findViewById(R.id.update);
        modifyCan = (Button) findViewById(R.id.modifyCan);
        resetuser = (Button) findViewById(R.id.resetuser);
        infousername = (EditText) findViewById(R.id.infousername);
        infotakename = (EditText) findViewById(R.id.infotakename);
        infotakenumber = (EditText) findViewById(R.id.infotakenumber);

        SharedPreferences sf = getSharedPreferences("Uinfo", MODE_PRIVATE);

        infousername.setText(sf.getString("uname","사용자 이름"));
        infotakename.setText(sf.getString("tname","수신자 이름"));
        infotakenumber.setText(sf.getString("tnumber","수신자 전봐번호"));

        modifyCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(infotakenumber.getText().length() < 12) {


                    //editText에서 아이디와 암호 가져와 PreferenceManager에 저장한다.
                    SharedPreferences sf = getSharedPreferences("Uinfo", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sf.edit();

                    String Uname = infousername.getText().toString();
                    String Tname = infotakename.getText().toString();
                    String Tnumber = infotakenumber.getText().toString();

                    editor.putString("uname", Uname); //uname 키값으로 저장
                    editor.putString("tname", Tname);//tname 키값으로 저장
                    editor.putString("tnumber", Tnumber);//tnumber 키값으로 저장

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);

                    startActivity(intent);

                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserModify.this);
                    builder.setTitle("알림");
                    builder.setMessage("연락처에는 번호만 기입해주세요 \n ex) 01012345678");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
            }
        });

        resetuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserModify.this);
                builder.setTitle("알림");
                builder.setMessage("정말 초기화 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        infousername.setText("");
                        infotakenumber.setText("");
                        infotakename.setText("");
                        SharedPreferences sharedPreferences = getSharedPreferences("Uinfo", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.clear();
                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.create().show();


            }
        });

    }

}
