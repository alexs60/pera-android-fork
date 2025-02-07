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

package com.algorand.wallet.account.info.data.mapper

import com.algorand.wallet.account.info.data.model.AccountInformationResponse
import com.algorand.wallet.account.info.data.database.model.AccountInformationEntity
import javax.inject.Inject

internal class AccountInformationEntityMapperImpl @Inject constructor() : AccountInformationEntityMapper {

    override fun invoke(response: AccountInformationResponse): AccountInformationEntity? {
        if (response.accountInformation?.address.isNullOrBlank()) return null
        return AccountInformationEntity(
            algoAddress = response.accountInformation?.address.orEmpty(),
            algoAmount = response.accountInformation?.amount?.toBigIntegerOrNull() ?: return null,
            lastFetchedRound = response.currentRound ?: return null,
            authAlgoAddress = response.accountInformation.rekeyAdminAddress,
            optedInAppsCount = response.accountInformation.totalAppsOptedIn ?: 0,
            totalCreatedAppsCount = response.accountInformation.totalCreatedApps ?: 0,
            totalCreatedAssetsCount = response.accountInformation.totalCreatedAssets ?: 0,
            appsTotalExtraPages = response.accountInformation.appsTotalExtraPages ?: 0,
            appStateNumByteSlice = response.accountInformation.appStateSchemaResponse?.numByteSlice,
            appStateSchemaUint = response.accountInformation.appStateSchemaResponse?.numUint,
            createdAtRound = response.accountInformation.createdAtRound
        )
    }
}
