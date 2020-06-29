package com.sourabhkarkal.blogreaderdemo.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sourabhkarkal.blogreaderdemo.R
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO

class BlogItemAdapter internal constructor(
    private var blogList: ArrayList<BlogResponseDTO>,
    var activity: Activity
) :
    RecyclerView.Adapter<BlogItemAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_blog_item, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: BlogViewHolder, position: Int) {
        val post = blogList[position]
        if (!post.user.isNullOrEmpty()) {
            //Username
            viewHolder.tvUserName.text = "${post.user[0]?.name} ${post.user[0]?.lastname}"
            //Designation
            viewHolder.tvDesignation.text = "${post.user[0]?.designation}"
            //Profile Pic
            Glide.with(activity).load("${post.user[0]?.avatar}").placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imgProfile);
        }

        if (!post.media.isNullOrEmpty()) {
            //Media Image
            if (!post.media[0]?.image.isNullOrBlank()) {
                Glide.with(activity).load("${post.media[0]?.image}")
                    .into(viewHolder.imgArticleImage);
            } else {
                viewHolder.imgProfile.visibility = View.GONE
            }

            //Title
            if (!post.media[0]?.title.isNullOrEmpty()) viewHolder.tvTitle.text = post.media[0]?.title
            else viewHolder.tvTitle.visibility = View.GONE

            //Url
            if (!post.media[0]?.url.isNullOrEmpty()) viewHolder.tvUrl.text = post.media[0]?.url
            else viewHolder.tvUrl.visibility = View.GONE
        }

        //Content
        if (!post.content.isNullOrEmpty()) viewHolder.tvContent.text = post.content
        else viewHolder.tvContent.visibility = View.GONE

        //Comments
        viewHolder.tvComments.text = post.comments.toString()
        //Likes
        viewHolder.tvLikes.text = post.likes.toString()

    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvUserName: TextView
        var tvDesignation: TextView
        var tvCreatedTime: TextView
        var tvContent: TextView
        var tvUrl: TextView
        var tvLikes: TextView
        var tvComments: TextView
        var imgArticleImage: ImageView
        var imgProfile: ImageView

        init {
            this.tvTitle = itemView.findViewById(R.id.tvTitle)
            this.tvUserName = itemView.findViewById(R.id.tvUserName)
            this.tvDesignation = itemView.findViewById(R.id.tvDesignation)
            this.tvCreatedTime = itemView.findViewById(R.id.tvCreatedTime)
            this.tvContent = itemView.findViewById(R.id.tvContent)
            this.tvUrl = itemView.findViewById(R.id.tvUrl)
            this.tvLikes = itemView.findViewById(R.id.tvLikes)
            this.tvComments = itemView.findViewById(R.id.tvComments)
            this.imgProfile = itemView.findViewById(R.id.imgProfile)
            this.imgArticleImage = itemView.findViewById(R.id.imgArticleImage)
        }
    }
}
