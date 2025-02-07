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

package com.algorand.wallet.account.info.domain.usecase

import com.algorand.wallet.account.info.domain.model.AccountCacheStatus
import com.algorand.wallet.account.local.domain.usecase.GetLocalAccountCountFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

internal class GetAccountDetailCacheStatusFlowUseCase @Inject constructor(
    private val getLocalAccountCountFlow: GetLocalAccountCountFlow,
    private val getCachedAccountInformationCountFlow: GetCachedAccountInformationCountFlow
) : GetAccountDetailCacheStatusFlow {

    override fun invoke(): Flow<AccountCacheStatus> {
        return combine(
            getLocalAccountCountFlow().distinctUntilChanged(),
            getCachedAccountInformationCountFlow().distinctUntilChanged()
        ) { localAccountCount, cachedAccountInformationCount ->
            if (cachedAccountInformationCount < localAccountCount) {
                AccountCacheStatus.LOADING
            } else {
                AccountCacheStatus.INITIALIZED
            }
        }
    }
}
