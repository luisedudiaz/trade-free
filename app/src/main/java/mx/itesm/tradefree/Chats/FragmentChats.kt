package mx.itesm.tradefree.Chats

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentChats : Fragment() {

    private lateinit var viewModelChats: ViewModelChats

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelChats = ViewModelProviders.of(this).get(ViewModelChats::class.java)
        val root = inflater.inflate(R.layout.fragment_chats, container, false)
        return root
    }
}
