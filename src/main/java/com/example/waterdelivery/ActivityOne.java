package com.example.waterdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityOne extends AppCompatActivity {

    private Button button1;
    private Button buttonsms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);


        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityOne.this,ActivityOneMap1.class);
                startActivity(i);
            }
        });

        Button mbutton = (Button) findViewById(R.id.send_sms);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "09665677337", null) );
                startActivity(intent);
            }
        });
    }



}
