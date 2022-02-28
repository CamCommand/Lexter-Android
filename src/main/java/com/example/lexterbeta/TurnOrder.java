package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TurnOrder extends AppCompatActivity {
    private static String data = "key";// Still for Lexter jokes
    String love;//store text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// will hide the title
        getSupportActionBar().hide();// hide the title bar
        setContentView(R.layout.activity_turn_order);
        Button speed = (Button) findViewById(R.id.speedbutton);
        Button backbutton1 = (Button) findViewById(R.id.enter6);
        final EditText save = (EditText) findViewById(R.id.EditText1);

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seek);
        final TextView textView = (TextView) findViewById(R.id.captext);// for stages
        final EditText s = (EditText) findViewById(R.id.ball);
        //final double Speed = Double.parseDouble((s.getText().toString()));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);// retrieving data
        String love2 = prefs.getString("love", "");
        save.setText(love2);

        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love = save.getText().toString();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(TurnOrder.this);// save data
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("love", love);

                editor.apply();
                startActivity(new Intent(TurnOrder.this, MainHead.class));
            }
        });

        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button

                if (TextUtils.isEmpty(s.getText().toString()) || Double.parseDouble(s.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    s.setError("Missing me or I'm below 1");
                } else{
                    double Speed = Double.parseDouble((s.getText().toString()));

                    if (seekbar.getProgress() == 0) {// Does the math conversion for different Stage boosts for Attack
                        Speed = Speed * .2;
                    } else if (seekbar.getProgress() == 1) {
                        Speed = Speed * .25;
                    } else if (seekbar.getProgress() == 2) {
                        Speed = Speed * .3;
                    } else if (seekbar.getProgress() == 3) {
                        Speed = Speed * .4;
                    } else if (seekbar.getProgress() == 4) {
                        Speed = Speed * .5;
                    } else if (seekbar.getProgress() == 5) {// skip 6 because that is Stage 0
                        Speed = Speed * .75;
                    } else if (seekbar.getProgress() == 7) {
                        Speed = Speed * 1.25;
                    } else if (seekbar.getProgress() == 8) {
                        Speed = Speed * 1.5;
                    } else if (seekbar.getProgress() == 9) {
                        Speed = Speed * 2;
                    } else if (seekbar.getProgress() == 10) {
                        Speed = Speed * 2.5;
                    } else if (seekbar.getProgress() == 11) {
                        Speed = Speed * 3;
                    } else if (seekbar.getProgress() == 12) {
                        Speed = Speed * 3.5;
                    }
                    s.setText("SP " + Speed);
                }
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final double Speed = Double.parseDouble((s.getText().toString()));
                textView.setText("Stage " + (progress - 6));// For the -6_6 stages of stat boosts
                //s.setText((int) (Speed * 2));
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
   /* private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.enter6);
        EditText save = (EditText) findViewById(R.id.EditText1);
        love = save.getText().toString();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(TurnOrder.this);// save data
        SharedPreferences.Editor editor = pref.edit();
        editor.apply();

        editor.putString("love", love);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TurnOrder.this, MainHead.class));
            }
        });
    }
    */

}