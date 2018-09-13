package com.mindvalleytask;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

  private static Context context;
  public static final String BASE_URL = "https://pastebin.com/raw/";
  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
  }

  public static Context getContext() {
    return context;
  }
}