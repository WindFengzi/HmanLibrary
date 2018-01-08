package com.hzx.activity;

import android.os.Bundle;
import android.webkit.WebView;

public class AboutContactUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_contact_us);
        setHeaderTitle(R.string.about_contact_us);
        getBackHeaderLeft();
        WebView agreementWv = (WebView)findViewById(R.id.about_service_agreement_wv);
        agreementWv.loadUrl("http://192.168.0.65:8080/waterquality/contact_us.html");

    }
}
