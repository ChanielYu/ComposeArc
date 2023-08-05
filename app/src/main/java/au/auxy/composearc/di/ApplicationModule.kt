package au.auxy.composearc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bloco.faker.Faker

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideDataFaker(): Faker = Faker()
}
