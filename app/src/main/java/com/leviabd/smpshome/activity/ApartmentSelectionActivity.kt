package com.leviabd.smpshome.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.messaging.FirebaseMessaging
import com.leviabd.smpshome.R
import com.leviabd.smpshome.adapter.ApartmentSelectionAdapter
import com.leviabd.smpshome.model.Apartments

class ApartmentSelectionActivity : AppCompatActivity() {
    private lateinit var buildingData: ArrayList<Apartments>
    private lateinit var apartmentSelectionAdapter: ApartmentSelectionAdapter
    private lateinit var rv_building_list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_selection)
        setTitle("Select Apartment")


        FirebaseMessaging.getInstance().subscribeToTopic("smps_home")

        initComponents()
        loadDummyData()
    }

    private fun initComponents(){
        buildingData = ArrayList()
        rv_building_list = findViewById(R.id.rv_building_list)
        apartmentSelectionAdapter = ApartmentSelectionAdapter(this, buildingData)

        rv_building_list.adapter = apartmentSelectionAdapter
        rv_building_list.layoutManager = GridLayoutManager(this, 2)
    }

    private fun loadDummyData(){
        buildingData.add(Apartments(1, "Apartment 1", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        buildingData.add(Apartments(2, "Apartment 2", "https://thumbs.dreamstime.com/b/apartment-building-modern-buildings-new-westminster-british-columbia-canada-40351928.jpg"));
        buildingData.add(Apartments(3, "Apartment 3", "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/ixPdSl7.SV8A/v1/-1x-1.jpg"));
        buildingData.add(Apartments(4, "Apartment 4", "https://media.istockphoto.com/id/1165384568/photo/europe-modern-complex-of-residential-buildings.jpg?s=612x612&w=0&k=20&c=iW4NBiMPKEuvaA7h8wIsPHikhS64eR-5EVPfjQ9GPOA="));
        buildingData.add(Apartments(5, "Apartment 5", "https://media.istockphoto.com/id/1322575582/photo/exterior-view-of-modern-apartment-building-offering-luxury-rental-units-in-silicon-valley.jpg?b=1&s=170667a&w=0&k=20&c=0s6qL5cIMm6LSnryH40h5GmaM6jCi11kchWzsaTJGZE="));
        buildingData.add(Apartments(6, "Apartment 6", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        buildingData.add(Apartments(7, "Apartment 7", "https://media.istockphoto.com/id/1273552068/photo/exterior-view-of-modern-apartment-building.jpg?s=170667a&w=0&k=20&c=RKZJI6gk4UILloqMtUHOUzIlhdOtfR7hdjDGbVtx04g="));
        buildingData.add(Apartments(8, "Apartment 8", "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/ixPdSl7.SV8A/v1/-1x-1.jpg"));
    }


}