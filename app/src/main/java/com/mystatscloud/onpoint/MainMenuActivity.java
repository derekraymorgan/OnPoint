package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * This activity handles the main menu of the app
 */
public class MainMenuActivity extends ActionBarActivity
{

	/**
	 * connect the appropriate view to this activity
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_menu);
	}

	/** Called when the user clicks the about menu item button */
	public void showAboutView(View view)
	{
		// load the intent and start the about activity
		Intent myIntent = new Intent(MainMenuActivity.this, AboutActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the faq menu item button */
	public void showFaqView(View view)
	{

		// load the intent and start the FAQ activity
		Intent myIntent = new Intent(MainMenuActivity.this, FaqActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the schedule test menu item button */
	public void showResourcesView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, ResourcesActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the testimonials menu item button */
	public void showTestimonialsView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, TestimonialsActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the test facility locator menu item button */
	public void showTestFacilityLocatorView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, TestFacilityLocatorActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the practice exam button */
	public void showPracticeExamView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, PracticeExamMenuActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	/** Called when the user clicks the schedule test menu item button */
	public void showScheduleTestView(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, ScheduleTestMenuActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}

	public void showMoo(View view)
	{
		Intent myIntent = new Intent(MainMenuActivity.this, PdfRendererActivity.class);

		MainMenuActivity.this.startActivity(myIntent);
	}


}
