package com.algorand.android.modules.assetinbox.send.summary.domain.model

import java.math.BigInteger

data class Arc59SendSummary(
    val isArc59OptedIn: Boolean,
    val minimumBalanceRequirement: BigInteger,
    val innerTxCount: Int,
    val totalProtocolAndMbrFee: BigInteger,
    val inboxAddress: String,
    val algoFundAmount: BigInteger,
    val warningMessage: Arc59SummaryWarningMessage?
)
