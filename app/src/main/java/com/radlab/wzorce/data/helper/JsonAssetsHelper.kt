package com.radlab.wzorce.data.helper

interface JsonAssetsHelper<T> {
    suspend fun designPatterns(): Result<T>
}
