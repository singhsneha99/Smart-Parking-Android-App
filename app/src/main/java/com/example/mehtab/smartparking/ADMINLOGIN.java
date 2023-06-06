package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class ADMINLOGIN extends AppCompatActivity {
    DatabaseHelper2 mydb;
    private static Button btn;
    EditText editaid,editapwd;
    Button btnsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        onClickButtonListner();
        mydb = new DatabaseHelper2(this);
        editaid = (EditText) findViewById(R.id.edt1);
        editapwd = (EditText) findViewById(R.id.edt2);
        btnsub = (Button) findViewById(R.id.admindashbtn);
        editapwd.setEnabled(false);
        btnsub.setEnabled(false);
        editaid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    editapwd.setEnabled(false);
                } else
                    editapwd.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editapwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    btnsub.setEnabled(false);
                } else
                    btnsub.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onClickButtonListner() {
        btn = (Button) findViewById(R.id.admindashbtn);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // mydb.addNewTable();
                        Boolean checkid = mydb.chkAdminId(editaid.getText().toString());
                        if(checkid==false){
                           Boolean check = mydb.AuthenticateAdminId(editaid.getText().toString(),editapwd.getText().toString());
                           if (check == true){
                                Intent intent = new Intent(".ADMINDASHBOARD");
                                startActivity(intent);
                           }
                           else{
                               Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                           }

                        }
                        else{
                         Toast.makeText(getApplicationContext(), "Invalid Admin Access", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}