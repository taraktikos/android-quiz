package com.example.QuizGame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Taras S. on 11.03.15.
 */
public class ResultActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
        TextView textView = (TextView) findViewById(R.id.textResult);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");
        Log.d("score", "ResultActivity Your score " + score);
        bar.setRating(score);
        switch (score) {
            case 1:
                textView.setText("Bad");
                break;
            case 2:
                textView.setText("Normal");
                break;
            case 3:
            case 4:
                textView.setText("Good");
                break;
            case 5:
                textView.setText("Awesome");
                break;
        }
    }

}
