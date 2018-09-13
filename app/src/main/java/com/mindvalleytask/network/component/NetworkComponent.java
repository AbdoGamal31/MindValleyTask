package com.mindvalleytask.network.component;

import android.content.Context;
import com.mindvalleytask.MyApplication;
import com.mindvalleytask.datastore.ImagesDataStore;
import com.mindvalleytask.network.module.ApplicationModule;
import com.mindvalleytask.network.module.NetworkModule;
import dagger.Component;
import javax.inject.Singleton;

import static com.mindvalleytask.MyApplication.BASE_URL;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
  void inject(ImagesDataStore imagesDataStore);

  class Initializer {

    private static NetworkComponent component;

    public static NetworkComponent buildComponent() {
      if (component == null) {
        Context context = MyApplication.getContext();
        component = DaggerNetworkComponent.builder()
            .applicationModule(new ApplicationModule(context))
            .networkModule(new NetworkModule(BASE_URL))
            .build();
      }
      return component;
    }
  }
}