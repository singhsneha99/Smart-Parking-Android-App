package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FARE extends AppCompatActivity {
    DatabaseHelper2 mydb;
    private static Button btn,btnfare;
    EditText edtbid;
    TextView txtfare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);
        edtbid=(EditText) findViewById(R.id.editbid);

        onClickButtonListner();
        onClickButtonListner1();
        mydb = new DatabaseHelper2(this);
        btn = (Button) findViewById(R.id.donebutton);
        btn.setEnabled(false);
    }
    public void onClickButtonListner() {
        txtfare=(TextView) findViewById(R.id.faretext);
        btnfare= (Button) findViewById(R.id.farebtn);
        btnfare.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Double fare = mydb.displayfare(edtbid.getText().toString());
                        if (fare == 0) {


                            Toast.makeText(getApplicationContext(), "You are not checked out yet ", Toast.LENGTH_SHORT).show();
                        } else {
                            btnfare.setEnabled(false);
                            btn.setEnabled(true);
                            txtfare.setText(String.valueOf(fare));
                        }
                    }
                }
        );
    }
    public void onClickButtonListner1(){
        btn = (Button) findViewById(R.id.donebutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "THANK YOU", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(".DASHBOARD");
                startActivity(intent);
                finish();
            }
        });
    }
}
