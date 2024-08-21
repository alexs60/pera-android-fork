/*
 * Copyright 2022 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */

package com.algorand.android.modules.baseledgersearch.instruction.step.enableledger.ui

import com.algorand.android.R
import com.algorand.android.models.ToolbarConfiguration
import com.algorand.android.modules.baseledgersearch.instruction.step.base.ui.BaseLedgerPairInfoBottomSheet

class EnableLedgerBluetoothBottomSheet : BaseLedgerPairInfoBottomSheet() {

    override val toolbarConfiguration = ToolbarConfiguration(
        titleResId = R.string.step_one,
        startIconResId = R.drawable.ic_close,
        startIconClick = ::navBack
    )

    override val instructions = listOf(
        R.string.ledger_info_power_first_paragraph,
        R.string.ledger_info_power_second_paragraph
    )
}
