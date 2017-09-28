package us.buddman.bubblepop.utils

import android.widget.EditText

/**
 * Created by swj8530 on 2017-09-29.
 */
class TextUtils(){
    companion object {
        fun isFilled(vararg editTexts : EditText) : Boolean{
            return editTexts.none { it.text.toString().trim() == "" }
        }
    }
}