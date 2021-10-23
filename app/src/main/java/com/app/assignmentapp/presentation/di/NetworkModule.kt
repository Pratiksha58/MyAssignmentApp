package com.app.assignmentapp.presentation.di


import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.app.assignmentapp.data.Mapper.UserDataMapper
import com.app.assignmentapp.data.repository.UserDataRepository
import com.app.assignmentapp.data.services.NetworkServices
import com.app.assignmentapp.data.services.RestApi
import com.app.assignmentapp.data.services.RestApiImpl
import com.app.assignmentapp.domain.interactor.DataUC
import com.app.assignmentapp.domain.repository.UserRepository
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        createRetrofit(
            createOkHttpClient()
        )
    }
    single { createNetworkApi(get()) }
    single {
        createRestAPI(
            get(),
            get()
        )
    }
    single {
        createUserRepository(
            get(),
            get()
        )
    }
    single { createGetDataUseCase(get()) }

}



fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .baseUrl("https://stage-services.truemeds.in/ArticleService/")
        .client(okHttpClient)
        .build()
}
fun createOkHttpClient(): OkHttpClient {

    val httpClient = OkHttpClient.Builder()

    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Accept", "application/json")
            .build()

        chain.proceed(request)
    }
        .addNetworkInterceptor(StethoInterceptor())
        .retryOnConnectionFailure(true)
        .callTimeout(
            2, TimeUnit.MINUTES
        )
        .connectTimeout(3000, TimeUnit.SECONDS)
        .writeTimeout(4000, TimeUnit.SECONDS)
        .readTimeout(3000, TimeUnit.SECONDS)

    return httpClient.build()
}
fun createNetworkApi(retrofit: Retrofit): NetworkServices {
    return retrofit.create(NetworkServices::class.java)
}

fun createRestAPI(mNetworkServices: NetworkServices, mContext: Context): RestApi {
    return RestApiImpl(
        mNetworkServices,
        mContext
    )
}

fun createUserRepository(mRestApi: RestApi, mapper: UserDataMapper): UserRepository {
    return UserDataRepository(
        mRestApi,
        mapper
    )
}

fun createGetDataUseCase(
    mUserRepository: UserRepository
): DataUC {
    return DataUC(mUserRepository)
}
