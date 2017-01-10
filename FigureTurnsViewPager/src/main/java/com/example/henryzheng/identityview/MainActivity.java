package com.example.henryzheng.identityview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {
    FigureTurnsViewPager figureTurnsViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater=LayoutInflater.from(this);


        figureTurnsViewPager= (FigureTurnsViewPager) findViewById(R.id.my_view_pager);

        Bitmap bmpA= BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Bitmap bmpB= BitmapFactory.decodeResource(getResources(), R.drawable.b);
        Bitmap bmpC= BitmapFactory.decodeResource(getResources(), R.drawable.c);
        Bitmap bmpD= BitmapFactory.decodeResource(getResources(), R.drawable.d);
        Bitmap bmpE= BitmapFactory.decodeResource(getResources(), R.drawable.e);
        Bitmap bmpF= BitmapFactory.decodeResource(getResources(), R.drawable.f);
        figureTurnsViewPager.addImageBitmap(bmpA);
        figureTurnsViewPager.addImageBitmap(bmpB);
        figureTurnsViewPager.addImageBitmap(bmpC);
        figureTurnsViewPager.addImageBitmap(bmpD);
        figureTurnsViewPager.addImageBitmap(bmpE);
        figureTurnsViewPager.addImageBitmap(bmpF);
        figureTurnsViewPager.build().startAnimate();

    }
}
