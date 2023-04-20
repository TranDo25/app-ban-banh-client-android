package com.example.ai_banh_my_khong_dat_g.uicomponent.itemdescription;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.ai_banh_my_khong_dat_g.GlobalVariable;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.databinding.ItemDescriptionBinding;
import com.example.ai_banh_my_khong_dat_g.model.Item;

import java.util.Locale;

public class ItemDescriptionFragment extends Fragment {
    private ItemDescriptionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ItemDescriptionBinding.inflate(inflater, container, false);

        setupActionButton();

        assert getArguments() != null;
        Item item = (Item) getArguments().getSerializable("Item");
        validateLayout(item);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupActionButton() {
        ImageView decreaseImage = binding.QLDecreseImage;
        decreaseImage.setOnClickListener(view -> {});

        ImageView increaseImage = binding.QLIncreaseImage;
        increaseImage.setOnClickListener(view -> {});

        binding.ExitImage.setOnClickListener(view ->{
            getParentFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                    .remove(this)
                    .commit();
            getParentFragmentManager().popBackStack();
        });
    }

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    private void validateLayout(@NonNull Item model) {
        binding.ItemImage.setImageResource(model.getImageRID());
        binding.CLNameText.setText(model.getNameRID());

        int textColor1 = GlobalVariable.currentContext.getResources().getColor(R.color.text_dark_pink);
        int textColor2 = GlobalVariable.currentContext.getResources().getColor(R.color.text_brown);
        if (model.getPriceDown() == 0) {
            binding.CLPriceDownText.setAlpha(0.0f);
            binding.CLOriginalPriceText.setText(model.getPrice());
            binding.CLOriginalPriceText.setTextColor(textColor1);
            binding.CLRealPriceText.setAlpha(0.0f);
        }
        else {
            binding.CLPriceDownText.setAlpha(1.0f);
            binding.CLPriceDownText.setText(String.valueOf(model.getPriceDown()) + '%');
            binding.CLOriginalPriceText.setText(String.format(Locale.ENGLISH, "%,d", model.getPrice()) + "đ");
            binding.CLOriginalPriceText.setTextColor(textColor1);
            binding.CLOriginalPriceText.setPaintFlags(binding.CLOriginalPriceText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            int realPrice = (int)((float)model.getPrice()*model.getPriceDown()/100.0f);
            binding.CLRealPriceText.setAlpha(1.0f);
            binding.CLRealPriceText.setText(String.format(Locale.ENGLISH, "%,d", realPrice) + "đ");
            binding.CLRealPriceText.setTextColor(textColor2);
        }

        int color1 = GlobalVariable.currentContext.getResources().getColor(R.color.color_dark_pink);
        int color2 = GlobalVariable.currentContext.getResources().getColor(R.color.color_brown);
        binding.CLFavouriteButton.setColorFilter(model.isFavourite() ? color1 : color2);
    }
}