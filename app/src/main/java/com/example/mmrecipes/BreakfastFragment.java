package com.example.mmrecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;


public class BreakfastFragment extends Fragment {

    CustomRecipesGridView myRecipesAdapter;
    GridView myGridView;
    DatabaseHelper databaseHelper;
    List<Recipe> storedRecipes;
    String tabName = "Breakfast";
    public byte[] myIngredients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        storedRecipes = databaseHelper.getAllRecipes(tabName);

        myGridView = (GridView)view.findViewById(R.id.gridview_breakfast);
        myRecipesAdapter = new CustomRecipesGridView(getContext(),  storedRecipes);

        myGridView.setAdapter(myRecipesAdapter);

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);

                myIngredients = storedRecipes.get(position).ingredients;


                intent.putExtra("title", storedRecipes.get(position).title);
                intent.putExtra("image", storedRecipes.get(position).image);
                intent.putExtra("description", storedRecipes.get(position).description);
                intent.putExtra("ingredients", storedRecipes.get(position).ingredients);
                intent.putExtra("steps", storedRecipes.get(position).steps);
                intent.putExtra("vegan", storedRecipes.get(position).vegan);
                intent.putExtra("vegetarian", storedRecipes.get(position).vegetarian);
                intent.putExtra("gluten", storedRecipes.get(position).gluten);
                intent.putExtra("diary", storedRecipes.get(position).diary);
                intent.putExtra("soy", storedRecipes.get(position).soy);
                intent.putExtra("alcohol", storedRecipes.get(position).alcohol);
                intent.putExtra("sesame", storedRecipes.get(position).sesame);
                intent.putExtra("seafood", storedRecipes.get(position).seafood);
                intent.putExtra("wheat", storedRecipes.get(position).wheat);
                intent.putExtra("celery", storedRecipes.get(position).celery);
                intent.putExtra("nuts", storedRecipes.get(position).nuts);
                intent.putExtra("peanuts", storedRecipes.get(position).peanuts);
                intent.putExtra("eggs", storedRecipes.get(position).eggs);
                intent.putExtra("pork", storedRecipes.get(position).pork);
                intent.putExtra("prepTime", storedRecipes.get(position).prepTime);
                intent.putExtra("cookTime", storedRecipes.get(position).cookTime);
                intent.putExtra("serving", storedRecipes.get(position).serving);
                intent.putExtra("skillLevel", storedRecipes.get(position).skillLevel);


                startActivity(intent);
            }
        });

        return view;
    }
}
