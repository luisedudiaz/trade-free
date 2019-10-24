package mx.itesm.tradefree.view.profileseller


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import mx.itesm.tradefree.presenter.presenters.ProfileSellerPresenter
import mx.itesm.tradefree.view.message.ActivityMessage
import java.io.Serializable
import com.androidnetworking.error.ANError
import android.graphics.Bitmap
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority


class ActivityProfileSeller : BaseActivity(), IProfileSellerContract.View, View.OnClickListener {


    private lateinit var profileSellerPresenter: ProfileSellerPresenter
    private lateinit var product: Product
    private lateinit var user: User
    private lateinit var txtNameSeller : TextView
    private lateinit var txtProductsSize : TextView
    private lateinit var btnContactSeller : TextView
    private lateinit var divider: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_seller)
        initViews()
        product = intent.getSerializableExtra("PRODUCT") as Product
        getUserData()
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnContactSeller -> goToMessage()
        }
    }

    private fun goToMessage() {
        val intent = Intent(this, ActivityMessage::class.java)
        intent.putExtra("USER", user as Serializable)
        intent.putExtra("USERID", product.user.id)
        startActivity(intent)
    }

    private fun getUserData() {
        profileSellerPresenter.getUserData(product.user.id)
    }

    private fun initViews() {
        profileSellerPresenter = ProfileSellerPresenter(this)
        txtNameSeller = this.findViewById(R.id.txtNameSeller)
        txtProductsSize = this.findViewById(R.id.txtProductsSize)
        btnContactSeller = this.findViewById(R.id.btnContactSeller)
        divider = this.findViewById(R.id.divider3)
        btnContactSeller.setOnClickListener(this)
    }

    override fun onDataUserSuccess(user: User, uid: String) {
        this.user = user
        if (product.user.id == uid) {
            btnContactSeller.visibility = View.GONE
            divider.visibility = View.GONE
        }
        setValues()
    }

    private fun setValues() {
        Log.d("USER",  user.products.size.toString())
        txtNameSeller.text = product.user.name
        txtProductsSize.text = user.products.size.toString()

    }

}
