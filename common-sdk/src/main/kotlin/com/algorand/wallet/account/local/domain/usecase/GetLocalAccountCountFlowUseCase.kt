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

package com.algorand.wallet.account.local.domain.usecase

import com.algorand.wallet.account.local.domain.repository.Algo25AccountRepository
import com.algorand.wallet.account.local.domain.repository.HdKeyAccountRepository
import com.algorand.wallet.account.local.domain.repository.LedgerBleAccountRepository
import com.algorand.wallet.account.local.domain.repository.NoAuthAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

internal class GetLocalAccountCountFlowUseCase(
    private val hdKeyAccountRepository: HdKeyAccountRepository,
    private val algo25AccountRepository: Algo25AccountRepository,
    private val ledgerBleAccountRepository: LedgerBleAccountRepository,
    private val noAuthAccountRepository: NoAuthAccountRepository
) : GetLocalAccountCountFlow {

    override fun invoke(): Flow<Int> {
        return combine(
            hdKeyAccountRepository.getAccountCountAsFlow(),
            algo25AccountRepository.getAccountCountAsFlow(),
            ledgerBleAccountRepository.getAccountCountAsFlow(),
            noAuthAccountRepository.getAccountCountAsFlow()
        ) { hdKeyCount, algo25Count, ledgerBleCount, noAuthCount ->
            hdKeyCount + algo25Count + ledgerBleCount + noAuthCount
        }.distinctUntilChanged()
    }
}
