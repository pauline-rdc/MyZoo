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
        TAnimalFragment.OnFragmentInteractionListener,
        InfosFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ZooBDD zooBDD = new ZooBDD(this);

        zooBDD.open();
        zooBDD.initialize();

        //Création des thèmes

        Theme theme1 = new Theme("Je marche");
        Theme theme2 = new Theme("Je vole");
        Theme theme3 = new Theme("Je nage");
        Theme theme4 = new Theme("Je rampe");
        zooBDD.insertTheme(theme1);
        zooBDD.insertTheme(theme2);
        zooBDD.insertTheme(theme3);
        zooBDD.insertTheme(theme4);

        //Ajout des animaux dans la BDD

        Animal animal1 = new Animal("Lion", "Le lion (Panthera leo) est une espèce de mammifères carnivores de la famille des félidés", "0.0.0.1", "tmpsnd1", "lion", zooBDD.getThemeById(1));
        Animal animal2 = new Animal("Tigre", "Le tigre (Panthera tigris1) est une espèce de mammifère carnivore de la famille des félidés (Felidae) du genre Panthera", "15.0.0.1", "tmpsnd2", "tigre", zooBDD.getThemeById(1));
        Animal animal3 = new Animal("Aigle royal", "L'Aigle royal (Aquila chrysaetos) est une espèce de grands rapaces de la famille des Accipitridae", "30.0.0.1", "tmpsnd3", "bird", zooBDD.getThemeById(2));
        Animal animal4 = new Animal("Poisson-lune", "Le poisson-lune, aussi appelé môle, est un poisson de la famille des Molidae, au corps circulaire pouvant atteindre 2 mètres et peser jusqu'à 1 000 kg", "45.0.0.1", "tmpsnd4", "poisson", zooBDD.getThemeById(3));
        Animal animal5 = new Animal("Poisson tigre goliath", "Le poisson tigre goliath (Hydrocynus goliath) ou encore Poisson-chien 1 et appelé localement mbenga est une espèce de poisson d'eau douce", "60.0.0.1", "tmpsnd5", "poisson_tigre", zooBDD.getThemeById(3));
        Animal animal6 = new Animal("Boa constricteur", "Boa constrictor, le Boa constricteur, est une espèce de serpents de la famille des Boidae", "75.0.0.1", "tmpsnd6", "boa_constriteur", zooBDD.getThemeById(4));
        Animal animal7 = new Animal("Crocodile", "Les crocodiles vivent dans les régions chaudes. Toutes les espèces - sauf le crocodile marin - fréquentent les eaux douces. Ils vivent immergés dans des cours d'eau stagnante où ils passent leurs journées à guetter leurs proies", "75.0.0.2", "tmpsnd7", "crocodile", zooBDD.getThemeById(4));
        Animal animal8 = new Animal("Chat", "Le chat domestique (Felis silvestris catus) est la sous-espèce issue de la domestication du chat sauvage, mammifère carnivore de la famille des félidés", "75.0.0.3", "tmpsnd8", "chat", zooBDD.getThemeById(1));
        Animal animal9 = new Animal("Chat Aux Poils Long", "Un chat à poil long est un chat dont la fourrure possède des poils longs à mi-longs.", "75.0.0.4", "tmpsnd9", "chat2", zooBDD.getThemeById(1));
        Animal animal10 = new Animal("Chat du Bengal", "Le bengal est une race de chat originaire des États-Unis. Cette race de taille moyenne à grande, par rapport aux autres chats, se caractérise par sa fourrure ressemblant à celle du Chat léopard du Bengale.", "75.0.0.4", "tmpsnd9", "chat_du_bengal", zooBDD.getThemeById(1));
        Animal animal11 = new Animal("Leopard", "Le léopard (Panthera pardus) ou panthère est une espèce de félins de la sous-famille des panthérinés. Ce félin présente un pelage fauve tacheté de rosettes ; une forme mélanique existe également. ", "75.0.0.4", "tmpsnd9", "leopard", zooBDD.getThemeById(1));
        Animal animal12 = new Animal("Chouette", "Chouette est le nom vernaculaire de certains oiseaux de la famille des Strigidaes, qui regroupe environ 200 espèces, caractérisées comme des rapaces solitaires et nocturnes.", "75.0.0.8", "tmpsnd9", "chouette", zooBDD.getThemeById(2));
        Animal animal13 = new Animal("Grand Duc", "Le Hibou grand-duc appelé aussi Grand-duc d'Europe (Bubo bubo) est une espèce de rapaces nocturnes que l'on rencontre notamment en Europe. Il ne doit pas être confondu avec le Grand-duc d'Amérique. On l'appelle Eagle Owl en anglais.", "75.0.0.8", "tmpsnd9", "grand_duc", zooBDD.getThemeById(2));
        Animal animal14 = new Animal("Mouette", "On nomme mouettes les oiseaux de plusieurs genres de la sous-famille des Larinae et de la tribu des Larini, qui comprend aussi les goélands", "75.0.0.8", "tmpsnd14", "mouette", zooBDD.getThemeById(2));
        Animal animal15 = new Animal("Oies", "Les oies forment un groupe d'oiseaux appartenant à la famille des anatidés parmi laquelle on trouve aussi les cygnes et les canards", "75.0.0.8", "tmpsnd15", "oies", zooBDD.getThemeById(2));
        Animal animal16 = new Animal("Pelicans", "Les pélicans forment le genre d'oiseaux Pelecanus, unique représentant de la famille des Pelecanidae (ou pélécanidés) qui compte huit espèces.", "75.0.0.8", "tmpsnd16", "pelican", zooBDD.getThemeById(2));
        Animal animal17 = new Animal("Méduse", "Les méduses sont généralement des prédateurs, elles paralysent leurs proies grâce à leurs cnidocytes et peuvent posséder des structures sensorielles très élaborées comme des ocelles.", "75.0.0.8", "tmpsnd17", "meduse", zooBDD.getThemeById(3));
        Animal animal18 = new Animal("Python Vert", "Ce python vert arboricole est caractérisé par un corps relativement mince. La queue relativement longue représente environ 14 % de sa longueur totale. La tête est large. ", "75.0.0.8", "tmpsnd17", "python", zooBDD.getThemeById(4));
        Animal animal19 = new Animal("Tigre Blanc", "Le tigre blanc, parfois aussi appelé tigre blanc royal, est une mutation colorée du tigre (Panthera tigris), un mammifère carnivore de la famille des Félidés. Son pelage est blanc avec des rayures plus ou moins noires.", "75.0.0.8", "tmpsnd17", "tigre_blanc", zooBDD.getThemeById(1));
        Animal animal20 = new Animal("Léopard des neiges", "L'once (Panthera uncia), aussi appelé irbis, panthère des neiges ou léopard des neiges est un félin de la sous-famille des panthérinés. On le rencontre dans les vallées isolées des hautes montagnes d'Asie centrale, de Sibérie centrale", "75.0.0.8", "tmpsnd17", "snow_leopard", zooBDD.getThemeById(1));
        Animal animal21 = new Animal("Couleuvre", "Couleuvre est un nom vernaculaire ambigu désignant certains serpents généralement non venimeux1, à la différence des vipères. Ce sont souvent des espèces diurnes.", "75.0.0.8", "tmpsnd17", "couleuvre", zooBDD.getThemeById(4));
        Animal animal22 = new Animal("Grand Dauphin", "Le grand dauphin est présent dans toutes les mers du monde, à l'exception des zones arctiques et antarctiques. Il existe deux populations assez distinctes : une côtière et une pélagique.", "75.0.0.8", "tmpsnd17", "dauphin", zooBDD.getThemeById(3));
        Animal animal23 = new Animal("Requin", "Les requins, squales ou sélachimorphes forment un super-ordre de poissons cartilagineux, possédant cinq à sept fentes branchiales sur les côtés de la tête et les nageoires pectorales qui ne sont pas fusionnés à la tête.", "75.0.0.8", "tmpsnd17", "requin", zooBDD.getThemeById(3));
        Animal animal24 = new Animal("Poisson Clown", "Les poissons-clowns, ou Amphiprioninae, sont une sous-famille de poissons appartenant à la famille des Pomacentridés.", "75.0.0.8", "tmpsnd17", "poisson_clown", zooBDD.getThemeById(3));
        Animal animal25 = new Animal("Raie Léopard", "L'aigle de mer-léopard ou raie-léopard (Aetobatus narinari) est une espèce de raie de la famille des Myliobatidae qui se rencontre dans les zones tropicales et subtropicales.", "75.0.0.8", "tmpsnd17", "raie", zooBDD.getThemeById(3));
        Animal animal26 = new Animal("Gorille", "Les Gorilles, Gorilla, forment un genre de grands singes de la famille des Hominidés. Les mâles, en particulier, peuvent développer une force physique colossale.", "75.0.0.8", "tmpsnd17", "gorille", zooBDD.getThemeById(1));

        zooBDD.insertAnimal(animal1);
        zooBDD.insertAnimal(animal2);
        zooBDD.insertAnimal(animal3);
        zooBDD.insertAnimal(animal4);
        zooBDD.insertAnimal(animal5);
        zooBDD.insertAnimal(animal6);
        zooBDD.insertAnimal(animal7);
        zooBDD.insertAnimal(animal8);
        zooBDD.insertAnimal(animal9);
        zooBDD.insertAnimal(animal10);
        zooBDD.insertAnimal(animal11);
        zooBDD.insertAnimal(animal12);
        zooBDD.insertAnimal(animal13);
        zooBDD.insertAnimal(animal14);
        zooBDD.insertAnimal(animal15);
        zooBDD.insertAnimal(animal16);
        zooBDD.insertAnimal(animal17);
        zooBDD.insertAnimal(animal18);
        zooBDD.insertAnimal(animal19);
        zooBDD.insertAnimal(animal20);
        zooBDD.insertAnimal(animal21);
        zooBDD.insertAnimal(animal22);
        zooBDD.insertAnimal(animal23);
        zooBDD.insertAnimal(animal24);
        zooBDD.insertAnimal(animal25);
        zooBDD.insertAnimal(animal26);

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
        } catch (Exception e) {
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
        } else {
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
        } else if (id == R.id.theme) {
            fragmentClass = ThemeFragment.class;
        } else if (id == R.id.animals) {
            fragmentClass = AllAnimalsFragment.class;
        } else if (id == R.id.favories) {
            fragmentClass = FavoriesFragment.class;
        } else if (id == R.id.contact) {
            fragmentClass = ContactFragment.class;
        } else if (id == R.id.map) {
            fragmentClass = MapFragment.class;
        } else if (id == R.id.info) {
            fragmentClass = InfosFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
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
