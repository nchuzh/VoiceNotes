package com.example.voicenotes.data.repository

import com.example.voicenotes.domain.PastebinRepositoryInterface
import com.example.voicenotes.network.CallCoordinator
import com.example.voicenotes.network.service.PastebinService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PastebinRepository @Inject constructor(private val service: PastebinService,
                                             private val callCoordinator: CallCoordinator): PastebinRepositoryInterface {

    override fun getData(): Single<String> {
        return Single.fromCallable {
            callCoordinator.execute(service.getData("id"))
        }.map { it.data }
    }
}