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

package com.algorand.wallet.account.info.data.repository

import com.algorand.wallet.account.info.data.database.dao.AssetHoldingDao
import com.algorand.wallet.account.info.data.database.model.AssetHoldingEntity
import com.algorand.wallet.account.info.data.mapper.AssetHoldingEntityMapper
import com.algorand.wallet.account.info.data.mapper.AssetHoldingMapper
import com.algorand.wallet.account.info.data.model.AssetHoldingResponse
import com.algorand.wallet.account.info.domain.model.AssetHolding
import javax.inject.Inject

internal class AssetHoldingCacheHelperImpl @Inject constructor(
    private val assetHoldingDao: AssetHoldingDao,
    private val assetHoldingEntityMapper: AssetHoldingEntityMapper,
    private val assetHoldingMapper: AssetHoldingMapper,
) : AssetHoldingCacheHelper {

    override suspend fun cacheAssetHolding(
        address: String,
        assetHoldings: List<AssetHoldingResponse>?
    ): List<AssetHolding> {
        val assetHoldingEntities = getAssetHoldingEntities(address, assetHoldings)
        assetHoldingDao.updateAssetHoldings(address, assetHoldingEntities)
        return assetHoldingMapper(assetHoldingEntities)
    }

    private fun getAssetHoldingEntities(
        address: String,
        assetHoldings: List<AssetHoldingResponse>?
    ): List<AssetHoldingEntity> {
        return assetHoldings?.mapNotNull {
            assetHoldingEntityMapper(address, it)
        }.orEmpty()
    }
}
