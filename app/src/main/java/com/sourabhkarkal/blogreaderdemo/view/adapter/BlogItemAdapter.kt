package com.sourabhkarkal.blogreaderdemo.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sourabhkarkal.blogreaderdemo.R
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.utils.Utils.Companion.formatDate
import com.sourabhkarkal.blogreaderdemo.utils.Utils.Companion.formatValue
import com.sourabhkarkal.blogreaderdemo.utils.OnLoadMoreListener


class BlogItemAdapter internal constructor(
    private var blogList: ArrayList<BlogResponseDTO?>,
    var activity: Activity, var recyclerView: RecyclerView
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var loading = false
    private val visibleThreshold = 2
    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var lastVisibleItem = 0
    private var totalItemCount = 0

    companion object {
        const val VIEW_ITEM: Int = 0;
        const val VIEW_PROGRESS: Int = 1;
    }

    init {
        if (recyclerView.layoutManager is LinearLayoutManager) {
            val linearLayoutManager = recyclerView
                .layoutManager as LinearLayoutManager?
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int, dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = linearLayoutManager!!.itemCount
                    lastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition()
                    if (!loading
                        && totalItemCount <= lastVisibleItem + visibleThreshold
                    ) {
                        onLoadMoreListener?.onLoadMore()
                        loading = true
                    }
                }
            })
        }
    }

    fun setLoaded() {
        loading = false
    }

    fun cancelLoading() {
        loading = true
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener?) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_blog_item, parent, false)
            BlogViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_progress, parent, false
            )
            ProgressViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BlogViewHolder) {
            val post = blogList[position]
            if (post != null && !post.user.isNullOrEmpty()) {
                //Username
                holder.tvUserName.text = "${post.user[0]?.name} ${post.user[0]?.lastname}"
                //Designation
                holder.tvDesignation.text = "${post.user[0]?.designation}"
                //Profile Pic
                Glide.with(activity).load("${post.user[0]?.avatar}")
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imgProfile);
            }

            if (post?.media != null && post.media.isNotEmpty()) {
                //Media Image
                if (!post.media[0]?.image.isNullOrBlank()) {
                    Glide.with(activity).load("${post.media[0]?.image}")
                        .into(holder.imgArticleImage);
                    holder.imgArticleImage.visibility = View.VISIBLE
                } else {
                    holder.imgArticleImage.visibility = View.GONE
                }

                //Title
                if (!post.media[0]?.title.isNullOrEmpty()) {
                    holder.tvTitle.text =
                        post.media[0]?.title
                    holder.tvTitle.visibility = View.VISIBLE
                }
                else holder.tvTitle.visibility = View.GONE

                //Url
                if (!post.media[0]?.url.isNullOrEmpty()) {
                    holder.tvUrl.text = post.media[0]?.url
                    holder.tvUrl.visibility = View.VISIBLE
                } else holder.tvUrl.visibility = View.GONE
            } else {
                holder.imgArticleImage.visibility = View.GONE
                holder.tvTitle.visibility = View.GONE
                holder.tvUrl.visibility = View.GONE
            }

            //Content
            if (!post?.content.isNullOrEmpty()) holder.tvContent.text = post?.content
            else holder.tvContent.visibility = View.GONE

            //Comments
            holder.tvComments.text = post?.comments?.toDouble()?.let { "${formatValue(it)} Comments" }
            //Likes
            holder.tvLikes.text = post?.likes?.toDouble()?.let { "${formatValue(it)} Likes" }

            //Date
            holder.tvCreatedTime.text = post?.createdAt?.let { formatDate(it) }

        } else (holder as ProgressViewHolder).progressBar.isIndeterminate = true

    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (blogList[position] != null) VIEW_ITEM else VIEW_PROGRESS
    }

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        var tvDesignation: TextView = itemView.findViewById(R.id.tvDesignation)
        var tvCreatedTime: TextView = itemView.findViewById(R.id.tvCreatedTime)
        var tvContent: TextView = itemView.findViewById(R.id.tvContent)
        var tvUrl: TextView = itemView.findViewById(R.id.tvUrl)
        var tvLikes: TextView = itemView.findViewById(R.id.tvLikes)
        var tvComments: TextView = itemView.findViewById(R.id.tvComments)
        var imgArticleImage: ImageView = itemView.findViewById(R.id.imgArticleImage)
        var imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)

    }

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}
