package com.hzx.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hzx.view.CustomItemDetailView;


public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";
    public TextView versionTv;

    private CustomItemDetailView aboutContactCidv, agreementTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setHeaderTitle(R.string.my_about);
        getBackHeaderLeft();
        versionTv = (TextView) findViewById(R.id.about_version_tv);
        versionTv.setText(getResources().getText(R.string.version_name)+getVersionName(this));
        Log.e(TAG, "name:" + getVersionName(this) + ";code:" + getVersionCode(this));

        /*联系我们*/
        aboutContactCidv = (CustomItemDetailView) findViewById(R.id.about_contact_us);
        aboutContactCidv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, AboutContactUsActivity.class);
                AboutActivity.this.startActivity(intent);
            }
        });

        /*服务协议*/
        agreementTv = (CustomItemDetailView) findViewById(R.id.about_service_agreement);
        agreementTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://192.168.0.65:8080/waterquality/water_service.html");
//                startActivity(new Intent(Intent.ACTION_VIEW,uri));
                Intent intent = new Intent(AboutActivity.this, AgreementActivity.class);
                startActivity(intent);
            }
        });

        /*功能介绍*/


    }
    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
