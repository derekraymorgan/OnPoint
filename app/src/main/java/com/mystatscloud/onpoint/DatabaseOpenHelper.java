package com.mystatscloud.onpoint;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * The main class which keeps track of the version of the database and forces an upgrade when the data is changed.
 *
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper
{

	// the name of the database that we are opening
	private static final String DATABASE_NAME = "onPoint.db";

	// the version of the database that we are currently working with
	private static final int DATABASE_VERSION = 17;

	public DatabaseOpenHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		// this particular database is not for specific user data but instead for informational and
		// system data so force the database to upgrade when a new database is available
		setForcedUpgrade();
	}


}