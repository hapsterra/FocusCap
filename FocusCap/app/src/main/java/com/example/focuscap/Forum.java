package com.example.focuscap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.focuscap.Model.Quote;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;


public class Forum extends AppCompatActivity {

    private ArrayList<Quote> quotes = new ArrayList<>();
    private TextView empty;
    private EditText editTextQuote;
    private Button btnQuote;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;
    private ArrayList<String> quotesString=new ArrayList<>();
    private RecyclerView recyclerQuotes;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        editTextQuote=findViewById(R.id.editTextQuote);
        btnQuote=findViewById(R.id.btnQuote);
        recyclerQuotes=findViewById(R.id.recycler_quote);
        progressBar=findViewById(R.id.progressBar);
        empty=findViewById(R.id.textViewEmpty);
        recyclerQuotes.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("quote");
        empty.setVisibility(View.INVISIBLE);
        auth=FirebaseAuth.getInstance();
        getQuotesFromFirebase();


        btnQuote.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(auth.getCurrentUser()!=null){
                    String[] arrOfMail=auth.getCurrentUser().getEmail().split("@");
                   if(quotes.size()>0){
                       int id=Integer.parseInt(quotes.get(quotes.size()-1).getId())+1;
                       addQuote1(id,arrOfMail[0],editTextQuote.getText().toString(), LocalDate.now().toString());
                   }else{
                       addQuote0(arrOfMail[0],editTextQuote.getText().toString(), LocalDate.now().toString());
                   }
                   getQuotesFromFirebase();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void goContact(View view){
        Intent i = new Intent(this,Contact.class);
        startActivity(i);
    }
    public void goFocused(View view){
        Intent i = new Intent(this,Focused.class);
        startActivity(i);}
    public void goMain(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void addQuote1(int id,String user, String quote, String date){

        Quote q=new Quote(id,user,date,quote);
        if(auth.getCurrentUser()!=null){
            myRef.child(q.getId()).setValue(q).addOnSuccessListener(unused -> Toast.makeText(Forum.this, "Posted Successfully",Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Forum.this, "Not posted Successfully",Toast.LENGTH_SHORT).show());
        }
        empty.setVisibility(View.INVISIBLE);
        editTextQuote.setText("");

    }

    public void addQuote0(String user, String quote, String date){

        Quote q=new Quote(user,date,quote);
        if(auth.getCurrentUser()!=null){
            myRef.child(q.getId()).setValue(q).addOnSuccessListener(unused -> Toast.makeText(Forum.this, "Posted Successfully",Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Forum.this, "Not posted Successfully",Toast.LENGTH_SHORT).show());
        }
        empty.setVisibility(View.INVISIBLE);
        editTextQuote.setText("");
    }
    private void getQuotesFromFirebase(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    quotes.clear();
                    Log.d("Snapshot","Llena");

                    for(DataSnapshot ds: snapshot.getChildren()) {

                        String data = ds.child("date").getValue().toString();
                        int id = Integer.parseInt(ds.child("id").getValue().toString());
                        String quote = ds.child("quote").getValue().toString();
                        String user = ds.child("user").getValue().toString();

                        quotes.add(new Quote(id, user, data, quote));
                    }
                    }else{
                    Log.d("Snapshot","Vacia");
                }
                progressBar.setVisibility(View.GONE);
                if(quotes.size()>0){
                    LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
                    DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(getApplicationContext(),llm.getOrientation() );
                    recyclerQuotes.addItemDecoration(dividerItemDecoration);
                    AdapterQuotes adapter= new AdapterQuotes(quotes);
                    recyclerQuotes.setAdapter(adapter);
                }
                else{
                    empty.setVisibility(View.VISIBLE);
                    Toast.makeText(Forum.this, "There are no quotes posted yet",Toast.LENGTH_SHORT).show();
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("Actualizar","Ya ha salido");

    }

}

