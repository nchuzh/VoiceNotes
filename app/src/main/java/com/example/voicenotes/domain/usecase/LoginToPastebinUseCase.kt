package com.example.voicenotes.domain.usecase

import com.example.voicenotes.data.repository.PastebinRepository
import com.example.voicenotes.network.NetworkException
import io.reactivex.Completable
import javax.inject.Inject

class LoginToPastebinUseCase @Inject constructor(val pastebinRepository: PastebinRepository) {
    companion object {
        val ErrorString = "Bad API"
    }

    fun execute(login: String, password: String): Completable {
        return pastebinRepository.getLoginToken(login, password).map {
            if (hasErrors(it)) {
                throw NetworkException(it)
            } else {
                pastebinRepository.saveLoginToken(it)
            }
        }.toCompletable()
    }

    private fun hasErrors(response: String): Boolean {
        return response.contains(ErrorString)
    }
}