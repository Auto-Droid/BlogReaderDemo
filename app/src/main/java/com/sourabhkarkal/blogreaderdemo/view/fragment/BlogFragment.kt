package com.sourabhkarkal.blogreaderdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sourabhkarkal.blogreaderdemo.R
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.utils.OnLoadMoreListener
import com.sourabhkarkal.blogreaderdemo.view.MainActivity
import com.sourabhkarkal.blogreaderdemo.view.adapter.BlogItemAdapter
import com.sourabhkarkal.blogreaderdemo.viewmodel.BlogFragmentViewModel
import kotlinx.android.synthetic.main.fragment_blog.*


class BlogFragment : Fragment() {

    private var blogViewModel: BlogFragmentViewModel? = null
    private var resultAppList = ArrayList<BlogResponseDTO?>()
    private lateinit var adapter: BlogItemAdapter
    private var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        blogViewModel = ViewModelProviders.of(this).get(BlogFragmentViewModel::class.java)
        blogViewModel?.getDataResponse()?.observe(this, observerPostLiveData)
        blogViewModel?.getNetworkStatusResponse()?.observe(this, observerNetworkStatusLiveData)
        blogViewModel?.callAllApi(page, limit)

        blogList.setHasFixedSize(true)
        blogList.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        adapter = BlogItemAdapter(resultAppList, activity as MainActivity, blogList);
        blogList.adapter = adapter

        (blogList.adapter as BlogItemAdapter).setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                resultAppList.add(null)
                (blogList.adapter as BlogItemAdapter).notifyItemInserted(resultAppList.size - 1)
                page++;
                blogViewModel?.callAllApi(page, limit)
            }
        })
    }

    private val observerPostLiveData = Observer<List<BlogResponseDTO>> { result ->
        if (page > 1 && resultAppList.isNotEmpty()) {
            resultAppList.removeAt(resultAppList.size - 1)
            blogList.adapter?.notifyItemRemoved(resultAppList.size)
        }
        if (result.isNotEmpty()) {
            resultAppList.addAll(result as ArrayList<BlogResponseDTO>)
            blogList.adapter?.notifyItemInserted(resultAppList.size - 1)
        }
        (blogList.adapter as BlogItemAdapter).setLoaded()
    }

    private val observerNetworkStatusLiveData = Observer<Boolean> {
        if(it) {
            (blogList.adapter as BlogItemAdapter).cancelLoading()
        }
    }

    companion object {
        const val limit: Int = 10
    }

}