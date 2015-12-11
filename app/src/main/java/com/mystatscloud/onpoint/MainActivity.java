package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity
{

	/**
	 * This is the entry point for the whole application
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		// load the splash screen
		setContentView(R.layout.splash);

		// show the splash screen while the app is "loading"
		int secondsDelayed = 4;

		// create a new runnable so that the splash screen is not included in the 'back button'
		new Handler().postDelayed(new Runnable()
		{
			// this is similar to an inline function or lambda function
			public void run()
			{
				// create the main menu
				startActivity(new Intent(MainActivity.this, MainMenuActivity.class));

				// exit the old activity and destroy it so that a user cannot return to it
				finish();
			}

		}, secondsDelayed * 1000);

	}
}
