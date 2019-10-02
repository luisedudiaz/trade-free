package mx.itesm.tradefree.Login

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import mx.itesm.tradefree.R
import mx.itesm.tradefree.Register.ActivityRegister


class FragmentLogin : Fragment() {

    private lateinit var viewModelLogin: ViewModelLogin

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelLogin = ViewModelProviders.of(this).get(ViewModelLogin::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val btnRegister: Button = root.findViewById(R.id.btnRegistrate)
        btnRegister.setOnClickListener {
            val intent = Intent(context, ActivityRegister::class.java)
            viewModelLogin.sendMessageToA("D")
            startActivity(intent)
            /** This line is for delete an activity from the stack.
             *
             * activity?.finish()
             *
             */

        }
        return root
    }


}
