package com.reactnativesafearea;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;

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


    @ReactMethod(isBlockingSynchronousMethod = true)
    float getBottomInset() {
        Activity activity = getCurrentActivity();
        if (activity == null) return 0;
        WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(activity.getWindow().getDecorView());
        if (insets == null) return 0;
        return PixelUtil.toDIPFromPixel(insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    float getTopInset() {
        Activity activity = getCurrentActivity();
        if (activity == null) return 0;
        WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(activity.getWindow().getDecorView());
        if (insets == null) return 0;
        return PixelUtil.toDIPFromPixel(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top);
    }
}
