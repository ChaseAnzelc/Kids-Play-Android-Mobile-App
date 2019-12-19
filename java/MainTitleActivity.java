package cs.ucmo.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.varunest.sparkbutton.SparkButton;

public class MainTitleActivity extends AppCompatActivity implements View.OnClickListener  {

    SparkButton playGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);


        playGames = findViewById(R.id.start_button);

        System.out.println("In here");


        playGames.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        playGames.playAnimation();
        playGames.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent myIntent = new Intent(MainTitleActivity.this, MainActivity.class);
                MainTitleActivity.this.startActivity(myIntent);
                playGames.setEnabled(true);
            }
        }, 600);





    }







    }
