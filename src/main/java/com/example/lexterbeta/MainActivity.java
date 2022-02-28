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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.Enter);
        final TextView textView = (TextView) findViewById(R.id.textstage1);// for attack
        final SeekBar seekbar = (SeekBar) findViewById(R.id.barstage1);

        final TextView textView2 = (TextView) findViewById(R.id.textstage2);// for defense
        final SeekBar seekbar2 = (SeekBar) findViewById(R.id.barstage2);
        configNextButton();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("Stage " + (progress - 6));// For the -6_6 stages of stat boosts
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
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView2.setText("Stage " + (progress - 6));// For the -6_6 stages of stat boosts
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
                EditText lvl = (EditText) findViewById(R.id.lvl);
                EditText pwr = (EditText) findViewById(R.id.pwr);
                EditText attackstat = (EditText) findViewById(R.id.attackstat);
                EditText defstat = (EditText) findViewById(R.id.defstat);
                EditText weak = (EditText) findViewById(R.id.weak);
                EditText ability = (EditText) findViewById(R.id.ability);
                EditText crit = (EditText) findViewById(R.id.crit);
                EditText weather = (EditText) findViewById(R.id.weather);

                if (TextUtils.isEmpty(lvl.getText().toString()) || Double.parseDouble(lvl.getText().toString()) > 100 || Double.parseDouble(lvl.getText().toString()) < 1) {// Making sure every field is filled and within bounds
                    lvl.setError("I might be wrong or missing");
                } else if (pwr.getText().length() == 0 || Double.parseDouble(pwr.getText().toString()) > 300 || Double.parseDouble(pwr.getText().toString()) < 10) {
                    pwr.setError("Forgot me or wronged me");
                } else if (attackstat.length() == 0 || Double.parseDouble(attackstat.getText().toString()) < 1) {
                    attackstat.setError("Need me to be real");
                } else if (defstat.length() == 0 || Double.parseDouble(defstat.getText().toString()) < 1) {
                    defstat.setError("Can't leave me");
                } else if (weak.length() == 0 || Double.parseDouble(weak.getText().toString()) < .5 || Double.parseDouble(weak.getText().toString()) > 2) {
                    weak.setError("I must be one of those hinted values");
                } else if (ability.length() == 0) {
                    ability.setError("I could just be 1");
                } else if (crit.length() == 0 || Double.parseDouble(crit.getText().toString()) < 0 || Double.parseDouble(crit.getText().toString()) > 4) {
                    crit.setError("I'm probably 0");
                } else if (weather.length() == 0 || Double.parseDouble(weather.getText().toString()) < .5 || Double.parseDouble(weather.getText().toString()) > 1.5) {
                    weather.setError("Almost done, try 1");
                } else {

                    double level = Double.parseDouble(lvl.getText().toString());// converting them to doubles for equation
                    double power = Double.parseDouble(pwr.getText().toString());
                    double attack_stat = Double.parseDouble(attackstat.getText().toString());
                    double defense_stat = Double.parseDouble(defstat.getText().toString());
                    double weakness = Double.parseDouble(weak.getText().toString());
                    double ability_mod = Double.parseDouble(ability.getText().toString());
                    double critical = Double.parseDouble(crit.getText().toString());

                    if (seekbar.getProgress() == 0) {// Does the math conversion for different Stage boosts for Attack
                        attack_stat = attack_stat * .2;
                    } else if (seekbar.getProgress() == 1) {
                        attack_stat = attack_stat * .25;
                    } else if (seekbar.getProgress() == 2) {
                        attack_stat = attack_stat * .3;
                    } else if (seekbar.getProgress() == 3) {
                        attack_stat = attack_stat * .4;
                    } else if (seekbar.getProgress() == 4) {
                        attack_stat = attack_stat * .5;
                    } else if (seekbar.getProgress() == 5) {// skip 6 because that is Stage 0
                        attack_stat = attack_stat * .75;
                    } else if (seekbar.getProgress() == 7) {
                        attack_stat = attack_stat * 1.25;
                    } else if (seekbar.getProgress() == 8) {
                        attack_stat = attack_stat * 1.5;
                    } else if (seekbar.getProgress() == 9) {
                        attack_stat = attack_stat * 2;
                    } else if (seekbar.getProgress() == 10) {
                        attack_stat = attack_stat * 2.5;
                    } else if (seekbar.getProgress() == 11) {
                        attack_stat = attack_stat * 3;
                    } else if (seekbar.getProgress() == 12) {
                        attack_stat = attack_stat * 3.5;
                    }

                    if (seekbar2.getProgress() == 0) {// Does the math conversion for different Stage boosts for Defense
                        defense_stat = defense_stat * .2;
                    } else if (seekbar2.getProgress() == 1) {
                        defense_stat = defense_stat * .25;
                    } else if (seekbar2.getProgress() == 2) {
                        defense_stat = defense_stat * .3;
                    } else if (seekbar2.getProgress() == 3) {
                        defense_stat = defense_stat * .4;
                    } else if (seekbar2.getProgress() == 4) {
                        defense_stat = defense_stat * .5;
                    } else if (seekbar2.getProgress() == 5) {// skip 6 becuase that is Stage 0
                        defense_stat = defense_stat * .75;
                    } else if (seekbar2.getProgress() == 7) {
                        defense_stat = defense_stat * 1.25;
                    } else if (seekbar2.getProgress() == 8) {
                        defense_stat = defense_stat * 1.5;
                    } else if (seekbar2.getProgress() == 9) {
                        defense_stat = defense_stat * 2;
                    } else if (seekbar2.getProgress() == 10) {
                        defense_stat = defense_stat * 2.5;
                    } else if (seekbar2.getProgress() == 11) {
                        defense_stat = defense_stat * 3;
                    } else if (seekbar2.getProgress() == 12) {
                        defense_stat = defense_stat * 3.5;
                    }


                    Random random = new Random();
                    double critical_mod = 1;
                    double critical_check;

                    if (critical == 0) {// checking Critical ratio of the attack
                        critical_check = 4;// then do the roll
                        int check1 = random.nextInt(99);// out of 100
                        check1 += 1;
                        if (critical_check >= check1) {// basically like rolling a D100 and getting >= your modifier based on the ratio
                            critical_mod = 1.5;// change critical_mod to reflect the increase, changed from x2 from the games it is too good
                        }
                    }
                    if (critical == 1) {
                        critical_check = 13;
                        int check1 = random.nextInt(99);
                        check1 += 1;
                        if (critical_check >= check1) {
                            critical_mod = 1.5;
                        }
                    }
                    if (critical == 2) {
                        critical_check = 50;
                        int check1 = random.nextInt(99);
                        check1 += 1;
                        if (critical_check >= check1) {
                            critical_mod = 1.5;
                        }
                    }
                    if (critical >= 3) {
                        critical_check = 100;
                        int check1 = random.nextInt(99);
                        check1 += 1;
                        if (critical_check >= check1) {
                            critical_mod = 1.5;
                        }
                    }

                    double weather_mod = Double.parseDouble(weather.getText().toString());
                    // I have removed the recording of multi-hit attacks as I have found they are unimportant and too lenient for foes

                    ToggleButton stab = (ToggleButton) findViewById(R.id.stab);
                    double stab_mod = 1;

                    if (stab.isChecked()) {// checking if there is Stab, no need for else statement
                        stab_mod = 1.5;
                    }

                    ToggleButton burn = (ToggleButton) findViewById(R.id.burn);
                    double burn_mod = 1;

                    if (stab.isChecked()) {
                        burn_mod = .75;
                    }

                    ToggleButton dodge = (ToggleButton) findViewById(R.id.dodgemiss);
                    double dodge_damage = 1;// if they miss a dodge and take more damage

                    if (dodge.isChecked()) {
                        dodge_damage = 1.2;
                    }


                    double modifier;
                    double range = .85 + (1 - .85) * random.nextDouble();// damage range between the same moves
                    modifier = weather_mod * critical_mod * range * stab_mod * weakness * burn_mod * dodge_damage * ability_mod;// breaking up the equation for readability
                    // these ones are for my sanity however
                    double num1 = ((2 * level) / 5) + 2;
                    double num2 = power * (attack_stat / defense_stat);
                    double num3 = num1 * num2;
                    // damage before rounding and critical
                    double damage = 1;
                    damage = ((num3 / 50) + 2) * modifier;

                    if (damage < 0)
                        damage = 1;// in case the math was really bad or attack was really weak

                    openDialog(critical_mod, damage);// Message pop-up for Damage
                }
            }
        });
    }

    private void openDialog(double critical_mod, double damage) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Damage");
        if (critical_mod > 1) {// check if critical
            dialog.setMessage("I calculate the damage is a loss of " + Math.round(damage) + " HP... Massive Critical Hit!");
        } else {
            dialog.setMessage("I calculate the damage is a loss of " + Math.round(damage) + " HP...");
        }
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                startActivity(new Intent(MainActivity.this, MainHead.class));
            }
        });
    }

}
