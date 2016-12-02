package com.shellever.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_style_spinner).setOnClickListener(this);
        findViewById(R.id.btn_style_frame).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_style_spinner:
                createSpinnerDialog(this, "正在加载中...").show();
                break;
            case R.id.btn_style_frame:
                createFrameDialog(this, "正在加载中...").show();
                break;
        }
    }

    public Dialog createSpinnerDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.spinner_style_dialog, null);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_spinner_dialog);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_spinner);
        TextView textView = (TextView) view.findViewById(R.id.tv_spinner);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        imageView.startAnimation(animation);

        textView.setText(msg);

        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(true);         // 设置按返回键时取消对话框
        dialog.setCanceledOnTouchOutside(false);    // 设置点击对话框外部时不取消对话框，默认为true
        dialog.setContentView(linearLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        return dialog;
    }

    public Dialog createFrameDialog(Context context, String msg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.frame_style_dialog, null);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_frame_dialog);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_frame);
        TextView textView = (TextView) view.findViewById(R.id.tv_frame);

        AnimationDrawable ad = (AnimationDrawable) imageView.getDrawable();
        ad.start();

        textView.setText(msg);

        Dialog dialog = new Dialog(context, R.style.loading_dialog);    //
        dialog.setCancelable(true);         // 设置按返回键时取消对话框
        dialog.setCanceledOnTouchOutside(false);    // 设置点击对话框外部时不取消对话框，默认为true
        dialog.setContentView(linearLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        return dialog;
    }
}
