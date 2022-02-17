package com.example.lexterbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.Enter);
        configNextButton();

        button.setOnClickListener(new View.OnClickListener() {// for the enter button
            @Override
            public void onClick(View v) {
                onClicked();
            }
        });
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

    @SuppressLint("SetTextI18n")
    public void onClicked(){
        EditText lvl = (EditText) findViewById(R.id.lvl);
        double level = Double.parseDouble(lvl.getText().toString());// converting them to doubles for equation

        EditText pwr = (EditText) findViewById(R.id.pwr);
        double power = Double.parseDouble(pwr.getText().toString());

        EditText attackstat = (EditText) findViewById(R.id.attackstat);
        double attack_stat = Double.parseDouble(attackstat.getText().toString());

        EditText defstat = (EditText) findViewById(R.id.defstat);
        double defense_stat = Double.parseDouble(defstat.getText().toString());

        EditText weak = (EditText) findViewById(R.id.weak);
        double weakness = Double.parseDouble(weak.getText().toString());

        EditText ability = (EditText) findViewById(R.id.ability);
        double ability_mod = Double.parseDouble(ability.getText().toString());

        EditText crit = (EditText) findViewById(R.id.crit);
        double critical = Double.parseDouble(crit.getText().toString());

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

        EditText weather = (EditText) findViewById(R.id.weather);// the reason this is separate from the other conversions is because I'm writing them in process order the game would take them
        double weather_mod = Double.parseDouble(weather.getText().toString());
        // I have removed the recording of multi-hit attacks as I have found they are unimportant and too lenient for foes

        ToggleButton stab = (ToggleButton) findViewById(R.id.stab);
        double stab_mod = 1;

        if(stab.isChecked()){// checking if there is Stab, no need for else statement
            stab_mod = 1.5;
        }

        ToggleButton burn = (ToggleButton) findViewById(R.id.burn);
        double burn_mod = 1;

        if(stab.isChecked()){
            burn_mod = .75;
        }

        double modifier;
        double range = .85 + (1 - .85) * random.nextDouble();// damage range between the same moves
        modifier = weather_mod * critical_mod * range * stab_mod * weakness * burn_mod * ability_mod;// breaking up the equation for readability
        // these ones are for my sanity however
        double num1 = ((2 * level) / 5) + 2;
        double num2 = power * (attack_stat / defense_stat);
        double num3 = num1 * num2;
        // damage before rounding and critical
        double damage = 1;
        damage = ((num3 / 50) + 2) * modifier;

        if (damage < 0) damage = 1;// in case the math was really bad or attack was really weak


        // print out the final damage total
        TextView lex = (TextView)findViewById(R.id.damtext);
        lex.setVisibility(View.VISIBLE);
        if (critical_mod > 1) {// check if critical
            lex.setText("I calculate the damage is a loss of " + Math.round(damage) + " HP... Massive Critical Hit!");
        } else {
            lex.setText("I calculate the damage is a loss of " + Math.round(damage) + " HP...");
        }
    }
}
