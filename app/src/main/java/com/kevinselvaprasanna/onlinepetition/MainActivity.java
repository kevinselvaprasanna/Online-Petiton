package com.kevinselvaprasanna.onlinepetition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kevinselvaprasanna.onlinepetition.Adapters.PetsAdapter;
import com.kevinselvaprasanna.onlinepetition.Helper.DatabaseHelper;
import com.kevinselvaprasanna.onlinepetition.Network.Connectivity;
import com.kevinselvaprasanna.onlinepetition.Network.PostRequest;
import com.kevinselvaprasanna.onlinepetition.Objects.Pet;
import com.kevinselvaprasanna.onlinepetition.Objects.PostParam;
import com.kevinselvaprasanna.onlinepetition.Objects.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayoutManager layoutManager;
    RecyclerView rvNot;
    ArrayList<Pet> pets;

    Boolean running = true;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreatePetition.class);
                startActivity(i);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        rvNot = (RecyclerView)findViewById(R.id.rvPets);
        layoutManager = new LinearLayoutManager(this);

        //set the recycler view to use the linear layout manager
        rvNot.setLayoutManager(layoutManager);

        // Load events from Database
        // events = Event.getAllRelevantEvents(getActivity());
        pets = Pet.getAllPets(this);

        //initialize events feed adapter
        PetsAdapter petsAdapter = new PetsAdapter(this, pets);

        //Use the events feed adapter
        rvNot.setAdapter(petsAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

       }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.action_create) {
            Intent i = new Intent(MainActivity.this, CreatePetition.class);
            startActivity(i);
            finish();
            return true;
        }else  if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create) {
            Intent i = new Intent(MainActivity.this, CreatePetition.class);
            startActivity(i);
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        UserProfile.setName("",MainActivity.this);
        DatabaseHelper dh = new DatabaseHelper(MainActivity.this);
        dh.deleteTable();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


}
