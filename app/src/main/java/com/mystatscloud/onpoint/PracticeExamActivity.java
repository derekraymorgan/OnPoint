package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class PracticeExamActivity extends ActionBarActivity
{


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_practice_exam);

		/*
		this.listView = (ListView) findViewById(R.id.examListView);

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		List<String> testQuestions = databaseAccess.getTestQuestions();

		databaseAccess.close();

		Context context = getApplicationContext();
		CharSequence text = testQuestions.get(0);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testQuestions);

		this.listView.setAdapter(adapter);
		*/

	}

}
