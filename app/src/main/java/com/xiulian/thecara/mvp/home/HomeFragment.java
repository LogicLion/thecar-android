package com.xiulian.thecara.mvp.home;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;

import org.jetbrains.annotations.Nullable;

/**
 * 首页
 *
 * @author wzh
 * @date 2020/9/23
 */
public class HomeFragment extends BaseFragment implements HomeContract {

    private TextView tvVersion;

    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupPresenter() {


    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvVersion = findViewById(R.id.tv_version);
        TextView tvPrice = findViewById(R.id.tv_price);
        TextView tvDiscount = findViewById(R.id.tv_discount);


        SpannableString priceString = changeTextSize("￥50.00");
        SpannableString discountString = changeTextSize("9折");

        tvPrice.setText(priceString);
        tvDiscount.setText(discountString);


    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    @Override
    public void setAppVersion(int versionCode) {
        tvVersion.setText("版本号：" + versionCode);
    }


    public SpannableString changeTextSize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains("￥")) {
            spannableString.setSpan(new RelativeSizeSpan(0.5f), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.5f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (value.contains("折")) {
            spannableString.setSpan(new RelativeSizeSpan(0.5f), value.length() - 1, value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

}
