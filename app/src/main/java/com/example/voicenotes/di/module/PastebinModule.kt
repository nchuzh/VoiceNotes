package com.example.voicenotes.di.module

import com.example.voicenotes.data.repository.PastebinRepository
import com.example.voicenotes.domain.usecase.LoginToPastebinUseCase
import dagger.Module
import dagger.Provides

@Module
class PastebinModule {
    @Provides
    fun provideLoginToPastebinUseCase(pastebinRepository: PastebinRepository) =
        LoginToPastebinUseCase(pastebinRepository)
}