/*
 * Copyright 2022 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */

package com.algorand.android.decider

import com.algorand.android.core.AccountManager
import com.algorand.android.mapper.TransactionTargetUserMapper
import com.algorand.android.models.TransactionTargetUser
import com.algorand.android.repository.ContactRepository
import com.algorand.android.utils.toShortenedAddress
import javax.inject.Inject

class TransactionUserUseCase @Inject constructor(
    private val accountManager: AccountManager,
    private val contactRepository: ContactRepository,
    private val transactionTargetUserMapper: TransactionTargetUserMapper
) {

    suspend fun getTransactionTargetUser(nonOwnerPublicKey: String): TransactionTargetUser {
        val foundContact = contactRepository.getAllContacts().firstOrNull { it.publicKey == nonOwnerPublicKey }
        if (foundContact != null) {
            return transactionTargetUserMapper.mapTo(
                publicKey = nonOwnerPublicKey,
                displayName = foundContact.name.ifEmpty { nonOwnerPublicKey.toShortenedAddress() },
                contact = foundContact
            )
        }
        val foundAccount = accountManager.getAccount(nonOwnerPublicKey)
        if (foundAccount != null) {
            return transactionTargetUserMapper.mapTo(
                publicKey = nonOwnerPublicKey,
                displayName = foundAccount.name.ifEmpty { nonOwnerPublicKey.toShortenedAddress() },
                account = foundAccount
            )
        }
        return transactionTargetUserMapper.mapTo(
            publicKey = nonOwnerPublicKey,
            displayName = nonOwnerPublicKey.toShortenedAddress()
        )
    }
}
