package com.hzx.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hman.notifydialog.Effectstype;

public class ModifyPwdActivity extends BaseActivity {

    private static final String TAG = "ModifyPwdActivity";
    private EditText oldPwdEt,pwdEt,rePwdEt;
    private Button commitBt;
    private String oldPwdStr,newPwdStr,reNewPwdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        setHeaderTitle(R.string.modify_password);
        getBackHeaderLeft();
        initView();
    }

    private void initView() {
        oldPwdEt = (EditText) findViewById(R.id.old_forget_pwd_pwd_et); // 旧密码
        pwdEt = (EditText) findViewById(R.id.forget_pwd_pwd_et); // 新密码
        rePwdEt = (EditText) findViewById(R.id.forget_pwd_re_pwd_et); // 确认密码
        commitBt = (Button) findViewById(R.id.forget_pwd_commit_bt); // 提交
        commitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPwdStr = oldPwdEt.getText().toString();
                newPwdStr = pwdEt.getText().toString();
                reNewPwdStr = rePwdEt.getText().toString();
                if (oldPwdStr == null || newPwdStr == null || reNewPwdStr == null || ("").equals(oldPwdStr) || ("").equals(newPwdStr) || ("").equals(reNewPwdStr)) {
                    dialogOk(Effectstype.Slideleft,intToString(R.string.password_not_null));
                    return;
                }
                if (!newPwdStr.equals(reNewPwdStr)) {
                    dialogOk(Effectstype.Shake,intToString(R.string.password_must_same));
                    return;
                }

//                String url = appContext.myPropertyUtil.getModifyPwd();
//                Log.e(TAG, url);
                showDialog();
//                havaModifyPwdUrl(url);
            }
        });
    }

//    private void havaModifyPwdUrl(String url) {
//
//        FormBody fBody = new FormBody.Builder()
//                .add("user_id",String.valueOf(appContext.uid))
//                .add("oldPwd",oldPwdStr)
//                .add("newPwd",newPwdStr).build();
//        final Request request = new Request.Builder().url(url).post(fBody).build();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response response = new OkHttpClient().newCall(request).execute();
//                    String stBody = response.body().string();
//                    Message message = new Message();
//                    Log.e(TAG,"stBody:" + stBody);
//                    if (stBody.contains("failed")) {
//                        String string = stBody.substring(stBody.indexOf("resMsg") + 9, stBody.lastIndexOf(",") - 1);
//                        //   Toast.makeText(RegisterActivity.this,"注册失败,"+s,Toast.LENGTH_SHORT).show();
//                        message.what = StrUtil.FORGET_PWD_FAILED;
//                        message.obj = string;
//                        handler.sendMessage(message);
//                        return;
//                    } else if (stBody.contains("succeed")){
//                        message.what = StrUtil.FORGET_PWD_SUCCEED;
//                        handler.handleMessage(message);
//                        Looper.prepare();
//                        Toast.makeText(ModifyPwdActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case StrUtil.FORGET_PWD_SUCCEED:
//                    dismissDialog();
////                    Toast.makeText(ModifyPwdActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
//                    finish();
//                    break;
//                case  StrUtil.FORGET_PWD_FAILED:
//                    dismissDialog();
//                    Toast.makeText(ModifyPwdActivity.this,"密码修改失败，" + msg.obj.toString(),Toast.LENGTH_SHORT).show();
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
}
