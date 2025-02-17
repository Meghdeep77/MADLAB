package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private TextView contentArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentArea = findViewById(R.id.content_area);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_options) {
            showOptionsDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showOptionsDialog() {
        String[] options = {"Workout Plans", "Trainers", "Membership"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Option")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            displayWorkoutPlans();
                            break;
                        case 1:
                            displayTrainers();
                            break;
                        case 2:
                            displayMembership();
                            break;
                    }
                });
        builder.show();
    }

    private void displayWorkoutPlans() {
        contentArea.setText("Workout Plans:\n- Weight Loss\n- Cardio");
    }

    private void displayTrainers() {
        contentArea.setText("Trainers:\n- John Doe (Strength Training)\n- Jane Smith (Yoga)");
    }

    private void displayMembership() {
        contentArea.setText("Membership:\n- Basic ($30/month)\n- Premium ($50/month)");
    }
}