package mx.itesm.tradefree.view.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.synnapps.carouselview.CarouselView
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import com.androidnetworking.error.ANError
import android.graphics.Bitmap
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.AndroidNetworking
import android.util.Log
import android.widget.ImageView
import com.androidnetworking.common.Priority
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_product.*
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import mx.itesm.tradefree.presenter.presenters.ProfileSellerPresenter
import mx.itesm.tradefree.view.message.ActivityMessage
import java.io.Serializable


class ActivityProduct : BaseActivity(), View.OnClickListener, IProfileSellerContract.View {

    override fun onDataUserSuccess(user: User, uid: String) {
        Log.d("size",bitmapArray.size.toString())
        this.user = user
        if (product.user.id == uid) {
            btnSendMessageProduct.visibility = View.INVISIBLE
            product.images.values.forEach {
                AndroidNetworking.get(it.toString().substring(5, it.toString().length-1))
                .setTag("imageRequestTag")
                .setPriority(Priority.MEDIUM)
                .setBitmapMaxHeight(100)
                .setBitmapMaxWidth(100)
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
                .getAsBitmap(object : BitmapRequestListener {
                    override fun onResponse(bitmap: Bitmap) {
                        imageView.setImageBitmap(bitmap)
                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
            }
        }
        setViews()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btnSendMessageProduct -> goToMessage()
        }
    }

    private fun goToMessage() {
        val intent = Intent(this, ActivityMessage::class.java)
        intent.putExtra("USER", user as Serializable)
        intent.putExtra("USERID", product.user.id)
        startActivity(intent)
    }



    private lateinit var profileSellerPresenter: ProfileSellerPresenter
    private lateinit var txtTitleProduct: TextView
    private lateinit var txtDescriptionProduct: TextView
    private lateinit var btnSendMessageProduct: Button
    private lateinit var btnProfileSellerProduct: TextView
    private lateinit var carouselView: ImageView
    private lateinit var product: Product
    private lateinit var user: User
    private var bitmapArray = ArrayList<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        // Initialize Floating Button
        product = intent.getSerializableExtra("PRODUCT") as Product
        initViews()
        getUserData()
    }



    private fun getUserData() {
        profileSellerPresenter = ProfileSellerPresenter(this)
        profileSellerPresenter.getUserData(product.user.id)
    }

    private fun setViews() {
        txtTitleProduct.text = product.title
        txtDescriptionProduct.text = product.description
        btnProfileSellerProduct.text = product.user.name
    }

    private fun initViews() {
        txtTitleProduct = this.findViewById(R.id.txtTitleProduct)
        txtDescriptionProduct = this.findViewById(R.id.txtDescriptionProduct)
        btnSendMessageProduct = this.findViewById(R.id.btnSendMessageProduct)
        btnProfileSellerProduct = this.findViewById(R.id.btnProfileSellerProduct)
        carouselView = this.findViewById(R.id.imageView)
        btnSendMessageProduct.setOnClickListener(this)

    }
}
