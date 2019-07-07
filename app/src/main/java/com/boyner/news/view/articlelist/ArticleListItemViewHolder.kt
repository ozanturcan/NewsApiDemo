package com.boyner.news.view.articlelist

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.boyner.news.R
import com.boyner.news.network.local.entity.Article
import com.boyner.news.view.ItemClickListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_list_item_viewholder.view.*

class ArticleListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewTime: AppCompatTextView = itemView.textView_time
    private val textViewTitle: AppCompatTextView = itemView.textView_title
    private val imageViewTitle: AppCompatImageView = itemView.imageView_Uri
    private val linearLayout: LinearLayoutCompat = itemView.linearLayout_article_item
    private val textViewInReadList: AppCompatTextView = itemView.textView_readList
    private lateinit var articleItemListener: ItemClickListener
    private lateinit var readListTextViewListener: ItemClickListener


    fun bindView(articleItem: Article): ArticleListItemViewHolder {

        textViewTitle.text = articleItem.title
        textViewTime.text = articleItem.publishedAt.orEmpty()
        if (articleItem.isInReadList) {
            textViewInReadList.text = "Okuma Listemden Çıkar"
        } else {
            textViewInReadList.text = "Okuma Listeme Ekle"
        }

        linearLayout.setOnClickListener { v ->
            articleItemListener.onItemClick(v, position)
        }
        textViewInReadList.setOnClickListener { v ->
            readListTextViewListener.onItemClick(v, position)
        }
        Picasso.get()
                .load(articleItem.urlToImage).error(R.drawable.ic_image_blue_24dp).fetch(object : Callback {
                    override fun onSuccess() {
                        imageViewTitle.alpha = 0f
                        Picasso.get()
                                .load(articleItem.urlToImage)
                                .error(R.drawable.ic_image_blue_24dp)
                                .placeholder(R.drawable.ic_image_blue_24dp)
                                .into(imageViewTitle)
                        imageViewTitle.animate()
                                .setDuration(300)
                                .alpha(1f)
                                .start()
                    }

                    override fun onError(e: Exception?) {
                    }
                })



        return this
    }

    fun setArticleItemListener(listener: ItemClickListener) {
        this.articleItemListener = listener
    }

    fun setReadListTextViewListener(listener: ItemClickListener) {
        this.readListTextViewListener = listener
    }

}

