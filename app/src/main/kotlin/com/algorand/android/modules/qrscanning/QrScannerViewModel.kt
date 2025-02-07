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

package com.algorand.android.modules.qrscanning

import androidx.lifecycle.viewModelScope
import com.algorand.android.core.BaseViewModel
import com.algorand.android.modules.accountstatehelper.domain.usecase.AccountStateHelperUseCase
import com.algorand.android.modules.deeplink.ui.DeeplinkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QrScannerViewModel @Inject constructor(
    private val deeplinkHandler: DeeplinkHandler,
    private val accountStateHelperUseCase: AccountStateHelperUseCase,
) : BaseViewModel() {

    private val _isQrCodeInProgressFlow = MutableSharedFlow<Boolean>()
    val isQrCodeInProgressFlow: SharedFlow<Boolean> = _isQrCodeInProgressFlow

    fun setQrCodeInProgress(isInProgress: Boolean) {
        viewModelScope.launch {
            _isQrCodeInProgressFlow.emit(isInProgress)
        }
    }

    fun setDeeplinkHandlerListener(listener: DeeplinkHandler.Listener) {
        deeplinkHandler.setListener(listener)
    }

    fun handleDeeplink(uri: String) {
        deeplinkHandler.handleDeepLink(uri)
    }

    fun removeDeeplinkHandlerListener() {
        deeplinkHandler.setListener(null)
    }

    fun hasAccountAuthority(accountAddress: String): Boolean {
        return accountStateHelperUseCase.hasAccountAuthority(accountAddress)
    }
}
