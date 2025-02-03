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

package com.algorand.wallet.account.core.domain.usecase

import com.algorand.wallet.account.custom.domain.model.CustomInfo
import com.algorand.wallet.account.custom.domain.usecase.SetAccountCustomInfo
import com.algorand.wallet.account.local.domain.model.LocalAccount
import com.algorand.wallet.account.local.domain.usecase.SaveHdKeyAccount

internal class AddHdKeyAccountUseCase(
    private val saveHdKeyAccount: SaveHdKeyAccount,
    private val setCustomInfo: SetAccountCustomInfo
) : AddHdKeyAccount {

    override suspend fun invoke(
        address: String,
        publicKey: ByteArray,
        privateKey: ByteArray,
        seedId: Int,
        account: Int,
        change: Int,
        keyIndex: Int,
        derivationType: Int,
        isBackedUp: Boolean,
        customName: String?
    ) {
        val account = LocalAccount.HdKey(address, publicKey, seedId, account, change, keyIndex, derivationType)
        saveHdKeyAccount(account, privateKey)
        setCustomInfo(CustomInfo(address, customName, Int.MAX_VALUE, isBackedUp))
    }
}
