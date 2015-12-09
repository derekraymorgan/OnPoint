package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class PracticeExamMenuActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_practice_exam_menu);

	}

	/** Called when the user clicks the skill based test menu item button */
	public void showSkillTestView(View view)
	{
		Intent myIntent = new Intent(PracticeExamMenuActivity.this, SkilledTestMenuActivity.class);

		PracticeExamMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the category based test menu item button */
	public void showCategoryTestView(View view)
	{
		Intent myIntent = new Intent(PracticeExamMenuActivity.this, CategoryTestMenuActivity.class);

		PracticeExamMenuActivity.this.startActivity(myIntent);
	}

}
