package com.example.reminder_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalTime;

import static android.database.DatabaseUtils.longForQuery;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"DB.db",null ,1);
        SQLiteDatabase db =this.getWritableDatabase();
        //Toast.makeText(context ,"Got Database",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Water(goal Integer primary key, current Integer default 0)");
        db.execSQL("create table Break(sTime time,eTime time,intervalTime Integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Water");
        db.execSQL("drop table if exists Break");
    }

    public Boolean insertData(int goal,int current)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        int g= (int) DatabaseUtils.longForQuery(db,"select goal from Water",new String[]{""});
        ContentValues contentValues = new ContentValues();

        contentValues.put("goal",goal);
        contentValues.put("current",current);
        long result = db.insert("Water",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }
    public Boolean updateData(int goal,int current) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //db.execSQL("create table Water(goal int primary key, current int default 0)");
        Cursor c= db.rawQuery("select * from Water",null);
        if(c.getCount()>0){


                contentValues.put("goal",goal);
                //int currentSet = (int) DatabaseUtils.longForQuery(db, "select current from Water", new String[]{"current"});
                c.moveToFirst();
                int currentSet = c.getInt(c.getColumnIndex("current"));
                contentValues.put("current", current + currentSet);
                long result = db.update("Water", contentValues, "goal=?", new String[]{"goal"});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }}
        else{
            contentValues.put("goal",goal);
            contentValues.put("current",current);
            long result = db.insert("Water",null,contentValues);
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }
            }



        public Cursor getData ()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            //int goalSet = (int) DatabaseUtils.longForQuery(db, "select goal from Water", new String[]{""});
            Cursor goalCurrent = db.rawQuery("select * from Water", null);
            return goalCurrent;

        }
        public int getGoal()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            //int presentGoalSet = (int) DatabaseUtils.longForQuery(db, "select goal from Water", new String[]{"goal"});
            Cursor goalCurrent = db.rawQuery("select * from Water", null);
            goalCurrent.moveToFirst();
            int presentGoalSet = goalCurrent.getInt(goalCurrent.getColumnIndex("goal"));
            return presentGoalSet;
        }








    public int updateDataBreak(long sTime, long eTime, int intervalTime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //db.execSQL("create table Water(goal int primary key, current int default 0)");
        Cursor c= db.rawQuery("select * from Break",null);
        if(c.getCount()>0) {

            db.execSQL("update break set sTime = " + sTime + ", eTime=" + eTime + ",intervalTime=" + intervalTime + ")");
            return 1;
            /*contentValues.put("sTime",sTime);
            //int currentSet = (int) DatabaseUtils.longForQuery(db, "select current from Water", new String[]{"current"});
            c.moveToFirst();
            //int currentSet = c.getInt(c.getColumnIndex("current"));
            contentValues.put("eTime",eTime);
            long result = db.update("Water", contentValues, "goal=?", new String[]{"goal"});*/
        }
        else if(c.getCount()==0){
            /*contentValues.put("goal",goal);
            contentValues.put("current",current);
            long result = db.insert("Water",null,contentValues);
            if(result==-1){
                return false;
            }
            else{
                return true;
            }*/
             db.execSQL("insert into Break values("+sTime+","+eTime+","+intervalTime+")");
             return 2;
        }
        else{
            return 3;
        }
    }



    public Cursor getDataBreak ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //int goalSet = (int) DatabaseUtils.longForQuery(db, "select goal from Water", new String[]{""});
        Cursor currentBreak = db.rawQuery("select * from Break", null);
        return currentBreak;

    }
    public long getSTimeBreak()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //int presentGoalSet = (int) DatabaseUtils.longForQuery(db, "select goal from Water", new String[]{"goal"});
        Cursor currentBreak = db.rawQuery("select * from Break", null);
        currentBreak.moveToFirst();
        long s = currentBreak.getLong(currentBreak.getColumnIndex("sTime"));
        return s;
    }
    public long getEtimeBreak()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //int presentGoalSet = (int) DatabaseUtils.longForQuery(db, "select goal from Water", new String[]{"goal"});
        Cursor currentBreak = db.rawQuery("select * from Break", null);
        currentBreak.moveToFirst();
        long e = currentBreak.getLong(currentBreak.getColumnIndex("eTime"));
        return e;
    }
    public int getItimeBreak()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //int presentGoalSet = (int) DatabaseUtils.longForQuery(db, "select goal from Water", new String[]{"goal"});
        Cursor currentBreak = db.rawQuery("select * from Break", null);
        currentBreak.moveToFirst();
        int i = currentBreak.getInt(currentBreak.getColumnIndex("intervalTime"));
        return i;
    }
    }