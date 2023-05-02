package com.example.ai_banh_my_khong_dat_g.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ai_banh_my_khong_dat_g.R;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {
    private List<PhotoBanner> mListPhoto;

    public PhotoViewPagerAdapter(List<PhotoBanner> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    @Override
    public int getCount() {
        if(mListPhoto!=null){
            return mListPhoto.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo_banner, container, false);
        ImageView img_photo_banner = view.findViewById(R.id.img_photo_banner);
        PhotoBanner photo = mListPhoto.get(position);
        img_photo_banner.setImageResource(photo.getResourceId());
        //add view
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
