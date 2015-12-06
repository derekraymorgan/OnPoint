package com.mystatscloud.onpoint;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PracticeExamActivity extends ActionBarActivity
{

	public List<ExamQuestion> testQuestions;
	public int lastQuestionIndex = 0;
	public int correctAnswers = 0;

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
		testQuestions = databaseAccess.getTestQuestions(selectedSkills);

		databaseAccess.close();

		showNextQuestion();

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


	public void showNextQuestion()
	{

		setContentView(R.layout.activity_practice_exam);

		// get the current question
		ExamQuestion currentQuestion = testQuestions.get(lastQuestionIndex);


		Context context = getApplicationContext();
		CharSequence text = Integer.toString(currentQuestion.questionID);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		// get the question image view
		ImageView questionImageView = (ImageView) findViewById(R.id.questionImage);


		// load the question image if there is one
		if(currentQuestion.resource != 0)
		{

			String uri = "@drawable/q" + currentQuestion.questionID;

			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			Drawable questionResource = getResources().getDrawable(imageResource);

			questionImageView.setImageDrawable(questionResource);
		}

		else
		{
			questionImageView.setImageResource(android.R.color.transparent);
		}



		// get the question text view
		TextView questionTextView = (TextView) findViewById(R.id.questionText);

		// load the question text
		questionTextView.setText(currentQuestion.question);

		// load the possible answers
		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		int questionID = currentQuestion.questionID;

		examAnswers examAnswers = databaseAccess.getExamAnswers(questionID);

		databaseAccess.close();


		// get the answer list view
		ListView answerListView = (ListView) findViewById(R.id.answerListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, examAnswers.answerList);

		answerListView.setAdapter(adapter);


	}



	/**
	 * submit the selected answer and show next question
	 * @param view
	 */
	public void submitAnswer(View view)
	{
		// get the selected answer

		// if answer is correct then

			// +1 to correct answers

		// else if answer was wrong then

			// show the solution explain

		// ask next question if there is one
		lastQuestionIndex += 1;

		if(lastQuestionIndex < testQuestions.size())
		{
			showNextQuestion();
		}

		else
		{
			// set the view for the exam questions
			setContentView(R.layout.activity_exam_complete);
		}


	}

}
