/*
 *  Copyright 2022 Pera Wallet, LDA
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.algorand.android.modules.assetinbox.send.ui.mapper

import com.algorand.android.models.BaseAssetDetail
import com.algorand.android.modules.assetinbox.send.domain.model.Arc59SendSummary
import com.algorand.android.modules.assetinbox.send.domain.model.Arc59SendTransaction
import com.algorand.android.modules.assetinbox.send.ui.model.Arc59SendSummaryPreview
import com.algorand.android.utils.ErrorResource
import com.algorand.android.utils.Event
import java.math.BigInteger

interface Arc59SendSummaryPreviewMapper {
    operator fun invoke(
        summary: Arc59SendSummary,
        amount: BigInteger,
        assetDetail: BaseAssetDetail,
        isLoading: Boolean,
        showError: Event<ErrorResource>?,
        onNavBack: Event<Unit>?,
        arc59Transactions: Event<List<Arc59SendTransaction>>?
    ): Arc59SendSummaryPreview

    fun getInitialPreview(): Arc59SendSummaryPreview
}
