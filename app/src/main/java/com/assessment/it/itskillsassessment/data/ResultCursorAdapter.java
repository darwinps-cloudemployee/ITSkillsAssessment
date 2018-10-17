package com.assessment.it.itskillsassessment.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.assessment.it.itskillsassessment.R;

public class ResultCursorAdapter extends CursorAdapter {
    public ResultCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.result_list, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvFullname = (TextView) view.findViewById(R.id.tvFullname);
        TextView tvResult = (TextView) view.findViewById(R.id.tvResult);
        // Extract properties from cursor
        String body = cursor.getString(cursor.getColumnIndex("fullname"));
        int priority = cursor.getInt(cursor.getColumnIndex("result"));
        // Populate fields with extracted properties
        tvFullname.setText(body);
        tvResult.setText(String.valueOf(priority));
    }
}