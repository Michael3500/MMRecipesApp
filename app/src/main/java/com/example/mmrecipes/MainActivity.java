package com.example.mmrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
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

        drawerLayout = findViewById(R.id.drawerLayout_main);
        navigationView =findViewById(R.id.navigationView_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout myTabLayout = (TabLayout)findViewById(R.id.tablayout);
        myTabLayout.addTab(myTabLayout.newTab().setText("Breakfast").setIcon(R.drawable.breakfast));
        myTabLayout.addTab(myTabLayout.newTab().setText("Lunch").setIcon(R.drawable.lunch));
        myTabLayout.addTab(myTabLayout.newTab().setText("Snack").setIcon(R.drawable.snack));
        myTabLayout.addTab(myTabLayout.newTab().setText("Dinner").setIcon(R.drawable.dinner));
        myTabLayout.addTab(myTabLayout.newTab().setText("Dessert").setIcon(R.drawable.dessert));
        myTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager myViewpager = (ViewPager)findViewById(R.id.viewpager);
        PageAdapter myPageAdapter = new PageAdapter(getSupportFragmentManager(), myTabLayout.getTabCount());
        myViewpager.setAdapter(myPageAdapter);
        myViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(myTabLayout));
        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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
                Intent intent2 = new Intent(this, SendRecipeActivity.class);
                startActivity(intent2);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
