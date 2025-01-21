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

package com.algorand.wallet.asset.data.model.collectible

import com.google.gson.annotations.SerializedName

internal data class CollectibleResponse(
    @SerializedName("standard") val standard: CollectibleStandardTypeResponse? = null,
    @SerializedName("media_type") val mediaType: CollectibleMediaTypeResponse? = null,
    @SerializedName("primary_image") val primaryImageUrl: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("collection") val collection: CollectionResponse? = null,
    @SerializedName("media") val collectibleMedias: List<CollectibleMediaResponse>? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("traits") val traits: List<CollectibleTraitResponse>? = null
)
