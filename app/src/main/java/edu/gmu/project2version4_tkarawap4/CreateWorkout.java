package edu.gmu.project2version4_tkarawap4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class CreateWorkout extends AppCompatActivity {

    EditText exerciseName, numReps, numSets, weight, notes;
    Button addData, viewData, cancel, testDelete, update;
    DatabaseOpenHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        exerciseName = findViewById(R.id.exerciseName);
        numReps = findViewById(R.id.numReps);
        numSets = findViewById(R.id.numSets);
        weight = findViewById(R.id.weight);
        notes = findViewById(R.id.notes);
        addData = findViewById(R.id.addToWorkout);
        viewData = findViewById(R.id.viewData);
        cancel = findViewById(R.id.cancel);
        testDelete = findViewById(R.id.testDelete);
        update = findViewById(R.id.update);
        DB = new DatabaseOpenHelper(this);

        addData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String nameText = exerciseName.getText().toString();
                String numRepsText = numReps.getText().toString();
                String numSetsText = numSets.getText().toString();
                String weightText = weight.getText().toString();
                String notesText = notes.getText().toString();

                Boolean checkInsert = DB.insertData(nameText,numRepsText,numSetsText,weightText,notesText);
                if(checkInsert == true) {
                    Toast.makeText(CreateWorkout.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CreateWorkout.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }

        });

        testDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = exerciseName.getText().toString();
                Boolean checkDelete = DB.deleteData(nameText);
                if(checkDelete == true) {
                    Toast.makeText(CreateWorkout.this, "Entry is deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateWorkout.this, "Entry is not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor pointData = DB.getData();
                if(pointData.getCount() == 0) {
                    Toast.makeText(CreateWorkout.this, "No Data Found", Toast.LENGTH_LONG).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateWorkout.this);
                builder.setCancelable(true);
                builder.setTitle("Workout Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = exerciseName.getText().toString();
                String repsText = numReps.getText().toString();
                String setsText = numSets.getText().toString();
                String weightText = weight.getText().toString();
                String notesText = notes.getText().toString();

                Boolean checkUpdate = DB.updateData(nameText,repsText,setsText,weightText,notesText);
                if(checkUpdate==true)
                    Toast.makeText(CreateWorkout.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CreateWorkout.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}