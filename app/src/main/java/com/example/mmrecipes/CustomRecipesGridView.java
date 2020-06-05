package com.example.mmrecipes;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomRecipesGridView extends BaseAdapter {

    private Context myContext;
    public List<Recipe> myRecipes;

    public CustomRecipesGridView(Context context, List<Recipe> recipes) {
        this.myContext = context;
        this.myRecipes = recipes;
    }

    @Override
    public int getCount() {
        return myRecipes.size();
    }

    @Override
    public Object getItem(int position) {
        return myRecipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.recipesview, null);
        }

        Log.v("testing", "Hello World");
        ImageView imageView = (ImageView)gridView.findViewById(R.id.recipesview_image);
        imageView.setImageURI(Uri.parse(myRecipes.get(position).image));
        //Log.v("testingImageUpload", myRecipes.get(position).image);

        TextView textView1 = (TextView)gridView.findViewById(R.id.recipesview_title);
        textView1.setText(myRecipes.get(position).title);
        //Log.v("testingTextUpload", myRecipes.get(position).title);

        TextView textView2 = (TextView)gridView.findViewById(R.id.recipesview_description);
        textView2.setText(myRecipes.get(position).getDescription());

        TextView textView3 = (TextView)gridView.findViewById(R.id.recipesview_skillLevel);
        textView3.setText(myRecipes.get(position).getSkillLevel());

        return gridView;
    }
}
