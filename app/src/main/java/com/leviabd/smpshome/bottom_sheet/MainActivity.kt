package com.leviabd.smpshome.bottom_sheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.leviabd.smpshome.R

class MainActivity : AppCompatActivity() {
    private lateinit var btn_send_feedback: Button
    private lateinit var feedbackSheet: FeedbackSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()

    }

    private fun initComponents(){
        btn_send_feedback = findViewById(R.id.btn_send_feedback)

        btn_send_feedback.setOnClickListener {
            feedbackSheet = FeedbackSheet()
            feedbackSheet.show(getSupportFragmentManager().beginTransaction(), "Feedback Sheet")
        }
    }
}