package com.mindvalleytask.repository;

import com.mindvalleytask.datastore.IImageDataStore;
import com.mindvalleytask.model.ImageBaseModel;
import io.reactivex.Observable;
import java.util.List;

public class ImagesRepository {
  IImageDataStore iImageDataStore;

  public ImagesRepository(IImageDataStore iImageDataStore) {
    this.iImageDataStore = iImageDataStore;
  }

  public Observable<List<ImageBaseModel>> getImages() {
    return iImageDataStore.getImages();
  }
}
