package edu.gmu.project2version4_tkarawap4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button doWorkoutButton,setupEditButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doWorkoutButton = (Button) findViewById(R.id.doAWorkoutButton);
        setupEditButton = (Button) findViewById(R.id.setupEditButton);

        doWorkoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Workout_Saved.class);
                startActivity(intent);
            }
        });

        setupEditButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetupEditWorkout.class);
                startActivity(intent);
            }
        });
    }
}