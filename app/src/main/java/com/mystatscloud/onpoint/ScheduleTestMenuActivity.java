package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


/**
 * Implementation of schedule a test menu
 */
public class ScheduleTestMenuActivity extends ActionBarActivity
{

    /**
     * Display schedule a test menu layout when created
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule_test_menu);
    }

    /**
     * Schedule a test by contacting OnPoint representative
     * @param view, current view of the screen
     */
    public void phoneContact(View view)
    {
        Intent myIntent = new Intent(ScheduleTestMenuActivity.this, ScheduleTestPhoneActivity.class);

        ScheduleTestMenuActivity.this.startActivity(myIntent);
    }

    // Schedule a test by filling out a form
    public void formContact(View view)
    {
        Intent myIntent = new Intent(ScheduleTestMenuActivity.this, ScheduleTestFormActivity.class);

        ScheduleTestMenuActivity.this.startActivity(myIntent);
    }

}
