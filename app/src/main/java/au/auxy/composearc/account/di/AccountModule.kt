package au.auxy.composearc.account.di

import androidx.lifecycle.SavedStateHandle
import au.auxy.composearc.account.model.AccountDetailArg
import au.auxy.composearc.account.navigation.AccountDetailExtraKey
import au.auxy.composearc.account.navigation.AccountDetailNameKey
import au.auxy.composearc.account.navigation.AccountDetailNumberKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class AccountModule {
    @Provides
    fun provideAccountDetailArg(savedStateHandle: SavedStateHandle) = AccountDetailArg(
        savedStateHandle[AccountDetailNameKey],
        savedStateHandle[AccountDetailNumberKey],
        savedStateHandle[AccountDetailExtraKey]
    )
}
