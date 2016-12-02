package com.shellever.alertdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Author: Shellever
 * Date:   11/9/2016
 * Email:  shellever@163.com
 */

public class CustomAdapter extends BaseAdapter {

    private List<User> mUserList;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, List<User> userList) {
        this.mUserList = userList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.simple_list_item_1line, null);
            vh.iv = (ImageView) convertView.findViewById(R.id.iv_user_avatar);
            vh.tv = (TextView) convertView.findViewById(R.id.tv_user_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        User user = mUserList.get(position);
        vh.iv.setImageResource(user.getImageId());
        vh.tv.setText(user.getName());

        return convertView;
    }

    private static class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
