package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ADMINCANCEL extends AppCompatActivity {
    private static Button btn;
    EditText edtcancel;
    DatabaseHelper2 mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincancel);
        onClickButtonListner();
        edtcancel=(EditText) findViewById(R.id.adcanedt);
        mydb = new DatabaseHelper2(this);
    }
    public void onClickButtonListner() {
        btn = (Button) findViewById(R.id.submitt);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean status=mydb.cancelbooking(edtcancel.getText().toString());
                        if (status==false){
                            Toast.makeText(getApplicationContext(), "INVALID BOOKING ID", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "BOOKING CANCELLED", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(".ADMINDASHBOARD");
                            startActivity(intent);
                            finish();
                        }
                    }
                }
        );
    }
}
