package com.shellever.progressdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_progress_indeterminate).setOnClickListener(this);
        findViewById(R.id.btn_progress_determinate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_progress_indeterminate:
                createIndeterminateProgressDialog();
                break;
            case R.id.btn_progress_determinate:
                createDeterminateProgressDialog();
                break;
        }
    }

    private void createIndeterminateProgressDialog() {
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void createDeterminateProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(R.drawable.ic_action_download);  // 设置图标
        dialog.setTitle("当前下载进度");          // 设置标题
        dialog.setMessage("正在加载中...");      // 设置消息内容
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  // 设置水平进度条
        dialog.setMax(100);     // 设置最大进度值
        dialog.setCancelable(true);                 // 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);    // 设置在点击Dialog外是否取消Dialog进度条
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "忽略", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        // 按Back键事件监听
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    try {
                        i += 5;
                        Thread.sleep(200);              // 5000ms
                        dialog.incrementProgressBy(5);  // 更新进度
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }).start();
    }
}
