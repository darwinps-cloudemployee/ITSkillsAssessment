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
import android.widget.EditText;

import com.assessment.it.itskillsassessment.data.DatabaseHelper;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText txtUsername = (EditText) findViewById(R.id.editText_nUserName);
        final EditText txtPassword = (EditText) findViewById(R.id.editText_nPassword);
        Button btnAdd = (Button) findViewById(R.id.button_AddUser);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper helper = new DatabaseHelper(v.getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME, String.valueOf(txtUsername.getText()));
                values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD,String.valueOf(txtPassword.getText()));
                long user_id = db.insert(ITSkillsAssessmentContract.UserEntry.TABLE_NAME,null,values);

                if(user_id > 0)
                {
                    Intent intent = new Intent(RegisterUserActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });



    }

}
