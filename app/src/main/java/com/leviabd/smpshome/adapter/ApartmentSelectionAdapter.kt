package com.leviabd.smpshome.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leviabd.smpshome.activity.ZoneSelectionActivity
import com.leviabd.smpshome.R
import com.leviabd.smpshome.model.Apartments

class ApartmentSelectionAdapter (val activity: Activity, val data: ArrayList<Apartments>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BuildingListHolderClass(
            LayoutInflater.from(activity).inflate(R.layout.layout_apartment_list, parent, false)
        )
    }

    override fun onBindViewHolder(headerClass: RecyclerView.ViewHolder, position: Int) {
        if (headerClass is BuildingListHolderClass) {
            val building_data = data.get(position)

            Glide.with(activity)
                .load(building_data.picture)
                .dontAnimate()
                .into(headerClass.img_building)



            headerClass.tv_building_name.setText(building_data.name)

            headerClass.cv_building_list.setOnClickListener {
                activity.startActivity(Intent(activity, ZoneSelectionActivity::class.java))
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BuildingListHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_building_name: TextView
        var img_building: ImageView
        var cv_building_list: CardView

        init {
            tv_building_name = itemView.findViewById(R.id.tv_building_name)
            img_building = itemView.findViewById(R.id.img_building)
            cv_building_list = itemView.findViewById(R.id.cv_building_list)
        }
    }

}