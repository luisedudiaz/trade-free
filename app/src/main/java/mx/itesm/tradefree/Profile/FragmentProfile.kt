package mx.itesm.tradefree.Profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.tradefree.BaseFragment

import mx.itesm.tradefree.R

class FragmentProfile : BaseFragment() {

    private lateinit var viewModelProfile: ViewModelProfile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelProfile = ViewModelProviders.of(this).get(ViewModelProfile::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root

    }
}
