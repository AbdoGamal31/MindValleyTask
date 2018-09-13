package com.mindvalleytask.presenter;

import com.mindvalleytask.usecase.GetImagesUseCase;
import com.mindvalleytask.view.IImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImagesPresenter implements IImagePresenter {
  private GetImagesUseCase getImagesUseCase;
  private IImageView iImageView;

  public ImagesPresenter(IImageView iImageView) {
    this.getImagesUseCase = new GetImagesUseCase();
    this.iImageView = iImageView;
  }

  @Override public void getAllImages() {
    iImageView.showLoadingIndicator();
    GetImagesUseCase getImagesUseCase =
        new GetImagesUseCase();
    getImagesUseCase.getAllImages()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(profileImage -> {
          iImageView.displayAllImages(profileImage);
          iImageView.hideLoadingIndicator();
        }, throwable -> {
          iImageView.hideLoadingIndicator();
        });
  }
}
