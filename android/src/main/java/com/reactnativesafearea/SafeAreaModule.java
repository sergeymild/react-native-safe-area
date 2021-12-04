package com.reactnativesafearea;

import android.view.View;
import android.view.WindowInsets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = SafeAreaModule.NAME)
public class SafeAreaModule extends ReactContextBaseJavaModule {
  public static final String NAME = "SafeArea";

  public SafeAreaModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @Nullable
  @Override
  public Map<String, Object> getConstants() {
    View rootView = getCurrentActivity().getWindow().getDecorView().findViewById(android.R.id.content);
    EdgeInsets insets = SafeAreaUtils.getSafeAreaInsets(rootView);
    HashMap<String, Object> constants = new HashMap<>(3);
    if (insets == null) return constants;
    constants.put("insetTop", PixelUtil.toDIPFromPixel(insets.top));
    constants.put("insetBottom", PixelUtil.toDIPFromPixel(insets.bottom));
    constants.put("statusBarHeight", PixelUtil.toDIPFromPixel(
      getCurrentActivity()
        .getResources()
        .getIdentifier("status_bar_height", "dimen", "android")
    ));
    return constants;
  }
}
