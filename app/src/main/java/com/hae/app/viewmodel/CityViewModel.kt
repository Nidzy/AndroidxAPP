package com.hae.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hae.app.model.CityInfo
import com.hae.app.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class CityViewModel : ViewModel() {

    private val repository = CityRepository()
    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    fun getCity(): LiveData<CityInfo> {

        return liveData(context = coroutineContext) {

            repeat(100) {
                runBlocking {

                    val responseBeijing = repository.fetchBeijingData()
                    if (responseBeijing.isSuccessful && responseBeijing.body() != null) {
                        emit(responseBeijing.body()!!)
                    }
                    delayTime()
                    val responseBerlin = repository.fetchBerlinData()
                    if (responseBerlin.isSuccessful && responseBerlin.body() != null) {
                        emit(responseBerlin.body()!!)
                    }
                    delayTime()
                    val responseCardiff = repository.fetchCardiffData()
                    if (responseCardiff.isSuccessful && responseCardiff.body() != null) {
                        emit(responseCardiff.body()!!)
                    }
                    delayTime()
                    val responseEdinburgh = repository.fetchEdinburghData()
                    if (responseEdinburgh.isSuccessful && responseEdinburgh.body() != null) {
                        emit(responseEdinburgh.body()!!)
                    }
                    delayTime()
                    val responseLondon = repository.fetchLondonData()
                    if (responseLondon.isSuccessful && responseLondon.body() != null) {
                        emit(responseLondon.body()!!)
                    }
                    delayTime()
                    val responseNottingham = repository.fetchNottinghamData()
                    if (responseNottingham.isSuccessful && responseNottingham.body() != null) {
                        emit(responseNottingham.body()!!)

                    }


                }
            }

        }
    }

   suspend fun delayTime(){
        delay(5000)
    }

}