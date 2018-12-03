package com.weex.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taobao.weex.WXRenderErrorCode;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.NestedContainer;
import com.taobao.weex.utils.WXSoInstallMgrSdk;
import com.weex.app.extend.ActivityNavBarSetter;
import com.weex.app.util.AppConfig;
import com.weex.app.util.Constants;


public class WXPageActivity extends AbsWeexActivity implements
    WXSDKInstance.NestedInstanceInterceptor {

  private static final String TAG = "WXPageActivity";
  private ProgressBar mProgressBar;
  private TextView mTipView;
  private boolean mFromSplash = false;

  @Override
  public void onCreateNestInstance(WXSDKInstance instance, NestedContainer container) {
    Log.d(TAG, "Nested Instance created.");
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wxpage);
    mContainer = findViewById(R.id.container);
    mProgressBar = findViewById(R.id.progress);
    mTipView = findViewById(R.id.index_tip);

    WXSDKEngine.setActivityNavBarSetter(new ActivityNavBarSetter(this));

    Intent intent = getIntent();
    Uri uri = intent.getData();
    String from = intent.getStringExtra("from");
    mFromSplash = "splash".equals(from);

    if (mFromSplash) {
      mUri = Uri.parse(AppConfig.getLaunchUrl());
    } else {
      mUri = uri;
    }

    if (!WXSoInstallMgrSdk.isCPUSupport()) {
      mProgressBar.setVisibility(View.INVISIBLE);
      mTipView.setText(R.string.cpu_not_support_tip);
      return;
    }

    String url = getUrl(mUri);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(url);
      getSupportActionBar().hide();
    }
    loadUrl(url);
  }

  private String getUrl(Uri uri) {
    String url = uri.toString();
    String scheme = uri.getScheme();
    if (uri.isHierarchical()) {
      if (TextUtils.equals(scheme, "http") || TextUtils.equals(scheme, "https")) {
        String weexTpl = uri.getQueryParameter(Constants.WEEX_TPL_KEY);
        if (!TextUtils.isEmpty(weexTpl)) {
          url = weexTpl;
        }
      }
    }
    return url;
  }

  protected void preRenderPage() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    Intent intent = new Intent("requestPermission");
    intent.putExtra("REQUEST_PERMISSION_CODE", requestCode);
    intent.putExtra("permissions", permissions);
    intent.putExtra("grantResults", grantResults);
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
  }


  @Override
  public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    mProgressBar.setVisibility(View.GONE);
    mTipView.setVisibility(View.GONE);
  }

  @Override
  public void onException(WXSDKInstance instance, String errCode, String msg) {
    mProgressBar.setVisibility(View.GONE);
    mTipView.setVisibility(View.VISIBLE);
    if (TextUtils.equals(errCode, WXRenderErrorCode.WX_NETWORK_ERROR)) {
      mTipView.setText(R.string.index_tip);
    } else {
      mTipView.setText("render error:" + errCode);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }
}
