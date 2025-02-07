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

package com.algorand.android.modules.keyreg.ui.mapper

import com.algorand.android.modules.keyreg.ui.model.KeyRegTransactionDetail
import com.algorand.android.modules.keyreg.ui.model.KeyRegTransactionPreview
import javax.inject.Inject

class KeyRegTransactionPreviewMapper @Inject constructor() {

    fun createInitialPreview(
        detail: KeyRegTransactionDetail,
    ): KeyRegTransactionPreview {
        return KeyRegTransactionPreview(
            isLoadingVisible = false,
            address = detail.address,
            fee = detail.fee ?: MINIMUM_TXN_FEE.toBigInteger(),
            type = detail.type,
            selectionKey = detail.selectionPublicKey.orEmpty(),
            votingKey = detail.voteKey.orEmpty(),
            stateProofKey = detail.sprfkey.orEmpty(),
            keyDilution = detail.voteKeyDilution.orEmpty(),
            firstValid = detail.voteFirstRound.orEmpty(),
            lastValid = detail.voteLastRound.orEmpty(),
            xNote = detail.xnote,
            note = detail.note,
            signTransactionEvent = null,
            showErrorEvent = null
        )
    }

    companion object {
        const val MINIMUM_TXN_FEE = 1000
    }
}
