package ru.maksonic.easypayments.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @Author maksonic on 27.11.2023
 */
val appModule = module {
    single<RequestManager> { provideGlide(androidContext()) }
}

private fun provideGlide(context: Context) = Glide.with(context)
    .setDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))