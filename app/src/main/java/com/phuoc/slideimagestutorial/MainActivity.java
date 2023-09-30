package com.phuoc.slideimagestutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator2;
import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private PhotoAdapter photoAdapter;

    private List<Photo> mListPhoto;
    private Timer mTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager);
        circleIndicator3 = findViewById(R.id.circle_indicator);

        photoAdapter = new PhotoAdapter(this);
        viewPager2.setAdapter(photoAdapter);

        circleIndicator3.setViewPager(viewPager2);
        circleIndicator3.setVisibility(View.INVISIBLE);
        photoAdapter.registerAdapterDataObserver(circleIndicator3.getAdapterDataObserver());


        photoAdapter.setData(getListPhoto());
        autoSlideImage();
    }

    private List<Photo> getListPhoto() {
        mListPhoto = new ArrayList<>();
        mListPhoto.add(new Photo(R.drawable.background_1));
        mListPhoto.add(new Photo(R.drawable.background_2));
        mListPhoto.add(new Photo(R.drawable.background_3));
        mListPhoto.add(new Photo(R.drawable.backgroud_4));
        mListPhoto.add(new Photo(R.drawable.background_5));
        mListPhoto.add(new Photo(R.drawable.background_6));
        mListPhoto.add(new Photo(R.drawable.background_7));
        mListPhoto.add(new Photo(R.drawable.backgroud_2));
        mListPhoto.add(new Photo(R.drawable.background_8));
        mListPhoto.add(new Photo(R.drawable.background_9));
        mListPhoto.add(new Photo(R.drawable.background_10));
        mListPhoto.add(new Photo(R.drawable.background_11));
        mListPhoto.add(new Photo(R.drawable.background_12));

        return mListPhoto;
    }

    private void autoSlideImage() {
        if (mListPhoto == null || mListPhoto.isEmpty() || viewPager2 == null) {
            return;
        }

        // init Timer
        if (mTimer == null) {
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager2.getCurrentItem();
                        int totalItem = mListPhoto.size()-1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager2.setCurrentItem(currentItem);
                        } else {
                            viewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}