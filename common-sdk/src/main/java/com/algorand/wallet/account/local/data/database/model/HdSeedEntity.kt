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

package com.algorand.wallet.account.local.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "hd_seeds",
    indices = [
        Index(value = ["encrypted_mnemonic_entropy"], unique = true),
        Index(value = ["encrypted_seed"], unique = true)
    ]
)
internal data class HdSeedEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("seed_id")
    val seedId: Int,

    @ColumnInfo("encrypted_mnemonic_entropy")
    val encryptedMnemonicEntropy: String,

    @ColumnInfo("encrypted_seed", typeAffinity = ColumnInfo.BLOB)
    val encryptedSeed: ByteArray,

    @ColumnInfo("entropy_custom_name")
    val entropyCustomName: String
)
