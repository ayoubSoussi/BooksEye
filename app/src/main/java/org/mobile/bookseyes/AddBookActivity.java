package org.mobile.bookseyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mobile.bookseyes.R;

import org.mobile.bookseyes.entities.Livre;
import org.mobile.bookseyes.handlers.DBHandler;

public class AddBookActivity extends Activity {
    EditText titre,auteur ;
    DBHandler dbHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titre = findViewById(R.id.titreLL) ;
        auteur = findViewById(R.id.auteurLL) ;

        dbHandler = new DBHandler(this, null, null, 1);
    }
    public void add(View v){

        String titre = this.titre.getText().toString() ;
        String auteur = this.auteur.getText().toString() ;

        if (titre.length() != 0 && auteur.length() != 0){

            Livre livre = new Livre(titre,auteur) ;
            dbHandler.addHandler(livre);
            Toast.makeText(this,"La livre est ajoutée avec succés !",Toast.LENGTH_LONG).show();
            Intent i = new Intent(AddBookActivity.this,MainActivity.class) ;
            startActivity(i);
        }else{
            Toast.makeText(this,"Veuillez remplir toutes les cases !",Toast.LENGTH_LONG).show();
        }


    }
}
