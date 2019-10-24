package mx.itesm.tradefree.view.profileseller


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import mx.itesm.tradefree.presenter.presenters.ProfileSellerPresenter
import mx.itesm.tradefree.view.message.ActivityMessage
import java.io.Serializable

class ActivityProfileSeller : BaseActivity(), IProfileSellerContract.View, View.OnClickListener {


    private lateinit var profileSellerPresenter: ProfileSellerPresenter
    private lateinit var product: Product
    private lateinit var user: User
    private lateinit var txtNameSeller : TextView
    private lateinit var txtProductsSize : TextView
    private lateinit var btnContactSeller : TextView

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
        btnContactSeller.setOnClickListener(this)
    }

    override fun onDataUserSuccess(user: User) {
        this.user = user

        setValues()
    }

    private fun setValues() {
        Log.d("USER",  user.products.size.toString())
        txtNameSeller.text = product.user.name
        txtProductsSize.text = user.products.size.toString()

    }

}
