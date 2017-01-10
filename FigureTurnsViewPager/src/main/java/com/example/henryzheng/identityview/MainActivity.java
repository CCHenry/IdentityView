package com.example.henryzheng.identityview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    FigureTurnsViewPager figureTurnsViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater=LayoutInflater.from(this);


        figureTurnsViewPager= (FigureTurnsViewPager) findViewById(R.id.my_view_pager);

        final Bitmap bmpA= BitmapFactory.decodeResource(getResources(), R.drawable.a);
        final Bitmap bmpB= BitmapFactory.decodeResource(getResources(), R.drawable.b);
        final Bitmap bmpC= BitmapFactory.decodeResource(getResources(), R.drawable.c);
        final Bitmap bmpD= BitmapFactory.decodeResource(getResources(), R.drawable.d);
        final Bitmap bmpE= BitmapFactory.decodeResource(getResources(), R.drawable.e);
        final Bitmap bmpF= BitmapFactory.decodeResource(getResources(), R.drawable.f);

        figureTurnsViewPager.setCount(6);
        figureTurnsViewPager.build();
//        figureTurnsViewPager.startAnimate();
        figureTurnsViewPager.setOnBannerItemInitListener(new BannerItemInitListener() {
            @Override
            public void initItemView(ImageView iv, int position) {
                switch (position)
                {
                    case 0:
                        iv.setImageBitmap(bmpA);
                        break;
                    case 1:
                        iv.setImageBitmap(bmpB);
                        break;
                    case 2:
                        iv.setImageBitmap(bmpC);
                        break;
                    case 3:
                        iv.setImageBitmap(bmpD);
                        break;
                    case 4:
                        iv.setImageBitmap(bmpE);
                        break;
                    case 5:
                        iv.setImageBitmap(bmpF);
                        break;

                }
            }


        });
    }
}
