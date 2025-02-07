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

package com.algorand.wallet.asset.data.mapper.entity

import com.algorand.wallet.asset.data.model.VerificationTierResponse
import com.algorand.wallet.asset.data.database.model.VerificationTierEntity

internal class VerificationTierEntityMapperImpl : VerificationTierEntityMapper {

    override fun invoke(response: VerificationTierResponse?): VerificationTierEntity {
        return when (response) {
            VerificationTierResponse.VERIFIED -> VerificationTierEntity.VERIFIED
            VerificationTierResponse.UNVERIFIED -> VerificationTierEntity.UNVERIFIED
            VerificationTierResponse.TRUSTED -> VerificationTierEntity.TRUSTED
            VerificationTierResponse.SUSPICIOUS -> VerificationTierEntity.SUSPICIOUS
            VerificationTierResponse.UNKNOWN, null -> VerificationTierEntity.UNKNOWN
        }
    }
}
