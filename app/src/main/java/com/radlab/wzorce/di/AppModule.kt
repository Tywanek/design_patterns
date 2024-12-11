package com.radlab.wzorce.di

import android.content.Context
import com.radlab.wzorce.data.helper.JsonAssetsHelper
import com.radlab.wzorce.data.helper.JsonHelperIpm
import com.radlab.wzorce.data.model.DesignPatternsData
import com.radlab.wzorce.utils.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResourcesProvider(@ApplicationContext context: Context): ResourcesProvider {
        return ResourcesProvider(context)
    }

    @Provides
    fun provideJsonHelper(resourcesProvider: ResourcesProvider): JsonAssetsHelper<DesignPatternsData> {
        return JsonHelperIpm(resourcesProvider)
    }
}
