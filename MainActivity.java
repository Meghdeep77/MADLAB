package com.example.movie;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Spinner itemSpinner;
    private Button addButton;
    private ListView listView;
    private TextView totalCostText;

    private List<String> selectedItems = new ArrayList<>();
    private double totalCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        itemSpinner = findViewById(R.id.spinnerItems);
        addButton = findViewById(R.id.buttonAdd);
        listView = findViewById(R.id.listView);
        totalCostText = findViewById(R.id.totalCostText);

        // Add initial grocery items (if needed)
        dbHelper.addGroceryItem("Apple", 1.5);
        dbHelper.addGroceryItem("Milk", 2.0);
        dbHelper.addGroceryItem("Bread", 1.2);

        // Populate Spinner with items from database
        List<String> items = dbHelper.getAllGroceryItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        itemSpinner.setAdapter(adapter);

        addButton.setOnClickListener(v -> addItemToCart());
    }

    private void addItemToCart() {
        String selectedItem = itemSpinner.getSelectedItem().toString();
        double price = dbHelper.getItemPrice(selectedItem);

        selectedItems.add(selectedItem + " - $" + price);
        totalCost += price;

        // Update ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedItems);
        listView.setAdapter(adapter);

        // Update total cost
        totalCostText.setText("Total Cost: $" + totalCost);
    }
}
