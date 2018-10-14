package com.assessment.it.itskillsassessment.data;

import android.provider.BaseColumns;

public final class ITSkillsAssessmentContract {
    public static final class UserEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "user";
        //column (field) names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";

    }

    public static final class QuestionEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "questions";
        //column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTIONA = "optionA";
        public static final String COLUMN_OPTIONB = "optionB";
        public static final String COLUMN_OPTIONC = "optionC";
        public static final String COLUMN_OPTIOND = "optionD";
        public static final String COLUMN_ANSWER = "answer";
    }
}
