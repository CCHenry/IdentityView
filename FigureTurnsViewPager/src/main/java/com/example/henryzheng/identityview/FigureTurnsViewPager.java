package com.example.henryzheng.identityview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2017/1/9.
 */
public class FigureTurnsViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener {
    List<View> views;
    LayoutInflater inflater;
    Context context;
    private ViewPager viewPage;
    List<Bitmap> bitmaps;
    private int _position = 0;
    private BannerItemInitListener bannerItemInitListener;

    public FigureTurnsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public FigureTurnsViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    void init() {
        bitmaps = new ArrayList<>();
        views = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rl_figure_turns_view_pager, null);
        viewPage = (ViewPager) view.findViewById(R.id.view_pager);
        addView(view);
    }


    public FigureTurnsViewPager addImageBitmap(Bitmap bitmap) {
        bitmaps.add(bitmap);
        return this;
    }


    public FigureTurnsViewPager build() {
        addHeader();
        for (int i = 0; i < bitmaps.size(); i++) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.image_view, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(i + 1 + "");
            imageView.setImageBitmap(bitmaps.get(i));
            views.add(view);
        }
        addAfter();
        viewPage.setAdapter(new MyPageAdapter());
        viewPage.addOnPageChangeListener(this);
        return this;
    }

    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            CCLog.print("destroyItem:" + position);
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            View view = (views.get(position));
            CCLog.print("instantiateItem:" + position);
            container.addView(view);
            ImageView iv= (ImageView) view.findViewById(R.id.image_view);
            bannerItemInitListener.initItemView(iv);
            return view;
        }
    }
    public void setOnBannerItemInitListener(BannerItemInitListener i){
        bannerItemInitListener=i;
    }

    private void addAfter() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setImageBitmap(bitmaps.get(0));
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("after");
        views.add(view);
    }

    private void addHeader() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setImageBitmap(bitmaps.get(bitmaps.size() - 1));
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("head");
        views.add(view);
    }

    /**
     * 启动动画
     */
    public void startAnimate() {
        viewPage.setCurrentItem(1, false);
        new Thread(new ThreadShow()).start();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        _position = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            //滑动静止时，如果是最后一个位置，就令viewpager回到第一个位置，如果是
            if (_position == bitmaps.size() + 1)
                viewPage.setCurrentItem(1, false);
            if (_position == 0)
                viewPage.setCurrentItem(bitmaps.size(), false
                );
        }
    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int position = msg.what;
            CCLog.print("position=" + position);
            setScrollerTime(300);
            viewPage.setCurrentItem(position, true);

        }
    };

    // 线程类
    class ThreadShow implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            int position = 1;
            while (true) {
                try {

                    CCLog.print("Thread sleep");

                    Message msg = new Message();
                    if (position == bitmaps.size() + 2) {
                        position = 1;
                    } else
                        Thread.sleep(2000);

                    if ((position != bitmaps.size() + 2) && (position != 1)) {
                        msg.what = position;
                        handler.sendMessage(msg);
                    }
                    System.out.println("send...");
                    position++;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }

    }

    public void setScrollerTime(int scrollerTime) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPage.getContext(),
                    new AccelerateInterpolator());
            scroller.setmDuration(scrollerTime);
            field.set(viewPage, scroller);
        } catch (Exception e) {

        }
    }
}
