package com.leviabd.smpshome.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leviabd.smpshome.R


class FeedbackSheet : BottomSheetDialogFragment() {
    private lateinit var rb_yes: RadioButton
    private lateinit var rb_no: RadioButton
    private lateinit var ll_adjust_temperature: LinearLayout
    private lateinit var seekbar_temperature: SeekBar
    private lateinit var tv_progress_value: TextView
    private lateinit var btn_send_feedback: Button
    private lateinit var tv_btn_cancel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.feedback_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            ll_adjust_temperature.visibility = View.GONE
        }
        rb_no.setOnClickListener {
            ll_adjust_temperature.visibility = View.VISIBLE
            seekbar_temperature.setProgress(15)
            tv_progress_value.setText("15° C")
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
            Toast.makeText(activity, "Thanks for your feedback", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}
