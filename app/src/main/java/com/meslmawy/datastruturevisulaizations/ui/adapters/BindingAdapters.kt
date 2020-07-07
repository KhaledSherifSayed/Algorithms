/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.meslmawy.datastruturevisulaizations.ui.adapters

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.meslmawy.datastruturevisulaizations.models.BubbleItem
import java.util.*



@BindingAdapter("bindImageBackground")
fun bindImageBackground(textView: TextView, item: BubbleItem?) {
    if (item != null) {
        if (item.fullySorted == false)
            textView.setBackgroundColor((Color.parseColor("#ffc107")))
        else
            textView.setBackgroundColor((Color.parseColor("#c79100")))
    }
}


@BindingAdapter("arrowVisibility")
fun arrowVisibility(imageView: ImageView, item: BubbleItem?) {
    when (item?.iterate) {
        true -> {
            imageView.visibility = View.VISIBLE
        }
        false -> {
            imageView.visibility = View.INVISIBLE
        }
    }
}

