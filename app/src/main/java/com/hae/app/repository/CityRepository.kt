package com.hae.app.repository

import com.hae.app.apihelper.ApiConfig
import com.hae.app.apihelper.ApiService

class CityRepository {
    suspend fun fetchBeijingData() =
        ApiConfig.getInstance().create(ApiService::class.java).getBeijing()

    suspend fun fetchBerlinData() =
        ApiConfig.getInstance().create(ApiService::class.java).getBerlin()

    suspend fun fetchCardiffData() =
        ApiConfig.getInstance().create(ApiService::class.java).getCardiff()

    suspend fun fetchEdinburghData() =
        ApiConfig.getInstance().create(ApiService::class.java).getEdinburgh()

    suspend fun fetchLondonData() =
        ApiConfig.getInstance().create(ApiService::class.java).getLondon()

    suspend fun fetchNottinghamData() =
        ApiConfig.getInstance().create(ApiService::class.java).getnottingham()
}