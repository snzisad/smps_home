package com.leviabd.smpshome.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.anastr.speedviewlib.ProgressiveGauge
import com.github.anastr.speedviewlib.SpeedView
import com.leviabd.smpshome.R
import com.leviabd.smpshome.bottom_sheet.FeedbackSheet
import com.leviabd.smpshome.model.SensorData
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {
    val server_url = "http://172.27.63.231:8080"
    val get_data_API = "$server_url/api/rooms/1/"
    val set_data_API = "$server_url/api/rooms/"

    private lateinit var srl_dashboard: SwipeRefreshLayout
    private lateinit var btn_send_feedback: Button
    private lateinit var feedbackSheet: FeedbackSheet
    private lateinit var speedView_temperature: SpeedView
    private lateinit var progressiveGauge_humidity: ProgressiveGauge
    private lateinit var progressiveGauge_co2: ProgressiveGauge
    private lateinit var progressbar_iaqi: ProgressBar
    private lateinit var tv_iaqi_value: TextView
    private lateinit var sensorData: SensorData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        setData()
        retrieveData()
    }

    private fun initComponents(){
        sensorData = SensorData("", 0, 0, 0, 0, 0, 0)
        srl_dashboard = findViewById(R.id.srl_dashboard)
        btn_send_feedback = findViewById(R.id.btn_send_feedback)
        speedView_temperature = findViewById(R.id.speedView_temperature)
        progressiveGauge_humidity = findViewById(R.id.progressiveGauge_humidity)
        progressiveGauge_co2 = findViewById(R.id.progressiveGauge_co2)
        progressbar_iaqi = findViewById(R.id.progressbar_iaqi)
        tv_iaqi_value = findViewById(R.id.tv_iaqi_value)


        speedView_temperature.minSpeed = -15f
        speedView_temperature.maxSpeed = 30f
        speedView_temperature.unit = "° C"
        speedView_temperature.withTremble = false

        progressiveGauge_humidity.unit = "gm-3"
        progressiveGauge_humidity.withTremble = false
        progressiveGauge_humidity.speedometerColor = Color.BLUE


        progressiveGauge_co2.unit = "ppm"
        progressiveGauge_co2.withTremble = false
        progressiveGauge_co2.speedometerColor = Color.GREEN


        srl_dashboard.setOnRefreshListener {
            retrieveData()
        }

        btn_send_feedback.setOnClickListener {
            feedbackSheet = FeedbackSheet()
            val bundle = Bundle()
            bundle.putInt("temperature", sensorData.temp.toInt())
            feedbackSheet.setArguments(bundle)
            feedbackSheet.show(getSupportFragmentManager().beginTransaction(), "User Feedback Sheet")


            feedbackSheet.setOnFeedbackSubmitListener(object : FeedbackSheet.OnFeedbackSubmitListener{
                override fun onSubmit(comfort_status: Boolean, temperature: Int) {
                    if(temperature != sensorData.temp.toInt()){
                        speedView_temperature.speedTo(temperature.toFloat(), 1000)
                    }
                    sendData(comfort_status, temperature)
                }
            })
        }


    }

    private fun setData(){
        speedView_temperature.speedTo(sensorData.temp.toFloat(), 1000)
        progressiveGauge_humidity.speedTo(sensorData.humidity.toFloat())
        progressiveGauge_co2.speedTo(sensorData.co2.toFloat())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressbar_iaqi.setProgress(sensorData.aqi, true)
        }
        tv_iaqi_value.setText("Air Quality(IAQI): "+sensorData.aqi)
    }

    private fun retrieveData(){
        srl_dashboard.isRefreshing = true
        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, get_data_API, null,
            { response ->
                try {
                    sensorData = SensorData(response.getString("name"), response.getInt("temperature"), response.getInt("humidity"), response.getInt("CO2"), response.getInt("ventilation"), response.getInt("facility_outlet"), response.getInt("facility_noise"))
                    setData()
                } catch (e: java.lang.Exception) {
                    Toast.makeText(this, "Something wrong. Try Again(301)", Toast.LENGTH_SHORT).show()
                    Log.e("301", ""+e.localizedMessage)
                }
                srl_dashboard.isRefreshing = false
            },
            { error ->
                srl_dashboard.isRefreshing = false
                error.printStackTrace()
                Log.e("302", error.toString())
                Toast.makeText(this, "Something wrong. Try again (302)", Toast.LENGTH_LONG).show()
            }
        )

        requestQueue.add(jsonObjectRequest)

    }


    private fun sendData(comfort_status: Boolean, temperature: Int){

        val requestQueue = Volley.newRequestQueue(this)
        val jsonBody = JSONObject()
        try {
            jsonBody.put("name", ""+sensorData.name)
            jsonBody.put("floor", "1")
            jsonBody.put("temperature", ""+temperature)
            jsonBody.put("humidity", ""+sensorData.humidity)
            jsonBody.put("CO2", ""+sensorData.co2)
            jsonBody.put("ventilation", ""+sensorData.aqi)
            jsonBody.put("comfortable", ""+comfort_status)
            jsonBody.put("facility_outlet", ""+sensorData.facility_outlet)
            jsonBody.put("facility_noise", ""+sensorData.facility_noise)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjReq: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
            set_data_API, jsonBody,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {
                    Log.e("Post Response", response.toString())
                }

            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {

                }
            }) {
            /**
             * Passing some request headers
             */
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }
        }

        requestQueue.add(jsonObjReq)
    }
}