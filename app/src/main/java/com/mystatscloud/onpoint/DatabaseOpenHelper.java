package com.mystatscloud.onpoint;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper

{
	private static final String DATABASE_NAME = "onPoint.db";
	private static final int DATABASE_VERSION = 2;

	public DatabaseOpenHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		setForcedUpgrade();
	}


}