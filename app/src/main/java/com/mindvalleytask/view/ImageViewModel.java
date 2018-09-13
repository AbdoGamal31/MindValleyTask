package com.mindvalleytask.view;

import android.arch.lifecycle.ViewModel;
import java.util.List;

public class ImageViewModel extends ViewModel {
  List<String> imagesURLList;

  public List<String> getImagesURL() {
    return imagesURLList;
  }

  public void setImagesURL(List<String> imagesURLList) {
    this.imagesURLList = imagesURLList;
  }
}
