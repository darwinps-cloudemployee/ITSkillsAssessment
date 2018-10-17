package com.assessment.it.itskillsassessment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.assessment.it.itskillsassessment.data.DatabaseHelper;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract;
import com.assessment.it.itskillsassessment.data.ResultCursorAdapter;

public class AllResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        //String[] projection = {ITSkillsAssessmentContract.ResultEntry.COLUMN_FULLNAME,
         //       ITSkillsAssessmentContract.ResultEntry.COLUMN_RESULT};

        //String selection = ITSkillsAssessmentContract.UserEntry.COLUMN_USERNAME + " = ? " + " AND " +
                //ITSkillsAssessmentContract.UserEntry.COLUMN_PASSWORD + " = ? ";

        //String[] selectionArgs = {userName,passWord};
       // Cursor c = db.query(ITSkillsAssessmentContract.UserEntry.TABLE_NAME,projection,
        //        null,null,null,null,null);

        //TodoDatabaseHandler handler = new TodoDatabaseHandler(this);
        // Get access to the underlying writeable database
        //        SQLiteDatabase db = handler.getWritableDatabase();
        // Query for items from the database and get a cursor back
        //
        Cursor c = db.rawQuery("SELECT  * FROM results", null);

        // Find ListView to populate
        ListView lvItems = (ListView) findViewById(R.id.lv_results);
        // Setup cursor adapter using cursor from last step
        ResultCursorAdapter resultAdapter = new ResultCursorAdapter(this, c);
        // Attach cursor adapter to the ListView
        lvItems.setAdapter(resultAdapter);


    }

}
