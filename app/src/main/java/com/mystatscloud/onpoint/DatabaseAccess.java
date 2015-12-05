package com.mystatscloud.onpoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mystatscloud.onpoint.TestFacilityLocator.TestFacility;

import java.util.ArrayList;
import java.util.List;

/**
 * Connects to the onPoint database
 */
public class DatabaseAccess
{


	private SQLiteOpenHelper openHelper;
	private SQLiteDatabase database;
	private static DatabaseAccess instance;

	/**
	 * Private constructor to avoid object creation from outside classes.
	 *
	 * @param context
	 */
	private DatabaseAccess(Context context)
	{
		this.openHelper = new DatabaseOpenHelper(context);
	}

	/**
	 * Return a singleton instance of DatabaseAccess.
	 *
	 * @param context the Context
	 * @return the instance of DabaseAccess
	 */
	public static DatabaseAccess getInstance(Context context)
	{
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
	 * Read all test questions from the database.
	 *
	 * @return a List of test questions
	 */

	public List<String> getTestQuestions()
	{
		List<String> list = new ArrayList<>();

		Cursor cursor = database.rawQuery("SELECT question FROM testQuestions", null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast())
		{

			list.add(cursor.getString(0));

			cursor.moveToNext();

		}

		cursor.close();

		return list;
	}

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
