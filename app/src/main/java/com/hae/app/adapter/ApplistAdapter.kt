package com.hae.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hae.app.R
import com.hae.app.databinding.RowAppsBinding
import com.hae.app.model.AppsInfo


class ApplistAdapter(var appList: MutableList<AppsInfo> = mutableListOf()) :
    RecyclerView.Adapter<ApplistAdapter.ApplistAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplistAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_apps, parent, false)
        return ApplistAdapterViewHolder(view)
    }


    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: ApplistAdapterViewHolder, position: Int) {
        with(holder) {
            if (appList.size > 0) {
                val apps = appList[position]
                binding.tvName.text = apps.appName
                binding.ivApp.setImageDrawable(apps.icon)

            }
        }
    }

    fun addData(listOfApps: List<AppsInfo>) {
        appList = mutableListOf()
        appList.addAll(listOfApps)
        notifyDataSetChanged()
    }


    class ApplistAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RowAppsBinding.bind(view)
    }

}