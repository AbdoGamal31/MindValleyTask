package com.mindvalleytask.usecase;

import com.mindvalleytask.datastore.ImagesDataStore;
import com.mindvalleytask.model.ImageBaseModel;
import com.mindvalleytask.repository.ImagesRepository;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

public class GetImagesUseCase {
  ImagesRepository imagesRepository;

  public GetImagesUseCase() {
    this.imagesRepository = new ImagesRepository(new ImagesDataStore());
  }

  public Observable<List<String>> getAllImages() {
    Observable<List<ImageBaseModel>> imageBaseModelObservable = imagesRepository.getImages();
    Observable<List<String>> profileImages = getAllProfileImages(imageBaseModelObservable);
    Observable<List<String>> imagesFromURLS = getAllImagesFromURL(imageBaseModelObservable);
    Observable<List<String>> downloadLinkImages = getAllDownloadImages(imageBaseModelObservable);

    return Observable.zip(profileImages, imagesFromURLS, downloadLinkImages,
        (profileImage, imagesFromURL, downloadLinkImage) -> {
          List<String> allImages = new ArrayList<>();
          allImages.addAll(profileImage);
          allImages.addAll(imagesFromURL);
          allImages.addAll(downloadLinkImage);
          return allImages;
        });
  }

  private Observable<List<String>> getAllProfileImages(
      Observable<List<ImageBaseModel>> imageBaseModelObservable) {
    return imageBaseModelObservable
        .flatMap(Observable::fromIterable)
        .map(user -> user.getUser().getProfileImage().getSmall())
        .toList().toObservable();
  }

  private Observable<List<String>> getAllImagesFromURL(
      Observable<List<ImageBaseModel>> imageBaseModelObservable) {
    return imageBaseModelObservable
        .flatMap(Observable::fromIterable)
        .map(user -> user.getUrls().getSmall())
        .toList().toObservable();
  }

  private Observable<List<String>> getAllDownloadImages(
      Observable<List<ImageBaseModel>> imageBaseModelObservable) {
    return imageBaseModelObservable
        .flatMap(Observable::fromIterable)
        .map(user -> user.getLinks().getDownload())
        .toList().toObservable();
  }
}
