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
import androidx.room.PrimaryKey

@Entity(tableName = "algo_25")
internal data class Algo25Entity(
    @PrimaryKey
    @ColumnInfo("algo_address")
    val algoAddress: String,

    @ColumnInfo("encrypted_secret_key", typeAffinity = ColumnInfo.BLOB)
    val encryptedSecretKey: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Algo25Entity

        if (algoAddress != other.algoAddress) return false
        if (!encryptedSecretKey.contentEquals(other.encryptedSecretKey)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = algoAddress.hashCode()
        result = 31 * result + encryptedSecretKey.contentHashCode()
        return result
    }
}
