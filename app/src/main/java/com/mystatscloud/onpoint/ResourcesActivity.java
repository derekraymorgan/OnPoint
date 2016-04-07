package com.mystatscloud.onpoint;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// This class holds all the data and logic for the Resources activity
public class ResourcesActivity extends ActionBarActivity {

	ListView resourcesView;  // the main view for the activity

    // Override the onCreate interface method
	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);  // make sure onCreate is called for parent class

        // current pdf resources from the ACT website
        String titles[] = {"Employer Brochure","NCRC Information","WorkKeys Assessments Information"};
        String links[] = {"Employers.pdf","Info.pdf","WorkKeys.pdf"};
        String details[] = {"(PDF; 4 Pages, 2.4 MB)","(PDF; 2 Pages, 176.6 KB)","(PDF; 2 pages, 154.6 KB)"};
        int imgResource = R.drawable.pdf; // pdf icon for user to identify file type

        setContentView(R.layout.activity_resources); // set the xml file being used to draw the activity

        resourcesView = (ListView) findViewById(R.id.resources_layout);  // fetch the list view in the xml file to operate on

        // create an adapter to move data to the list view
        final ResourcesAdapter adapter = new ResourcesAdapter(getApplicationContext(), R.layout.fragment_resources_row);

        // populate the data adapter with the resource items
        int i;
        int ResourceCount = 3;
        for (i = 0; i < ResourceCount; i++) {

            ResourceClass obj = new ResourceClass( titles[i], links[i], details[i], imgResource);
            adapter.add(obj);

        }

        CopyAssets();

        // link the data adapter to the list view for rendering
        resourcesView.setAdapter(adapter);
        resourcesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPos = position;
                String itemValue = adapter.getItem(position).getLink();
                OpenPdf(itemValue);
            }
        });
    }

    public void OpenPdf(String fileName)
    {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+fileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void CopyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("brochures");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for(String filename : files) {
            System.out.println("File name => "+filename);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("brochures/"+filename);   // if files resides inside the "Files" directory itself
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() +"/" + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(Exception e) {
                Log.e("tag", e.getMessage());
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
