package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addslots extends AppCompatActivity {
    DatabaseHelper2 mydb;
    private static Button btn;
    EditText loc,reg,pid,slot;
    Button btnadd;
    int pidd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addslots);
        onClickButtonListner();

        mydb = new DatabaseHelper2(this);
        loc = (EditText) findViewById(R.id.editloc);
        reg = (EditText) findViewById(R.id.editreg);
        pid = (EditText) findViewById(R.id.editpid);
        slot = (EditText) findViewById(R.id.edtslot);
        btnadd = (Button) findViewById(R.id.slotbtn);




        loc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    reg.setEnabled(false);
                } else
                    reg.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    pid.setEnabled(false);
                } else
                    pid.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    slot.setEnabled(false);
                } else
                    slot.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        slot.addTextChangedListener(new TextWatcher() {
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
    }

    public void onClickButtonListner() {
        btnadd = (Button) findViewById(R.id.slotbtn);
        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pidd=Integer.parseInt(pid.getText().toString());
                        mydb.addvalues(loc.getText().toString(),reg.getText().toString(),pidd,slot.getText().toString());
                        Intent intent = new Intent(".ADMINDASHBOARD");
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

}
