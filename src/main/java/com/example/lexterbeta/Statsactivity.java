package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Statsactivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_statsactivity);
        Button button = findViewById(R.id.enter);// for stats
        Button button2 = findViewById(R.id.enter2);// for mega
        configNextButton();// this is the back button


        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button
                EditText hp1 = (EditText) findViewById(R.id.hp1);
                EditText attack1 = (EditText) findViewById(R.id.attack1);
                EditText defense1 = (EditText) findViewById(R.id.defense1);
                EditText spattack1 = (EditText) findViewById(R.id.specialattack1);
                EditText spdefense1 = (EditText) findViewById(R.id.specialdefense1);
                EditText Speed1 = (EditText) findViewById(R.id.speed10);
                EditText Level1 = (EditText) findViewById(R.id.Lvl1);

                EditText HPplus = (EditText) findViewById(R.id.plushp);
                EditText attackplus = (EditText) findViewById(R.id.plusattack);
                EditText defenseplus = (EditText) findViewById(R.id.plusdefense);
                EditText spattackplus = (EditText) findViewById(R.id.plusspattack);
                EditText spdefenseplus = (EditText) findViewById(R.id.plusspdefense);
                EditText speedplus = (EditText) findViewById(R.id.plusspeed);

                EditText hp1an = (EditText) findViewById(R.id.hpanswer1);
                EditText attack1an = (EditText) findViewById(R.id.attackanswer1);
                EditText defense1an = (EditText) findViewById(R.id.defenseanswer1);
                EditText spattack1an = (EditText) findViewById(R.id.spattackanswer1);
                EditText spdefense1an = (EditText) findViewById(R.id.spdefesneanswer1);
                EditText speed1an = (EditText) findViewById(R.id.speedanswer1);

                if (TextUtils.isEmpty(hp1.getText().toString()) || Double.parseDouble(hp1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    hp1.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(attack1.getText().toString()) || Double.parseDouble(attack1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    attack1.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(defense1.getText().toString()) || Double.parseDouble(defense1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    defense1.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(spattack1.getText().toString()) || Double.parseDouble(spattack1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    spattack1.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(spdefense1.getText().toString()) || Double.parseDouble(spdefense1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    spdefense1.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(Speed1.getText().toString()) || Double.parseDouble(Speed1.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    Speed1.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(Level1.getText().toString()) || Double.parseDouble(Level1.getText().toString()) < 1 || Double.parseDouble(Level1.getText().toString()) > 100) {// Making sure every field is filled and within bounds
                    Level1.setError("Missing me or I'm below 1 or over 100");
                } else {
                    double HP1 = Double.parseDouble(hp1.getText().toString());// converting them to doubles for equation
                    double Attack1 = Double.parseDouble(attack1.getText().toString());
                    double Defense1 = Double.parseDouble(defense1.getText().toString());
                    double SPAttack1 = Double.parseDouble(spattack1.getText().toString());
                    double SPDefense1 = Double.parseDouble(spdefense1.getText().toString());
                    double Speed1baby = Double.parseDouble(Speed1.getText().toString());
                    double lvl1 = Double.parseDouble(Level1.getText().toString());

                    HPplus.setText("+" + ((int) Math.ceil(HP1 * .1)));// math for stat buffs after level up
                    attackplus.setText("+" + ((int) Math.ceil(Attack1 * .05)));
                    defenseplus.setText("+" + ((int) Math.ceil(Defense1 * .05)));
                    spattackplus.setText("+" + ((int) Math.ceil(SPAttack1 * .05)));
                    spdefenseplus.setText("+" + ((int) Math.ceil(SPDefense1 * .05)));
                    speedplus.setText("+" + ((int) Math.ceil(Speed1baby * .05)));

                    hp1an.setText((int) Math.ceil(((2 * HP1) * lvl1 / 100) + 10 + lvl1) + "");//math for the current stats
                    attack1an.setText((int) Math.ceil(((2 * Attack1) * lvl1 / 100) + 5) + "");
                    defense1an.setText((int) Math.ceil(((2 * Defense1) * lvl1 / 100) + 5) + "");
                    spattack1an.setText((int) Math.ceil(((2 * SPAttack1) * lvl1 / 100) + 5) + "");
                    spdefense1an.setText((int) Math.ceil(((2 * SPDefense1) * lvl1 / 100) + 5) + "");
                    speed1an.setText((int) Math.ceil(((2 * Speed1baby) * lvl1 / 100) + 5) + "");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button
                EditText hp = (EditText) findViewById(R.id.hp);// for base
                EditText attack = (EditText) findViewById(R.id.attack2);
                EditText defense = (EditText) findViewById(R.id.defense);
                EditText specialattack = (EditText) findViewById(R.id.specialattack);
                EditText specialdefense = (EditText) findViewById(R.id.specialdefense);
                EditText speed = (EditText) findViewById(R.id.speed);

                EditText hp2 = (EditText) findViewById(R.id.hp3);// for mega
                EditText attack2 = (EditText) findViewById(R.id.attack3);
                EditText defense2 = (EditText) findViewById(R.id.defense2);
                EditText specialattack2 = (EditText) findViewById(R.id.specialattack2);
                EditText specialdefense2 = (EditText) findViewById(R.id.specialdefense2);
                EditText speed2 = (EditText) findViewById(R.id.speed4);

                EditText lvl = (EditText) findViewById(R.id.lvl5);// for level

                if (TextUtils.isEmpty(hp.getText().toString()) || Double.parseDouble(hp.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    hp.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(attack.getText().toString()) || Double.parseDouble(attack.getText().toString()) < 1) {
                    attack.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(defense.getText().toString()) || Double.parseDouble(defense.getText().toString()) < 1) {
                    defense.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(specialattack.getText().toString()) || Double.parseDouble(specialattack.getText().toString()) < 1) {
                    specialattack.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(specialdefense.getText().toString()) || Double.parseDouble(specialdefense.getText().toString()) < 1) {
                    specialdefense.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(speed.getText().toString()) || Double.parseDouble(speed.getText().toString()) < 1) {
                    speed.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(hp2.getText().toString()) || Double.parseDouble(hp2.getText().toString()) < 1) {
                    hp2.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(attack2.getText().toString()) || Double.parseDouble(attack2.getText().toString()) < 1) {
                    attack2.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(defense2.getText().toString()) || Double.parseDouble(defense2.getText().toString()) < 1) {
                    defense2.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(specialattack2.getText().toString()) || Double.parseDouble(specialattack2.getText().toString()) < 1) {
                    specialattack2.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(specialdefense2.getText().toString()) || Double.parseDouble(specialdefense2.getText().toString()) < 1) {
                    specialdefense2.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(speed2.getText().toString()) || Double.parseDouble(speed2.getText().toString()) < 1) {
                    speed2.setError("Missing me or I'm below 1");
                } else if (TextUtils.isEmpty(lvl.getText().toString()) || Double.parseDouble(lvl.getText().toString()) < 1 || Double.parseDouble(lvl.getText().toString()) > 100) {
                    lvl.setError("Missing me or I'm below 1 or over 100");
                } else {

                    double HPbase = Double.parseDouble(hp.getText().toString());// converting them to doubles for equation
                    double Attackbase = Double.parseDouble(attack.getText().toString());
                    double Defensebase = Double.parseDouble(defense.getText().toString());
                    double SPAttackbase = Double.parseDouble(specialattack.getText().toString());
                    double SPDefensebase = Double.parseDouble(specialdefense.getText().toString());
                    double Speedbase = Double.parseDouble(speed.getText().toString());
                    double Level = Double.parseDouble(lvl.getText().toString());

                    double HPmega = Double.parseDouble(hp2.getText().toString());// converting them to doubles for equation
                    double Attackmega = Double.parseDouble(attack2.getText().toString());
                    double Defensemega = Double.parseDouble(defense2.getText().toString());
                    double SPAttackmega = Double.parseDouble(specialattack2.getText().toString());
                    double SPDefensemega = Double.parseDouble(specialdefense2.getText().toString());
                    double Speedmega = Double.parseDouble(speed2.getText().toString());

                    double percentage_shift = 0;// for multiplying
                    if (Level < 61) {
                        percentage_shift = 3;
                        if (Level < 41) {
                            percentage_shift = 2;
                        } else {
                            percentage_shift = 1;
                        }
                    }
                    if (Level > 60) {
                        percentage_shift = 4;
                    } else {
                        percentage_shift = 5;
                    }

                    double HPchange = (HPmega - HPbase) * percentage_shift;
                    double Attackchange = (Attackmega - Attackbase) * percentage_shift;
                    double Defensechange = (Defensemega - Defensebase) * percentage_shift;
                    double SPattackchange = (SPAttackmega - SPAttackbase) * percentage_shift;
                    double SPdefensechange = (SPDefensemega - SPDefensebase) * percentage_shift;
                    double Speedchange = (Speedmega - Speedbase) * percentage_shift;

                    openDialog(HPchange, Attackchange, Defensechange, SPattackchange, SPdefensechange, Speedchange);
                }

            }
        });
    }

    private void openDialog(double hPchange, double attackchange, double defensechange, double sPattackchange, double sPdefensechange, double speedchange) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Mega Evolving! Add these number to the Pokémon's current stats.");
        dialog.setMessage("HP " + hPchange + "\n" +
                "Attack " + attackchange + "\n" +
                "Defense " + defensechange + "\n" +
                "Special Attack " + sPattackchange + "\n" +
                "Special Defense " + sPdefensechange + "\n" +
                "Speed " + speedchange);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }
    private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.enter3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Statsactivity.this, MainHead.class));
            }
        });
    }

}
