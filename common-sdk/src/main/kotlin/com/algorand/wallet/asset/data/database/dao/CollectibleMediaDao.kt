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

package com.algorand.wallet.asset.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.algorand.wallet.asset.data.database.model.CollectibleMediaEntity

@Dao
internal interface CollectibleMediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CollectibleMediaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CollectibleMediaEntity>)

    @Query("DELETE FROM collectible_media WHERE collectible_asset_id = :collectibleAssetId")
    suspend fun deleteAllByCollectibleAssetId(collectibleAssetId: Long)

    @Query("SELECT * FROM collectible_media WHERE collectible_asset_id = :collectibleAssetId")
    suspend fun getByCollectibleAssetId(collectibleAssetId: Long): List<CollectibleMediaEntity>

    @Query("SELECT * FROM collectible_media WHERE collectible_asset_id IN (:collectibleAssetIds)")
    suspend fun getByCollectibleAssetIds(collectibleAssetIds: List<Long>): List<CollectibleMediaEntity>

    @Query("DELETE FROM collectible_media")
    suspend fun clearAll()
}
