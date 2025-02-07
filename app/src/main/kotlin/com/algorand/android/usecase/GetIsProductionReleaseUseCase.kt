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

package com.algorand.android.usecase

import com.algorand.android.BuildConfig
import javax.inject.Inject

// TODO: Update this class after deciding on where is the correct place to get the active node
class GetIsProductionReleaseUseCase @Inject constructor() {
    operator fun invoke(): Boolean {
        // return true if prodRelease variant
        return BuildConfig.BUILD_TYPE == "release" && BuildConfig.FLAVOR == "prod"
    }
}
