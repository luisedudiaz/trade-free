package mx.itesm.tradefree.view.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_home.view.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.UserProduct

class AdapterProfile(
    private val ctxt: Context,
    private val keys: List<Pair<String, UserProduct>>,
    private val listener: onProductCardListener):
    RecyclerView.Adapter<AdapterProfile.ProductCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCard {
        val view = LayoutInflater.from(ctxt).inflate(R.layout.adapter_home, parent, false)
        return ProductCard(view, listener)    }

    override fun getItemCount(): Int {
        return keys.size
    }

    override fun onBindViewHolder(holder: ProductCard, position: Int) {
        val product = keys[position].second
        holder.set(product)
    }

    inner class ProductCard(var productView: View, var onProductCardListener: onProductCardListener): RecyclerView.ViewHolder(productView), View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btnProfileSeller -> onProductCardListener.onProfileSellerClick(adapterPosition)
                R.id.btnContactSeller -> onProductCardListener.onSeeMoreClick(adapterPosition)
                R.id.btnDeleteProduct -> onProductCardListener.onDeleteProduct(keys[adapterPosition].second)
            }
        }

        fun set(product: UserProduct) {
            productView.btnProfileSeller.setOnClickListener(this)
            productView.btnContactSeller.setOnClickListener(this)
            productView.btnDeleteProduct.setOnClickListener(this)
            productView.txtTitleHome.text = product.title
            productView.txtNameSeller.visibility = View.GONE
            productView.btnProfileSeller.visibility = View.GONE
            productView.btnDeleteProduct.visibility = View.VISIBLE
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
        fun onDeleteProduct(product: UserProduct)
    }
}
