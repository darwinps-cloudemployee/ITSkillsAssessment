package com.assessment.it.itskillsassessment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.assessment.it.itskillsassessment.data.DatabaseHelper;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        float result = intent.getFloatExtra("result",0);
        String username = intent.getStringExtra("username");
        int finalResult = (int)(result * 100);
        TextView resultText = (TextView) findViewById(R.id.textView_result);
        Button takeExamButton = (Button) findViewById(R.id.button_retake);
        Button exitButton = (Button) findViewById(R.id.button_exit);

        String additionalText = "";

        if(finalResult < 50)
        {
            additionalText = "You need to review more, your score is too low";
        }

        if(finalResult > 50)
        {
            additionalText = "Congratulations you pass the exam";
        }


        resultText.setText("You got " + finalResult  + "%. " + additionalText);

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITSkillsAssessmentContract.ResultEntry.COLUMN_FULLNAME, String.valueOf(username));
        values.put(ITSkillsAssessmentContract.ResultEntry.COLUMN_RESULT,String.valueOf(finalResult));
        long user_id = writableDatabase.insert(ITSkillsAssessmentContract.ResultEntry.TABLE_NAME,null,values);

        takeExamButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });


        exitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,DifficultySelectActivity.class);
                startActivity(intent);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
