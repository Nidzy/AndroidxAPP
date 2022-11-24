package com.hae.app.apihelper

import com.hae.app.model.CityInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("beijing")
    suspend fun getBeijing(): Response<CityInfo>

    @GET("berlin")
    suspend fun getBerlin(): Response<CityInfo>

    @GET("cardiff")
    suspend fun getCardiff(): Response<CityInfo>

    @GET("edinburgh")
    suspend fun getEdinburgh(): Response<CityInfo>

    @GET("london")
    suspend fun getLondon(): Response<CityInfo>

    @GET("nottingham")
    suspend fun getnottingham(): Response<CityInfo>


}