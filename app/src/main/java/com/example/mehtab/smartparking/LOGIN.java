package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LOGIN extends AppCompatActivity {
    private static Button btn;
    private static Button btn1;
    private static Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onClickButtonListner();
        onClickButtonListner1();
        onClickButtonListner2();
    }
    public void onClickButtonListner() {
        btn= (Button) findViewById(R.id.adminbtn);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".ADMINLOGIN");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickButtonListner1() {
        btn1 = (Button) findViewById(R.id.userbtn);
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".USERLOGIN");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickButtonListner2() {
        btn2 = (Button) findViewById(R.id.newbtn);
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".REGISTER");
                        startActivity(intent);
                    }
                }
        );
    }

    }

