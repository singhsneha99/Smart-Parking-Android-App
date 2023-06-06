package com.example.mehtab.smartparking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ADMINDASHBOARD extends AppCompatActivity {
    private static Button btn;
    private static Button btn1;
    private static Button btn2;
    private static Button btn3;
    private static Button btn4;
    DatabaseHelper2 mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        //onClickButtonListner();
        onClickButtonListner1();
        onClickButtonListner2();
        onClickButtonListner3();
        onClickButtonListner4();
        btn = (Button) findViewById(R.id.viewfbtn);
        mydb = new DatabaseHelper2(this);
        viewAll1();
    }

    public void onClickButtonListner1() {
        btn1 = (Button) findViewById(R.id.cancelbtn);
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".ADMINCANCEL");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListner2() {
        btn2= (Button) findViewById(R.id.checkbtn);
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".CHECKOUT");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListner3() {
        btn3= (Button) findViewById(R.id.logoutbtn);
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".ADMINLOGIN");
                        startActivity(intent);
                        finishAndRemoveTask();
                    }
                }
        );
    }
    public void onClickButtonListner4() {
        btn4= (Button) findViewById(R.id.addslotbtn);
        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".addslots");
                        startActivity(intent);

                    }
                }
        );
    }
    public void viewAll1(){
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=mydb.getFeedback();
                        if(res.getCount()==0){
                            showmsg1("Error","No feedback found.");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Booking ID:"+res.getString(0)+"\n");
                            buffer.append("Feedback:"+res.getString(1)+"\n");
                            buffer.append("Parking ID:"+res.getString(2)+"\n\n\n");

                        }
                        showmsg1("Feedback Details",buffer.toString());
                    }
                }
        );
    }
    public void showmsg1(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

}
