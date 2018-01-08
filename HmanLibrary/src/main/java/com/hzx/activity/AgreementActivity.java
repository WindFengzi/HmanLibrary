package com.hzx.activity;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * 服务使用协议
 * */
public class AgreementActivity extends BaseActivity {
    public WebView agreementWv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        agreementWv = (WebView)findViewById(R.id.about_service_agreement_wv);
        agreementWv.loadUrl("http://192.168.0.65:8080/waterquality/water_service.html");
    }
}
