package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class CHECKOUT extends AppCompatActivity {
    DatabaseHelper2 mydb;
    private static Button btn;
    private TextView displayTime;
    private Button pickTime;
    public String bookid;
    private int pHour1;
    private int pMinute1;
    public int outtime;
    static final int TIME_DIALOG_ID = 0;
    EditText editbid;

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour1 = hourOfDay;
                    pMinute1 = minute;
                    updateDisplay();
                    displayToast();
                }
            };
    private void updateDisplay() {
        displayTime.setText(
                new StringBuilder()
                        .append(pad(pHour1)).append(":")
                        .append(pad(pMinute1)));
    }
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(displayTime.getText()),   Toast.LENGTH_SHORT).show();

    }
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        onClickButtonListner();
        displayTime = (TextView) findViewById(R.id.dtime);
        pickTime = (Button) findViewById(R.id.ptime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
        final Calendar cal = Calendar.getInstance();
        pHour1 = cal.get(Calendar.HOUR_OF_DAY);
        pMinute1 = cal.get(Calendar.MINUTE);
        updateDisplay();
        outtime= pHour1*60+pMinute1;
        mydb = new DatabaseHelper2(this);
        editbid=(EditText) findViewById(R.id.bidd);
        bookid= editbid.getText().toString();

    }
    public void onClickButtonListner() {

        btn= (Button) findViewById(R.id.sub);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mydb.fare(editbid.getText().toString(),outtime);
                        Intent intent = new Intent(".ADMINDASHBOARD");
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour1, pMinute1, false);
        }
        return null;
    }
}
