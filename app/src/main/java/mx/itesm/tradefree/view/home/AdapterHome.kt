package mx.itesm.tradefree.view.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.adapter_home.view.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import java.lang.Exception


class AdapterHome(private val ctxt: Context, private val products: MutableList<Product>, private val listener: onProductCardListener): RecyclerView.Adapter<AdapterHome.ProductCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCard {
        val view = LayoutInflater.from(ctxt).inflate(R.layout.adapter_home, parent, false)
        return ProductCard(view, listener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductCard, position: Int) {
        val product = products[position]
        holder.set(product)

    }

    inner class ProductCard(var productView: View, var onProductCardListener: onProductCardListener): RecyclerView.ViewHolder(productView), View.OnClickListener {

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btnProfileSeller -> onProductCardListener.onProfileSellerClick(adapterPosition)
                R.id.btnSeeMore -> onProductCardListener.onSeeMoreClick(adapterPosition)
            }

        }

        fun set(product: Product) {
            productView.btnProfileSeller.setOnClickListener(this)
            productView.btnSeeMore.setOnClickListener(this)
            productView.txtTitleHome.text = product.title
            productView.txtSellerHome.text = product.user.name
            productView.carouselViewHome.pageCount = product.images.values.size
            Log.d("IMAGES", product.images.values.size.toString())


            productView.carouselViewHome.setImageListener { position, imageView ->
                product.images.values.forEach {
                    Picasso.get().load(it.toString().substring(5, it.toString().length-1)).into(imageView)
                }


            }

        }
    }

    interface onProductCardListener {
        fun onProfileSellerClick(position: Int)
        fun onSeeMoreClick(position: Int)
    }
}