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
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class Accuracyacticity extends AppCompatActivity {
    private static String data = "key";

    // These are for making sure you went to another page for the Lexter quotes to change
    public static String getData() {
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// will hide the title
        getSupportActionBar().hide();// hide the title bar
        setContentView(R.layout.activity_accuracyacticity);
        Button button = findViewById(R.id.Enter);
        configNextButton();// this is the back button

        final TextView att = (TextView) findViewById(R.id.attack);// for attack accuracy
        final SeekBar seekbar1 = (SeekBar) findViewById(R.id.barstage1);
        final SeekBar seekbar2 = (SeekBar) findViewById(R.id.barstage2);
        final MediaPlayer rollsound = MediaPlayer.create(this, R.raw.diceroll);// sound for dice roll
        final TextView textView1 = (TextView) findViewById(R.id.textstage1);// for accuracy
        final TextView textView2 = (TextView) findViewById(R.id.textstage2);// for evasion

        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {// seekbar for stages in accuracy
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView1.setText("Accuracy Stage " + (progress - 6));// For the -6_6 stages of stat boosts
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {// seekbar for stages in evasion
                textView2.setText("Evasion Stage " + (progress - 6));// For the -6_6 stages of stat boosts
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {// For the enter button
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button
                double mod1 = 1;// mod1 is for accuracy stages
                double mod2 = 1;// mod2 is for evasion stages
                EditText acc = (EditText) findViewById(R.id.attack);
                if (TextUtils.isEmpty(acc.getText().toString()) || Double.parseDouble(acc.getText().toString()) >= 100 || Double.parseDouble(acc.getText().toString()) <= 0) {// Making sure every field is filled and within bounds
                    acc.setError("I might be wrong or missing");
                } else {
                    double Accuracy = Double.parseDouble(acc.getText().toString());// Accuracy at the base

                    if (seekbar1.getProgress() == 0) {// Does the math conversion for different Stage boosts for Accuracy
                        mod1 = .3;
                    } else if (seekbar1.getProgress() == 1) {
                        mod1 = .35;
                    } else if (seekbar1.getProgress() == 2) {
                        mod1 = .4;
                    } else if (seekbar1.getProgress() == 3) {
                        mod1 = .5;
                    } else if (seekbar1.getProgress() == 4) {
                        mod1 = .6;
                    } else if (seekbar1.getProgress() == 5) {// skip 6 because that is Stage 0
                        mod1 = .75;
                    } else if (seekbar1.getProgress() == 7) {
                        mod1 = 1.25;
                    } else if (seekbar1.getProgress() == 8) {
                        mod1 = 1.5;
                    } else if (seekbar1.getProgress() == 9) {
                        mod1 = 2;
                    } else if (seekbar1.getProgress() == 10) {
                        mod1 = 2.25;
                    } else if (seekbar1.getProgress() == 11) {
                        mod1 = 2.5;
                    } else if (seekbar1.getProgress() == 12) {
                        mod1 = 3;
                    }
                    if (seekbar2.getProgress() == 12) {// Does the math conversion for different Stage boosts for Evasion
                        mod2 = .3;
                    } else if (seekbar2.getProgress() == 11) {
                        mod2 = .35;
                    } else if (seekbar2.getProgress() == 10) {
                        mod2 = .4;
                    } else if (seekbar2.getProgress() == 9) {
                        mod2 = .5;
                    } else if (seekbar2.getProgress() == 8) {
                        mod2 = .6;
                    } else if (seekbar2.getProgress() == 7) {// skip 6 because that is Stage 0
                        mod2 = .75;
                    } else if (seekbar2.getProgress() == 5) {
                        mod2 = 1.25;
                    } else if (seekbar2.getProgress() == 4) {
                        mod2 = 1.5;
                    } else if (seekbar2.getProgress() == 3) {
                        mod2 = 2;
                    } else if (seekbar2.getProgress() == 2) {
                        mod2 = 2.25;
                    } else if (seekbar2.getProgress() == 1) {
                        mod2 = 2.5;
                    } else if (seekbar2.getProgress() == 0) {
                        mod2 = 3;
                    }

                    Accuracy = Math.round(Accuracy * mod1 * mod2);// Math for final accuracy peak
                    openDialog(Accuracy, rollsound);// Message pop-up for Accuracy

                }
            }
        });
    }

    private void openDialog(double Accuracy, final MediaPlayer rollsound) {// dialog about accuracy
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        if (Accuracy >= 100) {
            Accuracy = 99;
        } else if (Accuracy <= 1) {
            Accuracy = 1;
        }
        final double accuracy_check = Accuracy;
        dialog.setTitle("Accuracy");
        dialog.setMessage("You must roll on a D100 less than or equal to " + Accuracy + " for the attack to hit.\nWould you like to roll it now?");
        Global.acc = "You must roll on a D100 less than or equal to " + Accuracy + " for the attack to hit.\nWould you like to roll it now?";
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Random r = new Random();// D100 roll
                int roll = r.nextInt(100) + 1;
                rollsound.start();
                openDialog2(roll, accuracy_check);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.Back);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accuracyacticity.this, MainHead.class));
            }
        });
    }

    private void openDialog2(int roll, double accuracy_check) {// Dice roll dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Dice Roll");
        if (accuracy_check >= roll) {
            dialog.setMessage("You rolled a " + roll + " it will land!");
            //Global.accroll = "You rolled a " + roll + " it will land!";
        } else {
            dialog.setMessage("You rolled a " + roll + " it'll miss.");
            //Global.accroll = "You rolled a " + roll + " it'll miss.";
        }

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    public static class Global {
        public static String acc;
        public static String accroll;
    }


}