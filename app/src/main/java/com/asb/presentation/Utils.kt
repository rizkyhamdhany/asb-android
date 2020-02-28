package com.asb.presentation

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.asb.presentation.auth.LoginViewModel
import com.asb.presentation.medcheck.GetPateintDataViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { LoginViewModel(get()) }
    factory { GetPateintDataViewModel(get(), get()) }
}

fun TextView.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        var timer: CountDownTimer? = null

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            timer?.cancel()
            timer = object : CountDownTimer(1000, 1500) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    afterTextChanged.invoke(editable.toString())
                }
            }.start()
        }
    })
}