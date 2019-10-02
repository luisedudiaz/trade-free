package mx.itesm.tradefree.ProfileSeller

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentProfileSeller : Fragment() {

    private lateinit var viewModelProfileSeller: ViewModeltProfileSeller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelProfileSeller = ViewModelProviders.of(this).get(ViewModeltProfileSeller::class.java)
        val root = inflater.inflate(R.layout.fragment_profile_seller, container, false)
        return root
    }
}
