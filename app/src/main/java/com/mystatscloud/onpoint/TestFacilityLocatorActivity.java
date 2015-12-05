package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mystatscloud.onpoint.TestFacilityLocator.TestFacility;

import java.util.List;

public class TestFacilityLocatorActivity extends ActionBarActivity {

	ListView listView;
	EditText zipField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_facility_locator);

		final InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

		listView = (ListView) findViewById(R.id.test_facility_locator_list_view);
		zipField = (EditText) findViewById(R.id.test_facility_locator_zip_field);

		// When the number in the zipField is changed, query database for facility with given zip and update listview
		zipField.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override
			public void afterTextChanged(Editable s) {
				DatabaseAccess db = DatabaseAccess.getInstance(getApplicationContext());
				db.open();
				if (!zipField.getText().toString().isEmpty()) {
					// query database for facilities with given zip code
					List<TestFacility> facilities = db.findFacilityByZipCode(Integer.parseInt(zipField.getText().toString()));
					// Create adapter with list of facilities for listView to show
					TestFacilityAdapter adapter = new TestFacilityAdapter(facilities);
					listView.setAdapter(adapter);
				}
				db.close();
			}
		});

		// On ENTER or BACK button presses, hide soft input keyboards
		zipField.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					im.hideSoftInputFromWindow(zipField.getWindowToken(), 0);
					return true;
				} else if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_BACK)) {
					im.hideSoftInputFromWindow(zipField.getWindowToken(), 0);
					zipField.clearFocus();
					return true;
				}
				return false;
			}
		});


	}

	/*
	 * Adapts test facility location information for presenting in a list view in the test facility
	 * locator activity.
	 */
	private class TestFacilityAdapter extends ArrayAdapter<TestFacility> {
		public TestFacilityAdapter(List<TestFacility> facilities) {
			super(getApplicationContext(), 0, facilities);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_item_test_facility, null);
			}

			TestFacility facility = getItem(position);

			// Set list item address line to address from testFacility in list
			TextView address = (TextView) convertView.findViewById(R.id.list_item_test_facility_address);
			address.setText(facility.getAddress());

			// Set list item city, state, zip to corresponding values from testFacility in list
			TextView cityStateZip = (TextView) convertView.findViewById(R.id.list_item_test_facility_city_state_zip);
			cityStateZip.setText(facility.getCity() + ", " + facility.getState() + " " + facility.getZipCode());

			return convertView;
		}
	}
}