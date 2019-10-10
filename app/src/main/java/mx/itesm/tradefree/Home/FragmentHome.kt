package mx.itesm.tradefree.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import mx.itesm.tradefree.BaseFragment
import mx.itesm.tradefree.R

class FragmentHome : BaseFragment() {

    private lateinit var viewModelHome: ViewModelHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelHome = ViewModelProviders.of(this).get(ViewModelHome::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root

    }
}
