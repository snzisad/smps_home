package com.leviabd.smpshome.bottom_sheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.iid.FirebaseInstanceId
import com.leviabd.smpshome.R
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.util.HashMap


class FeedbackSheet : BottomSheetDialogFragment() {
    private lateinit var rb_yes: RadioButton
    private lateinit var rb_no: RadioButton
    private lateinit var ll_adjust_temperature: LinearLayout
    private lateinit var seekbar_temperature: SeekBar
    private lateinit var tv_progress_value: TextView
    private lateinit var btn_send_feedback: Button
    private lateinit var tv_btn_cancel: TextView
    private var initial_temperature: Int = 0
    var comfort_status = true

    private lateinit var onFeedbackSubmitListener: OnFeedbackSubmitListener

    fun setOnFeedbackSubmitListener(callback: OnFeedbackSubmitListener) {
        onFeedbackSubmitListener = callback
    }

    interface OnFeedbackSubmitListener {
        fun onSubmit(comfort_status: Boolean, temperature: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.feedback_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initial_temperature = requireArguments().getInt("temperature")

        initComponents(view)
        initListeners()
    }

    private fun initComponents(view: View){
        rb_yes = view.findViewById(R.id.rb_yes)
        rb_no = view.findViewById(R.id.rb_no)
        ll_adjust_temperature = view.findViewById(R.id.ll_adjust_temperature)
        seekbar_temperature = view.findViewById(R.id.seekbar_temperature)
        tv_progress_value = view.findViewById(R.id.tv_progress_value)
        btn_send_feedback = view.findViewById(R.id.btn_send_feedback)
        tv_btn_cancel = view.findViewById(R.id.tv_btn_cancel)

        ll_adjust_temperature.visibility = View.GONE
    }

    private fun initListeners(){
        rb_yes.setOnClickListener {
            comfort_status = true
            ll_adjust_temperature.visibility = View.GONE
        }
        rb_no.setOnClickListener {
            comfort_status = false
            ll_adjust_temperature.visibility = View.VISIBLE
            seekbar_temperature.setProgress(initial_temperature)
            tv_progress_value.setText("$initial_temperature° C")
        }

        seekbar_temperature.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            var progressChangedValue = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                progressChangedValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                tv_progress_value.setText("$progressChangedValue° C")
            }
        })

        tv_btn_cancel.setOnClickListener {
            dismiss()
        }
        btn_send_feedback.setOnClickListener {
            val temperature = seekbar_temperature.progress
            onFeedbackSubmitListener.onSubmit(comfort_status, temperature)
            Toast.makeText(activity, "Thanks for your feedback", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }



    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}
