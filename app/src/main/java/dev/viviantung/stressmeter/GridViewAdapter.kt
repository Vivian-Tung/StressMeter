package dev.viviantung.stressmeter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class GridViewAdapter(
    private val context: Context,
    private val images: IntArray
) : BaseAdapter() {
    override fun getCount(): Int = images.size

    override fun getItem(position: Int): Int = images[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView = convertView as? ImageView ?: ImageView(context)
        imageView.setImageResource(images[position])
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = AbsListView.LayoutParams(250, 250)
        return imageView
    }
}