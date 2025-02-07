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

package com.algorand.wallet.asset.data.mapper.model.collectible

import com.algorand.wallet.asset.data.database.model.CollectibleStandardTypeEntity
import com.algorand.wallet.asset.data.model.collectible.CollectibleStandardTypeResponse
import com.algorand.wallet.asset.domain.model.CollectibleStandardType
import javax.inject.Inject

internal class CollectibleStandardTypeMapperImpl @Inject constructor() : CollectibleStandardTypeMapper {

    override fun invoke(response: CollectibleStandardTypeResponse): CollectibleStandardType {
        return when (response) {
            CollectibleStandardTypeResponse.ARC_3 -> CollectibleStandardType.ARC_3
            CollectibleStandardTypeResponse.ARC_69 -> CollectibleStandardType.ARC_69
            CollectibleStandardTypeResponse.UNKNOWN -> CollectibleStandardType.UNKNOWN
        }
    }

    override fun invoke(entity: CollectibleStandardTypeEntity): CollectibleStandardType {
        return when (entity) {
            CollectibleStandardTypeEntity.ARC_3 -> CollectibleStandardType.ARC_3
            CollectibleStandardTypeEntity.ARC_69 -> CollectibleStandardType.ARC_69
            CollectibleStandardTypeEntity.UNKNOWN -> CollectibleStandardType.UNKNOWN
        }
    }
}
