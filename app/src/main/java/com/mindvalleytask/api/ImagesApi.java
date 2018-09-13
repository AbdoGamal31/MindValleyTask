package com.mindvalleytask.api;

import com.mindvalleytask.model.ImageBaseModel;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ImagesApi {
  @GET("wgkJgazE")
  Observable<List<ImageBaseModel>> getImages();
}
