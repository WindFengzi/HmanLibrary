package com.hzx.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;

import com.hzx.activity.R;

/**
 * 加载Loading
 * */
public class CustomLoadingDialog extends Dialog {
	private Context context = null;
	private static CustomLoadingDialog customProgressDialog = null;

	public CustomLoadingDialog(Context context) {
		super(context);
		this.context = context;
	}

	public CustomLoadingDialog(Context context, int theme) {
		super(context, theme);
	}

	public static CustomLoadingDialog createDialog(Context context) {
		customProgressDialog = new CustomLoadingDialog(context, R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.custom_loading_dialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}

		ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
		animationDrawable.start();
	}

	/**
	 * 
	 * [Summary] setTitile 标题
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public CustomLoadingDialog setTitile(String strTitle) {
		return customProgressDialog;
	}

	/**
	 * 
	 * [Summary] setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public CustomLoadingDialog setMessage(String strMessage) {
		// TextView tvMsg =
		// (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
		//
		// if (tvMsg != null){
		// tvMsg.setText(strMessage);
		// }

		return customProgressDialog;
	}
}
