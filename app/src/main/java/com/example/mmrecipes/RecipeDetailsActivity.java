package com.example.mmrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeDetailsActivity extends AppCompatActivity {

    TextView title, description, category, allergies, prepTime, cookTime, serving, skillLevel, ingredients, step;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        title = (TextView)findViewById(R.id.textView_recipeDetails_title);
        description = (TextView)findViewById(R.id.textView_recipeDetails_description);
        category = (TextView)findViewById(R.id.textView_recipeDetails_category);
        allergies = (TextView)findViewById(R.id.textView_recipeDetails_allergiesAndDiets);
        prepTime = (TextView)findViewById(R.id.textView_recipeDetails_prepTime);
        cookTime = (TextView)findViewById(R.id.textView_recipeDetails_cookTime);
        serving = (TextView)findViewById(R.id.textView_recipeDetails_servings);
        skillLevel = (TextView)findViewById(R.id.textView_recipeDetails_skillLevel);
        ingredients = (TextView)findViewById(R.id.textView_recipeDetails_ingredients);
        step = (TextView)findViewById(R.id.textView_recipeDetails_steps);

        image = (ImageView)findViewById(R.id.imageView_recipeDetails_image);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        category.setText(intent.getStringExtra("category"));
        //allergies.setText(intent.getStringExtra("allergies"));
        prepTime.setText(intent.getStringExtra("prepTime"));
        cookTime.setText(intent.getStringExtra("cookTime"));
        serving.setText(intent.getStringExtra("serving"));
        skillLevel.setText(intent.getStringExtra("skillLevel"));
        ingredients.setText(intent.getStringExtra("ingredients"));
        step.setText(intent.getStringExtra("steps"));
        image.setImageURI(Uri.parse(intent.getStringExtra("image")));

        String allAllergies = "Diets > ";

        if(intent.getStringExtra("vegan") == "true")
            allAllergies += "Vegan ";
        if(intent.getStringExtra("vegetarian") == "true")
            allAllergies += "Vegetarian ";
        allAllergies += "\nContains > ";
        if(intent.getStringExtra("gluten") == "true")
            allAllergies += "Gluten ";
        if(intent.getStringExtra("diary") == "true")
            allAllergies += "Diary ";
        if(intent.getStringExtra("soy") == "true")
            allAllergies += "Soy ";
        if(intent.getStringExtra("alcohol") == "true")
            allAllergies += "Alcohol ";
        if(intent.getStringExtra("sesame") == "true")
            allAllergies += "Sesame ";
        if(intent.getStringExtra("seafood") == "true")
            allAllergies += "Seafood ";
        if(intent.getStringExtra("wheat") == "true")
            allAllergies += "Wheat ";
        if(intent.getStringExtra("celery") == "true")
            allAllergies += "Celery ";
        if(intent.getStringExtra("nuts") == "true")
            allAllergies += "Nuts ";
        if( intent.getStringExtra("peanuts") == "true")
            allAllergies += "Peanuts ";
        if(intent.getStringExtra("eggs") == "true")
            allAllergies += "Eggs ";
        if(intent.getStringExtra("pork") == "true")
            allAllergies += "Pork ";

        allergies.setText(allAllergies);

    }
}
