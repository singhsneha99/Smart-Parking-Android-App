package com.example.mehtab.smartparking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//sneha start
import java.util.Calendar;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.TextView;
import android.widget.TimePicker;
//sneha end


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BOOKING extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper2 mydb;
    private static Button btn;
    Spinner spinner11;
    Spinner spinner22;
    EditText edituid,editdate,edittime;
    public String loc,reg,date,uid;
    public Integer pid=0;
    int time,pos;
    private int intime;

//sneha start
    Context context = this;
    EditText editDate;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date1;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);
    private TextView displayTime;
    private Button pickTime;
    private int pHour;
    private int pMinute;
    static final int TIME_DIALOG_ID = 0;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay();
                    displayToast();
                }
            };
    private void updateDisplay() {
        displayTime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
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
//sneha ends


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        spinner11=(Spinner)findViewById(R.id.spinner1) ;
        spinner22=(Spinner)findViewById(R.id.spinner2);
        edittime=findViewById(R.id.ttime);
        ArrayList<String> list=new ArrayList<>();
        list.add("VASHI");
        list.add("KHARGHAR");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        spinner11.setAdapter(adapter);
        spinner11.setOnItemSelectedListener(this);
        edituid=findViewById(R.id.tid);
        edituid.setText(getIntent().getStringExtra("USERID"));
        //edituid.setText(uid);
        mydb = new DatabaseHelper2(this);



        //sneha start
        editDate = (EditText) findViewById(R.id.tdate);
        long currentdate = System.currentTimeMillis();
        String dateString = sdf.format(currentdate);
        editDate.setText(dateString);
        date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };
        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        displayTime = (TextView) findViewById(R.id.ttime);
        pickTime = (Button)findViewById(R.id.addtime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
        final Calendar cal = Calendar.getInstance();
        pHour = cal.get(Calendar.HOUR_OF_DAY);
        pMinute = cal.get(Calendar.MINUTE);
        updateDisplay();


        onClickButtonListner();
        intime=pHour*60+pMinute;
    }

    //sneha
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }
    //sneha ends

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String sp1=String.valueOf(spinner11.getSelectedItem());
        loc=sp1;
        Toast.makeText(this,sp1, Toast.LENGTH_SHORT).show();
        if(sp1.contentEquals("VASHI")){
            ArrayList<String> list=new ArrayList<String>();
            list.add("INORBIT");
            list.add("FCRIT");
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter.notifyDataSetChanged();
            spinner22.setAdapter(adapter);
            //reg=String.valueOf(spinner22.getSelectedItem());
        }


        //Toast.makeText(this,reg, Toast.LENGTH_SHORT).show();

        if(sp1.contentEquals("KHARGHAR")){
            ArrayList<String> list=new ArrayList<String>();
            list.add("CENTRAL PARK");
            list.add("GLOMAX MALL");
            ArrayAdapter<String> dadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
            dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dadapter.notifyDataSetChanged();
            spinner22.setAdapter(dadapter);
            //reg=String.valueOf(spinner22.getSelectedItem());
        }

        //reg=String.valueOf(spinner22.getSelectedItem());
        //Toast.makeText(this,reg, Toast.LENGTH_SHORT).show();

        spinner22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(pos == 1){
                    reg=String.valueOf(spinner22.getSelectedItem());
                }
                else {
                    reg=String.valueOf(spinner22.getSelectedItem());
                }
                reg=String.valueOf(spinner22.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickButtonListner() {
        btn = (Button) findViewById(R.id.submbtn);
        date=editDate.getText().toString();
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean checkid = mydb.chkId(edituid.getText().toString());
                        if (checkid == false) {

                            pid = mydb.chkslot(loc, reg);
                            if(pid==0){
                              Toast.makeText(getApplicationContext(), "Parking Not Available", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mydb.insertbook(edituid.getText().toString(), date, loc, reg, edittime.getText().toString() , pid);
                                mydb.insertfare(pid,intime);
                                Toast.makeText(getApplicationContext(), "Booking Confirmed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(".DASHBOARD");
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter valid Booking ID", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
    private void updateDate(){
        editDate.setText(sdf.format(myCalendar.getTime()));
    }

}
