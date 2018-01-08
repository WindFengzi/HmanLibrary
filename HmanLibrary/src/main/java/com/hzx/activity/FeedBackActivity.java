package com.hzx.activity;

import android.os.Bundle;

public class FeedBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        setHeaderTitle(R.string.my_feed_back);
        getBackHeaderLeft();
        getHeaderRight(R.string.submit);

    }
}
