package com.mystatscloud.onpoint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PracticeExamActivity extends ActionBarActivity
{

	public List<ExamQuestion> testQuestions;
	public int lastQuestionIndex = 0;
	private int selectedAnswerIndex = -1;
	private examAnswers currentAnswers;
	public int totalCorrectAnswers = 0;

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


		/*
		Context context = getApplicationContext();
		CharSequence text = Integer.toString(currentQuestion.questionID);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		*/

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

		currentAnswers = databaseAccess.getExamAnswers(questionID);

		databaseAccess.close();


		// get the answer list view
		ListView answerListView = (ListView) findViewById(R.id.answerListView);

		answerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				selectedAnswerIndex = position;
			}

		});


		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, currentAnswers.answerList);

		answerListView.setAdapter(adapter);


	}

	/**
	 * submit the selected answer and show next question
	 * @param view
	 */
	public void submitAnswer(View view)
	{

		// if answer is correct then
		if(currentAnswers.correctAnswer == selectedAnswerIndex)
		{
			// +1 to correct answers
			totalCorrectAnswers += 1;

			AlertDialog.Builder builder = new AlertDialog.Builder(PracticeExamActivity.this);

			builder.setMessage("Correct Answer!");

			builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					readyNextQuestion();
				}

			});


			AlertDialog alert = builder.create();

			alert.show();

		}

		// else if answer was wrong then
		else
		{

			DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

			databaseAccess.open();

			// get the test questions
			final String explainedAnswer = databaseAccess.getExplainedAnswer(currentAnswers.questionID);

			databaseAccess.close();

			AlertDialog.Builder builder = new AlertDialog.Builder(PracticeExamActivity.this);

			builder.setMessage("Wrong Answer!");

			builder.setNegativeButton("Show Answer Explanation", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{

					AlertDialog.Builder alert = new AlertDialog.Builder(PracticeExamActivity.this);

					alert.setTitle("Answer Explained");

					alert.setMessage(explainedAnswer);

					alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int id)
						{
							readyNextQuestion();
						}

					});

					alert.show();

				}
			});

			builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					readyNextQuestion();
				}

			});

			AlertDialog alert = builder.create();

			alert.show();

		}


	}

	private void readyNextQuestion()
	{
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

			TextView scoreTextView = (TextView) findViewById(R.id.score);

			DecimalFormat scoreFormat = new DecimalFormat("##.#");

			scoreFormat.setRoundingMode(RoundingMode.CEILING);

			scoreTextView.setText(scoreFormat.format((float)totalCorrectAnswers/testQuestions.size()*100)+"%");

		}
	}

}
