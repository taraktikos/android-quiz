package com.example.QuizGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Taras S. on 11.03.15.
 */
public class MyActivity extends Activity {

    List<Question> questionList;
    int score = 0;
    int qId = 0;
    Question currentQuestion;
    TextView textQuestion;
    RadioButton radioButtonA;
    RadioButton radioButtonB;
    RadioButton radioButtonC;
    RadioButton radioButtonD;
    Button buttonNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
        currentQuestion = questionList.get(qId);
        textQuestion = (TextView) findViewById(R.id.textView1);
        radioButtonA = (RadioButton) findViewById(R.id.radio0);
        radioButtonB = (RadioButton) findViewById(R.id.radio1);
        radioButtonC = (RadioButton) findViewById(R.id.radio2);
        radioButtonD = (RadioButton) findViewById(R.id.radio3);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                Log.d("your_answer", currentQuestion.getAnswer() + " " + answer.getText());
                if (currentQuestion.getAnswer().equals(answer.getText())) {
                    score ++;
                    Log.d("score", "Your score " + score);
                }
                if (qId < dbHelper.rowCount()) {
                    currentQuestion = questionList.get(qId);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(MyActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("score", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
        setQuestionView();
    }

    private void setQuestionView() {
        textQuestion.setText(currentQuestion.getQuestion());
        radioButtonA.setText(currentQuestion.getOptionA());
        radioButtonB.setText(currentQuestion.getOptionB());
        radioButtonC.setText(currentQuestion.getOptionC());
        radioButtonD.setText(currentQuestion.getOptionD());
        qId ++;
    }
}
