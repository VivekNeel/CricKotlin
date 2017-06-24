package cricket.`in`.cricket.injection.module

import android.app.Application
import cricket.`in`.cricket.AppConfig
import cricket.`in`.cricket.data.local.CricketDatabase
import cricket.`in`.cricket.data.remote.api.CricketService
import cricket.`in`.cricket.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by vivek on 24/06/17.
 */

@Module
class CricNetModule {

    @Singleton
    @Provides
    fun provideCricketService(okHttpClient: OkHttpClient): CricketService {
        return Retrofit.Builder().baseUrl(AppConfig.BASE_URL)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(CricketService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttp = OkHttpClient.Builder().addInterceptor(logging).build()
        return okHttp
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): CricketDatabase = CricketDatabase.createPersistentDatabase(application)

    @Singleton
    @Provides
    fun provideCricketDao(appDatabase: CricketDatabase) = appDatabase.provideScoreDao()
}