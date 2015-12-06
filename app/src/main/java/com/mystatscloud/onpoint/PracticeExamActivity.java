package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

public class PracticeExamActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// set the view for the exam questions
		setContentView(R.layout.activity_practice_exam);

		Bundle selectedValues = getIntent().getExtras();

		ArrayList<String> selectedSkills = selectedValues.getStringArrayList("selectedSkills");

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		// get the test questions
		List<ExamQuestion> testQuestions = databaseAccess.getTestQuestions(selectedSkills);

		databaseAccess.close();

		for(ExamQuestion question: testQuestions)
		{

		}

		/*
		Context context = getApplicationContext();
		CharSequence text = testQuestions.get(0);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		*/


		/*
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testQuestions);

		this.listView.setAdapter(adapter);
		*/

	}

	protected void showQuestion(ExamQuestion exam_question)
	{

	/*
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_practice_exam);



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
