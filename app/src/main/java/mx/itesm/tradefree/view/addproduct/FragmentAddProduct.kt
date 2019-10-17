package mx.itesm.tradefree.view.addproduct

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import mx.itesm.tradefree.view.base.BaseFragment

import mx.itesm.tradefree.R


class FragmentAddProduct : BaseFragment(), View.OnClickListener, ImageListener {


    private lateinit var root: View
    private lateinit var carouselView: CarouselView
    private lateinit var btnUploadImages: Button

    private lateinit var carouselImages: MutableList<Uri>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_add_product, container, false)

        initView()

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {
                if (data?.clipData != null) {
                    val count = data.clipData!!.itemCount
                    var currentImage = 0
                    carouselImages.clear()

                    while (currentImage < count) {
                        val image: Uri = data.clipData!!.getItemAt(currentImage).uri
                        carouselImages.add(image)
                        currentImage++
                    }
                    carouselView.pageCount = carouselImages.size
                    carouselView.setImageListener(this)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddImagesProduct -> addMultipleImages()
        }
    }

    override fun setImageForPosition(position: Int, imageView: ImageView?) {
        if (carouselImages.isEmpty()) {
            Log.d("IMAGECAROUSEL", "HERE")
            imageView?.setImageResource(DEFAULT_IMAGE)
        } else {
            imageView?.setImageURI(carouselImages[position])
        }
    }

    private fun addMultipleImages() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, PICK_IMG)
    }


    private fun initView() {
        // View
        carouselView = root.findViewById(R.id.carouselView)
        // Buttons
        btnUploadImages = root.findViewById(R.id.btnAddImagesProduct)
        // Array
        carouselImages = mutableListOf()
        // ButtonsListeners
        btnUploadImages.setOnClickListener(this)
        // CarouselViewListeners
        carouselView.pageCount = PICK_IMG
        carouselView.setImageListener(this)

    }
    companion object {
        private const val PICK_IMG = 1
        private const val DEFAULT_IMAGE = R.drawable.default_image
    }
}
