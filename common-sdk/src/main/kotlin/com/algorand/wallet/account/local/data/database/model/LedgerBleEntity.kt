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

@Entity(tableName = "ledger_ble")
internal data class LedgerBleEntity(
    @PrimaryKey
    @ColumnInfo("algo_address")
    val algoAddress: String,

    @ColumnInfo("device_mac_address")
    val deviceMacAddress: String,

    @ColumnInfo("account_index_in_ledger")
    val accountIndexInLedger: Int,

    @ColumnInfo("bluetooth_name")
    val bluetoothName: String?
)
