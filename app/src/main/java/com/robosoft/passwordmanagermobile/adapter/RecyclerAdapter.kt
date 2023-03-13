package com.robosoft.passwordmanagermobile.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.passwordmanagermobile.R
import com.robosoft.passwordmanagermobile.activity.SiteDetailsActivity
import com.robosoft.passwordmanagermobile.entity.SiteDetails

class RecyclerAdapter(val context: Context, private val siteData: List<SiteDetails>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val websiteName = itemView.findViewById<TextView>(R.id.websiteName_tv)
        val websiteLink = itemView.findViewById<TextView>(R.id.website_tv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_tab_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.websiteName.text = siteData[position].siteName
        holder.websiteLink.text = siteData[position].url

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SiteDetailsActivity::class.java)
            intent.putExtra("userPhone", siteData[position].userPhoneNo)
            intent.putExtra("siteUrl", siteData[position].url)
            intent.putExtra("siteName", siteData[position].siteName)
            intent.putExtra("sector", siteData[position].selector)
            intent.putExtra("userName", siteData[position].userName)
            intent.putExtra("userPassword", siteData[position].sitePassword)
            intent.putExtra("notes", siteData[position].notes)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return siteData.size
    }
}