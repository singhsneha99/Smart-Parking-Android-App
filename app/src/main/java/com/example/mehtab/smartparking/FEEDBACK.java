package com.example.mehtab.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FEEDBACK extends AppCompatActivity {
    private static Button btn;
    EditText editfeed,editbid,editpid;
    Button btnFeed;
    DatabaseHelper2 mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        onClickButtonListner();
        editfeed = (EditText) findViewById(R.id.feedbacktext);
        editbid = (EditText) findViewById(R.id.inbid);
        editpid = (EditText) findViewById(R.id.inpid);
        btnFeed = (Button) findViewById(R.id.submt);
        mydb = new DatabaseHelper2(this);
        AddData();

    }
    public void onClickButtonListner() {
        btn= (Button) findViewById(R.id.submt);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(".DASHBOARD");
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }
    public void AddData(){
        btnFeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = mydb.insertDatafeedback(editbid.getText().toString(), editpid.getText().toString(), editfeed.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(FEEDBACK.this, "Feedback entered successfully", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(FEEDBACK.this, "Feedback could not be entered", Toast.LENGTH_LONG).show();

                    }   }
        );
    }
}
