package com.example.co.intentexample2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton button = findViewById(R.id.imageButton);

        final EditText editText = (EditText)findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                //EditTet에서 사용자가 입력한 문자열을 넘김, 스트링 변환 후 넘겨야함
                intent.putExtra("sendText", String.valueOf(editText.getText()));
                //SubActivity를 호출
                startActivity(intent);

            }
        });
    }
}
