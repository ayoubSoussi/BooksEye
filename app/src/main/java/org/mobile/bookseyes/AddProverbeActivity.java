package org.mobile.bookseyes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mobile.bookseyes.R;

import org.mobile.bookseyes.entities.Proverbe;
import org.mobile.bookseyes.handlers.DBHandlerProverbs;

public class AddProverbeActivity extends AppCompatActivity {

    EditText titre, texte,  page ;
    private DBHandlerProverbs dbHandler;
    private int bookID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proverbe);
        dbHandler = new DBHandlerProverbs(this, null, null, 1);
        Intent i = getIntent() ;
        bookID = Integer.parseInt(i.getStringExtra("id")) ;
        titre = findViewById(R.id.titre) ;
        texte = findViewById(R.id.texte) ;
        page = findViewById(R.id.page) ;
        texte.setText(i.getStringExtra("text"));
    }
    public void goToCamera(View v){
        Intent i = new Intent(AddProverbeActivity.this,CameraParamsActivity.class) ;
        i.putExtra("id",String.valueOf(bookID));
        startActivity(i) ;
    }
    public void add(View v){

        String titre = this.titre.getText().toString() ;
        String texte = this.texte.getText().toString() ;
        String page = this.page.getText().toString() ;

        if (titre.length() != 0 && texte.length() != 0 && page.length() != 0 ) {

            Proverbe proverbe = new Proverbe(titre,texte,page) ;
            dbHandler.addHandler(bookID,proverbe) ;
            Toast.makeText(this,"La proverbe est ajoutée avec succés !",Toast.LENGTH_LONG).show();
            Intent i = new Intent(AddProverbeActivity.this,ProverbesListeActivity.class) ;
            i.putExtra("id",String.valueOf(bookID));
            startActivity(i);

        }else{
            Toast.makeText(this,"Veuillez remplir toutes les cases !",Toast.LENGTH_LONG).show();
        }
    }
}
