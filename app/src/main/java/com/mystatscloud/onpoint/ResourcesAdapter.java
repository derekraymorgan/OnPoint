package com.mystatscloud.onpoint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ResourcesAdapter extends ArrayAdapter<ResourceClass> {

    // this static class is used to hold the information for the list-item being rendered
    static class ResourceBuffer{
        ImageView image;
        TextView title;
        TextView details;
    }

    // this list holds all list-items in the adapter
    private List<ResourceClass> resources = new ArrayList<>();

    // constructor
    public ResourcesAdapter(Context context, int resource) {
        super(context, resource);
    }

    // add method used to insert list-items into the adapter's internal list
    public void add(ResourceClass object){
        resources.add(object);
        super.add(object);
    }

    // getCount() interface method used to get the number of list-items in the adapter
    @Override
    public int getCount() {
        return this.resources.size();
    }

    // getItem() interface method used to get a particular list-item to work with
    @Override
    public ResourceClass getItem(int position) {
        return this.resources.get(position);
    }

    // getView() interface method detailing how each list-item in the adapter is rendered
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ResourceBuffer buffer;
        View row = convertView;
        LayoutInflater inflater;

        //  when views are created rapidly it is more efficient to convert a view already in memory rather than create new one
        // if the row for the list-item isn't being converted however then a new one needs to be created
        if( convertView == null ) {
            inflater = LayoutInflater.from(getContext()); // get a reference to the inflater being used to render views
            row = inflater.inflate(R.layout.fragment_resources_row, parent, false); // inflate a new row from the xml template
            buffer = new ResourceBuffer();  // create a buffer to hold a collection of views for the row
            buffer.title = (TextView) row.findViewById(R.id.resources_link);  // specify the text view used to show the title
            buffer.details = (TextView) row.findViewById(R.id.resources_details); // specify the text view used to show the details data
            buffer.image = (ImageView) row.findViewById(R.id.resources_image);  // specify the image view used to show the file-type icon

            row.setTag(buffer); // apply the views to the row
        }
        // otherwise use a set of views already in memory to render the new row
        else {
            buffer = (ResourceBuffer) row.getTag();
        }

        // regardless of a view being recycled or newly created, update the row for the item getView is being called on
        ResourceClass obj = getItem(position);  // get the item being rendered
        buffer.image.setImageResource(obj.getImgResource()); // set the image in the view to the image for this item
        buffer.title.setText(obj.getTitle());  // set the title in the view to the title for this item
        buffer.title.setTextColor(Color.DKGRAY);  // set the color of the text
        buffer.details.setText(obj.getDetails());  // set the details in the view to the details for this item
        buffer.details.setTextColor(Color.BLACK);  // set the color of the text

        // return the row to be rendered
        return row;
    }



}

