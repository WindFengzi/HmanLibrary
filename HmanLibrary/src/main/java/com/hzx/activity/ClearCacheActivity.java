package com.hzx.activity;

import android.os.Bundle;

public class ClearCacheActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_cache);
        setHeaderTitle(R.string.my_delete_save);
    }
}
