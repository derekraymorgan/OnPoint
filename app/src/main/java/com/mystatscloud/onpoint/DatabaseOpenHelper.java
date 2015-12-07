package com.mystatscloud.onpoint;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * The main class which keeps track of the version of the database and forces an upgrade when the data is changed.
 *
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper
{

	private static final String DATABASE_NAME = "onPoint.db";

	private static final int DATABASE_VERSION = 5;

	public DatabaseOpenHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		setForcedUpgrade();
	}


}