package com.example.mmrecipes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendRecipeActivity extends AppCompatActivity {

    private final SendRecipeActivity activity = SendRecipeActivity.this;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Recipe recipe;

    ByteArrayOutputStream byteArrayOutputStream;
    ObjectOutputStream objectOutputStream;

    private NestedScrollView nestedScrollView;

    private TextInputLayout layout_title, layout_description, layout_category, layout_ingredients, layout_steps, layout_prepTime, layout_cookTime, layout_servings, layout_skillLevel;

    private EditText title, description, ingredients, steps, prepTime, cookTime, servings;

    private CheckBox vegan, vegetarian, gluten, diary, soy, alcohol, sesame, seafood, wheat, celery, nuts, peanuts, eggs, pork;

    private ImageView image;
    private String imageUri;

    private Spinner spinnerCategory, spinnerSkillLevel;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private String category = null;
    private String skillLevel = null;

    Button btn_submit;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_recipe);

        if(ContextCompat.checkSelfPermission(SendRecipeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SendRecipeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if(ContextCompat.checkSelfPermission(SendRecipeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SendRecipeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        initialView();
        initialObject();

    }

    private void pickImageFromGallery() {
        Intent intentImage = new Intent(Intent.ACTION_PICK);
        intentImage.setType("image/*");
        startActivityForResult(intentImage, IMAGE_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Uri imageData = data.getData();
            imageUri = imageData.toString();
            image.setImageURI(imageData);
        }
    }

    private void initialView() {
        nestedScrollView = findViewById(R.id.nestedScrollView_sendARecipe);

        layout_title = findViewById(R.id.textInputLayout_sendRecipe_title);
        layout_description = findViewById(R.id.textInputLayout_sendRecipe_description);
        layout_category = findViewById(R.id.textInputLayout_sendRecipe_category);
        layout_ingredients = findViewById(R.id.textInputLayout_sendRecipe_ingredients);
        layout_steps = findViewById(R.id.textInputLayout_sendRecipe_steps);
        layout_prepTime = findViewById(R.id.textInputLayout_sendRecipe_prepTime);
        layout_cookTime = findViewById(R.id.textInputLayout_sendRecipe_cookTime);
        layout_servings = findViewById(R.id.textInputLayout_sendRecipe_servings);
        layout_skillLevel = findViewById(R.id.textInputLayout_sendRecipe_skillLevel);

        title = findViewById(R.id.editText_sendRecipe_title);
        description = findViewById(R.id.editText_sendRecipe_description);
        ingredients = findViewById(R.id.editText_sendRecipe_ingredients);
        steps = findViewById(R.id.editText_sendRecipe_steps);
        prepTime = findViewById(R.id.editText_sendRecipe_prepTime);
        cookTime = findViewById(R.id.editText_sendRecipe_cookTime);
        servings = findViewById(R.id.editText_sendRecipe_servings);

        vegan = findViewById(R.id.checkbox_vegan);
        vegetarian = findViewById(R.id.checkbox_vegetarian);
        gluten = findViewById(R.id.checkbox_gluten);
        diary = findViewById(R.id.checkbox_diary);
        soy = findViewById(R.id.checkbox_soy);
        alcohol = findViewById(R.id.checkbox_alcohol);
        sesame = findViewById(R.id.checkbox_sesame);
        seafood = findViewById(R.id.checkbox_seafood);
        wheat = findViewById(R.id.checkbox_wheat);
        celery = findViewById(R.id.checkbox_celery);
        nuts = findViewById(R.id.checkbox_nuts);
        peanuts = findViewById(R.id.checkbox_peanuts);
        eggs = findViewById(R.id.checkbox_eggs);
        pork = findViewById(R.id.checkbox_pork);

        image = findViewById(R.id.imageButton_sendRecipe_image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery();
                    }
                }
                else{
                    pickImageFromGallery();
                }
            }
        });

        spinnerCategory = (Spinner)findViewById(R.id.spinner_sendRecipe_category);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.food_categories, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter1);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = spinnerCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSkillLevel = (Spinner)findViewById(R.id.spinner_sendRecipe_skillLevel);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.skill_level, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSkillLevel.setAdapter(adapter2);
        spinnerSkillLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skillLevel = spinnerSkillLevel.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit = findViewById(R.id.button_sendRecipe_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PostDataToSqlite();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialObject() {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(activity);
        recipe = new Recipe();
    }

    private void PostDataToSqlite() throws IOException {
        if (!inputValidation.isEdittextFilled(title, layout_title, getString(R.string.error_sendARecipe))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(description, layout_description, getString(R.string.error_sendARecipe))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(ingredients, layout_ingredients, getString(R.string.error_sendARecipe))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(steps, layout_steps, getString(R.string.error_sendARecipe))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(prepTime, layout_prepTime, getString(R.string.error_sendARecipe))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(cookTime, layout_cookTime, getString(R.string.error_sendARecipe))) {
            return;
        }
        if (!inputValidation.isEdittextFilled(servings, layout_servings, getString(R.string.error_sendARecipe))) {
            return;
        }

        recipe.setTitle(title.getText().toString());
        recipe.setDescription(description.getText().toString());
        recipe.setCategory(category);

        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(ingredients.getText().toString().split("\n"));
        objectOutputStream.flush();
        objectOutputStream.close();
        recipe.setIngredients(byteArrayOutputStream.toByteArray());

        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(steps.getText().toString().split("\n"));
        objectOutputStream.flush();
        objectOutputStream.close();
        recipe.setSteps(byteArrayOutputStream.toByteArray());

        recipe.setVegan(vegan.isChecked());
        recipe.setVegetarian(vegetarian.isChecked());
        recipe.setGluten(gluten.isChecked());
        recipe.setDiary(diary.isChecked());
        recipe.setSoy(soy.isChecked());
        recipe.setAlcohol(alcohol.isChecked());
        recipe.setSesame(sesame.isChecked());
        recipe.setSeafood(seafood.isChecked());
        recipe.setWheat(wheat.isChecked());
        recipe.setCelery(celery.isChecked());
        recipe.setNuts(nuts.isChecked());
        recipe.setPeanuts(peanuts.isChecked());
        recipe.setEggs(eggs.isChecked());
        recipe.setPork(pork.isChecked());
        recipe.setPrepTime(Integer.parseInt(prepTime.getText().toString()));
        recipe.setCookTime(Integer.parseInt(cookTime.getText().toString()));
        recipe.setServing(Integer.parseInt(servings.getText().toString()));
        recipe.setSkillLevel(skillLevel);
        recipe.setImage(imageUri);

        databaseHelper.addRecipe(recipe);

        Snackbar.make(nestedScrollView, getString(R.string.sendARecipe_success), Snackbar.LENGTH_LONG).show();
        emptyInputEdittext();
    }

    private void emptyInputEdittext() {
        title.setText(null);
        description.setText(null);
        ingredients.setText(null);
        steps.setText(null);
        vegan.setChecked(false);
        vegetarian.setChecked(false);
        gluten.setChecked(false);
        diary.setChecked(false);
        soy.setChecked(false);
        alcohol.setChecked(false);
        sesame.setChecked(false);
        seafood.setChecked(false);
        wheat.setChecked(false);
        celery.setChecked(false);
        nuts.setChecked(false);
        peanuts.setChecked(false);
        eggs.setChecked(false);
        pork.setChecked(false);
        prepTime.setText(null);
        cookTime.setText(null);
        servings.setText(null);
        image.setImageURI(null);
    }

}
