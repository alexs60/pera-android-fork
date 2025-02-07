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

package com.algorand.wallet.account.local.domain.model

sealed interface LocalAccount {

    val algoAddress: String

    data class HdKey(
        override val algoAddress: String,
        val publicKey: ByteArray,
        val seedId: Int,
        val account: Int,
        val change: Int,
        val keyIndex: Int,
        val derivationType: Int
    ) : LocalAccount

    data class Algo25(override val algoAddress: String) : LocalAccount

    data class LedgerBle(
        override val algoAddress: String,
        val deviceMacAddress: String,
        val bluetoothName: String?,
        val indexInLedger: Int
    ) : LocalAccount

    data class NoAuth(
        override val algoAddress: String
    ) : LocalAccount
}
