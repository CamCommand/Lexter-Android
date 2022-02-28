package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class captureactivity extends AppCompatActivity {
    private static String data = "key";// Still for Lexter jokes

    // These are for making sure you went to another page for the Lexter quotes to change
    public static String getData() {
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// will hide the title
        getSupportActionBar().hide();// hide the title bar
        setContentView(R.layout.activity_captureactivity);
        Button button = findViewById(R.id.enter);
        configNextButton();// this is the back button

        final TextView cap = (TextView) findViewById(R.id.captext);// for Capture Skill
        final SeekBar seekbar = (SeekBar) findViewById(R.id.seek1);
        final MediaPlayer rollsound = MediaPlayer.create(this, R.raw.diceroll);// sound for dice roll

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cap.setText("Capture Skill " + (progress));// change view for what level of Skill you are on
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {// for the Enter
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button
                EditText hp1 = (EditText) findViewById(R.id.hp1);
                EditText hp2 = (EditText) findViewById(R.id.hp2);
                EditText ball = (EditText) findViewById(R.id.ball);

                if (TextUtils.isEmpty(hp1.getText().toString()) || Double.parseDouble(hp1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    hp1.setError("Missing me or I'm below 1");
                } else if (hp2.getText().length() == 0 || Double.parseDouble(hp2.getText().toString()) < 0) {
                    hp2.setError("Forgot me or I'm negative");
                } else if (ball.getText().length() == 0 || Double.parseDouble(ball.getText().toString()) < 1 || Double.parseDouble(ball.getText().toString()) > 2) {
                    ball.setError("Come on user, the options are right there");
                } else {
                    double HP1 = Double.parseDouble(hp1.getText().toString());// converting them to doubles for equation
                    double HP2 = Double.parseDouble((hp2.getText().toString()));
                    double Ball = Double.parseDouble((ball.getText().toString()));

                    double type = 1;// declaring them for reuse sake
                    double bonusstat = 1.0;

                    double bonus = 0;// Skill bonus

                    RadioGroup group1;// For ailments
                    group1 = findViewById(R.id.radioGroup);
                    int checkedId = group1.getCheckedRadioButtonId();
                    if (checkedId == -1) {
                        // first group can be unchecked
                    } else {
                        bonusstat = findRadioButton(checkedId, bonusstat);
                    }
                    RadioGroup group2;// For stage of pkmn
                    group2 = findViewById(R.id.radioGroup2);
                    int checkedId2 = group2.getCheckedRadioButtonId();
                    if (checkedId2 == -1) {
                        // this group cannot be unchecked
                    } else {
                        type = findRadioButton2(checkedId2, type);
                    }

                    while(seekbar.getProgress() != bonus){// Does the math conversion for different Skill boosts for Capture
                        bonus++;
                    }
                    bonus = bonus * 5;


                    double BC = 1;// Base Capture
                    //BC = Math.round(((((3 * HP1 - 2 * HP2) * type * Ball) / (3 * HP1)) * bonusstat) - Math.random() * ((4) + 1)) + bonus;// 5 percent margin -1 bc only 100% capture rate on master balls
                    BC = Math.round(((3 * HP1 - 2 * HP2) * (type * Ball) / (3 * HP1)) * (bonusstat)) + bonus;
                    if (BC > 100) {// in case of out of bounds which happens sometimes
                        BC = 99;
                    }
                    if (BC < 1) {
                        BC = 1;
                    }

                    openDialog(BC, rollsound);// open pop up
                    group1.clearCheck();// clear group 1 in case aliment is removed or mistake, better than adding a none or na slot
                }
            }

            private double findRadioButton2(int checkedId2, double type) {// this is for adding the margin difference between different stages of pkmn
                double i1 = Math.random() * (255 - 190 + 1) + 190;// baby
                double i2 = Math.random() * (230 - 100 + 1) + 100;// basic
                double i3 = Math.random() * (99 - 45 + 1) + 45;// stage 1
                double i4 = Math.random() * (44 - 4 + 1) + 4;// stage 2
                double i5 = Math.random() * (3 - 1 + 1) + 1;// legendary

                switch (checkedId2) {
                    case R.id.baby:
                        type = i1;
                        break;
                    case R.id.basic:
                        type = i2;
                        break;
                    case R.id.stage1:
                        type = i3;
                        break;
                    case R.id.stage2:
                        type = i4;
                        break;
                    case R.id.legendary:
                        type = i5;
                        break;
                }
                return type;

            }

            private double findRadioButton(int checkedId, double bonusstat) {// This is to check for adding bonus bc target is afflicted
                switch (checkedId) {
                    case R.id.radioButton1:
                        bonusstat = 1.5;// Asleep or Frozen
                        break;
                    case R.id.radioButton2:
                        bonusstat = 1.2;// Paralyzed Burned or Poisoned
                        break;
                    case R.id.radioButton3:
                        bonusstat = 1.1;// Flinched Infatuated or Confused
                        break;
                }
                return bonusstat;
            }
        });

    }


    private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.back);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(captureactivity.this, MainHead.class));
            }
        });
    }


    private void openDialog(final double BC, final MediaPlayer rollsound) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Catching...");
        dialog.setMessage("You must roll less than or equal to " + BC + " on a D100 roll. Would you like to roll now?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Random r = new Random();// D100 roll
                int roll = r.nextInt(100) + 1;
                rollsound.start();
                openDialog2(roll, BC);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //empty
            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void openDialog2(int roll, double BC) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Rolling...");
        if (roll <= BC) {
            dialog.setMessage("You caught them!");
            dialog.setPositiveButton("Yay!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else {
            dialog.setMessage("Oh darn, they weren't caught.");
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }


}