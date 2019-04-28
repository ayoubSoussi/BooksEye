package com.example.bookseyes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProverbeActivity extends AppCompatActivity {
    TextView titre,contenu,page ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proverbe);

        titre = findViewById(R.id.Ptitre) ;
        contenu = findViewById(R.id.Pcontenu) ;
        page = findViewById(R.id.Ppage) ;

        Intent i = getIntent() ;
        titre.setText(i.getStringExtra("titre"));
        contenu.setText(i.getStringExtra("contenu"));
        page.setText(i.getStringExtra("page"));
    }
}
