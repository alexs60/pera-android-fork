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

package com.algorand.wallet.deeplink.builder

import com.algorand.wallet.deeplink.model.DeepLink
import com.algorand.wallet.deeplink.model.DeepLinkPayload
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DiscoverBrowserDeepLinkBuilderTest {

    private val sut = DiscoverBrowserDeepLinkBuilder()

    @Test
    fun `EXPECT true WHEN deeplink requirements match`() {
        val result = sut.doesDeeplinkMeetTheRequirements(VALID_DEEP_LINK)

        assertTrue(result)
    }

    @Test
    fun `EXPECT false WHEN deeplink requirements do not match`() {
        val invalidDeepLink = VALID_DEEP_LINK.copy(accountAddress = "accountAddress")

        val result = sut.doesDeeplinkMeetTheRequirements(invalidDeepLink)

        assertFalse(result)
    }

    @Test
    fun `EXPECT discover browser deep link`() {
        val result = sut.createDeepLink(VALID_DEEP_LINK)

        val expected = DeepLink.DiscoverBrowser(webUrl = "url")
        assertEquals(expected, result)
    }

    private companion object {
        val VALID_DEEP_LINK = DeepLinkPayload(
            url = "url",
            walletConnectUrl = null,
            assetId = null,
            amount = null,
            note = null,
            xnote = null,
            accountAddress = null,
            label = null,
            notificationGroupType = null,
            webImportQrCode = null,
            rawDeepLinkUri = ""
        )
    }
}
