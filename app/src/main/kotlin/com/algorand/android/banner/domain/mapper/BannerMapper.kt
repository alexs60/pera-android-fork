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

package com.algorand.android.banner.domain.mapper

import com.algorand.android.banner.domain.model.BannerDetailDTO
import com.algorand.android.banner.domain.model.BaseBanner
import javax.inject.Inject

class BannerMapper @Inject constructor() {

    fun mapToGovernanceBanner(bannerDetailDTO: BannerDetailDTO): BaseBanner.GovernanceBanner {
        return BaseBanner.GovernanceBanner(
            bannerId = bannerDetailDTO.bannerId,
            title = bannerDetailDTO.title,
            description = bannerDetailDTO.description,
            buttonTitle = bannerDetailDTO.buttonText,
            buttonUrl = bannerDetailDTO.buttonUrl
        )
    }

    fun mapToStakingBanner(bannerDetailDTO: BannerDetailDTO): BaseBanner.StakingBanner {
        return BaseBanner.StakingBanner(
            bannerId = bannerDetailDTO.bannerId,
            title = bannerDetailDTO.title,
            description = bannerDetailDTO.description,
            buttonTitle = bannerDetailDTO.buttonText,
            buttonUrl = bannerDetailDTO.buttonUrl
        )
    }

    fun mapToGenericBanner(bannerDetailDTO: BannerDetailDTO): BaseBanner.GenericBanner {
        return BaseBanner.GenericBanner(
            bannerId = bannerDetailDTO.bannerId,
            title = bannerDetailDTO.title,
            description = bannerDetailDTO.description,
            buttonTitle = bannerDetailDTO.buttonText,
            buttonUrl = bannerDetailDTO.buttonUrl
        )
    }
}
