package com.example.aidebot;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

//import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    FloatingActionsMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab1 = findViewById(R.id.new_med);
        fab1.setOnClickListener(this);
        FloatingActionButton fab2 = findViewById(R.id.new_prescription);
        fab2.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu = findViewById(R.id.menu_fab);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Fragment per defecte
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TreatmentsFragment()).commit();
            navigationView.setCheckedItem(R.id.treatments);
        }
    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public void onClick(View v) {
        drawer.closeDrawer(GravityCompat.START);
        menu.collapse();
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.new_med:
                getSupportActionBar().setTitle("AideBot - New Medicine \uD83D\uDC8A");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewMedFragment()).commit();
                break;


            case R.id.new_prescription:
                getSupportActionBar().setTitle("AideBot - New Prescription \uD83D\uDCC3");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewPrescriptionFragment()).commit();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menu.collapse();
        switch (item.getItemId()) {
            case R.id.treatments:
                getSupportActionBar().setTitle("AideBot - Treatments \uD83C\uDFE5");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TreatmentsFragment()).commit();
                break;
            case R.id.history:
                getSupportActionBar().setTitle("AideBot - History \uD83D\uDCD6");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).commit();
                break;
            case R.id.inventory:
                getSupportActionBar().setTitle("AideBot - Inventory ⚖️");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InventoryFragment()).commit();
                break;
            case R.id.calendar:
                getSupportActionBar().setTitle("AideBot - Calendar \uD83D\uDCC5");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalendarFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
