package com.example.bookseyes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProverbeActivity extends AppCompatActivity {
    EditText proverbe ;
    private int bookID ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_proverbe);
        proverbe = findViewById(R.id.text) ;
        Intent i = getIntent() ;

        bookID = Integer.parseInt(i.getStringExtra("id")) ;

        proverbe.setText(i.getStringExtra("text"));
    }
    public void go(View v){
        Intent i = new Intent(EditProverbeActivity.this,AddProverbeActivity.class) ;
        i.putExtra("id",String.valueOf(bookID));
        i.putExtra("text",proverbe.getText().toString()) ;
        startActivity(i) ;
    }
}
