package com.example.focuscap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.focuscap.Model.Petition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class Contact extends AppCompatActivity {
    private EditText et_name;
    private EditText et_mail;
    private EditText et_phone;
    private EditText et_message;
    private Button btnSend;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        et_name=findViewById(R.id.et_name);
        et_mail=findViewById(R.id.et_mail);
        et_phone=findViewById(R.id.et_phone);
        et_message=findViewById(R.id.et_message);
        btnSend= findViewById(R.id.btnSend);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("contactPetition");
        auth=FirebaseAuth.getInstance();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                postContactPetition(et_name.getText().toString(),et_mail.getText().toString(),et_phone.getText().toString(),et_message.getText().toString());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void postContactPetition(String name, String mail, String phone, String message) {
        Petition p= new Petition(name,mail,phone,message);
        String date= LocalDate.now().toString();
        if(auth.getCurrentUser()!=null){
            myRef.child(date).child(name).setValue(p).addOnSuccessListener(unused -> Toast.makeText(Contact.this, "Posted Successfully",Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Contact.this, "Not posted Successfully",Toast.LENGTH_SHORT).show());
        }
        et_name.setText("");
        et_mail.setText("");
        et_phone.setText("");
        et_message.setText("");
    }

    public void goForum(View view){
        Intent i = new Intent(this,Forum.class);
    startActivity(i);}
    public void goFocused(View view){
        Intent i = new Intent(this,Focused.class);
        startActivity(i);
    }
    public void goMain(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}