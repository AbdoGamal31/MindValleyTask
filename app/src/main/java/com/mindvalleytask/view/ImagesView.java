package com.mindvalleytask.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mindvalleytask.R;
import com.mindvalleytask.presenter.IImagePresenter;
import com.mindvalleytask.presenter.ImagesPresenter;
import java.util.List;

public class ImagesView extends AppCompatActivity implements IImageView {

  @BindView(R.id.rec_view) RecyclerView imageRecView;

  private ImageRecAdapter imageRecAdapter;
  private ImageViewModel imageViewModel;
  private IImagePresenter iImagePresenter;
  private ProgressDialog loadingInductor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    iImagePresenter = new ImagesPresenter(this);
    imageRecView.setLayoutManager(new LinearLayoutManager(this));
    imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
    imageRecAdapter = new ImageRecAdapter();
    loadingInductor = new ProgressDialog(this);
    if (imageViewModel.getImagesURL() != null) {
      displayAllImages(imageViewModel.getImagesURL());
    }
  }

  @Override public void showLoadingIndicator() {
    loadingInductor.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    loadingInductor.setCancelable(false);

    loadingInductor.show();
  }

  @Override public void hideLoadingIndicator() {
    loadingInductor.hide();
  }

  @Override public void displayAllImages(List<String> imagesURL) {
    imageRecAdapter.setImagesURL(imagesURL);
    imageViewModel.setImagesURL(imagesURL);
    imageRecView.setAdapter(imageRecAdapter);
    imageRecAdapter.notifyDataSetChanged();
  }

  public void getAllImages(View view) {
    iImagePresenter.getAllImages();
  }
}
