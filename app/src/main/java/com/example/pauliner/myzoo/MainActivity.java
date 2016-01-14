package com.example.pauliner.myzoo;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        AllAnimalsFragment.OnFragmentInteractionListener,
        AnimalFragment.OnFragmentInteractionListener,
        FavoriesFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        ThemeFragment.OnFragmentInteractionListener,
        MapFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        TAnimalFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ZooBDD zooBDD = new ZooBDD(this);

        zooBDD.open();
        zooBDD.initialize();

        Theme theme1 = new Theme("Je marche");
        Theme theme2 = new Theme("Je vole");
        Theme theme3 = new Theme("Je nage");
        Theme theme4 = new Theme("Je rampe");
        zooBDD.insertTheme(theme1);
        zooBDD.insertTheme(theme2);
        zooBDD.insertTheme(theme3);
        zooBDD.insertTheme(theme4);

        Animal animal1 = new Animal("Lion", "Le lion (Panthera leo) est une espèce de mammifères carnivores de la famille des félidés", "0.0.0.1", "tmpsnd1", "lion", zooBDD.getThemeById(1));
        Animal animal2 = new Animal("Tigre", "Le tigre (Panthera tigris1) est une espèce de mammifère carnivore de la famille des félidés (Felidae) du genre Panthera", "15.0.0.1", "tmpsnd2", "lion", zooBDD.getThemeById(1));
        Animal animal3 = new Animal("Aigle royal", "L'Aigle royal (Aquila chrysaetos) est une espèce de grands rapaces de la famille des Accipitridae", "30.0.0.1", "tmpsnd3", "bird", zooBDD.getThemeById(2));
        Animal animal4 = new Animal("Poisson-lune", "Le poisson-lune, aussi appelé môle, est un poisson de la famille des Molidae, au corps circulaire pouvant atteindre 2 mètres et peser jusqu'à 1 000 kg", "45.0.0.1", "tmpsnd4", "poisson", zooBDD.getThemeById(3));
        Animal animal5 = new Animal("Poisson tigre goliath", "Le poisson tigre goliath (Hydrocynus goliath) ou encore Poisson-chien 1 et appelé localement mbenga est une espèce de poisson d'eau douce", "60.0.0.1", "tmpsnd5", "poisson_tigre", zooBDD.getThemeById(3));
        Animal animal6 = new Animal("Boa constricteur", "Boa constrictor, le Boa constricteur, est une espèce de serpents de la famille des Boidae", "75.0.0.1", "tmpsnd6", "boa_constriteur", zooBDD.getThemeById(4));
        Animal animal7 = new Animal("Crocodile", "Les crocodiles vivent dans les régions chaudes. Toutes les espèces - sauf le crocodile marin - fréquentent les eaux douces. Ils vivent immergés dans des cours d'eau stagnante où ils passent leurs journées à guetter leurs proies", "75.0.0.2", "tmpsnd7", "crocodile", zooBDD.getThemeById(4));

        zooBDD.insertAnimal(animal1);
        zooBDD.insertAnimal(animal2);
        zooBDD.insertAnimal(animal3);
        zooBDD.insertAnimal(animal4);
        zooBDD.insertAnimal(animal5);
        zooBDD.insertAnimal(animal6);
        zooBDD.insertAnimal(animal7);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Class fragmentClass = HomeFragment.class;
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        Class fragmentClass;
        fragmentClass = HomeFragment.class;

        if (id == R.id.home) {
            fragmentClass = HomeFragment.class;
        }
        else if (id == R.id.theme) {
            fragmentClass = ThemeFragment.class;
        }
        else if (id == R.id.animals) {
            fragmentClass = AllAnimalsFragment.class;
        }
        else if (id == R.id.favories) {
            fragmentClass = FavoriesFragment.class;
        }
        else if (id == R.id.contact) {
            fragmentClass = ContactFragment.class;
        }
        else if (id == R.id.map) {
            fragmentClass = MapFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
