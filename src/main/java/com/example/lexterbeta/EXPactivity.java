package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.Random;

public class EXPactivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_e_x_pactivity);

        Button button = findViewById(R.id.enter);
        configNextButton();// this is the back button

        final EditText lvl = (EditText) findViewById(R.id.lvl);// for lvl
        final EditText bonus = (EditText) findViewById(R.id.bonus);// for bonus
        bonus.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = "Key_Check";// For Main check that you pressed a button
                if (TextUtils.isEmpty(lvl.getText().toString()) || Double.parseDouble(lvl.getText().toString()) < 1 || Double.parseDouble(lvl.getText().toString()) > 100) {
                    lvl.setError("I need to be valid");
                } else {

                    double Level = Double.parseDouble(lvl.getText().toString());// converting them to doubles for equation
                    double Bonus = Double.parseDouble(bonus.getText().toString() + 0);
                    if (Bonus == 0.0) {// empty input
                        Bonus = 1;
                    } else {
                        Bonus = Bonus / 10;
                        // Because this is an optional input I need to call it as a double just in case, but need to add the 0 if left blank
                        // But when it's not empty it still has the extra 0 so divide by 10 moves the Bonus all behind the decimal point which is not right
                        // So then I just push it forward again so we have the correct double input, like 1.1 or 1.5
                        // This also works for negatives in case you have a crap modifier because of some Perk
                        // I understand this is a crap way of doing this but it works and I'm proud I came up with this
                        Bonus = Double.parseDouble(bonus.getText().toString() + 0);
                    }

                    ToggleButton Wild = (ToggleButton) findViewById(R.id.wild);
                    double wild_mod = 1.5;

                    if (Wild.isChecked()) {// checking if pkmn is trained or wild
                        wild_mod = 1;
                    }

                    ToggleButton Faint = (ToggleButton) findViewById(R.id.faint);
                    double faint_mod = 1;

                    if (Faint.isChecked()) {// check if Pokemon fainted in case of capture or battle ending
                        faint_mod = 2;
                    }

                    RadioGroup group2;// For stage of pkmn
                    double type = 1;
                    group2 = findViewById(R.id.radioGroup2);
                    int checkedId2 = group2.getCheckedRadioButtonId();
                    if (checkedId2 == -1) {
                        // this group cannot be unchecked
                    } else {
                        type = findRadioButton(checkedId2, type);
                    }

                    Random random = new Random();
                    double shift_mod = .85 + (1 - .85) * random.nextDouble();// for exp deviation
                    double EXP = Math.round(((type * Level * wild_mod * Bonus / 7) * shift_mod) / faint_mod);
                    openDialog(EXP);// open pop up
                }
            }
        });
    }

    private double findRadioButton(int checkedId2, double type) {
        switch (checkedId2) {
            case R.id.baby:
                type = 30;
                break;
            case R.id.basic:
                type = 60;
                break;
            case R.id.stage1:
                type = 120;
                break;
            case R.id.stage2:
                type = 240;
                break;
            case R.id.legendary:
                type = 480;
                break;
        }
        return type;

    }

    private void configNextButton() {// for the back button to go back to home screen
        Button nextButton = (Button) findViewById(R.id.back);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EXPactivity.this, MainHead.class));
            }
        });
    }

    private void openDialog(double EXP) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Your Pokémon Gained...");
        dialog.setMessage(EXP + " experience");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        AlertDialog alertDialog = dialog.create();// show and create
        alertDialog.show();
    }
}

