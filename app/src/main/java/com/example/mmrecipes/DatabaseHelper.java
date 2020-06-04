package com.example.mmrecipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "RecipeApp.db";

    private String CREATE_USER_TABLE = "CREATE TABLE Users ( user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_fullname TEXT, user_email TEXT, user_password TEXT )";
    private String CREATE_RECIPE_TABLE = "CREATE TABLE Recipes ( recipe_id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_title TEXT, recipe_description TEXT, recipe_category TEXT," +
                                        "recipe_ingredients TEXT, recipe_quantities TEXT, recipe_steps TEXT, " +
                                        "recipe_vegan BOOLEAN, recipe_vegetarian BOOLEAN, recipe_gluten BOOLEAN, recipe_diary BOOLEAN, recipe_soy BOOLEAN, recipe_alcohol BOOLEAN, recipe_sesame BOOLEAN," +
                                        " recipe_seafood BOOLEAN, recipe_wheat BOOLEAN, recipe_celery BOOLEAN, recipe_nuts BOOLEAN, recipe_peanuts BOOLEAN, recipe_eggs BOOLEAN, recipe_pork BOOLEAN," +
                                        " recipe_prepTime INTEGER, recipe_cookTime INTEGER, recipe_servings INTEGER, recipe_skillLevel TEXT, recipe_image TEXT)";

    private String DROP_TABLE_USER = "DROP TABLE IF EXISTS Users";
    private String DROP_TABLE_RECIPE = "DROP TABLE IF EXISTS Recipes";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_RECIPE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_fullname", user.getFullname());
        values.put("user_email", user.getEmail());
        values.put("user_password", user.getPassword());
        db.insert("Users", null, values);
        db.close();
    }

    public boolean checkUser(String email) {
        String[] column = {"user_id"};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "user_email = ?";
        String[] selectionParameter = {email};

        Cursor cursor = db.query("Users", column, selection, selectionParameter, null, null, null);
        int cursorcount = cursor.getCount();
        db.close();
        if (cursorcount > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUser(String email, String password) {
        String[] column = {"user_id"};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "user_email = ? AND user_password = ?";
        String[] selectionParameter = {email, password};

        Cursor cursor = db.query("Users", column, selection, selectionParameter, null, null, null);
        int cursorcount = cursor.getCount();
        db.close();
        if (cursorcount > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recipe_title", recipe.getTitle());
        values.put("recipe_description", recipe.getDescription());
        values.put("recipe_category", recipe.getCategory());
        values.put("recipe_ingredients", recipe.getIngredients());
        values.put("recipe_quantities", recipe.getQuantities());
        values.put("recipe_steps", recipe.getSteps());
        values.put("recipe_vegan", recipe.isVegan());
        values.put("recipe_vegetarian", recipe.isVegetarian());
        values.put("recipe_gluten", recipe.isGluten());
        values.put("recipe_diary", recipe.isDiary());
        values.put("recipe_soy", recipe.isSoy());
        values.put("recipe_alcohol", recipe.isAlcohol());
        values.put("recipe_sesame", recipe.isSesame());
        values.put("recipe_seafood", recipe.isSeafood());
        values.put("recipe_wheat", recipe.isWheat());
        values.put("recipe_celery", recipe.isCelery());
        values.put("recipe_nuts", recipe.isNuts());
        values.put("recipe_peanuts", recipe.isPeanuts());
        values.put("recipe_eggs", recipe.isEggs());
        values.put("recipe_pork", recipe.isPork());
        values.put("recipe_prepTime", recipe.getPrepTime());
        values.put("recipe_cookTime", recipe.getCookTime());
        values.put("recipe_servings", recipe.getServing());
        values.put("recipe_skillLevel", recipe.getSkillLevel());
        values.put("recipe_image", recipe.getImage());
        db.insert("Recipes", null, values);
        db.close();
    }
}
