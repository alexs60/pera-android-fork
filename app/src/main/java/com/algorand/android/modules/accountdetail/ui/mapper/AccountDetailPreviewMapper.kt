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

package com.algorand.android.modules.accountdetail.ui.mapper

import androidx.navigation.NavDirections
import com.algorand.android.modules.accountdetail.ui.model.AccountDetailPreview
import com.algorand.android.utils.Event
import javax.inject.Inject

class AccountDetailPreviewMapper @Inject constructor() {

    fun mapToAccountDetail(
        copyAssetIDToClipboardEvent: Event<Long>? = null,
        showGlobalErrorEvent: Event<Int>? = null,
        onNavigationEvent: Event<NavDirections>? = null
    ): AccountDetailPreview {
        return AccountDetailPreview(
            copyAssetIDToClipboardEvent = copyAssetIDToClipboardEvent,
            showGlobalErrorEvent = showGlobalErrorEvent,
            onNavigationEvent = onNavigationEvent
        )
    }
}
