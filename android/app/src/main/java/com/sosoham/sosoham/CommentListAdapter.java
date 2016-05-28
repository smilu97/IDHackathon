package com.sosoham.sosoham;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Yeong Jin on 2016-05-29.
 */
public class CommentListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<CommentListItem> data;
    private int layout;

    public CommentListAdapter(Context context, int layout, ArrayList<CommentListItem> data) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public CommentListItem getItem(int position) {
        return data.get(position);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        CommentListItem comDat = data.get(position);
        ImageView profileImage = (ImageView)convertView.findViewById(R.id.actionProfileImage);
        ImageView actionImage = (ImageView)convertView.findViewById(R.id.actionImage);
        TextView nameText = (TextView)convertView.findViewById(R.id.commentNameText);
        URL imageurl = null;
        try {
            imageurl = new URL(comDat.GetProfileUrl());
            Bitmap imageBitmap = null;
            try {
                imageBitmap = BitmapFactory.decodeStream(imageurl.openConnection().getInputStream());
                profileImage.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        nameText.setText(comDat.GetName());

        return convertView;
    }
}
