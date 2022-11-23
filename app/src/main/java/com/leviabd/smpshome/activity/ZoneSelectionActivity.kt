package com.leviabd.smpshome.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leviabd.smpshome.R
import com.leviabd.smpshome.adapter.ZoneSelectionAdapter
import com.leviabd.smpshome.model.Zones

class ZoneSelectionActivity : AppCompatActivity() {
    private lateinit var apartmentData: ArrayList<Zones>
    private lateinit var zoneSelectionAdapter: ZoneSelectionAdapter
    private lateinit var rv_apartment_list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zone_selection)
        setTitle("Select Zone")

        initComponents()
        loadDummyData()
    }


    private fun initComponents(){
        apartmentData = ArrayList()
        rv_apartment_list = findViewById(R.id.rv_apartment_list)
        zoneSelectionAdapter = ZoneSelectionAdapter(this, apartmentData)

        rv_apartment_list.adapter = zoneSelectionAdapter
        rv_apartment_list.layoutManager = LinearLayoutManager(this)
    }

    private fun loadDummyData(){
        apartmentData.add(Zones(1, "Zone 1"))
        apartmentData.add(Zones(2, "Zone 2"))
        apartmentData.add(Zones(3, "Zone 3"))
        apartmentData.add(Zones(4, "Zone 4"))
        apartmentData.add(Zones(5, "Zone 5"))
        apartmentData.add(Zones(6, "Zone 6"))
        apartmentData.add(Zones(7, "Zone 7"))
        apartmentData.add(Zones(8, "Zone 8"))
        apartmentData.add(Zones(9, "Zone 9"))
        apartmentData.add(Zones(10, "Zone 10"))
        apartmentData.add(Zones(11, "Zone 11"))
    }

}