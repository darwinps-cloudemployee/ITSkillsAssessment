package com.assessment.it.itskillsassessment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.assessment.it.itskillsassessment.data.DatabaseHelper;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        //InitializeData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button loginButton = (Button) findViewById(R.id.button_Login);
        Button registerButton = (Button) findViewById(R.id.button_Register);
        final EditText usernameText = (EditText) findViewById(R.id.editText_Username);
        final EditText passwordText = (EditText) findViewById(R.id.editText_Password);

        registerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });



        loginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameText.getText().toString();
                String passWord = passwordText.getText().toString();
                String message = "";

                DatabaseHelper helper = new DatabaseHelper(v.getContext());
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] projection = {ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME,
                        ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD};

                String selection = ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME + " = ? " + " AND " +
                        ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD + " = ? ";
                String[] selectionArgs = {userName,passWord};
                Cursor c = db.query(ITSkillsAssessmentContract.UserEntry.TABLE_NAME,projection,
                        selection,selectionArgs,null,null,null);

                int i = c.getCount();
                //Log.d("Record Count", String.valueOf(i));

                if((userName.equals("")) && (passWord.equals("")))
                {
                    message = "Username and Password is blank";
                    Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
                else
                {
                    //if((userName.equals("test") && passWord.equals("test") ))
                    if(i > 0)
                    {
                        Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
                        startActivity(intent);
                       /* message = "User is registered";
                        Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }
                    else
                    {
                         message = "You are not registered";
                        Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                }


            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void InitializeData() {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
/*
        String query = "INSERT INTO user ("
                + ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME + ","
                + ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD + ")"
                + " VALUES (\"test\",\"test\"); ";

        db.execSQL(query);*/

        ContentValues values = new ContentValues();
        values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME,"test");
        values.put(ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD,"test");
        long user_id = db.insert(ITSkillsAssessmentContract.UserEntry.TABLE_NAME,null,values);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
