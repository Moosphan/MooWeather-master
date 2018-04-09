package com.moos.weather.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by moos on 2018/2/25.
 * func:base activity for kotlin
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())

    }

    protected abstract fun setLayoutId():Int

    protected abstract fun initViews()

    protected abstract fun initDatas()




}