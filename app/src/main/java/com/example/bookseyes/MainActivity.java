package com.example.bookseyes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bookseyes.adapters.LivresAdapter;
import com.example.bookseyes.adapters.ProverbsAdapter;
import com.example.bookseyes.entities.Livre;
import com.example.bookseyes.entities.Proverbe;
import com.example.bookseyes.handlers.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Livre> livreList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LivresAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.livres_list);

        mAdapter = new LivresAdapter(livreList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new LivresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Livre livre = livreList.get(position) ;
                Intent i = new Intent(MainActivity.this,ProverbesListeActivity.class) ;
                i.putExtra("id",String.valueOf(livre.getId()));
                startActivity(i);
            }
        });

        prepareLivresData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        livreList = new ArrayList<>();
        prepareLivresData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        livreList = new ArrayList<>();
        prepareLivresData();
    }

    private void prepareLivresData() {

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Cursor cursor = dbHandler.loadHandler();

        while (cursor.moveToNext()) {

            int result_0 = cursor.getInt(0);

            String result_1 = cursor.getString(1);

            String result_2 = cursor.getString(2);

            Livre livre = new Livre(result_0, result_1, result_2);
            livreList.add(livre);

        }

        cursor.close();

        mAdapter.notifyDataSetChanged();
    }
    public void go(View v){

        Intent i = new Intent(MainActivity.this,AddBookActivity.class) ;
        startActivity(i);

    }
}
