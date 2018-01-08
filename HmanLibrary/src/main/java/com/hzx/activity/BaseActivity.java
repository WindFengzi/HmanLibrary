package com.hzx.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hman.notifydialog.Effectstype;
import com.hman.notifydialog.NiftyDialogBuilder;
import com.hzx.view.CustomLoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";
    public TextView titleTv; // 标题
    public ImageButton titleLeftIb; // 左边按钮
    public TextView titleRightTv;
    public LinearLayout titleLeftLL;
    private static boolean isExit = false;
//    public MyApplication appContext;
    private CustomLoadingDialog progressDialog = null;
    private RelativeLayout headRoot;
    public NiftyDialogBuilder dialogBuilder;// 弹框
    public Effectstype effect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_header);

        // 添加Activity到堆栈
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); // 隐藏软件键盘输入。
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题栏。
//        AppManager.getAppManager().addActivity(this);

//        appContext = (MyApplication) getApplication();
        dialogBuilder = NiftyDialogBuilder.getInstance(this);
    }

    /**
     * 修改背景颜色
     * */
    public void modifyColor(int colorId) {
        headRoot = (RelativeLayout) findViewById(R.id.header_root);
//        headRoot.setBackgroundResource(colorId);
        headRoot.setBackgroundColor(colorId);
    }

    /**
     * 修改标题
     * */
    public void setHeaderTitle(int title){
        titleTv = (TextView) findViewById(R.id.title_center);
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText(title);
    }

    /**
     * 左侧返回按钮
     * */
    public void getBackHeaderLeft() {
        titleLeftIb = (ImageButton) findViewById(R.id.title_left);
        titleLeftIb.setVisibility(View.VISIBLE);
        titleLeftIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleLeftLL = (LinearLayout) findViewById(R.id.title_left_ll);
        titleLeftLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 右侧文字设置
     * */
    public void getHeaderRight(int string) {
        titleRightTv = (TextView) findViewById(R.id.title_right);
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText(string);
    }

    /**
     * 显示自定义
     */
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = CustomLoadingDialog.createDialog(this);
//			progressDialog.setMessage("正在加载中...");
        }
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    /**
     * 判断progressDialog是否存在
     * */
    public Boolean isDialog() {
        return progressDialog != null;
    }

    public void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public String intToString(int strInt) {
        return getResources().getString(strInt);
    }

    public void dialogOk(Effectstype type, String message) {
        effect = type;
        dialogBuilder.withTitle("提示信息")
                .withDividerColor("#11000000")                              //def
                .withMessage(message)                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FF22cb6d").withDuration(700)
                .isCancelableOnTouchOutside(true)
                .withEffect(effect).withButton1Text("确定").setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//				Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }
        }).show();
    }

    public void dialogEditText(Effectstype type, String title, final View view, Context context) {
        effect = type;
        dialogBuilder.withTitle(title)
                .withDividerColor("#11000000")                              //def
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FF22cb6d").withDuration(700)
                .isCancelableOnTouchOutside(true).setCustomView(view,context)
                .withEffect(effect).withButton1Text("确定").setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nickName = (EditText) view.findViewById(R.id.the_nick_name);
                String nickText = nickName.getText().toString();
                Toast.makeText(v.getContext(), nickName.getText().toString() + "" + (!("").equals(nickText)), Toast.LENGTH_SHORT).show();
                if (!("").equals(nickText)) {
                    dialogListener.onClickOkGoBackTextListener(nickName.getText().toString());
                }
                dialogBuilder.dismiss();
            }
        }).show();
    }

    /**
     * 判断是否是数字
     * */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 判断是否是json结构
     */
    public boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public boolean compareString(String type, String response) {
        if (type != null && response != null) {
            return type.equals(response);
        }
        return false;
    }
    OnDialogBackTextListener dialogListener;
    public interface OnDialogBackTextListener{
        void onClickOkGoBackTextListener(String text);
    }

    public void setOnDialogBackTextListener (OnDialogBackTextListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    OnBackDataListener listener;
    public interface OnBackDataListener {
        void goBackDataString(String stBody);
    }
    public void setOnBackDataListener(OnBackDataListener listener) {
        this.listener = listener;
    }

    public Runnable getInstanceRunnable(final String url, final Map<String, String> params) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                FormBody.Builder builder = new FormBody.Builder();
                for(String param : params.keySet()) {
                    Log.e(TAG, "k:" + param + ";v:" + params.get(param));
                    builder = builder.add(param, params.get(param));
                }
                FormBody fBody = builder.build();
                Request request = new Request.Builder().url(url).post(fBody).build();
                try {
                    Response response = new OkHttpClient().newCall(request).execute();
                    Message message = new Message();
                    Log.e(TAG,"url:"+ url + "," + response.isSuccessful() + ",code:" + response.code());
                    if (response.isSuccessful()) {
                        String stBody = response.body().string();
                        if (isJson(stBody)){
                            Log.e(TAG,"BaseActivity stBody:" + stBody);
                            listener.goBackDataString(stBody);
                        }
                    }else {
                        listener.goBackDataString(String.valueOf(response.code()));
//                        Toast.makeText(BaseActivity.this, "未知错误" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    }

//    @Override
//    public void goBackDataString(String stBody) {
//        Message message = new Message();
//        if (isNumeric(stBody)) {
//            message.obj = stBody;
//            myHandler.sendMessage(message);
//            return;
//        }
//        User backMessage = JSON.parseObject(stBody, User.class);
//        Log.e(TAG,"LoginActivity stBody:" + stBody);
//        message.obj = backMessage;
//        myHandler.sendMessage(message);
//    }

//    Handler myHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (isNumeric(msg.obj.toString())) {
//                Toast.makeText(LoginActivity.this, "未知错误" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
//                if (isDialog()) {dismissDialog();}
//                return;
//            }
//            User user = (User) msg.obj;
//            Log.e(TAG, user.getRes() + "; failed:" + compareString(StrUtils.RESPONSE_FAILED, user.getRes()));
//            if (compareString(StrUtils.RESPONSE_FAILED, user.getRes())) {
//                Toast.makeText(LoginActivity.this, user.getResMsg(), Toast.LENGTH_SHORT).show();
//                dismissDialog();
//                return;
//            } else if (compareString(StrUtils.RESPONSE_SUCCEED, user.getRes())) {
//                saveCacheData(user.getObj());
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//            if (isDialog()) {dismissDialog();}
//        }
//    };
}
