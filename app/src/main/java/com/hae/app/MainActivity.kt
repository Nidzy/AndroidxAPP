package com.hae.app

import android.content.pm.PackageInfo
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hae.app.adapter.ApplistAdapter
import com.hae.app.databinding.ActivityMainBinding
import com.hae.app.model.AppsInfo
import com.hae.app.viewmodel.CityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appListAdapter: ApplistAdapter
    val appList: ArrayList<AppsInfo> = ArrayList<AppsInfo>()
    lateinit var cityViewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        binding.txtClock.format12Hour = "hh:mm:ss a"

        // Call battery manager service
        val batteryManager = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager

        //get battery level
        val batLevel: Int = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

        binding.tvBattery.text = "Battery : $batLevel%"
        binding.btnLaunch.setOnClickListener {
            getInstalledApps(true)

        }

        binding.rvApps.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            setItemViewCacheSize(0)
        }
        appListAdapter = ApplistAdapter()
        binding.rvApps.adapter = appListAdapter
    }
    override fun onResume() {
        super.onResume()
        if (!isConnectedToIntenet()) {
            showAlertForInternet(getString(R.string.warning_no_internet))
        } else {
            initCityData()
        }
    }

    /*
    * Fetch city data from remote api
    * */
    private fun initCityData(){
        cityViewModel.getCity().observe(this, Observer { cityData ->
            if (cityData != null) {

                Log.d("cityName :", cityData.city)
                Log.d("country :", cityData.country)
                Log.d("temperature :", cityData.temperature)
                Log.d("description :", cityData.description)

                binding.tvCityData.text =
                    " City Name: ${cityData.city} , \n Country: ${cityData.country} , \n Temperature: ${cityData.temperature} , \n Description: ${cityData.description} "

            }
        })
    }

/*
* Fetch installed apps from device
* */
    private fun getInstalledApps(getSysPackages: Boolean) {

        binding.progressBar.visibility = View.VISIBLE
        val packs: List<PackageInfo> = packageManager.getInstalledPackages(0)

        for (i in packs.indices) {
            val p = packs[i]
            if (!getSysPackages && p.versionName == null) {
                continue
            }
            val newInfo = AppsInfo(
                p.applicationInfo.loadLabel(packageManager).toString(),
                p.packageName,
                p.versionName,
                p.versionCode.toString(),
                p.applicationInfo.loadIcon(packageManager)
            )
            appList.add(newInfo)
        }

        if (appList.isNotEmpty()) {
            binding.progressBar.visibility = View.GONE
            appListAdapter.addData(appList)
        }

    }

}
