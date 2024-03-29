@file:Suppress("DEPRECATION")

package mx.itesm.tradefree.view.base

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import mx.itesm.tradefree.R


@Suppress("DEPRECATION")
open class BaseFragment: Fragment() {

    /**
     *  Progress Dialog initialization.
     */
    @VisibleForTesting
    val progressDialog by lazy {
        ProgressDialog(context)
    }

    fun showProgressDialog() {
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.isIndeterminate = true
        progressDialog.show()
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
    
}