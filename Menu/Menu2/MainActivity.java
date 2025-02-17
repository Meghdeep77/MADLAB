package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void MenuHandle(View V) {
        Button b = findViewById(R.id.button2);
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, b);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

        // Set a click listener for menu items
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.MenuA){
                    RadioButton r1 = findViewById(R.id.radioButton);
                    r1.toggle();
                    if(r1.isChecked()){
                        r1.setChecked(false);
                    }

                }
                if(item.getItemId() == R.id.MenuB){
                    RadioButton r1 = findViewById(R.id.radioButton2);
                    r1.toggle();

                }
                if(item.getItemId() == R.id.MenuC){
                    RadioButton r1 = findViewById(R.id.radioButton3);
                    r1.toggle();

                }
                return true;


            }
        });

        // Show the popup menu
        popupMenu.show();
    }



}



