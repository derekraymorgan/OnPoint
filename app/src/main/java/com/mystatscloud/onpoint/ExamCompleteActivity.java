package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * This class might be used for a more in depth test score user experience. Could add functionality
 * to view high scores for skill levels or for category
 */
public class ExamCompleteActivity extends ActionBarActivity
{

	/**
	 * Connect the activity to the view
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_exam_complete);
	}

}
