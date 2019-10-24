package mx.itesm.tradefree.model.models.Image

import android.graphics.Bitmap

data class ImageProductPosition(
    val index: Int,
    val subIndex: Int,
    val bitmap: Bitmap
)
