package com.example.iintentp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxPizza, checkBoxBurger, checkBoxSandwich;
    private EditText editTextPizzaQuantity, editTextBurgerQuantity, editTextSandwichQuantity;
    private ArrayList<String[]> selectedItems = new ArrayList<>();
  private String customerName, customerEmail,customerPhone,totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       EditText customerName1= findViewById(R.id.customerName);
       EditText customerEmail1= findViewById(R.id.customerEmail);
       EditText customerPhone1= findViewById(R.id.customerPhone);
       EditText totalPrice1= findViewById(R.id.totalPrice);
        // Initialize checkboxes and edit texts
        checkBoxPizza = findViewById(R.id.checkBoxPizza);
        checkBoxBurger = findViewById(R.id.checkBoxBurger);
        checkBoxSandwich = findViewById(R.id.checkBoxSandwich);

        editTextPizzaQuantity = findViewById(R.id.editTextPizzaQuantity);
        editTextBurgerQuantity = findViewById(R.id.editTextBurgerQuantity);
        editTextSandwichQuantity = findViewById(R.id.editTextSandwichQuantity);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItems.clear();  // Clear previous selections
               customerName=customerName1.getText().toString();
               customerEmail=customerEmail1.getText().toString();
               customerPhone=customerPhone1.getText().toString();
               totalPrice=totalPrice1.getText().toString();
               // Check each item, if selected, add it with its quantity
                if (checkBoxPizza.isChecked()) {
                    String quantity = editTextPizzaQuantity.getText().toString();
                    if (!quantity.isEmpty()) {
                        selectedItems.add(new String[]{"Pizza", quantity});
                    }
                }

                if (checkBoxBurger.isChecked()) {
                    String quantity = editTextBurgerQuantity.getText().toString();
                    if (!quantity.isEmpty()) {
                        selectedItems.add(new String[]{"Burger", quantity});
                    }
                }

                if (checkBoxSandwich.isChecked()) {
                    String quantity = editTextSandwichQuantity.getText().toString();
                    if (!quantity.isEmpty()) {
                        selectedItems.add(new String[]{"Sandwich", quantity});
                    }
                }

                if (!selectedItems.isEmpty()) {
                    // Pass selectedItems to SecondActivity
                    Intent intent = new Intent(MainActivity.this, Notification.class);
                    intent.putExtra("selectedItems", selectedItems);
                    intent.putExtra("customerName", customerName);
                    intent.putExtra("customerEmail", customerEmail);
                    intent.putExtra("customerPhone", customerPhone);
                    intent.putExtra("totalPrice", totalPrice);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please select at least one item with a quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
