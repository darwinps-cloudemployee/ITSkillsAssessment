package com.assessment.it.itskillsassessment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
        final EditText txtFullname = (EditText) findViewById(R.id.editText_Fullname);
        final EditText txtSchool = (EditText) findViewById(R.id.editText_School);
        Button btnAdd = (Button) findViewById(R.id.button_AddUser);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                DatabaseHelper helper = new DatabaseHelper(v.getContext());

                SQLiteDatabase readableDatabase = helper.getReadableDatabase();
                String[] projection = {ITSkillsAssessmentContract.UserEntry.COLUMN_FULLNAME,
                        ITSkillsAssessmentContract.UserEntry.COLUMN_SCHOOL,
                        ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME,
                        ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD,
                        ITSkillsAssessmentContract.UserEntry.COLUMN_ISADMIN};

                String selection = ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME + " = ? " + " AND " +
                        ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD + " = ? ";
                String[] selectionArgs = {txtUsername.getText().toString(),txtPassword.getText().toString()};
                Cursor c = readableDatabase.query(ITSkillsAssessmentContract.UserEntry.TABLE_NAME,projection,
                        selection,selectionArgs,null,null,null);

               // String fullname = c.getString(c.getColumnIndex(ITSkillsAssessmentContract.UserEntry.COLUMN_FULLNAME));
               // String school = c.getString(c.getColumnIndex(ITSkillsAssessmentContract.UserEntry.COLUMN_SCHOOL));
                //String username = c.getString(c.getColumnIndex(ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME));
                //String password = c.getString(c.getColumnIndex(ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD));
                //int isadmin = c.getInt(c.getColumnIndex(ITSkillsAssessmentContract.UserEntry.COLUMN_ISADMIN));


                if(c.getCount() > 0)
                {
                    Snackbar.make(v, "User is already registered", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    SQLiteDatabase writableDatabase = helper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME, String.valueOf(txtUsername.getText()));
                    values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD,String.valueOf(txtPassword.getText()));
                    values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_FULLNAME,String.valueOf(txtFullname.getText()));
                    values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_SCHOOL,String.valueOf(txtSchool.getText()));
                    values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_ISADMIN,0);
                    long user_id = writableDatabase.insert(ITSkillsAssessmentContract.UserEntry.TABLE_NAME,null,values);

                    if(user_id > 0)
                    {
                        Intent intent = new Intent(RegisterUserActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }


            }
        });



    }

}
