package com.example.iintentp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the ArrayList<String[]> from the intent
        Intent intent = getIntent();
        ArrayList<String[]> selectedItems = (ArrayList<String[]>) intent.getSerializableExtra("selectedItems");

        TextView textView = findViewById(R.id.textView);
        StringBuilder displayText = new StringBuilder("Selected Items:\n");

        if (selectedItems != null) {
            for (String[] item : selectedItems) {
                displayText.append("Item: ").append(item[0]).append(", Quantity: ").append(item[1]).append("\n");
            }
        } else {
            displayText.append("No items selected.");
        }

        textView.setText(displayText.toString());
    }
}
