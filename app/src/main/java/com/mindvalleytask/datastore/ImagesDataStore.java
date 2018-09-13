package com.mindvalleytask.datastore;

import com.mindvalleytask.api.ImagesApi;
import com.mindvalleytask.model.ImageBaseModel;
import com.mindvalleytask.network.component.NetworkComponent;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Retrofit;

public class ImagesDataStore implements IImageDataStore {
  @Inject
  Retrofit retrofit;
  ImagesApi imagesApi;

  public ImagesDataStore() {
    NetworkComponent.Initializer.buildComponent().inject(this);
    imagesApi = retrofit.create(ImagesApi.class);
  }

  @Override
  public Observable<List<ImageBaseModel>> getImages() {
    return imagesApi.getImages();
  }
}
