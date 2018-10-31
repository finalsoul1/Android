package com.example.co.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);
        Toast.makeText(this, "key = "+ key, Toast.LENGTH_LONG).show();
    }
}
