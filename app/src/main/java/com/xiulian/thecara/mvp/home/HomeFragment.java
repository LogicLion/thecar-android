package com.xiulian.thecara.mvp.home;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.icu.util.VersionInfo;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;
import com.xiulian.thecara.databinding.FragmentHomeBinding;
import com.xiulian.thecara.entity.VersionInfoBean;
import com.xiulian.thecara.utils.RxJavaExtKt;

import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 首页
 *
 * @author wzh
 * @date 2020/9/23
 */
public class HomeFragment extends BaseFragment {

    private TextView tvVersion;
    private HomeViewModel viewModel = new HomeViewModel();
    private CompositeDisposable compositeDisposable;
    private FragmentHomeBinding homeBinding;
    private VersionInfoBean mVersionInfo;

    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvVersion = findViewById(R.id.tv_version);
        TextView tvPrice = findViewById(R.id.tv_price);
        TextView tvDiscount = findViewById(R.id.tv_discount);
        Button btnChangeVersion = findViewById(R.id.btn_change_version);
        btnChangeVersion.setOnClickListener(v -> {
            mVersionInfo.setAppVersionCode(40);
            homeBinding.setVersionInfo(mVersionInfo);
        });


        homeBinding = DataBindingUtil.bind(contentView.getChildAt(0));


        SpannableString priceString = changeTextSize("￥50.00");
        SpannableString discountString = changeTextSize("9折");

        tvPrice.setText(priceString);
        tvDiscount.setText(discountString);

    }


    @Override
    public void initViewModel() {
        compositeDisposable = new CompositeDisposable();

        Disposable disposable = RxJavaExtKt.handleHttpResult(viewModel.getVersionCode())
                .subscribe(
                        versionInfoBean -> {
                            homeBinding.setVersionInfo(versionInfoBean);
                            mVersionInfo=versionInfoBean;
                        },
                        error -> {
                        }
                );
        compositeDisposable.add(disposable);
    }


    public static HomeFragment getInstance() {
        return new HomeFragment();
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

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
