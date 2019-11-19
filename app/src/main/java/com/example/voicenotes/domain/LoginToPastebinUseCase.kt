package com.example.voicenotes.domain

import com.example.voicenotes.data.repository.PastebinRepository
import io.reactivex.Completable
import javax.inject.Inject

class LoginToPastebinUseCase @Inject constructor(val pastebinRepository: PastebinRepository) {

    fun execute(login: String, password: String): Completable {
        return pastebinRepository.getLoginToken(login, password).map {
            pastebinRepository.saveLoginToken(it)
        }.toCompletable()
    }
}