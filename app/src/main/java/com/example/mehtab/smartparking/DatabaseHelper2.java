package com.example.mehtab.smartparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.Date;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "car_details.db";
    public static final String TABLE1 = "users";
    public static final String col11 = "user_id";
    public static final String col12 = "user_name";
    public static final String col13 = "user_phone";
    public static final String col14 = "user_email";
    public static final String col15 = "user_pwd";


    public static final String TABLE2 = "bookings";
    public static final String col21 = "bid";
    public static final String col22 = "user_id";
    public static final String col23 = "date";
    public static final String col24 = "location";
    public static final String col25 = "region";
    public static final String col26 = "intime";
    public static final String col27 = "pid";

    public static final String TABLE3 = "parking_slots";
    public static final String col31 = "location";
    public static final String col32 = "region";
    public static final String col33 = "pid";
    public static final String col34="slot";

    public static final String TABLE4 = "feedback";
    public static final String col41 = "bid";
    public static final String col42 = "feedback";
    public static final String col43="pid";

    public static final String TABLE5="fare";
    public static final String col51="bid";
    public static final String col52="intime";
    public static final String col53="outtime";
    public static final String col54="fare";

    public static final String TABLE6 = "admin";
    public static final String col61 = "admin_id";
    public static final String col62 = "admin_pwd";
    String slott;
    public int index;
    public Integer pidd;


    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users (user_id VARCHAR(20)PRIMARY KEY not null,user_name TEXT not null,user_phone INTEGER not null,user_email TEXT not null,user_pwd varchar(20) not null)");
        db.execSQL("create table if not exists  bookings (bid INTEGER PRIMARY KEY AUTOINCREMENT,user_id VARCHAR(20),date date,location TEXT, region TEXt, intime time,foreign key(user_id) references users(user_id) )");
        db.execSQL("create table if not exists  parking_slots(location TEXT, region TEXT, pid INTEGER primary key not null,bid integer,foreign key(bid) references bookings(bid))");
        db.execSQL("create table if not exists feedback(bid INTEGER ,feedback TEXT,pid INTEGER,foreign key(bid) references bookings(bid),foreign key(pid) references parking_slots(pid))");
        db.execSQL("create table if not exists  fare(bid INTEGER, intime TIME, outtime TIME,fare INTEGER,foreign key(bid) references bookings(bid))");
        db.execSQL("create table if not exists  admin(admin_id INTEGER primary key not null,admin_pwd TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public boolean insertData(String id, String name, String phone, String email, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col11,id);
        contentValues.put(col12,name);
        contentValues.put(col13,phone);
        contentValues.put(col14,email);
        contentValues.put(col15,pwd);

        long result = db.insert(TABLE1,null,contentValues);
        if(result==-1)
            return true;
        else
            return false;
    }

    public Boolean chkId(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where user_id=?",new String[]{id});
        if(cursor.getCount()>0){
            return false;
        }
        else
            return true;
    }

    public Boolean AuthenticateId(String id,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where user_id=? and user_pwd=?",new String[]{id,password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    public Boolean chkAdminId(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from admin where admin_id=?",new String[]{id});
        if(cursor.getCount()>0){
            return false;
        }
        else
            return true;
    }
    public Boolean AuthenticateAdminId(String id,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from admin where admin_id=? and admin_pwd=?",new String[]{id,password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    public Integer chkslot(String loc,String reg){
        String slot;
        //Integer pid=0;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select pid,slot from parking_slots where location=? and region=?",new String[]{loc,reg});
        while (cursor.moveToNext()){

            index=cursor.getColumnIndexOrThrow("slot");
            slott=cursor.getString(index);
            if(slott.equals("E")){
                index=cursor.getColumnIndexOrThrow("pid");
                pidd=cursor.getInt(index);
                //pid=cursor.getInt(cursor.getColumnIndex("pid"));
                slott="F";
                //db1.execSQL("replace into parking_slots (slot) values('F') where pid=?",new Integer[]{pidd});
                db1.execSQL("update parking_slots set slot='F' where pid=?",new Integer[]{pidd});
                //insertslot(loc,reg,pidd,slott);
                //return cursor.getInt(cursor.getColumnIndex("pid"));
                break;
            }
            else{
                pidd=0;
            }

        }
        cursor.close();
        return pidd;
    }

    public void insertslot(String loc,String reg,Integer pid,String slot){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col31,loc);
        contentValues.put(col32,reg);
        contentValues.put(col33,pid);
        contentValues.put(col34,slot);

        long result = db.insert(TABLE3,null,contentValues);

    }

    public void insertbook(String uid, String date,String loc,String reg,String time,Integer pid){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db1 = this.getReadableDatabase();
        //Integer id=pid;
        //insertfare(id);
        ContentValues contentValues=new ContentValues();
        contentValues.put(col22,uid);
        contentValues.put(col23,date);
        contentValues.put(col24,loc);
        contentValues.put(col25,reg);
        contentValues.put(col26,time);
        contentValues.put(col27,pid);
        db.insert(TABLE2,null,contentValues);
    }

    public void insertfare(Integer id,Integer intm){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db1 = this.getWritableDatabase();
        Integer idx,bkid;
        Integer time;
        Cursor cursor=db.rawQuery("select bid from bookings where pid=?",new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            idx = cursor.getColumnIndexOrThrow("bid");
            bkid = cursor.getInt(idx);
            time = intm;
            db1.execSQL("insert into fare(bid,intime) values ("+bkid+","+time+")");
            //db1.execSQL("insert into fare(bid,intime,outtime,fare) values ("+bkid+","+time+",0,0)");
        }

        cursor.close();

    }


    public void fare(String id,Integer time){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db1 = this.getReadableDatabase();
        Integer indx,itime,otime,pd;
        double tfare;

        db.execSQL("update fare set outtime="+time+" where bid=?",new String[]{id});
        Cursor cursor = db1.rawQuery("select intime,outtime from fare where bid=?",new String[]{id});
        Cursor cursor1 = db1.rawQuery("select pid from bookings where bid=?",new String[]{id});
        while (cursor.moveToNext()) {
            indx = cursor.getColumnIndexOrThrow("intime");
            itime = cursor.getInt(indx);
            indx = cursor.getColumnIndexOrThrow("outtime");
            otime = cursor.getInt(indx);
            tfare = (otime - itime) * 0.5;
            db.execSQL("update fare set fare="+tfare+" where bid=?",new String[]{id});
            while (cursor1.moveToNext()){
                indx = cursor1.getColumnIndexOrThrow("pid");
                pd = cursor1.getInt(indx);
                db.execSQL("update parking_slots set slot='E' where pid="+pd);
            }
        }


        cursor.close();

    }

    public double displayfare(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Integer indx;
        double f=0;
        Cursor cursor=db.rawQuery("select fare from fare where bid=?",new String[]{id});
        while (cursor.moveToNext()){
            indx = cursor.getColumnIndexOrThrow("fare");
            f = cursor.getInt(indx);
        }
        cursor.close();
        return f;

    }

    public boolean cancelbooking(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db1 = this.getReadableDatabase();
        Integer indx,pid;
        boolean status=false;
        Cursor cursor=db.rawQuery("select pid from bookings where bid=?",new String[]{id});
        while (cursor.moveToNext()){
            indx = cursor.getColumnIndexOrThrow("pid");
            pid=cursor.getInt(indx);

            if(pid==0){
                status=false;
            }
            else {
                db.execSQL("Delete from bookings where bid=?",new String[]{id});
                db.execSQL("Delete from fare where bid=?",new String[]{id});
                db.execSQL("update parking_slots set slot='E' where pid="+pid);
                status=true;
            }

        }
        return status;

    }


    /*public void addNewTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists "+TABLE2);
        //db.execSQL("drop table if exists "+TABLE3);
        //db.execSQL("drop table if exists "+TABLE5);
        db.execSQL("create table if not exists  bookings (bid INTEGER PRIMARY KEY AUTOINCREMENT,user_id VARCHAR(20),date varchar,location TEXT, region TEXT, intime varchar(10),pid integer,foreign key(user_id) references users(user_id) )");
        //db.execSQL("create table if not exists  parking_slots(location TEXT, region TEXT, pid INTEGER,slot varchar)");
        //db.execSQL("create table if not exists  fare(bid INTEGER, intime integer, outtime integer,fare INTEGER,foreign key(bid) references bookings(bid))");
        /*db.execSQL("drop table if exists admin");
        db.execSQL("create table if not exists admin( admin_id VARCHAR(20) PRIMARY KEY not null ,admin_pwd VARCHAR(20))");
        db.execSQL("insert into " + TABLE6 + " values ('abc101','abc1')");
        db.execSQL("insert into " + TABLE6 + " values ('abc102','abc2')");
        db.execSQL("insert into " + TABLE6 + " values ('abc103','abc3')");
        db.execSQL("insert into " + TABLE6 + " values ('abc104','abc4')");*/
    //}
    public boolean addvalues(String location, String region, Integer pid, String slot) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col31,location);
        contentValues.put(col32,region);
        contentValues.put(col33,pid);
        contentValues.put(col34,slot);

        long result = db.insert(TABLE1,null,contentValues);
        if(result==-1)
            return true;
        else
            return false;
    }


    public boolean insertDatafeedback(String bid, String pid, String feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer bd,pd;
        bd=Integer.parseInt(bid);
        pd=Integer.parseInt(pid);
        ContentValues contentValues=new ContentValues();
        contentValues.put(col41,bd);
        contentValues.put(col42,feedback);
        contentValues.put(col43,pd);

        long result = db.insert(TABLE4,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public void addslots(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists "+TABLE3);
        db.execSQL("create table if not exists  parking_slots(location TEXT, region TEXT, pid INTEGER,slot varchar(10))");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','INORBIT',1101,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','INORBIT',1102,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','INORBIT',1103,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','INORBIT',1104,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','FCRIT',1201,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','FCRIT',1202,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','FCRIT',1203,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('VASHI','FCRIT',1204,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','CENTRAL PARK',2101,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','CENTRAL PARK',2102,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','CENTRAL PARK',2103,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','CENTRAL PARK',2104,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','GLOMAX MALL',2201,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','GLOMAX MALL',2202,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','GLOMAX MALL',2203,'E')");
        db.execSQL("insert into " + TABLE3 + " values ('KHARGHAR','GLOMAX MALL',2204,'E')");
    }

    public Cursor getBookData(String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from bookings where user_id=? ",new String[]{uid});
        return res;

    }
    public Cursor getFeedback(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from feedback" ,null);
        return res;

    }
}
