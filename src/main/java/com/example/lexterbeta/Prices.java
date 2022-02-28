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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Random;

public class Prices extends AppCompatActivity {
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
        setContentView(R.layout.activity_prices);

        Button button1 = findViewById(R.id.enter4);// trade enter button
        Button button2 = findViewById(R.id.enter);// poach enter button
        final MediaPlayer beepinput = MediaPlayer.create(this, R.raw.beepinput);// sound for input

        configNextButton();// this is the back button
        final MediaPlayer sparkle = MediaPlayer.create(this, R.raw.shiny);// sound for shiny
        ToggleButton toggle = (ToggleButton) findViewById(R.id.shiny);
        final TextView lvl = (TextView) findViewById(R.id.lvltext2);// for level
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // if toggle button is enabled/on
                    sparkle.start();
                } else {
                    // If toggle button is disabled/off do nothing
                }
            }
        });

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekuno);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lvl.setText("Level " + (progress + 1));// change view for what level you are
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button

                EditText team_num = (EditText) findViewById(R.id.team);
                EditText trade_lvl = (EditText) findViewById(R.id.levelinput2);
                EditText sum_lvl = (EditText) findViewById(R.id.Levelinput);
                if (TextUtils.isEmpty(team_num.getText().toString()) || Double.parseDouble(team_num.getText().toString()) < 1 || Double.parseDouble(team_num.getText().toString()) > 50) {// Making sure every field is filled and within bounds
                    team_num.setError("I might be an inappropriate number");
                } else if (TextUtils.isEmpty(trade_lvl.getText().toString()) || Double.parseDouble(trade_lvl.getText().toString()) < 1 || Double.parseDouble(trade_lvl.getText().toString()) > 100) {
                    trade_lvl.setError("I might be an inappropriate number");
                } else {
                    double team = Double.parseDouble(team_num.getText().toString());// converting them to doubles for equation
                    double tradedlvl = Double.parseDouble(trade_lvl.getText().toString());// converting them to doubles for equation
                    double sumlvl = Double.parseDouble(sum_lvl.getText().toString());// converting them to doubles for equation

                    double answer1 = sumlvl / team;// average level of team
                    double answer2 = Math.round(tradedlvl - answer1);// required levels
                    team = 6 - team;
                    if (team == 0) {
                        team = 1;
                    } else if (team < 0) {
                        team = team * team;
                    }
                    if (answer2 >= 0) {
                        openDialog2(answer2, team);
                    } else {
                        openDialog5();
                    }

                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button

                RadioGroup group1;// For stage of pkmn
                double stage = 100;
                group1 = findViewById(R.id.radioGroup3);
                int checkedId1 = group1.getCheckedRadioButtonId();
                if (checkedId1 == -1) {
                    // this group cannot be unchecked
                } else {
                    stage = findRadioButton(checkedId1, stage);
                }

                RadioGroup group2;// For nativity of PKMN
                double nave = 100;
                group2 = findViewById(R.id.radioGroup);
                int checkedId2 = group2.getCheckedRadioButtonId();
                if (checkedId2 == -1) {
                    // this group cannot be unchecked
                } else {
                    nave = findRadioButton2(checkedId2, nave);
                }

                ToggleButton shiny = (ToggleButton) findViewById(R.id.shiny);// toggle shiny increase
                double shiny_mod = 1;
                if (shiny.isChecked()) {// checking if pkmn is shiny or not
                    shiny_mod = 2;
                }


                double lvl = seekbar.getProgress() + 1;// to gte the level

                double price1 = (((stage * nave) * (lvl / 100)) * shiny_mod);
                openDialog(price1);// open pop up
            }
        });
    }

    private void openDialog5() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final MediaPlayer rollsound = MediaPlayer.create(this, R.raw.diceroll);// sound for dice roll
        dialog.setTitle("Pokémon Trade Completed! ");
        dialog.setMessage("Your Pokémon is comfortable being on your team, they will listen to you.");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void openDialog2(double answer2, final double team) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final MediaPlayer rollsound = MediaPlayer.create(this, R.raw.diceroll);// sound for dice roll
        dialog.setTitle("Pokémon Trade Completed! However...");
        dialog.setMessage("Your Pokémon might not listen to you for the next " + answer2 + " action(s) or attacks.\nYou must make " + team + " successful rolls on a D20 if you want them to obey.\n" +
                "(Baby >= 5, Basic >= 8, Stage 1 >= 11, Stage 2 >= 15, Legendary/Mythical/Ultra Beast >= 18)\n" +
                "Would you like to roll now?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rollsound.start();
                openDialog3(team);
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

    private void openDialog3(double team) {// Dice roll dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Random r = new Random();// D20 roll
        dialog.setTitle("Your roll(s) are...");
        int lop = (int) team;// this is one of my 'Going Mad' variables
        int[] roll = new int[lop];

        for (int i = 0; i < lop; i++) {
            roll[i] = r.nextInt(20) + 1;
        }
        dialog.setMessage(Arrays.toString(roll) + "\nDid you succeed?");//print out array of rolls

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Random r = new Random();// D6 roll
                int roll = r.nextInt(6) + 1;
                openDialog4(roll);
            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void openDialog4(double roll) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Your Pokémon disobeyed you");
        if (roll == 1) {
            dialog.setMessage("The Pokémon fell asleep.");
        } else if (roll == 2) {
            dialog.setMessage("The Pokémon became drowsy.");
        } else if (roll == 3) {
            dialog.setMessage("The Pokémon ignored you.");
        } else if (roll == 4) {
            dialog.setMessage("The Pokémon did another move instead.");
        } else if (roll == 5) {
            dialog.setMessage("The Pokémon is loafing around: Evasion Stage -1.");
        } else {
            dialog.setMessage("The Pokémon dropped their item.");
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private void openDialog(double price1) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Poaching your Pokémon");
        dialog.setMessage("The Pokémon you are selling is generally priced at " + Math.round(price1) + " in this area.");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }

    private double findRadioButton(int checkedId1, double stage) {// stages
        switch (checkedId1) {
            case R.id.baby:
                stage = 100;
                break;
            case R.id.basic:
                stage = 200;
                break;
            case R.id.stage1:
                stage = 300;
                break;
            case R.id.stage2:
                stage = 400;
                break;
            case R.id.legendary:
                stage = 500;
                break;
        }
        return stage;

    }

    private double findRadioButton2(int checkedId2, double nave) {// rare
        switch (checkedId2) {
            case R.id.common1:
                nave = 100;
                break;
            case R.id.uncommon1:
                nave = 200;
                break;
            case R.id.rare1:
                nave = 300;
                break;
            case R.id.for1:
                nave = 400;
                break;
        }
        return nave;

    }

    private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.back1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Prices.this, MainHead.class));
            }
        });
    }

}