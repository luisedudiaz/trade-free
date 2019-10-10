package mx.itesm.tradefree.Product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import mx.itesm.tradefree.R


class FragmentProduct : Fragment() {

    private lateinit var viewModelProduct: ViewModelProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelProduct = ViewModelProviders.of(this).get(ViewModelProduct::class.java)
        val root = inflater.inflate(R.layout.fragment_product, container, false)
        val btnSendMessageProduct : Button = root.findViewById(R.id.btnSendMessageProduct)
        btnSendMessageProduct.setOnClickListener {
            val intent = Intent(context, ActivityProduct::class.java)
            startActivity(intent)
        }

        return root
    }
}
