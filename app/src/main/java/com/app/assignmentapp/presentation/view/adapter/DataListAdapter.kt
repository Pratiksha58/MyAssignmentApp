package com.app.assignmentapp.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.assignmentapp.R
import com.app.assignmentapp.data.Responses.ArticleE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_item.view.*

class DataListAdapter(
    var mctx : Context,
    val mList: List<ArticleE>) :
    RecyclerView.Adapter<DataListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText("Name : "+mList[position].name)
        holder.author_name.setText("Author Name : "+mList[position].author)
        holder.category_name.setText("Category Name : "+mList[position].categoryName)
        if (mList[position].image != null){
            Glide.with(mctx)
                .load(mList[position].image)
                .into(holder.img_article);
        }else {
            Glide.with(mctx)
                .load(R.drawable.img_medicine)
                .into(holder.img_article)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author_name = itemView.tv_author_name
        val category_name = itemView.tv_category_name
        val name = itemView.name
        val img_article = itemView.img_article


    }

}
