package mx.itesm.tradefree.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_home.view.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Image.ImageProductPosition
import mx.itesm.tradefree.model.models.Product.Product


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
                R.id.btnContactSeller -> onProductCardListener.onSeeMoreClick(adapterPosition)
            }

        }

        fun set(product: Product) {
            productView.btnProfileSeller.setOnClickListener(this)
            productView.btnContactSeller.setOnClickListener(this)
            productView.txtTitleHome.text = product.title
            productView.txtNameSeller.text = product.user.name
            var index = 0
            productView.carouselViewHome.pageCount = 1
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