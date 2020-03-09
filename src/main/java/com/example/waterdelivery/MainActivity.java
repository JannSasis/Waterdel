package com.example.waterdelivery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   if (finalI == 0){
                       Intent intent = new Intent(MainActivity.this,ActivityOne.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 1){
                       Intent intent = new Intent(MainActivity.this,ActivityTwo.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 2){
                       Intent intent = new Intent(MainActivity.this,ActivityThree.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 3){
                       Intent intent = new Intent(MainActivity.this,ActivityFour.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 4){
                       Intent intent = new Intent(MainActivity.this,Five.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 5){
                       Intent intent = new Intent(MainActivity.this,ActivitySix.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 6){
                       Intent intent = new Intent(MainActivity.this,ActivitySeven.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 7){
                       Intent intent = new Intent(MainActivity.this,ActivityEight.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 8){
                       Intent intent = new Intent(MainActivity.this,ActivityNine.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 9){
                       Intent intent = new Intent(MainActivity.this,ActivityTen.class);
                       startActivity(intent);
                   }
                   else  if (finalI == 10){
                       Intent intent = new Intent(MainActivity.this,ActivityOne.class);
                       startActivity(intent);
                   }


                }
            });
        }
    }
}