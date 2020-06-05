package com.example.mmrecipes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

public class DinnerFragment extends Fragment {

    CustomRecipesGridView myRecipesAdapter;
    GridView myGridView;
    DatabaseHelper databaseHelper;
    List<Recipe> storedRecipes;
    String tabName = "Dinner";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dinner, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        storedRecipes = databaseHelper.getAllRecipes(tabName);

        myGridView = (GridView)view.findViewById(R.id.gridview_dinner);
        myRecipesAdapter = new CustomRecipesGridView(getContext(),  storedRecipes);

        myGridView.setAdapter(myRecipesAdapter);

        return view;
    }
}
