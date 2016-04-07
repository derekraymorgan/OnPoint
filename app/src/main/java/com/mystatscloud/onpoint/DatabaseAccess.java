package com.mystatscloud.onpoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mystatscloud.onpoint.TestFacilityLocator.TestFacility;
import com.mystatscloud.onpoint.expandListView.Classes.ExpandListChild;
import com.mystatscloud.onpoint.expandListView.Classes.ExpandListParent;

import java.util.ArrayList;
import java.util.List;


/**
 * Creates an instance of the onPoint database and provides methods for accessing the data.
 *
 */
public class DatabaseAccess
{


	private SQLiteOpenHelper openHelper;
	private SQLiteDatabase database;
	private static DatabaseAccess instance;

	/**
	 * Private constructor to avoid object creation from outside classes.
	 * @param  context
	 * @return an instance of the database open helper class
	 * @see DatabaseOpenHelper
	 */
	private DatabaseAccess(Context context)
	{
		this.openHelper = new DatabaseOpenHelper(context);
	}

	/**
	 *
	 * @param context
	 * @return an instance of the onPoint database
	 */
	public static DatabaseAccess getInstance(Context context)
	{
		// only create a new database when the database does not exist
		if (instance == null)
		{
			instance = new DatabaseAccess(context);
		}

		return instance;
	}

	/**
	 * Open the database connection.
	 */
	public void open()
	{
		this.database = openHelper.getWritableDatabase();
	}

	/**
	 * Close the database connection.
	 */
	public void close()
	{
		if (database != null)
		{
			this.database.close();
		}
	}

	/**
	 * Read all test questions from the database corresponding to selected skill level.
	 * @param skills the list of skill levels that we want to return questions for(Ex: [1,2,3] or [3,5,6]
	 * @return a List of test questions
	 */
	public List<ExamQuestion> getTestQuestions(List<String> skills, List<String> categories)
	{
		List<ExamQuestion> questions = new ArrayList<>();

		// create a raw SQL string. This will be used in the where VAR in (?)
		String skillsSqlString = "";

		for (int i = 0; i < skills.size(); i++)
		{
			// put commas between all the quoted where arguments
			if(i != skills.size() - 1)
			{
				skillsSqlString += '"' + skills.get(i) + '"' + ", ";
			}

			// only quote the where clause arguments
			else
			{
				skillsSqlString += '"' + skills.get(i) + '"';
			}

		}

		// create a raw SQL string. This will be used in the where VAR in (?)
		String categoriesSqlString = "";

		for (int i = 0; i < categories.size(); i++)
		{
			// put commas between all the quoted where arguments
			if(i != categories.size() - 1)
			{
				categoriesSqlString += '"' + categories.get(i) + '"' + ", ";
			}

			// only quote the where clause arguments
			else
			{
				categoriesSqlString += '"' + categories.get(i) + '"';
			}

		}

		// this line of code can be used for int/float type of arguments in the where clause.
		//skillsSqlString = TextUtils.join(",",skills);
		Log.d("Cate",categoriesSqlString);
		Log.d("Skill",skillsSqlString);

		// query the database
		Cursor cursor = database.rawQuery("SELECT * FROM testQuestions where level in (" + skillsSqlString + ") or type in (" + categoriesSqlString + ")", null);


		// go to the first record return from the database
		cursor.moveToFirst();

		// while there are still more record to get
		while (!cursor.isAfterLast())
		{
			// create a new exam question
			ExamQuestion newQuestion = new ExamQuestion(cursor.getInt(0), cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4));

			// add the question the the list of questions to be returned
			questions.add(newQuestion);

			cursor.moveToNext();

		}

		//close the database
		cursor.close();

		return questions;
	}

	/**
	 * Read the "explained answer" from the database using question id as PK.
	 * @param questionID the single primary key that corresponds to the questionID
	 * @return a List of test question answers
	 */
	public String getExplainedAnswer(int questionID)
	{
		// there is only one answer explanation per question, so just reurn it as a single string
		Cursor cursor = database.rawQuery("SELECT explainedAnswer FROM testAnswersExplained where questionID = " + questionID, null);

		cursor.moveToFirst();

		return cursor.getString(0);
	}

	/**
	 * Read all test answers from the database corresponding to question id.
	 * @param questionID the single primary key that corresponds to the questionID
	 * @return a List of test question answers
	 */
	public examAnswers getExamAnswers(int questionID)
	{

		// query the database for all answers that correspond to the questions primary key
		Cursor cursor = database.rawQuery("SELECT * FROM testAnswers where questionID = " + questionID, null);

		cursor.moveToFirst();

		// create a list of answers to return
		List<String> answers = new ArrayList<>();

		// need to keep a counter to find the index of the correct answer
		int correctAnswerIndex = 0;
		int correctAnswer = 0;

		// loop through all of the sql query results
		while (!cursor.isAfterLast())
		{

			// add each answer to a list
			answers.add(cursor.getString(1));

			// if the correct answer column(index 2) is set to true(true = 1 because there is no boolean type in sqlite)
			// then save the index of the correct answer
			if(cursor.getInt(2) == 1)
			{
				correctAnswer = correctAnswerIndex;
			}

			cursor.moveToNext();

			correctAnswerIndex += 1;

		}

		// create a new exam answers object and populate it with the info from the database
		examAnswers newAnswers = new examAnswers(questionID, answers, correctAnswer);

		cursor.close();

		// return the answer object
		return newAnswers;
	}

	/**
	 * Read all unique test question categories from the database.
	 *
	 * @return a List of test question categories
	 */
	public List<String> getTestCategories()
	{

		// a list to store the string values returned from the DB
		List<String> list = new ArrayList<>();

		// retrieve all of the unique question categories from the sql database
		Cursor cursor = database.rawQuery("SELECT distinct type FROM testQuestions", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast())
		{
			// these test categories are just string to add them to a list
			list.add(cursor.getString(0));

			cursor.moveToNext();

		}

		// close the DB
		cursor.close();

		// return the list
		return list;
	}

	/**
	 * Read all unique test question skill levels from the database.
	 *
	 * @return a List of test question skill levels
	 */
	public List<String> getTestSkillLevels()
	{
		// alist to return the string values from the DB
		List<String> list = new ArrayList<>();

		// query the DB for a distinct/unique skill level
		Cursor cursor = database.rawQuery("SELECT distinct level FROM testQuestions", null);

		cursor.moveToFirst();

		// loop through query results
		while (!cursor.isAfterLast())
		{

			// even though these skill values are integers we want them as strings
			list.add(cursor.getString(0));

			cursor.moveToNext();

		}

		// close the DB
		cursor.close();

		// return the  list of skill levels
		return list;
	}

	/**
	 * Query testFacilityLocations table for entry with given zip code
	 * @param zipCode Zip code of desired entry
	 * @return list of test facilities with the given zip code
	 */
	public List<TestFacility> findFacilityByZipCode(int zipCode)
	{
		List<TestFacility> facilities = new ArrayList<>();

		Cursor cursor = database.rawQuery("SELECT * FROM testFacilityLocations WHERE zipCode = "
											+ Integer.toString(zipCode) + ";", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			TestFacility facility = new TestFacility(cursor.getString(0), cursor.getString(1)
									, cursor.getString(2), cursor.getInt(3), cursor.getString(4)
									, cursor.getString(5));
			facilities.add(facility);

			cursor.moveToNext();
		}

		cursor.close();

		return facilities;
	}

	/**
	 * Query testFacilityLocations table for entry with given city or state
	 * @param string Name of city or state to query database
	 * @return List of test facilities that match the city or state
	 */
	public List<TestFacility> findFacilityByCityOrState(String string) {
		List<TestFacility> facilities = new ArrayList<>();

		Cursor cursor = database.rawQuery("SELECT * FROM testFacilityLocations "
				+ "WHERE UPPER(REPLACE(city, ' ', '')) LIKE UPPER(REPLACE('%"
				+ string + "%', ' ', '')) OR UPPER(state) LIKE ('" + string + "')", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			TestFacility facility = new TestFacility(cursor.getString(0), cursor.getString(1)
					, cursor.getString(2), cursor.getInt(3), cursor.getString(4)
					, cursor.getString(5));
			facilities.add(facility);

			cursor.moveToNext();
		}

		cursor.close();

		return facilities;
	}

	/**
	 * Read and populate a group of questions and answers for FAQs from database
	 *
	 * @return Array list of FAQ questions and answers
	 */

	public ArrayList<ExpandListParent> getFAQs()
	{
		ArrayList<ExpandListParent> groupList = new ArrayList<ExpandListParent>();
		ArrayList<ExpandListChild> answerList;
		ExpandListParent question;
		ExpandListChild answer;

		Cursor cursor = database.rawQuery("SELECT * FROM faqQA", null);

		cursor.moveToFirst();

		while(!cursor.isAfterLast())
		{
			// Make a new question, answer, and answer list
			question = new ExpandListParent();
			answer = new ExpandListChild();
			answerList = new ArrayList<ExpandListChild>();

			// Add values to created variables
			question.setName(cursor.getString(0));
			answer.setName((cursor.getString(1)));
			answer.setTag(null);

			// Add answer to answer list and the answer list to the question
			answerList.add(answer);
			question.setItems(answerList);

			// Add the question to group list
			groupList.add(question);

			// Move cursors
			cursor.moveToNext();
		}

		cursor.close();

		return groupList;
	}
}
