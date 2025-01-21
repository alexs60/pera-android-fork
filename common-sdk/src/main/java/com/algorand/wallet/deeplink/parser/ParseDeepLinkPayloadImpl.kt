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

package com.algorand.wallet.deeplink.parser

import com.algorand.wallet.deeplink.model.DeepLinkPayload
import com.algorand.wallet.deeplink.model.NotificationGroupType
import com.algorand.wallet.deeplink.model.WebImportQrCode
import com.algorand.wallet.deeplink.parser.query.DeepLinkQueryParser

internal class ParseDeepLinkPayloadImpl(
    private val peraUriParser: PeraUriParser,
    private val accountAddressQueryParser: DeepLinkQueryParser<String?>,
    private val assetIdQueryParser: DeepLinkQueryParser<Long?>,
    private val notificationGroupTypeQueryParser: DeepLinkQueryParser<NotificationGroupType?>,
    private val webImportQrCodeQueryParser: DeepLinkQueryParser<WebImportQrCode?>,
    private val urlQueryParser: DeepLinkQueryParser<String?>,
    private val mnemonicQueryParser: DeepLinkQueryParser<String?>,
    private val walletConnectUrlQueryParser: DeepLinkQueryParser<String?>,
) : ParseDeepLinkPayload {

    override fun invoke(url: String): DeepLinkPayload {
        val peraUri = peraUriParser.parseUri(url)
        return DeepLinkPayload(
            accountAddress = accountAddressQueryParser.parseQuery(peraUri),
            walletConnectUrl = walletConnectUrlQueryParser.parseQuery(peraUri),
            assetId = assetIdQueryParser.parseQuery(peraUri),
            amount = peraUri.getQueryParam(AMOUNT_QUERY_KEY),
            note = peraUri.getQueryParam(NOTE_QUERY_KEY),
            xnote = peraUri.getQueryParam(XNOTE_QUERY_KEY),
            label = peraUri.getQueryParam(LABEL_QUERY_KEY),
            transactionId = peraUri.getQueryParam(TRANSACTION_ID_KEY),
            transactionStatus = peraUri.getQueryParam(TRANSACTION_STATUS_KEY),
            mnemonic = mnemonicQueryParser.parseQuery(peraUri),
            url = urlQueryParser.parseQuery(peraUri),
            webImportQrCode = webImportQrCodeQueryParser.parseQuery(peraUri),
            notificationGroupType = notificationGroupTypeQueryParser.parseQuery(peraUri),
            fee = peraUri.getQueryParam(FEE_QUERY_KEY),
            votekey = peraUri.getQueryParam(VOTEKEY_QUERY_KEY),
            selkey = peraUri.getQueryParam(SELKEY_QUERY_KEY),
            sprfkey = peraUri.getQueryParam(SPRFKEY_QUERY_KEY),
            votefst = peraUri.getQueryParam(VOTEFST_QUERY_KEY),
            votelst = peraUri.getQueryParam(VOTELST_QUERY_KEY),
            votekd = peraUri.getQueryParam(VOTEKD_QUERY_KEY),
            type = peraUri.getQueryParam(TYPE_QUERY_KEY),
            path = peraUri.getQueryParam(PATH_KEY),
            host = peraUri.host,
            rawDeepLinkUri = url
        )
    }

    private companion object {
        const val AMOUNT_QUERY_KEY = "amount"
        const val NOTE_QUERY_KEY = "note"
        const val XNOTE_QUERY_KEY = "xnote"
        const val LABEL_QUERY_KEY = "label"
        const val TRANSACTION_ID_KEY = "transactionId"
        const val TRANSACTION_STATUS_KEY = "transactionStatus"
        const val TYPE_QUERY_KEY = "type"
        const val SELKEY_QUERY_KEY = "selkey"
        const val SPRFKEY_QUERY_KEY = "sprfkey"
        const val VOTEFST_QUERY_KEY = "votefst"
        const val VOTELST_QUERY_KEY = "votelst"
        const val VOTEKD_QUERY_KEY = "votekd"
        const val VOTEKEY_QUERY_KEY = "votekey"
        const val FEE_QUERY_KEY = "fee"
        const val PATH_KEY = "path"
    }
}
