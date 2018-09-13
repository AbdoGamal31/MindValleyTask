package com.mindvalleytask.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mindvalleytask.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ImageRecAdapter extends RecyclerView.Adapter<ImageRecAdapter.DataViewHolder> {

  private ArrayList<String> imagesURL;
  private Context context;

  public ImageRecAdapter() {
    this.imagesURL = new ArrayList<>();
  }

  @Override
  public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.image_card, parent, false);
    return new DataViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
    Picasso.with(context)
        .load(imagesURL.get(position))
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(holder.imageView, new Callback() {
          @Override public void onSuccess() {

          }

          @Override public void onError() {
            Picasso.with(context).load(imagesURL.get(position)).into(holder.imageView);
          }
        });
  }

  public void setImagesURL(List<String> imageList) {
    if (imagesURL == null) {
      return;
    }
    imagesURL.clear();
    imagesURL.addAll(imageList);
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return imagesURL.size();
  }

  public class DataViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_image)
    ImageView imageView;

    public DataViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}

