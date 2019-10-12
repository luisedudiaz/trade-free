package mx.itesm.tradefree.model.utils.classes

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

class Date {

    /**
     *  This method return the current system date.
     *  @return Current system date.
     */
    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }
}