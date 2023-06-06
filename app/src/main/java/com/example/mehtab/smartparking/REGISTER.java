package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class REGISTER extends AppCompatActivity {
    DatabaseHelper2 mydb;
    private static Button btn;
    EditText editid,editname,editphone,editemail,editpwd;
    Button btnadd,subutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        onClickButtonListner();
        mydb = new DatabaseHelper2(this);
        editid = (EditText) findViewById(R.id.e1);
        editname = (EditText) findViewById(R.id.e2);
        editphone = (EditText) findViewById(R.id.e3);
        editemail = (EditText) findViewById(R.id.e4);
        editpwd = (EditText) findViewById(R.id.e5);
        btnadd = (Button) findViewById(R.id.subbtn);
        subutton = (Button) findViewById(R.id.proceedbtn);
        editname.setEnabled(false);
        editphone.setEnabled(false);
        editemail.setEnabled(false);
        editpwd.setEnabled(false);
        btnadd.setEnabled(false);
        subutton.setEnabled(false);

        editid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    editname.setEnabled(false);
                } else
                    editname.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    editphone.setEnabled(false);
                } else
                    editphone.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    editemail.setEnabled(false);
                } else
                    editemail.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    editpwd.setEnabled(false);
                } else
                    editpwd.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    btnadd.setEnabled(false);
                } else
                    btnadd.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    btnadd.setEnabled(false);
                } else
                    btnadd.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        AddData();
    }



    public void AddData(){
               btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean checkid = mydb.chkId(editid.getText().toString());
                        if (checkid == true) {
                            boolean isInserted = mydb.insertData(editid.getText().toString(), editname.getText().toString(), editphone.getText().toString(), editemail.getText().toString(),editpwd.getText().toString());
                            if (isInserted = true)
                                Toast.makeText(REGISTER.this, "Data inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(REGISTER.this, "Data not inserted", Toast.LENGTH_LONG).show();
                            btnadd = (Button) findViewById(R.id.subbtn);
                            btnadd.setEnabled(false);
                            subutton = (Button) findViewById(R.id.proceedbtn);
                            subutton.setEnabled(true);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "User id already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }


    public void onClickButtonListner() {
        btn = (Button) findViewById(R.id.proceedbtn);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".USERLOGIN");
                        startActivity(intent);
                    }
                }
        );
    }
}

