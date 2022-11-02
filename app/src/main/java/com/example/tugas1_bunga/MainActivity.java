package com.example.tugas1_bunga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvArtis;
    private ArrayList<Artis> list = new ArrayList<>();

    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new TentangFragment();
                        break;
                    case R.id.nav_call:
                        selectedFragment = new CallsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_con, selectedFragment).commit();
                return true;
            }
        });
        rvArtis = findViewById(R.id.rv_artis);
        rvArtis.setHasFixedSize(true);

        list.addAll(getListArtis());
        showRecyclerCardView();
    }

    public ArrayList<Artis> getListArtis(){
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        ArrayList<Artis> listArtis = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            Artis artis = new Artis();
            artis.setName(dataName[i]);
            artis.setDescription(dataDescription[i]);
            artis.setPhoto(dataPhoto.getResourceId(i,-1));
            listArtis.add(artis);
        }
        return listArtis;
    }

    private void showRecyclerCardView() {
        rvArtis.setLayoutManager(new LinearLayoutManager(this));
        ListArtis listArtisAdapter = new ListArtis(list);
        rvArtis.setAdapter(listArtisAdapter);

        listArtisAdapter.setOnItemClickCallback(new ListArtis.OnItemClickCallback(){
            @Override
            public void onItemClicked(Artis artis) {
                Intent move = new Intent(MainActivity.this, DetailActivity.class);
                move.putExtra(DetailActivity.ITEM_EXTRA, artis);
                startActivity(move);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView)
                    (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu2) {
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            return true;
        } else {
            return true;
        }
    }
}