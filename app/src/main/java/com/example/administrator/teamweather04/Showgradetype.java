package com.example.administrator.teamweather04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Showgradetype extends AppCompatActivity {
    Button btn11,btn22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showgradetype);
      btn11=(Button)findViewById(R.id.btn11);
        btn22=(Button)findViewById(R.id.btn22);

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Showgradetype.this,StudentGrade.class);
                startActivity(intent);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Showgradetype.this,Adminxhgrade.class);
                startActivity(intent);
            }
        });
    }
}
