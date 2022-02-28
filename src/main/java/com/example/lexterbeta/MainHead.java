package com.example.Lexter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainHead extends AppCompatActivity {
    Random random = new Random();
    ImageView imageView1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// will hide the title
        getSupportActionBar().hide();// hide the title bar
        Date currentTime = Calendar.getInstance().getTime();

        setContentView(R.layout.activity_main_head);
        Button button1 = findViewById(R.id.Attack);// All those wacky buttons
        Button button2 = findViewById(R.id.Cap);
        Button button3 = findViewById(R.id.exp);
        Button button4 = findViewById(R.id.acc);
        Button button5 = findViewById(R.id.diceroll);
        Button button6 = findViewById(R.id.diceroll2);
        Button button7 = findViewById(R.id.dodge);
        Button button9 = findViewById(R.id.Resources);
        Button button10 = findViewById(R.id.price);
        Button button11 = findViewById(R.id.Stats);
        Button button8 = findViewById(R.id.Turn);

        ImageView imageView = findViewById(R.id.imageView5);
        final MediaPlayer rollsound = MediaPlayer.create(this, R.raw.diceroll);// sound for dice roll
        final MediaPlayer rollsound2 = MediaPlayer.create(this, R.raw.diceroll2);// sound for dice roll

        String data = MainActivity.getData();
        String data2 = captureactivity.getData();
        String data3 = EXPactivity.getData();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCaptureActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEXPActivity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccActivity();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDodgeActvity();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResourceActvity();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPriceActvity();
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsActvity();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainHead.this, TurnOrder.class);
                startActivity(intent3);
            }
        });

        int lex = random.nextInt(5);

        TextView lex_says = (TextView) findViewById(R.id.open);

        if (data.equals("Key_Check") || data2.equals("Key_Check") || data3.equals("Key_Check")) {
            if (lex == 0) {
                lex_says.setText("Oh hey, you're back. Cool, welcome.");
            } else if (lex == 1) {
                lex_says.setText("Thanks for still using me. My expectations were very low.");
            } else if (lex == 2) {
                lex_says.setText("Well those results were, um, shocking.");
            } else if (lex == 3) {
                lex_says.setText("Have you heard of the world's most powerful Pokémon? No? I don't blame you.");
            } else if (lex == 4) {
                lex_says.setText("Did you catch a new Pokémon recently?");
            } else if (lex == 5) {
                lex_says.setText("Are you even trying to battle?");
            }
        } else {
            if (lex == 0) {
                lex_says.setText("Currently it is " + currentTime + ". That's prime Pokemon time!");
            } else if (lex == 1) {
                lex_says.setText("Hello my name is Lexter. I'm not a Pokédex like my brother I just do math what can I do for you.");
            } else if (lex == 2) {
                lex_says.setText("I'm juiced up, lets GO!");
            } else if (lex == 3) {
                lex_says.setText("Have you heard of the world's most powerful trainer on top of Mt. Silver? It's said he got to the top with only a Rattata.");
            } else if (lex == 4) {
                lex_says.setText("Hey coolest trainer in town. What's up?");
            } else if (lex == 5) {
                lex_says.setText("I heard once that Jeremiah CremeSlide ruined an entire hockey rink. That's might just be a myth.");
            }
        }

        button5.setOnClickListener(new View.OnClickListener() {// For the enter button
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Random rull = new Random();
                int twosound = rull.nextInt(2);
                Button diceroll = (Button) findViewById(R.id.diceroll);
                Random r = new Random();// D100 roll
                int roll = r.nextInt(100) + 1;
                if (twosound == 1) {
                    rollsound.start();
                } else {
                    rollsound2.start();
                }
                diceroll.setText("" + roll);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {// For the enter button
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Button diceroll2 = (Button) findViewById(R.id.diceroll2);
                Random r = new Random();// D100 roll
                Random rull = new Random();
                int twosound2 = rull.nextInt(2);
                int roll = r.nextInt(20) + 1;
                if (twosound2 == 1) {
                    rollsound.start();
                } else {
                    rollsound2.start();
                }
                diceroll2.setText("" + roll);
            }
        });

    }


    public void openMainActivity() {// main activity is the attack activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAccActivity() {// main activity is the accuracy activity
        Intent intent = new Intent(this, Accuracyacticity.class);
        startActivity(intent);
    }

    public void openDodgeActvity() {// main activity is the dodge activity
        Intent intent = new Intent(this, DodgeActivity.class);
        startActivity(intent);
    }

    public void openCaptureActivity() {// main activity is the capture activity
        Intent intent1 = new Intent(MainHead.this, captureactivity.class);
        startActivity(intent1);
    }

    public void openEXPActivity() {// main activity is the EXP activity
        Intent intent2 = new Intent(MainHead.this, EXPactivity.class);
        startActivity(intent2);
    }

    public void openResourceActvity() {// main activity is the Resource activity
        Intent intent2 = new Intent(MainHead.this, Resources.class);
        startActivity(intent2);
    }


    public void openPriceActvity() {// main activity is the price activity
        Intent intent2 = new Intent(MainHead.this, Prices.class);
        startActivity(intent2);
    }
    public void openStatsActvity() {// main activity is the stats activity
        Intent intent3 = new Intent(MainHead.this, Statsactivity.class);
        startActivity(intent3);
    }

    public void displayToast(String message) {// click on Lexter Toast message
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void showfeedback(View view) {// display feedback and go to Docs
        displayToast(getString(R.string.touch));
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView5);
        imageView1.setImageResource(R.mipmap.lexter_sup_foreground);// 160 zoom
        imageView1.requestLayout();

        String url = (String) view.getTag();// for the toast message
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        intent.setData(Uri.parse(url));// Pass the url to intent data

        startActivity(intent);
    }
}
