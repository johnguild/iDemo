package com.test.itunesdemo.helpers

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Helper functions that can be used on EditText instance or descendant
 */


/**
 * Adds TextWatcher on EditText and executes given script after a 1.5second
 * delay of the user typing.
 *
 * @see android.text.TextWatcher
 */
fun EditText.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
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

/**
 * Set the cursor on the end of the text inside the focused EditText
 */
fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}