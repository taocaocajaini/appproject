package com.cdhxqh.travel_ticket_app.ui.activity;

import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cdhxqh.travel_ticket_app.R;
import com.cdhxqh.travel_ticket_app.ui.adapter.Bee_PageAdapter;
import com.cdhxqh.travel_ticket_app.viewpagerindicator.PageIndicator;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";
    private ViewPager bannerViewPager;
    private PageIndicator mIndicator;

    private ArrayList<View> bannerListView;
    private Bee_PageAdapter bannerPageAdapter;

    private View mTouchTarget;

    private int[] images = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById();
        initView();

    }

    @Override
    protected void findViewById() {
        bannerViewPager = (ViewPager) findViewById(R.id.banner_viewpager);
        mIndicator = (PageIndicator) findViewById(R.id.indicator);

    }

    @Override
    protected void initView() {
        LayoutParams params1 = bannerViewPager.getLayoutParams();
        params1.width = getDisplayMetricsWidth();
        params1.height = (int) (params1.width * 1.0 / 484 * 250);


        bannerViewPager.setLayoutParams(params1);

        bannerListView = new ArrayList<View>();
        bannerPageAdapter = new Bee_PageAdapter(bannerListView);
        bannerViewPager.setAdapter(bannerPageAdapter);
        addBannerView();


        bannerViewPager.setAdapter(bannerPageAdapter);
        bannerViewPager.setCurrentItem(0);

        bannerViewPager.setOnPageChangeListener(bannerViewPagerOnPageChangeListener);


    }


    private ViewPager.OnPageChangeListener bannerViewPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        private int mPreviousState = ViewPager.SCROLL_STATE_IDLE;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.i(TAG, "**position=" + position);
            mIndicator.setCurrentItem(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // All of this is to inhibit any scrollable container from consuming our touch events as the user is changing

            if (mPreviousState == ViewPager.SCROLL_STATE_IDLE) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    mTouchTarget = bannerViewPager;
                }
            } else {
                if (state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_SETTLING) {
                    mTouchTarget = null;
                }
            }

            mPreviousState = state;

            Log.i(TAG,"mPreviousState="+mPreviousState);
        }
    };


    //获取屏幕宽度
    public int getDisplayMetricsWidth() {
        int i = getWindowManager().getDefaultDisplay().getWidth();
        int j = getWindowManager().getDefaultDisplay().getHeight();
        return Math.min(i, j);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addBannerView() {
        bannerListView.clear();
        for (int i = 0; i < images.length; i++) {
            Log.i(TAG, "ssss");
            ImageView viewOne = (ImageView) LayoutInflater.from(this).inflate(R.layout.index_banner_cell, null);
            viewOne.setImageResource(images[i]);
            bannerListView.add(viewOne);


        }
        mIndicator.setViewPager(bannerViewPager);
        mIndicator.notifyDataSetChanged();
        mIndicator.setCurrentItem(0);
        bannerPageAdapter.mListViews = bannerListView;


    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        exit(HomeActivity.this);
//    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            exit(HomeActivity.this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
