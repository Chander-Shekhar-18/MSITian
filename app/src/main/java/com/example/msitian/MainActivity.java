package com.example.msitian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.msitian.ebook.EbookActivity;
import com.example.msitian.ebook.PlacementActivity;
import com.example.msitian.fees.FeeStructure;
import com.example.msitian.recruiter.RecruiterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottamNavigation);
        NavController navController = Navigation.findNavController(this, R.id.frame_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("MSITians");

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_eBook:
                startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.navigation_theme:
                startActivity(new Intent(this, FeeStructure.class));
                break;
            case R.id.navigation_website:
                startActivity(new Intent(this, WebsiteActivity.class));
                break;
            case R.id.navigation_placement:
                startActivity(new Intent(this, PlacementActivity.class));
                break;
            case R.id.navigation_rate:
                Toast.makeText(MainActivity.this, "Rate Fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_developer:
                gotoUrl("https://www.linkedin.com/in/chander-shekhar-721b301a4/");
                break;
            case R.id.navigation_recruiter:
                startActivity(new Intent(this, RecruiterActivity.class));
                break;
            case R.id.navigation_logOut:
                logout();
                break;
        }
        return false;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
        Toast.makeText(MainActivity.this, "Log Out Successfully", Toast.LENGTH_SHORT).show();
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}






















