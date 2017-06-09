package com.example.alex.largo.news.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alex.largo.news.R;
import com.example.alex.largo.news.api.NewsService;
import com.example.alex.largo.news.model.NewsResponse;
import com.example.alex.largo.news.ui.fragment_list_news.ListNewsFragment;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String BBC_NEWS = "bbc-news";
    private static final String BBC_SPORT = "bbc-sport";
    private static final String MTV_NEWS = "mtv-news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        insertFragment(BBC_NEWS);
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.bbc_news : replaceFragment(BBC_NEWS); break;
            case R.id.bbc_sport : replaceFragment(BBC_SPORT); break;
            case R.id.mtv_news : replaceFragment(MTV_NEWS); break;
            case R.id.favorite_news : ; break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(String key){
        ListNewsFragment listNewsFragment = new ListNewsFragment();
        Bundle args = new Bundle();
        args.putString(ListNewsFragment.NEWS_KEY, key);
        listNewsFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, listNewsFragment)
                .addToBackStack("").commit();
    }

    private void insertFragment(String key){
        ListNewsFragment listNewsFragment = new ListNewsFragment();
        Bundle args = new Bundle();
        args.putString(ListNewsFragment.NEWS_KEY, key);
        listNewsFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, listNewsFragment)
                .addToBackStack("").commit();
    }
}
