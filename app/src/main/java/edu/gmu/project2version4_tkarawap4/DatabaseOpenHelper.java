package edu.gmu.project2version4_tkarawap4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    final private static Integer VERSION = 1;
    final private Context context;


    public DatabaseOpenHelper(Context context) {
        super(context, "WorkoutData.db", null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table WorkoutDetails(exerciseName TEXT primary key, numReps TEXT, numSets TEXT, weight TEXT, notes TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists WorkoutDetails");
    }

    public Boolean insertData(String exerciseName, String numReps, String numSets, String weight, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("exerciseName",exerciseName);
        contentValues.put("numReps",numReps);
        contentValues.put("numSets",numSets);
        contentValues.put("weight",weight);
        contentValues.put("notes",notes);

        long result=db.insert("WorkoutDetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean deleteData(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from WorkoutDetails where exerciseName =?", new String[]{name});

        if(cursor.getCount() > 0) {
            long result = DB.delete("WorkoutDetails", "exerciseName=?", new String[]{name});
            if(result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean updateData(String exerciseName, String numReps, String numSets, String weight, String notes) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("exerciseName",exerciseName);
        contentValues.put("numReps",numReps);
        contentValues.put("numSets",numSets);
        contentValues.put("weight",weight);
        contentValues.put("notes",notes);

        Cursor cursor = DB.rawQuery("Select * from WorkoutDetails where exerciseName =?", new String[]{exerciseName});
        if (cursor.getCount() > 0) {
            long result = DB.update("WorkoutDetails", contentValues, "exerciseName=?", new String[]{exerciseName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }



    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from WorkoutDetails", null);
        return cursor;
    }
}
