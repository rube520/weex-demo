package com.weex.app.extend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.taobao.weex.appfram.navigator.IActivityNavBarSetter;
import com.weex.app.WXPageActivity;

import org.json.JSONObject;

/**
 * created by ffh
 * on 2018/12/3.
 */
public class ActivityNavBarSetter implements IActivityNavBarSetter {

    private Context mContext;

    public ActivityNavBarSetter(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean push(String param) {
        try {
            JSONObject jsonObject = new JSONObject(param);
            String url = jsonObject.optString("url", "");
            if (!TextUtils.isEmpty(url)) {
                Uri rawUri = Uri.parse(url);
                String scheme = rawUri.getScheme();
                Uri.Builder builder = rawUri.buildUpon();
                if (TextUtils.isEmpty(scheme)) {
                    builder.scheme("http");
                }
                Intent intent = new Intent(mContext, WXPageActivity.class);
                intent.setData(builder.build());
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean pop(String param) {
        return false;
    }

    @Override
    public boolean setNavBarRightItem(String param) {
        return false;
    }

    @Override
    public boolean clearNavBarRightItem(String param) {
        return false;
    }

    @Override
    public boolean setNavBarLeftItem(String param) {
        return false;
    }

    @Override
    public boolean clearNavBarLeftItem(String param) {
        return false;
    }

    @Override
    public boolean setNavBarMoreItem(String param) {
        return false;
    }

    @Override
    public boolean clearNavBarMoreItem(String param) {
        return false;
    }

    @Override
    public boolean setNavBarTitle(String param) {
        return false;
    }
}
