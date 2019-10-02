package mx.itesm.tradefree.Chat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentChat : Fragment() {

    private lateinit var viewModelChat: ViewModelChat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelChat = ViewModelProviders.of(this).get(ViewModelChat::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        return root
    }
}
