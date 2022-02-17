package com.example.lexterbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainHead extends AppCompatActivity {
    private Button button;
    Random random = new Random();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_head);
        button = findViewById(R.id.Attack);
        TextView lex_says = (TextView)findViewById(R.id.open);
        lex_says.setText("Oh hey, you're back. Cool, welcome.");
        /*if(i > 1) {
            int lex = random.nextInt(4);
            if (lex == 0) {
                lex_says.setText("Oh hey, you're back. Cool, welcome.");
            } else if (lex == 1) {
                lex_says.setText("Thanks for still using me. My expectations are very low.");
            } else if (lex == 2) {
                lex_says.setText("Well those results were, um, shocking.");
            } else if (lex == 3) {
                lex_says.setText("Have you heard of the world's most powerful Pokemon? No? I don't blame you.");
            } else if (lex == 4) {
                lex_says.setText("Hey coolest trainer in town.");
            }
        }
        
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
