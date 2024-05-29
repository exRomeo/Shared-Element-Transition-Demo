package com.exromeo.sharedelementtransitiondemo.products.di

import com.exromeo.sharedelementtransitiondemo.products.data.remote.data_source.ProductsRemoteSource
import com.exromeo.sharedelementtransitiondemo.products.data.remote.data_source.ProductsRemoteSourceImpl
import com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.service.ProductsService
import com.exromeo.sharedelementtransitiondemo.products.data.repository.ProductsRepositoryImpl
import com.exromeo.sharedelementtransitiondemo.products.domain.repository.ProductsRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindProductsRemoteSource(
        remoteSource: ProductsRemoteSourceImpl
    ): ProductsRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindProductsRepository(
        remoteSource: ProductsRepositoryImpl
    ): ProductsRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideProductsService(
            retrofit: Retrofit
        ): ProductsService =
            retrofit
                .create(ProductsService::class.java)
    }
}