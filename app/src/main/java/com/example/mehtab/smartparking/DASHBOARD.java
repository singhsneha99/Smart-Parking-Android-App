package com.example.mehtab.smartparking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DASHBOARD extends AppCompatActivity {
    private static Button btn1;
    private static Button btn2;
    private static Button btn3;
    private static Button btn4;
    private static Button btn5;
    private static Button btn6;
    DatabaseHelper2 mydb;
    String userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        onClickButtonListner1();
        onClickButtonListner2();
        onClickButtonListner3();
        onClickButtonListner4();
        onClickButtonListner5();
        btn2 = (Button) findViewById(R.id.viewbookbtn);
        mydb = new DatabaseHelper2(this);
        viewAll();
        userid=getIntent().getStringExtra("USERID");


    }
    public void onClickButtonListner1() {

        btn1 = (Button) findViewById(R.id.newbookbtn);
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // mydb.addslots();
                        Intent intent = new Intent(DASHBOARD.this,BOOKING.class);
                        intent.putExtra("USERID",userid);
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListner2() {
        btn6 = (Button) findViewById(R.id.payfarebtn);
        btn6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".FARE");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListner3() {
        btn3 = (Button) findViewById(R.id.feedbackbtn);
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".FEEDBACK");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListner4() {
        btn4 = (Button) findViewById(R.id.canbookbtn);
        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".CANCELBOOKING");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListner5() {
        btn5 = (Button) findViewById(R.id.logoutbtn);
        btn5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".USERLOGIN");
                        startActivity(intent);
                        finishAndRemoveTask();
                    }
                }
        );
    }
    public void viewAll(){
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=mydb.getBookData(userid);
                        if(res.getCount()==0){
                            showmsg("Error","No data found. Please make a booking first.");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Booking ID:"+res.getString(0)+"\n");
                            buffer.append("User ID:"+res.getString(1)+"\n");
                            buffer.append("Date:"+res.getString(2)+"\n");
                            buffer.append("Time:"+res.getString(5)+"\n");
                            buffer.append("Location:"+res.getString(3)+"\n");
                            buffer.append("Region:"+res.getString(4)+"\n\n\n");
                        }
                        showmsg("Booking Details",buffer.toString());
                    }
                }
        );
    }
    public void showmsg(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
