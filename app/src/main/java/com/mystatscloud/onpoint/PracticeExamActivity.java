package com.mystatscloud.onpoint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity that implements the actions needed to take the practice test
 */
public class PracticeExamActivity extends ActionBarActivity
{

	// a lit off all the questions for the practice test to deliver to the user
	public List<ExamQuestion> testQuestions;

	// a the exam answer object which contains alist of possible answers and the index to the correct answer
	private examAnswers currentAnswers;

	public int totalCorrectAnswers = 0;
	public int lastQuestionIndex = 0;
	private int selectedAnswerIndex = -1;
	private String currentQuestionImageID = "none";
	private boolean showedTip = false;


	/**
	 * Connect the activity to the correct view and methods
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		// set the view for the exam questions
		setContentView(R.layout.activity_practice_exam);


		// grab some saved data from the last activity
		Bundle selectedValues = getIntent().getExtras();

		ArrayList<String> selectedSkills = new ArrayList<String>();
		ArrayList<String> selectedCategories = new ArrayList<String>();


		if(getIntent().hasExtra("selectedSkills"))
		{
			selectedSkills = selectedValues.getStringArrayList("selectedSkills");
		}

		if(getIntent().hasExtra("selectedCategories"))
		{
			selectedCategories = selectedValues.getStringArrayList("selectedCategories");
		}

		// create an instance of the DB
		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		// open the DB
		databaseAccess.open();

		// get the test questions
		testQuestions = databaseAccess.getTestQuestions(selectedSkills, selectedCategories);

		databaseAccess.close();

		// show the next test question, which happens to also be the first test question
		showNextQuestion();

		// this code is strictly used for debugging purposes
		/*
		Context context = getApplicationContext();
		CharSequence text = testQuestions.get(0);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		*/

	}


	/**
	 * Show the next question that is stored in the testQuestions member variable
	 */
	public void showNextQuestion()
	{

		// show the correct view
		setContentView(R.layout.activity_practice_exam);

		// get the current question
		ExamQuestion currentQuestion = testQuestions.get(lastQuestionIndex);


		// used for debugging
		/*
		Context context = getApplicationContext();
		CharSequence text = Integer.toString(currentQuestion.questionID);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		*/

		// some question have an image associated with them, get the question image view
		ImageView questionImageView = (ImageView) findViewById(R.id.questionImage);


		// load the question image if there is one
		if(currentQuestion.resource != 0)
		{

			if(!showedTip)
			{
				showedTip = true;

				Toast toast = Toast.makeText(this, "Touch the image to zoom in", Toast.LENGTH_LONG);
				toast.show();
			}



			// a simple boolean from the sql DB will tell us if there is an image associated with
			// this question. If there is an image then use the questions primary key to construct
			// the image file path
			String uri = "@drawable/q" + currentQuestion.questionID;

			this.currentQuestionImageID = uri;

			// get the translate the image file path to the internal resource identifier for this image
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			// get the image using the internal resource identifier
			Drawable questionResource = getResources().getDrawable(imageResource);

			// put the image inside of the image view on the screen
			questionImageView.setImageDrawable(questionResource);

		}

		else
		{
			// no image with this question so remove old image by setting to transparent color
			questionImageView.setImageResource(android.R.color.transparent);

			this.currentQuestionImageID = "none";
		}


		// get the question text view
		TextView questionTextView = (TextView) findViewById(R.id.questionText);

		// load the question text
		questionTextView.setText(currentQuestion.question);


		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		int questionID = currentQuestion.questionID;

		// load the possible answers for the current question
		currentAnswers = databaseAccess.getExamAnswers(questionID);

		databaseAccess.close();

		// get the answer list view
		ListView answerListView = (ListView) findViewById(R.id.answerListView);

		// add an onclick listener to all of the answers so that the user can select an
		// answer by touching it
		answerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			// when an answer is selected then get the index of the selected answer
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				selectedAnswerIndex = position;
			}

		});

		// load the answer list along with the click listeners into a list
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, currentAnswers.answerList);

		// shove them into the list view
		answerListView.setAdapter(adapter);

		TextView currentQuestionNumberView = (TextView) findViewById(R.id.questionNumber);

		currentQuestionNumberView.setText("Question " + Integer.toString(this.lastQuestionIndex+1) + " of 10");


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

			// tell the user that the answer is correct
			AlertDialog.Builder builder = new AlertDialog.Builder(PracticeExamActivity.this);

			builder.setMessage("Correct Answer!");

			// create a button for the user to precede to the next question
			builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener()
			{
				// when the user clicks the button the generate the next question
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

			// get the correct answer explanation from database
			DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

			databaseAccess.open();

			// get the test questions
			final String explainedAnswer = databaseAccess.getExplainedAnswer(currentAnswers.questionID);

			databaseAccess.close();

			AlertDialog.Builder builder = new AlertDialog.Builder(PracticeExamActivity.this);

			// tell the user that the answer picked was wrong
			builder.setMessage("Wrong Answer!");

			// build a button for the user to view the answer explanation
			builder.setNegativeButton("Show Answer Explanation", new DialogInterface.OnClickListener()
			{

				public void onClick(DialogInterface dialog, int id)
				{

					AlertDialog.Builder alert = new AlertDialog.Builder(PracticeExamActivity.this);

					alert.setTitle("Answer Explained");

					alert.setMessage(explainedAnswer);

					// after user views answer explained then allow user to click ok and then process to next question
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

		// test if there are more questions to ask the user
		if(lastQuestionIndex < testQuestions.size())
		{
			showNextQuestion();
		}

		// if no more questions then save/show the high score
		else
		{
			// set the view for the exam questions
			setContentView(R.layout.activity_exam_complete);

			TextView scoreTextView = (TextView) findViewById(R.id.score);

			DecimalFormat scoreFormat = new DecimalFormat("##.#");

			scoreFormat.setRoundingMode(RoundingMode.CEILING);

			// show the user the current test score
			scoreTextView.setText(scoreFormat.format((float) totalCorrectAnswers / testQuestions.size() * 100) + "%");

			Float score = (float) totalCorrectAnswers / testQuestions.size() * 100;

			// retrieve the previous high score
			SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();

			Float highScore = sharedPref.getFloat("highScore", 0);

			// set the current high score
			if(highScore < score)
			{
				editor.putFloat("highScore", score);
				editor.commit();
				highScore = score;

			}

			TextView highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);

			// show the high score
			highScoreTextView.setText(scoreFormat.format((float) highScore) + "%");


		}
	}

	/** Called when the user clicks the category based test menu item button */
	public void showFullScreenImage(View view)
	{
		Intent myIntent = new Intent(this, FullScreenImageActivity.class);

		// save the selected skill levels choosen by the user
		myIntent.putExtra("currentQuestionImageID", this.currentQuestionImageID);

		this.startActivity(myIntent);
	}

}
