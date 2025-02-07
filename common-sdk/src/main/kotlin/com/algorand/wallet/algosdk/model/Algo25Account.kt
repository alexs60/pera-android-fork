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

package com.algorand.wallet.algosdk.model

data class Algo25Account(
    val address: String,
    val encryptedMnemonic: String,
    val encryptedSecretKey: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Algo25Account

        if (address != other.address) return false
        if (encryptedMnemonic != other.encryptedMnemonic) return false
        if (!encryptedSecretKey.contentEquals(other.encryptedSecretKey)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address.hashCode()
        result = 31 * result + encryptedMnemonic.hashCode()
        result = 31 * result + encryptedSecretKey.contentHashCode()
        return result
    }
}
