package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Resources extends AppCompatActivity {
    TextView textView1;// We need one for each or else they wont all link at the same time
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// will hide the title
        getSupportActionBar().hide();// hide the title bar
        setContentView(R.layout.activity_resources);

        textView1 = findViewById(R.id.Dexlink);// linking each option
        textView2 = findViewById(R.id.Itemlink);
        textView3 = findViewById(R.id.Maplink);
        textView4 = findViewById(R.id.PKMNlink);
        textView5 = findViewById(R.id.Attacklink);
        textView6 = findViewById(R.id.Abilitylink);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
        textView5.setMovementMethod(LinkMovementMethod.getInstance());
        textView6.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void hiddenback(View view) {
        Intent intent1 = new Intent(Resources.this, MainHead.class);
        startActivity(intent1);
    }
}