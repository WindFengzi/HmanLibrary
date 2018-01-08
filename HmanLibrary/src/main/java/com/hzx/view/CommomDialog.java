package com.hzx.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

import com.hzx.activity.R;

import java.util.List;

/**
 * Created by Hman on 2017/8/7.
 */
public class CommomDialog {

    private final static String TAG = "CommomDialog";
    private Dialog dialog;
    Context context;
    private List<?> lists;
    DialogPositiveListener positiveListener;
    DialogNegativeListener negativeListener;
    DialogItemListener itemListener;

    public CommomDialog(Context context) {
        super();
        this.context = context;
        initDialog();
    }

    public CommomDialog(Context context, List<?> lists) {
        super();
        this.context = context;
        this.lists = lists;
//        initDialog();
    }

    public void setPositiveListener(DialogPositiveListener positiveListener) {
        this.positiveListener = positiveListener;
    }

    public void setNegativeListener(DialogNegativeListener negativeListener) {
        this.negativeListener = negativeListener;
    }

    public void setItemListener(DialogItemListener itemListener) {
        this.itemListener = itemListener;
    }

    /**
     * context promptMsg 提示信息
     * */
    public Dialog initDialog(String promptMsg) {
        return initDialog();
    }

    public static Dialog creatAlertDialog(Context context, View view) {
        Dialog loading = new Dialog(context, R.style.commonDialog);
        loading.setCancelable(true);
        loading.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (LinearLayout.LayoutParams.MATCH_PARENT)));
        return loading;
    }

    /**
     * context promptMsg 提示信息 cancleBtnMsg 取消按钮信息 sureBtnMsg 确认按钮信息
     * */
    public Dialog initDialog() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_common_dialog, null);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        dialog = creatAlertDialog(context, view);
        ListView lv = (ListView) view.findViewById(R.id.com_dialog_lv);
        lv.setAdapter(new ArrayAdapter<String>(context, R.layout.layout_common_item, R.id.dialog_item_id, (List<String>) lists));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (itemListener != null) {
                    itemListener.onClick((String) lists.get(position));
                }
                dialog.dismiss();
            }
        });
//        TextView tvPromptMsg = (TextView) view.findViewById(R.id.tvPromptMsg);
//        Button btnCancle = (Button) view.findViewById(R.id.btnCancle);
//        Button btnSure = (Button) view.findViewById(R.id.btnSure);
//
//        tvPromptMsg.setText(promptMsg);
//        btnCancle.setText(cancleBtnMsg);
//        btnSure.setText(sureBtnMsg);

//        btnCancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (negativeListener != null) {
//                    negativeListener.onClick();
//                }
//                dialog.dismiss();
//            }
//        });
//        btnSure.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (positiveListener != null) {
//                    positiveListener.onClick();
//                }
//                dialog.dismiss();
//            }
//        });
        return dialog;
    }

    public interface DialogPositiveListener {
        void onClick();
    }

    public interface DialogNegativeListener {
        void onClick();
    }

    public interface DialogItemListener {
        void onClick(String string);
    }
}
