package au.auxy.composearc.account.di

import androidx.lifecycle.SavedStateHandle
import au.auxy.composearc.account.model.AccountArg
import au.auxy.composearc.account.navigation.AccountExtraKey
import au.auxy.composearc.account.navigation.AccountNameKey
import au.auxy.composearc.account.navigation.AccountNumberKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class AccountModule {
    @Provides
    fun provideAccountDetailArg(savedStateHandle: SavedStateHandle) = AccountArg(
        savedStateHandle[AccountNameKey],
        savedStateHandle[AccountNumberKey],
        savedStateHandle[AccountExtraKey]
    )
}
