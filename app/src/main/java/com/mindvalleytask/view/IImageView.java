package com.mindvalleytask.view;

import java.util.List;

public interface IImageView {

  void showLoadingIndicator();

  void hideLoadingIndicator();

  void displayAllImages(List<String> imagesURL);

}
