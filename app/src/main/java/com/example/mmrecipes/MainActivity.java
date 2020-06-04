package com.example.mmrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TabLayout myTabLayout = (TabLayout)findViewById(R.id.tablayout);
        myTabLayout.addTab(myTabLayout.newTab().setText("Breakfast").setIcon(R.drawable.breakfast));
        myTabLayout.addTab(myTabLayout.newTab().setText("Lunch").setIcon(R.drawable.lunch));
        myTabLayout.addTab(myTabLayout.newTab().setText("Dinner").setIcon(R.drawable.dinner));
        myTabLayout.addTab(myTabLayout.newTab().setText("Snacks").setIcon(R.drawable.snack));
        myTabLayout.addTab(myTabLayout.newTab().setText("Desserts").setIcon(R.drawable.dessert));
        myTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        drawerLayout = findViewById(R.id.drawerLayout_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_sidemenu_home:
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.item_sidemenu_sendYourRecipe:
                Intent intent2 = new Intent(MainActivity.this, SendRecipeActivity.class);
                startActivity(intent2);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
