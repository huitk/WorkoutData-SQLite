package edu.gmu.project2version4_tkarawap4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateWorkout extends AppCompatActivity {
    private Button update2, cancelUpdate;
    private EditText exerciseName, numReps, numSets, weight, notes ;
    private DatabaseOpenHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_workout);
        cancelUpdate = findViewById(R.id.cancelUpdate);
        exerciseName = findViewById(R.id.exerciseName);
        numReps = findViewById(R.id.numReps);
        numSets = findViewById(R.id.numSets);
        weight = findViewById(R.id.weight);
        notes = findViewById(R.id.notes);
        exerciseName.setEnabled(false);
        update2 = findViewById(R.id.update2);
        dbHelper = new DatabaseOpenHelper(this);

        Intent intent = getIntent();
        setTitle("SetupEditWorkout");
        String myString = intent.getStringExtra("myString");
        Toast.makeText(UpdateWorkout.this, "myString: " + myString, Toast.LENGTH_SHORT).show();
        exerciseName.setText(myString);
        String string1;
        String string2;
        String string3;
        String string4;

        Cursor pointData = dbHelper.getData();
        StringBuffer buffer = new StringBuffer();
        while(pointData.moveToNext()) {
            if (pointData.getString(0).equals(myString)) {
                buffer.append("Exercise Name: " + pointData.getString(0) + "\n");
                buffer.append("Number Reps: " + pointData.getString(1) + "\n");
                buffer.append("Number Sets: " + pointData.getString(2) + "\n");
                buffer.append("Weight: " + pointData.getString(3) + "\n");
                buffer.append("Notes: " + pointData.getString(4) + "\n\n");

                string1 = pointData.getString(1);
                string2 = pointData.getString(2);
                string3 = pointData.getString(3);
                string4 = pointData.getString(4);
                numReps.setText(string1);
                numSets.setText(string2);
                weight.setText(string3);
                notes.setText(string4);
            }
        }
        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = exerciseName.getText().toString();
                String repsText = numReps.getText().toString();
                String setsText = numSets.getText().toString();
                String weightText = weight.getText().toString();
                String notesText = notes.getText().toString();

                Boolean checkUpdate = dbHelper.updateData(nameText,repsText,setsText,weightText,notesText);
                if(checkUpdate==true)
                    Toast.makeText(UpdateWorkout.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateWorkout.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        cancelUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}