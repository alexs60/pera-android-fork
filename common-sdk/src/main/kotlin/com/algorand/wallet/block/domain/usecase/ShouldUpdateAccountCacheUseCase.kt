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

package com.algorand.wallet.block.domain.usecase

import com.algorand.wallet.account.info.domain.usecase.GetAllAccountInformation
import com.algorand.wallet.account.local.domain.usecase.GetLocalAccounts
import com.algorand.wallet.block.domain.repository.BlockPollingRepository
import com.algorand.wallet.foundation.PeraResult
import javax.inject.Inject

internal class ShouldUpdateAccountCacheUseCase @Inject constructor(
    private val getLocalAccounts: GetLocalAccounts,
    private val blockPollingRepository: BlockPollingRepository,
    private val getAllAccountInformation: GetAllAccountInformation
) : ShouldUpdateAccountCache {

    override suspend fun invoke(): PeraResult<Boolean> {
        val localAccountAddresses = getLocalAccounts().map { it.algoAddress }
        val cachedAccounts = getAllAccountInformation()
        if (localAccountAddresses.size > cachedAccounts.size) {
            return PeraResult.Success(true)
        }
        return blockPollingRepository.shouldUpdateAccountCache(localAccountAddresses)
    }
}
