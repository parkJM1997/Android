package com.example.speed_helper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ContactSave extends AppCompatActivity {
    private Button update, modifyCan, resetuser;
    private EditText infousername, infotakename, infotakenumber;
    private TextView infousernametext;
    int number;
    String username, takename, takenumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Intent intent = getIntent();
        number = intent.getIntExtra("number", 0);

        takename = "tname" + number;
        takenumber = "tnumber" + number;

        update = (Button) findViewById(R.id.update);
        modifyCan = (Button) findViewById(R.id.modifyCan);
        resetuser = (Button) findViewById(R.id.resetuser);
        infousername = (EditText) findViewById(R.id.infousername);
        infotakename = (EditText) findViewById(R.id.infotakename);
        infotakenumber = (EditText) findViewById(R.id.infotakenumber);
        infousernametext = (TextView) findViewById(R.id.infounserametext);

        infousernametext.setVisibility(View.INVISIBLE);
        infousername.setVisibility(View.INVISIBLE);

        update.setText("저장");
        modifyCan.setText("취소");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(infotakenumber.getText().length() < 12) {


                    //editText에서 아이디와 암호 가져와 PreferenceManager에 저장한다.
                    SharedPreferences sf = getSharedPreferences("Contact", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sf.edit();

                    String Tname = infotakename.getText().toString();
                    String Tnumber = infotakenumber.getText().toString();

                    editor.putString(takename, Tname);//tname 키값으로 저장
                    editor.putString(takenumber, Tnumber);//tnumber 키값으로 저장

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), Contact.class);

                    startActivity(intent);

                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ContactSave.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactSave.this);
                builder.setTitle("알림");
                builder.setMessage("정말 초기화 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        infousername.setText("");
                        infotakenumber.setText("");
                        infotakename.setText("");
                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.create().show();
            }
        });
    }
}
