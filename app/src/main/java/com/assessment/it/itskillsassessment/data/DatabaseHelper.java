package com.assessment.it.itskillsassessment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract.UserEntry;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract.QuestionEntry;
import com.assessment.it.itskillsassessment.data.ITSkillsAssessmentContract.ResultEntry;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "itskills.db";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_USER_CREATE=
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY, " +
                    UserEntry.COLUMN_FULLNAME + " TEXT, " +
                    UserEntry.COLUMN_SCHOOL + " TEXT, " +
                    UserEntry.COLUMN_USERNAME + " TEXT, " +
                    UserEntry.COLUMN_PASSWORD + " TEXT, " +
                    UserEntry.COLUMN_ISADMIN + " INTEGER DEFAULT 0 " +
                    ")";

    private static final String TABLE_QUESTION_CREATE =
            "CREATE TABLE " + QuestionEntry.TABLE_NAME + " (" +
                    QuestionEntry._ID + " INTEGER PRIMARY KEY, " +
                    QuestionEntry.COLUMN_QUESTION + " TEXT, " +
                    QuestionEntry.COLUMN_OPTIONA + " TEXT, " +
                    QuestionEntry.COLUMN_OPTIONB + " TEXT, " +
                    QuestionEntry.COLUMN_OPTIONC + " TEXT, " +
                    QuestionEntry.COLUMN_OPTIOND + " TEXT, " +
                    QuestionEntry.COLUMN_ANSWER + " TEXT " +
                    ")";

    private static final String TABLE_RESULT_CREATE =
            "CREATE TABLE " + ResultEntry.TABLE_NAME + " (" +
                    ResultEntry._ID + " INTEGER PRIMARY KEY, " +
                    ResultEntry.COLUMN_FULLNAME + " TEXT, " +
                    ResultEntry.COLUMN_RESULT + " TEXT " +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_QUESTION_CREATE);
        db.execSQL(TABLE_RESULT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ResultEntry.TABLE_NAME);
        onCreate(db);
    }
}
