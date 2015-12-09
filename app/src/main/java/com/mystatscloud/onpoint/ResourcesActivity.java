package com.mystatscloud.onpoint;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

// This class holds all the data and logic for the Resources activity
public class ResourcesActivity extends ActionBarActivity {

	ListView resourcesView;  // the main view for the activity

    // Override the onCreate interface method
	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);  // make sure onCreate is called for parent class

        // current pdf resources from the ACT website
        String titles[] = {"Employer Brochure","Employer Handbook","Career Seeker Brochure","Bookmark"};
        String links[] = {"www.act.org/certificate/pdf/NCRC-EmployersBrochure.pdf","www.act.org/certificate/pdf/NCRCEmployersHandbook",
                "www.act.org/certificate/pdf/NCRC-CareerSeekersBrochure.pdf","www.act.org/certificate/pdf/NCRC-Certify-Skills-Bookmark.pdf"};
        String details[] = {"(PDF; 4 Pages, 532 KB)","(PDF; 36 Pages, 1 MB)","(PDF; 4 pages, 483 KB)","(PDF; 1 page, 389 KB)"};
        int imgResource = R.drawable.pdf_icon; // pdf icon for user to identify file type

        setContentView(R.layout.activity_resources); // set the xml file being used to draw the activity

        resourcesView = (ListView) findViewById(R.id.resources_layout);  // fetch the list view in the xml file to operate on

        // create an adapter to move data to the list view
        ResourcesAdapter adapter = new ResourcesAdapter(getApplicationContext(), R.layout.fragment_resources_row);

        // populate the data adapter with the resource items
        int i;
        int ResourceCount = 4;
        for (i = 0; i < ResourceCount; i++) {

            ResourceClass obj = new ResourceClass( titles[i], links[i], details[i], imgResource);
            adapter.add(obj);

        }

        // link the data adapter to the list view for rendering
        resourcesView.setAdapter(adapter);
    }
}
