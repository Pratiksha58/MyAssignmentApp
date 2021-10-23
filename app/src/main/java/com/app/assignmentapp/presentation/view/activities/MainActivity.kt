package com.app.assignmentapp.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.assignmentapp.R
import com.app.assignmentapp.data.Responses.ArticleE
import com.app.assignmentapp.presentation.enums.Status
import com.app.assignmentapp.presentation.view.adapter.DataListAdapter
import com.app.assignmentapp.presentation.view.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    val mDataViewModel: DataViewModel by viewModel()

    var mLists: ArrayList<ArticleE>? = null

    companion object {

        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            )
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTimer()
        btn_reset.setOnClickListener {
            timer.cancel()
            setTimer()
        }

    }


    private fun setTimer() {

            timer = object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Used for formatting digit to be in 2 digits only
                    val f: NumberFormat = DecimalFormat("00")
                    val hour = millisUntilFinished / 3600000 % 24
                    val min = millisUntilFinished / 60000 % 60
                    val sec = millisUntilFinished / 1000 % 60
                    tv_countdown.setText(f.format(min) + ":" + f.format(sec))
                }

                // When the task is over it will print 00:00 there
                override fun onFinish() {
                    tv_countdown.setText("00:00")
                    getDataRequest()

                }
            }.start()
    }

    private fun getDataRequest() {
        mDataViewModel.getData()
        observeDataResponse()
    }

    private fun observeDataResponse() {
        mDataViewModel.getDataResponse().observe(this, androidx.lifecycle.Observer { res ->
            when(res.status){
                    Status.SUCCESS ->{
                        mLists = res.mDataEntity?.result?.article
                        if (mLists!= null){
                            showDataAdapter()
                        }
                    }
                    Status.ERROR->{
                        Log.e("ErrorResponse ", "" + res.status)

                    }
            }
        })
    }


    private fun showDataAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycl_item.layoutManager = linearLayoutManager
        val mDataAdapter =
            let {
                DataListAdapter(this, mLists!!)
            }
        recycl_item.adapter = mDataAdapter
    }
}