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

package com.algorand.common.account.info.data.mapper

import com.algorand.common.account.info.data.database.model.AccountInformationEntity
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class AccountInformationErrorEntityMapperImplTest {

    private val sut = AccountInformationErrorEntityMapperImpl()

    @Test
    fun `EXPECT entity with default values`() {
        val result = sut(ADDRESS)

        val expected = AccountInformationEntity(
            algoAddress = ADDRESS,
            algoAmount = "0",
            optedInAppsCount = 0,
            appsTotalExtraPages = 0,
            authAlgoAddress = null,
            createdAtRound = null,
            lastFetchedRound = 0,
            totalCreatedAppsCount = 0,
            totalCreatedAssetsCount = 0,
            appStateNumByteSlice = null,
            appStateSchemaUint = null
        )
        assertEquals(expected, result)
    }

    private companion object {
        private const val ADDRESS = "ADDRESS"
    }
}
