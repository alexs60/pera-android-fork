/*
 *   ~ Copyright 2022 Pera Wallet, LDA
 *   ~ Licensed under the Apache License, Version 2.0 (the "License");
 *   ~ you may not use this file except in compliance with the License.
 *   ~ You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *   ~ Unless required by applicable law or agreed to in writing, software
 *   ~ distributed under the License is distributed on an "AS IS" BASIS,
 *   ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   ~ See the License for the specific language governing permissions and
 *   ~ limitations under the License
 *   -->
 */

package com.algorand.android.modules.assetinbox.detail.transactiondetail.mapper

import android.content.Context
import com.algorand.android.modules.assetinbox.detail.transactiondetail.model.Arc59TransactionDetailArgs
import com.algorand.android.modules.assetinbox.detail.transactiondetail.model.Arc59TransactionDetailArgs.BaseAssetDetail
import com.algorand.android.modules.assetinbox.detail.transactiondetail.model.Arc59TransactionDetailArgs.BaseAssetDetail.AssetDetail
import com.algorand.android.modules.assetinbox.detail.transactiondetail.model.Arc59TransactionDetailArgs.BaseAssetDetail.CollectibleDetail
import com.algorand.android.modules.assetinbox.detail.transactiondetail.model.Arc59TransactionDetailPreview
import com.algorand.android.modules.currency.domain.model.Currency
import com.algorand.android.utils.formatAmount
import com.algorand.android.utils.formatAsAlgoString
import com.algorand.android.utils.formatAsAssetAmount
import com.algorand.android.utils.formatAsCurrency
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Arc59TransactionDetailPreviewMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : Arc59TransactionDetailPreviewMapper {

    override fun invoke(args: Arc59TransactionDetailArgs): Arc59TransactionDetailPreview {
        return Arc59TransactionDetailPreview(
            receiverAccountDetailPreview = args.receiverAccountDetailPreview,
            primaryText = getPrimaryText(args),
            secondaryText = getSecondaryText(args),
            assetId = args.assetDetail.id,
            nftUrl = (args.assetDetail as? CollectibleDetail)?.imageUrl,
            sendersAmountMap = args.senders.associate {
                it.address to getFormattedAssetAmount(it.amount, args.assetDetail)
            },
            optInExpense = args.optInExpense.formatAsAlgoString()
        )
    }

    private fun getPrimaryText(args: Arc59TransactionDetailArgs): String {
        return when (args.assetDetail) {
            is AssetDetail -> getFormattedAssetAmount(args.assetDetail)
            is CollectibleDetail -> {
                args.assetDetail.name.getName(context.resources)
            }
        }
    }

    private fun getSecondaryText(args: Arc59TransactionDetailArgs): String {
        return when (val assetDetail = args.assetDetail) {
            is AssetDetail -> getFormattedUsdValue(assetDetail)
            is CollectibleDetail -> {
                assetDetail.shortName.getName(context.resources)
            }
        }
    }

    private fun getFormattedAssetAmount(assetDetail: AssetDetail): String {
        return with(assetDetail) {
            amount.formatAmount(decimal).formatAsAssetAmount(shortName.getName(context.resources))
        }
    }

    private fun getFormattedAssetAmount(
        amount: String,
        assetDetail: BaseAssetDetail
    ): String {
        return when (assetDetail) {
            is AssetDetail -> amount.formatAsAssetAmount(assetDetail.shortName.getName(context.resources))
            is CollectibleDetail -> amount
        }
    }

    private fun getFormattedUsdValue(assetDetail: AssetDetail): String {
        return assetDetail.usdValue
            .toBigDecimalOrNull()
            ?.formatAsCurrency(Currency.USD.symbol, isCompact = true)
            ?: ""
    }
}
