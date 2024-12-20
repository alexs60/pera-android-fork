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

package com.algorand.common.qr.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import qrscanner.CameraLens
import qrscanner.QrScanner

sealed interface QrScannerViewEvent {
    data object PauseCamera : QrScannerViewEvent
    data object ResumeCamera : QrScannerViewEvent
}

@Composable
fun PeraQrScanner(
    viewEvent: State<QrScannerViewEvent>,
    modifier: Modifier = Modifier,
    onQrScanned: (String) -> Unit
) {
    QrScanner(
        modifier = modifier.fillMaxSize(),
        flashlightOn = false,
        cameraLens = CameraLens.Back,
        openImagePicker = false,
        onCompletion = onQrScanned,
        imagePickerHandler = { },
        onFailure = { },
        customOverlay = cameraOverlay()
    )
}


