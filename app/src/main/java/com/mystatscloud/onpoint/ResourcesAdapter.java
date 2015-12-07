package com.mystatscloud.onpoint;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResourcesAdapter extends ArrayAdapter<ResourceClass> {

    static class ResourceBuffer{
        ImageView image;
        TextView link;
        TextView details;
    }

    private List<ResourceClass> resources = new ArrayList<>();

    public ResourcesAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(ResourceClass object){
        resources.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.resources.size();
    }

    @Override
    public ResourceClass getItem(int position) {
        return this.resources.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ResourceBuffer buffer;
        View row = convertView;
        LayoutInflater inflater;

        if( convertView == null ) {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.fragment_resources_row, parent, false);
            buffer = new ResourceBuffer();
            buffer.link = (TextView) row.findViewById(R.id.resources_link);
            buffer.details = (TextView) row.findViewById(R.id.resources_details);
            buffer.image = (ImageView) row.findViewById(R.id.resources_image);

            row.setTag(buffer);
        }
        else {
            buffer = (ResourceBuffer) row.getTag();
        }

        ResourceClass obj = getItem(position);
        buffer.image.setImageResource(obj.getImgResource());
        buffer.link.setText(obj.getLink());
        buffer.link.setTextColor(Color.DKGRAY);
        buffer.details.setText(obj.getDetails());
        buffer.details.setTextColor(Color.BLACK);

        return row;
    }
}

