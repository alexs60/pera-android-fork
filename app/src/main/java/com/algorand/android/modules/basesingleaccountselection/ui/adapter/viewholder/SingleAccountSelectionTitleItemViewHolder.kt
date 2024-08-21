/*
 * Copyright 2022 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.algorand.android.modules.basesingleaccountselection.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.algorand.android.databinding.ItemSingleAccountSelectionTitleBinding
import com.algorand.android.models.BaseViewHolder
import com.algorand.android.modules.basesingleaccountselection.ui.model.SingleAccountSelectionListItem

class SingleAccountSelectionTitleItemViewHolder(
    private val binding: ItemSingleAccountSelectionTitleBinding
) : BaseViewHolder<SingleAccountSelectionListItem>(binding.root) {

    override fun bind(item: SingleAccountSelectionListItem) {
        if (item !is SingleAccountSelectionListItem.TitleItem) return
        binding.titleTextView.setText(item.textResId)
    }

    companion object {
        fun create(parent: ViewGroup): SingleAccountSelectionTitleItemViewHolder {
            val binding = ItemSingleAccountSelectionTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SingleAccountSelectionTitleItemViewHolder(binding)
        }
    }
}
