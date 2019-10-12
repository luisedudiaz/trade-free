package mx.itesm.tradefree.view.chat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.tradefree.view.base.BaseFragment

import mx.itesm.tradefree.R
//import mx.itesm.tradefree.app.viewmodels.ViewModelChat

class FragmentChat : BaseFragment() {

  //  private lateinit var viewModelChat: ViewModelChat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   viewModelChat = ViewModelProviders.of(this).get(ViewModelChat::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        return root
    }

}
