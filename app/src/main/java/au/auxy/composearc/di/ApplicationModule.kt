package au.auxy.composearc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bloco.faker.Faker
import java.text.DateFormat
import java.text.SimpleDateFormat

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideDataFaker(): Faker = Faker()

    @Provides
    fun provideDateFormat(): DateFormat = SimpleDateFormat.getDateInstance()
}
