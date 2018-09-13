package com.mindvalleytask.datastore;

import com.mindvalleytask.model.ImageBaseModel;
import io.reactivex.Observable;
import java.util.List;

public interface IImageDataStore {
  Observable<List<ImageBaseModel>> getImages();
}
