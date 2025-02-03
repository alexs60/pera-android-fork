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

package com.algorand.wallet.account.detail.domain.usecase

import com.algorand.wallet.account.custom.domain.usecase.GetAccountCustomInfoOrNull
import com.algorand.wallet.account.detail.domain.model.AccountDetail
import javax.inject.Inject

internal class GetAccountDetailUseCase @Inject constructor(
    private val getCustomInfoOrNull: GetAccountCustomInfoOrNull,
    private val getAccountState: GetAccountState
) : GetAccountDetail {

    override suspend fun invoke(address: String): AccountDetail {
        val accountState = getAccountState(address)
        return AccountDetail(
            address = address,
            customInfo = getCustomInfoOrNull(address),
            accountRegistrationType = accountState.accountRegistrationType,
            accountType = accountState.accountType
        )
    }
}
