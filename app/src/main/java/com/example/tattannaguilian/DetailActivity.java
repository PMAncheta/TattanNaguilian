package com.example.tattannaguilian;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String item = intent.getStringExtra("item");
        String description = intent.getStringExtra("description");

        TextView textView = findViewById(R.id.detailTextView);
        TextView defintionText = findViewById(R.id.defitionText);

        if (item != null && description != null) {
            textView.setText(item);
            defintionText.setText(description);
        } else {
            textView.setText("Item not found");
            defintionText.setText("Description not available");
        }
    }
}
