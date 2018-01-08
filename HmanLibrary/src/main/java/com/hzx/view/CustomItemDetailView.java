package com.hzx.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzx.activity.R;

/**
 * 设置界面的列表选项
 * Created by Hman on 2017/8/2.
 */
public class CustomItemDetailView extends RelativeLayout {

    private static final String TAG = "CustomItemDetailView";
    private TextView leftNameTv, rightNameTv;
    private RelativeLayout root;
    private ImageView rightIconIv;
    private CircleImageView headPhotoCiv;
    private Context context;
    private OnClickItemDetailListener listener;

    public interface OnClickItemDetailListener {
        String clickItem(String left, String right, View view);
    }

    public void setOnClickItemDetailListener(OnClickItemDetailListener listener){
        this.listener = listener;
    }

    public CustomItemDetailView(Context context) {
        super(context);
        init(context);
    }

    public CustomItemDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setValue(attrs);
    }

    public CustomItemDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setValue(attrs);
    }

    public void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_detail,this);
        leftNameTv = (TextView) view.findViewById(R.id.detail_left_name);
        rightNameTv = (TextView) view.findViewById(R.id.detail_right_value);
        root = (RelativeLayout) view.findViewById(R.id.item_root_id);
        rightIconIv = (ImageView) view.findViewById(R.id.detail_right_icon);
        headPhotoCiv = (CircleImageView) view.findViewById(R.id.detail_mine_me_header);

//        setAble(false);
    }

//    public void setAble(boolean b) {
//        if (b) {
//            root.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        String leftText = leftNameTv.getText().toString();
//                        String rightText = rightNameTv.getText().toString();
//                        String back = listener.clickItem(leftText, rightText, v);
//                        Log.e(TAG, "back:" + back);
//                        rightNameTv.setText(back);
//                    }
//                }
//            });
//        }
//    }

    public void setValue(AttributeSet attrs) {
        // 加载自定义属性配置
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomItemDetailView);
        // 为自定义属性添加特性
        if (typedArray != null) {
            // 设置左文字
            String leftText = typedArray.getString(R.styleable.CustomItemDetailView_left_name);
            int leftColor = typedArray.getColor(R.styleable.CustomItemDetailView_left_name_color, getResources().getColor(R.color.black));
            int leftTextSize = typedArray.getInt(R.styleable.CustomItemDetailView_left_text_size,15);
            leftNameTv.setText(leftText);
            leftNameTv.setTextColor(leftColor);
            leftNameTv.setTextSize(leftTextSize);

            // 设置右边文字
            String rightText = typedArray.getString(R.styleable.CustomItemDetailView_right_name);
            int rightColor = typedArray.getColor(R.styleable.CustomItemDetailView_right_name_color, getResources().getColor(R.color.main_bottom_tab_textcolor_normal));
            int rightTextSize = typedArray.getInt(R.styleable.CustomItemDetailView_right_text_size, 15);
            rightNameTv.setText(rightText);
            rightNameTv.setTextColor(rightColor);
            rightNameTv.setTextSize(rightTextSize);

            // 设置item的背景
            int rootBg = typedArray.getResourceId(R.styleable.CustomItemDetailView_item_bg,R.color.white);
            float rootHeight = typedArray.getDimension(R.styleable.CustomItemDetailView_item_height, 45);
            root.setBackgroundResource(rootBg);
            // root.setMinimumHeight();

            // 设置右侧头像
            int rightPhoto = typedArray.getResourceId(R.styleable.CustomItemDetailView_right_photo,0);
            // headPhotoCiv.setBackgroundResource(rightPhoto);
            headPhotoCiv.setImageResource(rightPhoto);

            // 设置右边图标
            int rightIcon = typedArray.getResourceId(R.styleable.CustomItemDetailView_right_icon,R.mipmap.right_next_black);
            rightIconIv.setBackgroundResource(rightIcon);

            typedArray.recycle();
        }
    }

    /*设置总背景*/
    public void setRootBg(int res) {
        root.setBackgroundResource(res);
    }

    /*设置字体颜色*/
    public void setTextColor(int color) {
        leftNameTv.setTextColor(color);
        rightNameTv.setTextColor(color);
    }

    public void setLeftColor(int color){
        leftNameTv.setTextColor(color);
    }

    /*字体颜色设置白色*/
    public void setTextColorWhite() {
        leftNameTv.setTextColor(getResources().getColor(R.color.white));
        rightNameTv.setTextColor(getResources().getColor(R.color.white));
    }

    /*更换右图标*/
    public void changeRightICon(int res) {
        rightIconIv.setBackgroundResource(res);
    }

    /*设置右侧字内容*/
    public void setRightNameText(String string) {
        rightNameTv.setText(string);
    }

    /*获取右侧字内容*/
    public String getRightNameText() {
        return rightNameTv.getText().toString();
    }
}
