package mx.itesm.tradefree.view

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_home.view.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import kotlin.math.log

class AdapterHome(val ctxt: Context, val products: MutableList<Product>): RecyclerView.Adapter<AdapterHome.ProductCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCard {
        val view = LayoutInflater.from(ctxt).inflate(R.layout.adapter_home, parent, false)
        return ProductCard(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductCard, position: Int) {
        val product = products[position]
        holder.set(product)

    }

    inner class ProductCard(var productView: View): RecyclerView.ViewHolder(productView) {
        fun set(product: Product) {
            productView.txtTitleHome.text = product.title
            productView.txtSellerHome.text = product.user.name

            productView.carouselViewHome.pageCount = product.images.values.size
            Log.d("IMAGES", product.images.values.size.toString())
            productView.carouselViewHome.setImageListener { position, imageView ->
                product.images.values.forEach {
                    Log.d("IMAGES", it.toString().substring(5, it.toString().length-1))
                    Picasso.get().load(it.toString().substring(5, it.toString().length-1)).into(imageView)
                }

            }
        }
    }
}