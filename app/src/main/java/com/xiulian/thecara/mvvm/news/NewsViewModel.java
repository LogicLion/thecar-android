package com.xiulian.thecara.mvvm.news;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/10
 */
public class NewsViewModel extends ViewModel {

    public final ObservableField<List<NewsInfo>> newsInfoList = new ObservableField<>();
}
