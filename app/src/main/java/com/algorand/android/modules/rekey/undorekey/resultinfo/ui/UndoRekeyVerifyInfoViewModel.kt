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

package com.algorand.android.modules.rekey.undorekey.resultinfo.ui

import androidx.lifecycle.SavedStateHandle
import com.algorand.android.core.BaseViewModel
import com.algorand.android.modules.rekey.undorekey.resultinfo.ui.usecase.UndoRekeyVerifyInfoPreviewUseCase
import com.algorand.android.utils.AccountDisplayName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UndoRekeyVerifyInfoViewModel @Inject constructor(
    private val undoRekeyVerifyInfoPreviewUseCase: UndoRekeyVerifyInfoPreviewUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val navArgs = UndoRekeyVerifyInfoFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val accountAddress = navArgs.accountAddress

    val accountDisplayName: AccountDisplayName
        get() = undoRekeyVerifyInfoPreviewUseCase.getAccountDisplayName(accountAddress)
}
