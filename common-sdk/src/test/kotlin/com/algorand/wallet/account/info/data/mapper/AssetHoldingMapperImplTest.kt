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

import com.algorand.wallet.account.info.data.database.model.AssetHoldingEntity
import com.algorand.wallet.account.info.data.database.model.AssetStatusEntity
import com.algorand.wallet.account.info.data.model.AssetHoldingResponse
import com.algorand.wallet.account.info.domain.model.AssetHolding
import com.algorand.wallet.account.info.domain.model.AssetStatus
import java.math.BigInteger
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AssetHoldingMapperImplTest {

    private val sut = AssetHoldingMapperImpl()

    @Test
    fun `EXPECT null WHEN response asset id is null`() {
        val response = VALID_RESPONSE.copy(
            assetId = null
        )

        val result = sut(response)

        assertNull(result)
    }

    @Test
    fun `EXPECT mapped object with default value WHEN optional fields are missing`() {
        val response = VALID_RESPONSE.copy(
            isFrozen = null,
            isDeleted = null,
            amount = null,
        )

        val result = sut(response)

        val expected = VALID_ASSET_HOLDING.copy(
            amount = BigInteger.ZERO,
            isFrozen = false,
            isDeleted = false
        )
        assertEquals(expected, result)
    }

    @Test
    fun `EXPECT mapped object with pending asset status WHEN entity status is pending`() {
        val entity = ASSET_HOLDING_ENTITY.copy(
            assetStatusEntity = AssetStatusEntity.PENDING_FOR_ADDITION
        )

        val result = sut(entity)

        val expected = VALID_ASSET_HOLDING.copy(
            status = AssetStatus.PENDING_FOR_ADDITION
        )
        assertEquals(expected, result)
    }

    @Test
    fun `EXPECT mapped object with owned asset status WHEN entity status is owned`() {
        val entity = ASSET_HOLDING_ENTITY.copy(
            assetStatusEntity = AssetStatusEntity.OWNED_BY_ACCOUNT
        )

        val result = sut(entity)

        val expected = VALID_ASSET_HOLDING.copy(
            status = AssetStatus.OWNED_BY_ACCOUNT
        )
        assertEquals(expected, result)
    }

    @Test
    fun `EXPECT mapped object with pending removal asset status WHEN entity status is pending removal`() {
        val entity = ASSET_HOLDING_ENTITY.copy(
            assetStatusEntity = AssetStatusEntity.PENDING_FOR_REMOVAL
        )

        val result = sut(entity)

        val expected = VALID_ASSET_HOLDING.copy(
            status = AssetStatus.PENDING_FOR_REMOVAL
        )
        assertEquals(expected, result)
    }

    @Test
    fun `EXPECT mapped object with pending sending asset status WHEN entity status is pending sending`() {
        val entity = ASSET_HOLDING_ENTITY.copy(
            assetStatusEntity = AssetStatusEntity.PENDING_FOR_SENDING
        )

        val result = sut(entity)

        val expected = VALID_ASSET_HOLDING.copy(
            status = AssetStatus.PENDING_FOR_SENDING
        )
        assertEquals(expected, result)
    }

    private companion object {
        const val ADDRESS = "address"

        val VALID_RESPONSE = AssetHoldingResponse(
            assetId = 1,
            amount = "10",
            isFrozen = false,
            isDeleted = false,
            optedInAtRound = 3,
            optedOutAtRound = 4
        )

        val VALID_ASSET_HOLDING = AssetHolding(
            assetId = 1,
            amount = BigInteger.TEN,
            isFrozen = false,
            isDeleted = false,
            optedInAtRound = 3,
            optedOutAtRound = 4,
            status = AssetStatus.OWNED_BY_ACCOUNT
        )

        val ASSET_HOLDING_ENTITY = AssetHoldingEntity(
            algoAddress = ADDRESS,
            assetId = 1,
            amount = BigInteger.TEN,
            isFrozen = false,
            isDeleted = false,
            optedInAtRound = 3,
            optedOutAtRound = 4,
            assetStatusEntity = AssetStatusEntity.OWNED_BY_ACCOUNT
        )
    }
}
