package com.cdhxqh.travel_ticket_app.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.travel_ticket_app.R;
import com.cdhxqh.travel_ticket_app.ui.fragment.OrderThreeInFragment;
import com.cdhxqh.travel_ticket_app.ui.fragment.OrderThreeOutFragment;


/**
 * 订单列表*
 */

public class OrderActivity extends BaseActivity {
    /**
     * 标题*
     */
    TextView titleTextView;

    // 获取TabHost对象
    TabHost tabHost;

    /**
     * 三个月内*
     */
    OrderThreeInFragment orderThreeInFragment;
    /**
     * 三个月外*
     */
    OrderThreeOutFragment orderThreeOutFragment;
    /**
     * FragmentTransaction*
     */
    FragmentTransaction fragmentTransaction;

    /**
     * 三个月内*
     */
    TextView inttextView;
    /**
     * 三个月前*
     */
    TextView outtextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        titleTextView = (TextView) findViewById(R.id.title_text_id);
        inttextView = (TextView) findViewById(R.id.order_in_text);
        outtextView = (TextView) findViewById(R.id.order_out_text);


    }

    @Override
    protected void initView() {
        titleTextView.setText(getString(R.string.order_title_text));
        inttextView.setOnClickListener(inttextViewOnClickListener);
        outtextView.setOnClickListener(outtextViewOnClickListener);
        OnTabSelected("three_in");
    }

    /**
     * 三个月内*
     */
    private View.OnClickListener inttextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OnTabSelected("three_in");
        }
    };


    /**
     * 三个月前*
     */
    private View.OnClickListener outtextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OnTabSelected("three_out");

        }
    };


    void OnTabSelected(String tabName) {
        if (tabName == "three_in") {

            if (null == orderThreeInFragment) {
                orderThreeInFragment = new OrderThreeInFragment();
            }

            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.container, orderThreeInFragment, "three_in");
            localFragmentTransaction.commit();

            inttextView.setBackgroundColor(getResources().getColor(R.color.green_color));
            outtextView.setBackgroundColor(getResources().getColor(R.color.white));


        } else if (tabName == "three_out") {

            orderThreeOutFragment = new OrderThreeOutFragment();


            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.container, orderThreeOutFragment, "three_out");
            localFragmentTransaction.commit();

            inttextView.setBackgroundColor(getResources().getColor(R.color.white));
            outtextView.setBackgroundColor(getResources().getColor(R.color.green_color));


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            exit(OrderActivity.this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
