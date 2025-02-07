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

package com.algorand.android.modules.assetinbox.send.summary.ui

import com.algorand.android.R
import com.algorand.android.ledger.LedgerBleOperationManager
import com.algorand.android.ledger.LedgerBleSearchManager
import com.algorand.android.models.AnnotatedString
import com.algorand.android.modules.assetinbox.send.summary.domain.mapper.Arc59SignedTransactionDetailMapper
import com.algorand.android.modules.assetinbox.send.summary.domain.model.Arc59SendTransaction
import com.algorand.android.modules.transaction.signmanager.ExternalTransactionQueuingHelper
import com.algorand.android.modules.transaction.signmanager.ExternalTransactionSignManager
import com.algorand.android.modules.transaction.signmanager.ExternalTransactionSignResult
import com.algorand.android.modules.transaction.signmanager.ExternalTransactionSignResult.Error
import com.algorand.android.modules.transaction.signmanager.ExternalTransactionSignResult.Success
import com.algorand.android.usecase.AccountDetailUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class Arc59SendTransactionSignManager @Inject constructor(
    private val arc59SignedTransactionDetailMapper: Arc59SignedTransactionDetailMapper,
    ledgerBleSearchManager: LedgerBleSearchManager,
    ledgerBleOperationManager: LedgerBleOperationManager,
    externalTransactionQueuingHelper: ExternalTransactionQueuingHelper,
    accountDetailUseCase: AccountDetailUseCase
) : ExternalTransactionSignManager<Arc59SendTransaction>(
    ledgerBleSearchManager,
    ledgerBleOperationManager,
    externalTransactionQueuingHelper,
    accountDetailUseCase
) {

    private var unsignedTransactions: List<Arc59SendTransaction>? = null

    val arc59SendTransactionSignResultFlow = signResultFlow.map {
        when (it) {
            is Success<*> -> mapSignedTransactions(
                unsignedTransactions,
                it.signedTransactionsByteArray
            )

            else -> it
        }
    }

    fun signArc59SendTransaction(transactions: List<Arc59SendTransaction>) {
        unsignedTransactions = transactions
        signTransaction(transactions)
    }

    private fun mapSignedTransactions(
        transactions: List<Arc59SendTransaction>?,
        signedTransactions: List<ByteArray?>?
    ): ExternalTransactionSignResult {
        val signedTransactionDetails =
            arc59SignedTransactionDetailMapper(transactions, signedTransactions)
        return if (signedTransactionDetails.isNullOrEmpty()) {
            Error.Defined(AnnotatedString(R.string.an_error_occured))
        } else {
            Success(signedTransactionDetails)
        }
    }
}
