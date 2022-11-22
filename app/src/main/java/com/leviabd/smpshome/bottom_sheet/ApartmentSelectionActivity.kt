package com.leviabd.smpshome.bottom_sheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leviabd.smpshome.R
import com.leviabd.smpshome.adapter.ApartmentSelectionAdapter
import com.leviabd.smpshome.model.Apartments

class ApartmentSelectionActivity : AppCompatActivity() {
    private lateinit var apartmentData: ArrayList<Apartments>
    private lateinit var apartmentSelectionAdapter: ApartmentSelectionAdapter
    private lateinit var rv_apartment_list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_selection)
        setTitle("Select Apartment")

        initComponents()
        loadDummyData()
    }


    private fun initComponents(){
        apartmentData = ArrayList()
        rv_apartment_list = findViewById(R.id.rv_apartment_list)
        apartmentSelectionAdapter = ApartmentSelectionAdapter(this, apartmentData)

        rv_apartment_list.adapter = apartmentSelectionAdapter
        rv_apartment_list.layoutManager = LinearLayoutManager(this)
    }

    private fun loadDummyData(){
        apartmentData.add(Apartments(1, "Apartment 1"))
        apartmentData.add(Apartments(2, "Apartment 2"))
        apartmentData.add(Apartments(3, "Apartment 3"))
        apartmentData.add(Apartments(4, "Apartment 4"))
        apartmentData.add(Apartments(5, "Apartment 5"))
        apartmentData.add(Apartments(6, "Apartment 6"))
        apartmentData.add(Apartments(7, "Apartment 7"))
        apartmentData.add(Apartments(8, "Apartment 8"))
        apartmentData.add(Apartments(9, "Apartment 9"))
        apartmentData.add(Apartments(10, "Apartment 10"))
        apartmentData.add(Apartments(11, "Apartment 11"))
    }

}