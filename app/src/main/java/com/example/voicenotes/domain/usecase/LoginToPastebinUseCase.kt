package com.example.voicenotes.domain.usecase

import com.example.voicenotes.data.repository.PastebinRepository
import com.example.voicenotes.network.NetworkException
import io.reactivex.Completable
import javax.inject.Inject

class LoginToPastebinUseCase @Inject constructor(val pastebinRepository: PastebinRepository) {

    fun execute(login: String, password: String): Completable {
        return pastebinRepository.getLoginToken(login, password).map {
            if (it.contains("Bad API")) {
                throw NetworkException(it)
            } else {
                pastebinRepository.saveLoginToken(it)
            }
        }.toCompletable()
    }
}