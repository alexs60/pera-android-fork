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

package com.algorand.wallet.cache.domain.usecase

import com.algorand.wallet.account.info.domain.usecase.FetchAndCacheAccountInformation
import com.algorand.wallet.account.local.domain.usecase.GetLocalAccounts
import com.algorand.wallet.asset.domain.usecase.FetchAndCacheAssets
import javax.inject.Inject

internal class UpdateAccountCacheUseCase @Inject constructor(
    private val getLocalAccounts: GetLocalAccounts,
    private val fetchAndCacheAccountInformation: FetchAndCacheAccountInformation,
    private val fetchAndCacheAssets: FetchAndCacheAssets
) : UpdateAccountCache {

    override suspend fun invoke() {
        val localAccountAddresses = getLocalAccounts().map { it.algoAddress }
        val assetIds = fetchAndCacheAccountInformation(localAccountAddresses).mapNotNull {
            if (it.value == null) return@mapNotNull null
            it.value?.assetHoldings?.map { assetHolding ->
                assetHolding.assetId
            }
        }.flatten()
        fetchAndCacheAssets(assetIds, false)
    }
}
