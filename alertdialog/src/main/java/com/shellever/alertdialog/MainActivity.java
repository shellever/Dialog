package com.shellever.alertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dialog_alert).setOnClickListener(this);
        findViewById(R.id.btn_dialog_list_simple).setOnClickListener(this);
        findViewById(R.id.btn_dialog_list_radio).setOnClickListener(this);
        findViewById(R.id.btn_dialog_list_check).setOnClickListener(this);
        findViewById(R.id.btn_dialog_custom_adapter).setOnClickListener(this);
        findViewById(R.id.btn_dialog_custom_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_alert:
                showAlertDialog();
                break;
            case R.id.btn_dialog_list_simple:
                showSimpleListDialog();
                break;
            case R.id.btn_dialog_list_radio:
                showRadioListDialog();
                break;
            case R.id.btn_dialog_list_check:
                showCheckListDialog();
                break;
            case R.id.btn_dialog_custom_adapter:
                showCustomAdapterDialog();
                break;
            case R.id.btn_dialog_custom_view:
                showCustomViewDialog();
                break;
        }
    }

    private void showAlertDialog() {
        // 使用建造者模式创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_android_green_a700_24dp);     // 图标
        builder.setTitle(R.string.text_alert_title);                // 标题
        builder.setMessage(R.string.text_alert_msg);                // 消息内容
        // 确定性质的按钮 (是，有，确定)
        builder.setPositiveButton(R.string.text_alert_btn_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.text_alert_btn_positive_tip, Toast.LENGTH_SHORT).show();
            }
        });
        // 否定性质的按钮 (否，没有，取消)
        builder.setNegativeButton(R.string.text_alert_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.text_alert_btn_negative_tip, Toast.LENGTH_SHORT).show();
            }
        });
        // 中立性质的按钮 (不确定，沉默，保密，忽略，详细)
        builder.setNeutralButton(R.string.text_alert_btn_neutral, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.text_alert_btn_neutral_tip, Toast.LENGTH_SHORT).show();
            }
        });
        // 通过建造者来创建对话框
        AlertDialog dialog = builder.create();      // -
        // 显示对话框
        dialog.show();                              // -

        // 直接使用建造者显示对话框
        // builder.show();                             // +
    }

    private void showSimpleListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // builder.setTitle(R.string.text_list_simple_title);
        final String[] platforms = getResources().getStringArray(R.array.platforms);
        // 设置简单列表的子项内容
        builder.setItems(platforms, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Selected: " + platforms[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showRadioListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.text_list_radio_title);
        final String[] cities = getResources().getStringArray(R.array.cities);
        int checkedItem = 0;
        final String[] result = {cities[checkedItem]};
        // 设置单选子项的内容
        builder.setSingleChoiceItems(cities, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result[0] = cities[which];
            }
        });
        builder.setPositiveButton(R.string.text_list_radio_btn_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Selected: " + result[0], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.text_list_radio_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Default: " + result[0], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showCheckListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.text_list_check_title);
        final String[] languages = getResources().getStringArray(R.array.languages);
        final List<String> results = new ArrayList<>();
        // 设置多选子项内容
        builder.setMultiChoiceItems(languages, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    results.add(languages[which]);
                } else {
                    results.remove(languages[which]);
                }
            }
        });
        builder.setPositiveButton(R.string.text_list_check_btn_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, results.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.text_list_check_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.text_list_check_btn_negative, Toast.LENGTH_SHORT).show();
                results.clear();
            }
        });
        builder.show();
    }

    private void showCustomAdapterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final List<User> mUserList = new ArrayList<>();
        mUserList.add(new User(R.drawable.ic_user_avatar, "Stephen Curry"));
        mUserList.add(new User(R.drawable.ic_user_avatar, "Kevin Durant"));
        CustomAdapter adapter = new CustomAdapter(this, mUserList);
        // 设置自定义数据适配器，用于内部的ListView
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Selected: " + mUserList.get(which).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showCustomViewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_user_signin);
        builder.setTitle(R.string.text_custom_view_title);
        // builder.setView(R.layout.layout_signin);    // API21
        final View view = getLayoutInflater().inflate(R.layout.layout_signin, null);
        builder.setView(view);      // 设置自定义内容视图
        builder.setPositiveButton(R.string.text_custom_view_btn_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText mUsernameEt = (EditText) view.findViewById(R.id.et_username);
                EditText mPasswordEt = (EditText) view.findViewById(R.id.et_password);
                String username = mUsernameEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                String info = String.format(Locale.getDefault(), "[%s:%s]", username, password);
                Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.text_custom_view_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, R.string.text_custom_view_btn_negative, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
