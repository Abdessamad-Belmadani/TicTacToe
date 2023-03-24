package com.abdessamadbelmadani.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_play=findViewById(R.id.btn_play);

        btn_play.setOnClickListener(view -> {
            Intent intent =new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        });


    }
}