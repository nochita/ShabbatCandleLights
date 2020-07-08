package com.nochita.shabbatCandles.ui

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.*
import androidx.core.text.bold
import androidx.core.text.scale
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nochita.shabbatCandles.R
import com.nochita.shabbatCandles.extentions.createNotification
import com.nochita.shabbatCandles.extentions.parseToDayOfTheMonth
import com.nochita.shabbatCandles.extentions.parseToHoursAndSeconds
import com.nochita.shabbatCandles.model.ShabbatCandlesData
import com.nochita.shabbatCandles.viewmodel.ShabbatCandleViewModel
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        private val TAG = MainFragment::class.java.simpleName

        fun newInstance(): MainFragment = MainFragment()

        public const val DATE_EXTRA = "DATA_EXTRA"
        public const val PARASHAT_EXTRA = "PARASHAT_EXTRA"

        public const val REQUEST_CODE_MAP = 1
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

        shimmer_view_container.startShimmer()
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            populateUI(it)
        })
    }

    private fun populateUI(data : ShabbatCandlesData?) {
        data?.let {
            val spannableStringBuilder = SpannableStringBuilder()
                .append(getString(R.string.friday_at, data.date.parseToDayOfTheMonth()))
                .bold { scale(1.2f)  {append(data.date.parseToHoursAndSeconds()) }}

            tvTime.text = spannableStringBuilder //data.date
            tvParashat.text = data.parashat.capitalize()

            setAlarmButton.setOnClickListener{ programmAlarm(data) }

        }

        shimmer_view_container.hideShimmer()
    }

    private fun programmAlarm(data : ShabbatCandlesData) {
        var alarmMgr: AlarmManager? = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmMgr?.let {
            alarmMgr.createNotification(context!!, data)
            alarmMgr.createNotification(context!!, data, AlarmManager.INTERVAL_HOUR)
        }

        tvExplanation.text = getString(R.string.explanation_done)
    }
}
