package com.nochita.shabbatCandles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.nochita.shabbatCandles.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit { replace(R.id.flContent,
            MainFragment.newInstance()
        ) }

    }
}
