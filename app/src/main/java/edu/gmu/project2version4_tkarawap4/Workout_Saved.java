package edu.gmu.project2version4_tkarawap4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Workout_Saved extends AppCompatActivity {

    private ListView workoutSavedView;
    private ArrayAdapter myAdapter;
    private EditText exerciseName;
    private Button viewAll;
    private ListAdapter listAdapter;
    private DatabaseOpenHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_saved);

        exerciseName = findViewById(R.id.exerciseName);
        workoutSavedView = findViewById(R.id.myWorkoutList);
        viewAll = findViewById(R.id.viewAll);
        dbHelper = new DatabaseOpenHelper(this);

        myAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);
        workoutSavedView.setAdapter(myAdapter);
        workoutSavedView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        workoutSavedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();

            }
        });

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = dbHelper.getData();
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(0));
                listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                workoutSavedView.setAdapter(listAdapter);
            }
        }

        workoutSavedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectText = (String) (workoutSavedView.getItemAtPosition(position));
                String myString = selectText;
                if(selectText == null){
                    myString ="";
                    Toast.makeText(Workout_Saved.this, "ExerciseName is null", Toast.LENGTH_SHORT).show();
                }


                else {
                    Boolean checkDelete = dbHelper.deleteData(myString);
                    if (checkDelete == true) {
                        //myAdapter.notifyDataSetChanged();
                        //workoutSavedView.setAdapter(myAdapter);

                        Toast.makeText(Workout_Saved.this, "Entry is deleted", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Workout_Saved.this, "Entry is not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor pointData = dbHelper.getData();
                if(pointData.getCount() == 0) {
                    Toast.makeText(Workout_Saved.this, "No Data Found", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(pointData.moveToNext()) {
                    buffer.append("Exercise Name: " + pointData.getString(0) + "\n");
                    buffer.append("Number Reps: " + pointData.getString(1) + "\n");
                    buffer.append("Number Sets: " + pointData.getString(2) + "\n");
                    buffer.append("Weight: " + pointData.getString(3) + "\n");
                    buffer.append("Notes: " + pointData.getString(4) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Workout_Saved.this);
                builder.setCancelable(true);
                builder.setTitle("Workout Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}