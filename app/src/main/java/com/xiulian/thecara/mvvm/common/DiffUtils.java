/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiulian.thecara.mvvm.common;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.xiulian.thecara.entity.NewsInfo;

public class DiffUtils {


    private DiffUtil.ItemCallback<NewsInfo> mTestMusicItemCallback;

    private DiffUtils() {
    }

    private static DiffUtils sDiffUtils = new DiffUtils();

    public static DiffUtils getInstance() {
        return sDiffUtils;
    }


    public DiffUtil.ItemCallback<NewsInfo> getTestMusicItemCallback() {
        if (mTestMusicItemCallback == null) {
            mTestMusicItemCallback = new DiffUtil.ItemCallback<NewsInfo>() {
                @Override
                public boolean areItemsTheSame(@NonNull NewsInfo oldItem, @NonNull NewsInfo newItem) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(@NonNull NewsInfo oldItem, @NonNull NewsInfo newItem) {
                    return false;
                }
            };
        }
        return mTestMusicItemCallback;
    }
}
