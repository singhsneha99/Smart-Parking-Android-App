package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CANCELBOOKING extends AppCompatActivity {
    private static Button btn;
    EditText edtcan;
    DatabaseHelper2 mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelbooking);
        onClickButtonListner();
        edtcan=(EditText) findViewById(R.id.canid);
        mydb = new DatabaseHelper2(this);
    }
    public void onClickButtonListner() {
        btn= (Button) findViewById(R.id.subm);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean status=mydb.cancelbooking(edtcan.getText().toString());
                        if (status==false){
                            Toast.makeText(getApplicationContext(), "INVALID BOOKING ID", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "BOOKING CANCELLED", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(".DASHBOARD");
                            startActivity(intent);
                            finish();
                        }

                    }
                }
        );
    }
}
