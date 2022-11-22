package com.leviabd.smpshome.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.leviabd.smpshome.bottom_sheet.MainActivity
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
            val apartment_data = data.get(position)

            headerClass.tv_apartment_name.setText(apartment_data.name)

            headerClass.cv_apartment_list.setOnClickListener {
                val builder = AlertDialog.Builder(activity)
                builder.setMessage("Do you want to register for this apartment?")
                builder.setPositiveButton("Yes") { dialog, which ->
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                }

                builder.setNegativeButton("No") { dialog, which ->
                    null
                }

                builder.create().show()

            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BuildingListHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_apartment_name: TextView
        var cv_apartment_list: CardView

        init {
            tv_apartment_name = itemView.findViewById(R.id.tv_apartment_name)
            cv_apartment_list = itemView.findViewById(R.id.cv_apartment_list)
        }
    }

}