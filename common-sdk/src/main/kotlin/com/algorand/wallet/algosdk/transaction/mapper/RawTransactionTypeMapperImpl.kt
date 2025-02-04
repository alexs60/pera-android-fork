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

package com.algorand.wallet.algosdk.transaction.mapper

import com.algorand.wallet.algosdk.transaction.model.RawTransactionType
import com.algorand.wallet.algosdk.transaction.model.payload.RawTransactionTypePayload
import javax.inject.Inject

internal class RawTransactionTypeMapperImpl @Inject constructor() : RawTransactionTypeMapper {

    override fun invoke(payload: RawTransactionTypePayload): RawTransactionType {
        return when (payload) {
            RawTransactionTypePayload.PAY_TRANSACTION -> RawTransactionType.PAY_TRANSACTION
            RawTransactionTypePayload.ASSET_TRANSACTION -> RawTransactionType.ASSET_TRANSACTION
            RawTransactionTypePayload.APP_TRANSACTION -> RawTransactionType.APP_TRANSACTION
            RawTransactionTypePayload.ASSET_CONFIGURATION -> RawTransactionType.ASSET_CONFIGURATION
            RawTransactionTypePayload.UNDEFINED -> RawTransactionType.UNDEFINED
        }
    }
}
