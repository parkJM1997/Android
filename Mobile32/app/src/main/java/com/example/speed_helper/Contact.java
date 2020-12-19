package com.example.speed_helper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    TextView takename1, takename2, takename3, takenumber1, takenumber2, takenumber3;
    Button contact1, delete1, contact2, delete2, contact3, delete3, check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        takename1 = (TextView) findViewById(R.id.takename1);
        takename2 = (TextView) findViewById(R.id.takename2);
        takename3 = (TextView) findViewById(R.id.takename3);
        takenumber1 = (TextView) findViewById(R.id.takenumber1);
        takenumber2 = (TextView) findViewById(R.id.takenumber2);
        takenumber3 = (TextView) findViewById(R.id.takenumber3);
        contact1 = (Button) findViewById(R.id.contact1);
        contact2 = (Button) findViewById(R.id.contact2);
        contact3 = (Button) findViewById(R.id.contact3);
        delete1 = (Button) findViewById(R.id.delete1);
        delete2 = (Button) findViewById(R.id.delete2);
        delete3 = (Button) findViewById(R.id.delete3);
        check = (Button) findViewById(R.id.check);

        SharedPreferences sf = getSharedPreferences("Contact", MODE_PRIVATE);

        takename1.setText(sf.getString("tname1", "저장 기록이 없습니다."));
        takename2.setText(sf.getString("tname2", "저장 기록이 없습니다."));
        takename3.setText(sf.getString("tname3", "저장 기록이 없습니다."));
        takenumber1.setText(sf.getString("tnumber1", "저장 기록이 없습니다."));
        takenumber2.setText(sf.getString("tnumber2", "저장 기록이 없습니다."));
        takenumber3.setText(sf.getString("tnumber3", "저장 기록이 없습니다."));

        if(takename1.getText().toString() == "저장 기록이 없습니다."){
            delete1.setVisibility(View.INVISIBLE);
            contact1.setText("저장하기");
        }

        if (takename2.getText().toString() == "저장 기록이 없습니다."){
            delete2.setVisibility(View.INVISIBLE);
            contact2.setText("저장하기");
        }

        if(takename3.getText().toString() == "저장 기록이 없습니다."){
            delete3.setVisibility(View.INVISIBLE);
            contact3.setText("저장하기");
        }

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact1.getText().toString() == "저장하기"){
                    btn_Click(1);
                }else {
                    SharedPreferences sf = getSharedPreferences("Uinfo", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sf.edit();

                    String Tname = takename1.getText().toString();
                    String Tnumber = takenumber1.getText().toString();

                    editor.putString("tname", Tname);//tname 키값으로 저장
                    editor.putString("tnumber", Tnumber);//tnumber 키값으로 저장

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);

                    startActivity(intent);

                    finish();
                }
            }
        });

        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact2.getText().toString() == "저장하기"){
                    btn_Click(2);
                }else {
                    SharedPreferences sf = getSharedPreferences("Uinfo", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sf.edit();

                    String Tname = takename2.getText().toString();
                    String Tnumber = takenumber2.getText().toString();

                    editor.putString("tname", Tname);//tname 키값으로 저장
                    editor.putString("tnumber", Tnumber);//tnumber 키값으로 저장

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);

                    startActivity(intent);

                    finish();
                }
            }
        });

        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact3.getText().toString() == "저장하기"){
                    btn_Click(3);
                }else {
                    SharedPreferences sf = getSharedPreferences("Uinfo", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sf.edit();

                    String Tname = takename3.getText().toString();
                    String Tnumber = takenumber3.getText().toString();

                    editor.putString("tname", Tname);//tname 키값으로 저장
                    editor.putString("tnumber", Tnumber);//tnumber 키값으로 저장

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), UserInfo.class);

                    startActivity(intent);

                    finish();
                }
            }
        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf = getSharedPreferences("Contact", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();

                editor.remove("tname1");
                editor.remove("tnumber1");

                editor.commit();

                takename1.setText("저장 기록이 없습니다.");
                takenumber1.setText("저장 기록이 없습니다.");
                delete1.setVisibility(View.INVISIBLE);
                contact1.setText("저장하기");
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf = getSharedPreferences("Contact", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();

                editor.remove("tname2");
                editor.remove("tnumber2");

                editor.commit();

                takename2.setText("저장 기록이 없습니다.");
                takenumber2.setText("저장 기록이 없습니다.");
                delete2.setVisibility(View.INVISIBLE);
                contact2.setText("저장하기");
            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf = getSharedPreferences("Contact", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();

                editor.remove("tname3");
                editor.remove("tnumber3");

                editor.commit();

                takename3.setText("저장 기록이 없습니다.");
                takenumber3.setText("저장 기록이 없습니다.");
                delete3.setVisibility(View.INVISIBLE);
                contact3.setText("저장하기");
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);

                startActivity(intent);

                finish();
            }
        });


    }

    private void btn_Click(int number){

            Intent intent = new Intent(getApplicationContext(), ContactSave.class);
            intent.putExtra("number", number);
            startActivity(intent);

            finish();

        }

    }

