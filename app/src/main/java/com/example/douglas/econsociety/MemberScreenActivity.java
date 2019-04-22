package com.example.douglas.econsociety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MemberScreenActivity extends AppCompatActivity {
    TextView name,id,num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_screen);

        name = findViewById(R.id.text);
        id = findViewById(R.id.text1);
        num = findViewById(R.id.text2);
    }

    public  void generate(View view){
        String Mname = name.getText().toString();
        String Mid = id.getText().toString();
        String Mnum = num.getText().toString();
        String[] arr = {Mname,Mid,Mnum};
        Intent intent = new Intent(this, QrActivity.class);
        intent.putExtra("cred",arr);
        startActivity(intent);
    }

}
