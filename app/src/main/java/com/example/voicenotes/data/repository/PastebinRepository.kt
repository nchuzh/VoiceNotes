package com.example.voicenotes.data.repository

import com.example.voicenotes.BuildConfig
import com.example.voicenotes.domain.repository.PastebinRepositoryInterface
import com.example.voicenotes.network.CallCoordinator
import com.example.voicenotes.network.service.PastebinService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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
            callCoordinator.execute(service.login(BuildConfig.apikey, login, password))
        }.subscribeOn(Schedulers.io()).map { it }
    }

    override fun saveLoginToken(token: String) {
        loginToken = token
    }
}