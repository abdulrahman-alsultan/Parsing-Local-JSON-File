package com.example.bonusassignment.PLJF

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bonusassignment.MainActivity
import com.example.bonusassignment.R
import kotlinx.android.synthetic.main.image_recycler_view_items.view.*

class ImageRecyclerViewAdapter(private val images: List<ImageData>, private val ctx: ParsingLocalJSONFile): RecyclerView.Adapter<ImageRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_recycler_view_items,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val image = images[position]

        holder.itemView.apply {
            recycler_view_copyright.text = "@${image.copyright}"
            recycler_view_date.text = "Date: ${image.date}"
            recycler_view_title.text = image.title
            Glide.with(holder.itemView.context).load(image.hdurl).into(recycler_view_image)


            recycler_view_more.setOnClickListener {
                ctx.openDialog(image.explanation)
            }

            recycler_view_image.setOnClickListener {
                ctx.openImage(image.hdurl)
            }

        }
    }

    override fun getItemCount(): Int = images.size

}