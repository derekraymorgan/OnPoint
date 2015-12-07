package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ResourcesActivity extends ActionBarActivity {

	ListView resourcesView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);


        resourcesView = (ListView) findViewById(R.id.resources_layout);
        ResourcesAdapter adapter = new ResourcesAdapter(getApplicationContext(), R.layout.fragment_resources_row);

        int i;
        int ResourceCount = 4;

        String tempLinks[] = {"Employer Brochure","Employer Handbook","Career Seeker Brochure","Bookmark"};
        String tempDetails[] = {"(PDF; 4 Pages, 532 KB)","(PDF; 36 Pages, 1 MB)","(PDF; 4 pages, 483 KB)","(PDF; 1 page, 389 KB)"};
        int tempImgResource = R.drawable.pdf_icon;

        for (i = 0; i < ResourceCount; i++) {

            ResourceClass obj = new ResourceClass(tempLinks[i], tempDetails[i], tempImgResource);
            adapter.add(obj);

        }

        resourcesView.setAdapter(adapter);
    }


}
