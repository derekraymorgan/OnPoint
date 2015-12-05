package com.mystatscloud.onpoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mystatscloud.onpoint.TestFacilityLocator.TestFacility;

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
	 *
	 * @return a List of test questions
	 */
	public List<String> getTestQuestions(List<String> skills)
	{
		List<String> questions = new ArrayList<>();

		String skillsSqlString = "";

		for (int i = 0; i < skills.size(); i++)
		{
			if(i != skills.size() - 1)
			{
				skillsSqlString += '"' + skills.get(i) + '"' + ", ";
			}

			else
			{
				skillsSqlString += '"' + skills.get(i) + '"';
			}

		}


		//skillsSqlString = TextUtils.join(",",skills);

		Cursor cursor = database.rawQuery("SELECT question FROM testQuestions where level in (" + skillsSqlString + ")", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast())
		{

			questions.add(cursor.getString(0));

			cursor.moveToNext();

		}

		cursor.close();

		return questions;
	}

	/**
	 * Read all unique test question categories from the database.
	 *
	 * @return a List of test question categories
	 */
	public List<String> getTestCategories()
	{
		List<String> list = new ArrayList<>();

		Cursor cursor = database.rawQuery("SELECT distinct type FROM testQuestions", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast())
		{

			list.add(cursor.getString(0));

			cursor.moveToNext();

		}

		cursor.close();

		return list;
	}

	/**
	 * Read all unique test question skill levels from the database.
	 *
	 * @return a List of test question skill levels
	 */
	public List<String> getTestSkillLevels()
	{
		List<String> list = new ArrayList<>();

		Cursor cursor = database.rawQuery("SELECT distinct level FROM testQuestions", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast())
		{

			list.add(cursor.getString(0));

			cursor.moveToNext();

		}

		cursor.close();

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

}
