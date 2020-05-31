package com.nochita.shabbatCandles.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nochita.shabbatCandles.R
import com.nochita.shabbatCandles.model.ShabbatCandlesData
import com.nochita.shabbatCandles.receiver.AlarmReceiver
import com.nochita.shabbatCandles.viewmodel.ShabbatCandleViewModel
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    lateinit var viewModel : ShabbatCandleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShabbatCandleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            populateUI(it)
        })
    }

    private fun populateUI(data : ShabbatCandlesData?) {
        data?.let {
            tvTime.text = data.date
            tvParashat.text = data.parashat
            setAlarmButton.isVisible = true

            setAlarmButton.setOnClickListener{ programmAlarm(data) }
        }
    }

    private fun programmAlarm(data : ShabbatCandlesData) {
        var alarmMgr: AlarmManager? = null
        var alarmIntent: PendingIntent
        alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }

        Log.d("lala", "firing alarm in ${data.getDateInMillis()} milliseconds")

        alarmMgr?.set(
            AlarmManager.RTC_WAKEUP,
            data.getDateInMillis()!!,
            alarmIntent
        )
    }
}
