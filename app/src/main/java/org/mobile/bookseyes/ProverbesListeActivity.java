package org.mobile.bookseyes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.mobile.bookseyes.R;

import org.mobile.bookseyes.adapters.ProverbsAdapter;
import org.mobile.bookseyes.entities.Livre;
import org.mobile.bookseyes.entities.Proverbe;
import org.mobile.bookseyes.handlers.DBHandler;
import org.mobile.bookseyes.handlers.DBHandlerProverbs;

import java.util.ArrayList;
import java.util.List;

public class ProverbesListeActivity extends AppCompatActivity {

    private List<Proverbe> proverbsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView bookNameTV ;
    private ProverbsAdapter mAdapter;
    private int bookID ;
    private Livre currentBook ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proverbes_liste);

        recyclerView = (RecyclerView) findViewById(R.id.proverbs_list);
        bookNameTV = findViewById(R.id.livre) ;

        Intent i = getIntent() ;
        bookID = Integer.parseInt(i.getStringExtra("id")) ;

        mAdapter = new ProverbsAdapter(proverbsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProverbsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Proverbe proverbe = proverbsList.get(position) ;
                Intent i = new Intent(ProverbesListeActivity.this,ProverbeActivity.class) ;
                i.putExtra("titre",proverbe.getTitre()) ;
                i.putExtra("contenu",proverbe.getContenu()) ;
                i.putExtra("page",proverbe.getPage()) ;
                startActivity(i);
            }
        });

        prepareProverbeData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        proverbsList = new ArrayList<>();
        prepareProverbeData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        proverbsList = new ArrayList<>();
        prepareProverbeData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        proverbsList = new ArrayList<>();
        prepareProverbeData();
    }

    private void prepareProverbeData() {
        DBHandlerProverbs dbHandler  = new DBHandlerProverbs(this, null, null, 1);
        Cursor cursor = dbHandler.loadHandler(bookID);
        DBHandler dbHandlerBooks = new DBHandler(this,null,null,1) ;
        currentBook = dbHandlerBooks.findHandler(bookID) ;
        bookNameTV.setText(currentBook.getTitre());

        while (cursor.moveToNext()) {

            int result_0 = cursor.getInt(0); // id

            String result_1 = cursor.getString(1); // titre

            String result_2 = cursor.getString(2); // texte

            String result_3 = cursor.getString(3); // page

            Proverbe proverbe1 = new Proverbe( result_1, result_2, result_3);
            proverbsList.add(proverbe1);

        }

        cursor.close();


        mAdapter.notifyDataSetChanged();
    }
    public void go(View v){

        Intent i = new Intent(ProverbesListeActivity.this,AddProverbeActivity.class) ;
        i.putExtra("id",String.valueOf(bookID));
        i.putExtra("text","");
        startActivity(i);
    }

}
