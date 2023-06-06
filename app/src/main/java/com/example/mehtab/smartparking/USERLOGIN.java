package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class USERLOGIN extends AppCompatActivity {
    DatabaseHelper2 mydb;
    private static Button btn;
    private static Button btn1;
    EditText editid,editpwd;
    Button btnsub,btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        onClickButtonListner();
        onClickButtonListner1();
        mydb = new DatabaseHelper2(this);
        editid = (EditText) findViewById(R.id.ed1);
        editpwd = (EditText) findViewById(R.id.ed2);
        btnsub = (Button) findViewById(R.id.button);
        btnreg=(Button)findViewById(R.id.button2);
        editpwd.setEnabled(false);
        btnsub.setEnabled(false);
        btnreg.setEnabled(false);
        String idid=editid.getText().toString();
        editid.addTextChangedListener(new TextWatcher() {
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
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean checkid=mydb.chkId(editid.getText().toString());
                        if(checkid==false){
                            Boolean check = mydb.AuthenticateId(editid.getText().toString(),editpwd.getText().toString());
                            if (check == true){
                                //mydb.addvalues();

                                String userid=editid.getText().toString();
                                Intent intent = new Intent(USERLOGIN.this,DASHBOARD.class);
                                intent.putExtra("USERID",userid);
                                startActivity(intent);
                                finishAndRemoveTask();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Incorrect username and password", Toast.LENGTH_SHORT).show();
                                btnreg = (Button) findViewById(R.id.button2);
                                btnreg.setEnabled(true);
                            }
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Not a Registered User", Toast.LENGTH_SHORT).show();
                            btnsub = (Button) findViewById(R.id.button);
                            btnsub.setEnabled(false);
                            btnreg = (Button) findViewById(R.id.button2);
                            btnreg.setEnabled(true);
                        }

                    }
                }
        );
    }

    public void onClickButtonListner1() {
        btn1 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(
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
