package com.example.iintentp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Notification extends AppCompatActivity {
private String customerName,customerEmail,customerPhone,totalPrice,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         String customerName,customerEmail,customerPhone,totalPrice;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Retrieve the ArrayList<String[]> from the intent
        Intent intent = getIntent();
        ArrayList<String[]> selectedItems = (ArrayList<String[]>) intent.getSerializableExtra("selectedItems");
        customerName=intent.getStringExtra("customerName");
        customerEmail=intent.getStringExtra("customerEmail");
        customerPhone=intent.getStringExtra("customerPhone");
        totalPrice=intent.getStringExtra("totalPrice");

        email=customerEmail.trim();

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("customer name:  "+customerName +"\n"+ "customer email: "+customerEmail+"\n"+
                "customerPhone: "+ customerPhone+ "\n"+ "totol price: "+ totalPrice + "\n" );

        StringBuilder displayText = new StringBuilder("Selected Items:\n");




        if (selectedItems != null) {
            for (String[] item : selectedItems) {
                displayText.append("Item: ").append(item[0]).append(", Quantity: ").append(item[1]).append("\n");
            }
        } else {
            displayText.append("No items selected.");
        }

        textView.setText(displayText.toString());
        StringBuilder messageToUser=new StringBuilder();
        messageToUser.append("user detail: :"+"\n").append("customer name:  "+customerName +"\n"+ "customer email: "+customerEmail+"\n"+
                "customerPhone: "+ customerPhone+ "\n"+ "totol price: "+ totalPrice + "\n").append(displayText);
        String finalMessage=messageToUser.toString();




    }
}
