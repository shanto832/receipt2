//



package com.example.iintentp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Notification extends AppCompatActivity implements View.OnClickListener {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String totalPrice;
    private String email;
    private String finalMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button sendEmailButton = findViewById(R.id.sendEmailButton);

        // Retrieve the ArrayList<String[]> from the intent
        Intent intent = getIntent();
        ArrayList<String[]> selectedItems = (ArrayList<String[]>) intent.getSerializableExtra("selectedItems");
        customerName = intent.getStringExtra("customerName");
        customerEmail = intent.getStringExtra("customerEmail");
        customerPhone = intent.getStringExtra("customerPhone");
        totalPrice = intent.getStringExtra("totalPrice");

        email = customerEmail.trim();

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("customer name:  " + customerName + "\n" +
                "customer email: " + customerEmail + "\n" +
                "customerPhone: " + customerPhone + "\n" +
                "total price: " + totalPrice + "\n");

        StringBuilder displayText = new StringBuilder("Selected Items:\n");

        if (selectedItems != null) {
            for (String[] item : selectedItems) {
                displayText.append("Item: ").append(item[0]).append(", Quantity: ").append(item[1]).append("\n");
            }
        } else {
            displayText.append("No items selected.");
        }

        textView.setText(displayText.toString());

        StringBuilder messageToUser = new StringBuilder();
        messageToUser.append("user detail: \n")
                .append("customer name:  ").append(customerName).append("\n")
                .append("customer email: ").append(customerEmail).append("\n")
                .append("customerPhone: ").append(customerPhone).append("\n")
                .append("total price: ").append(totalPrice).append("\n")
                .append(displayText);

        finalMessage = messageToUser.toString();

        sendEmailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendEmailButton) {
            // Check if the email is valid
            if (isValidEmail(email)) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this

                // Set the email recipient and message
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Transaction summary");
                emailIntent.putExtra(Intent.EXTRA_TEXT, finalMessage);
                startActivity(emailIntent);
            } else {
                Toast.makeText(this, "Email structure is not correct", Toast.LENGTH_SHORT).show();
                sendSMS(customerPhone, finalMessage);
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void sendSMS(String phoneNumber, String message) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:" + phoneNumber)); // Set the data to be an SMS URI
        smsIntent.putExtra("sms_body", finalMessage); // Set the message body

        // Check if there's an app that can handle this intent
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent); // Open the SMS app with the pre-filled message
        } else {
            Toast.makeText(this, "No SMS clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
