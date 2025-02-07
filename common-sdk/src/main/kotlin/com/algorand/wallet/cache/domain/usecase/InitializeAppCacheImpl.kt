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

import androidx.lifecycle.Lifecycle
import com.algorand.wallet.asset.domain.manager.AssetDetailCacheManager
import com.algorand.wallet.account.info.domain.manager.AccountCacheManager
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class InitializeAppCacheImpl @Inject constructor(
    private val accountCacheManager: AccountCacheManager,
    private val assetDetailCacheManager: AssetDetailCacheManager,
    private val clearPreviousSessionCache: ClearPreviousSessionCache
) : InitializeAppCache {

    override suspend fun invoke(lifecycle: Lifecycle) {
        clearPreviousSessionCache()
        withContext(Dispatchers.Main) {
            accountCacheManager.initialize(lifecycle)
            assetDetailCacheManager.initialize(lifecycle)
        }
    }
}
