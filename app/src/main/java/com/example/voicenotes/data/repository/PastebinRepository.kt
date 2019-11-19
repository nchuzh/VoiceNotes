package com.example.voicenotes.data.repository

import com.example.voicenotes.domain.repository.PastebinRepositoryInterface
import com.example.voicenotes.network.API_DEV_KEY
import com.example.voicenotes.network.CallCoordinator
import com.example.voicenotes.network.request.LoginRequest
import com.example.voicenotes.network.service.PastebinService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PastebinRepository @Inject constructor(
    private val service: PastebinService,
    private val callCoordinator: CallCoordinator
) : PastebinRepositoryInterface {

    var loginToken: String? = null

    override fun getLoginToken(login: String, password: String): Single<String> {
        return Single.fromCallable {
            callCoordinator.execute(service.login(LoginRequest(API_DEV_KEY, login, password)))
        }.map { it.token }
    }

    override fun saveLoginToken(token: String) {
        loginToken = token
    }
}