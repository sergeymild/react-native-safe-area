package com.reactnativesafearea;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

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
    int getBottomInset() {
        Activity activity = getCurrentActivity();
        if (activity == null) return 0;
        WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(activity.getWindow().getDecorView());
        if (insets == null) return 0;
        return insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    int getTopInset() {
        Activity activity = getCurrentActivity();
        if (activity == null) return 0;
        WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(activity.getWindow().getDecorView());
        if (insets == null) return 0;
        return insets.getInsets(WindowInsetsCompat.Type.systemBars()).top;
    }
}
