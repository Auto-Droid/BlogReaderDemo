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
import com.sourabhkarkal.blogreaderdemo.view.MainActivity
import com.sourabhkarkal.blogreaderdemo.view.adapter.BlogItemAdapter
import com.sourabhkarkal.blogreaderdemo.viewmodel.BlogFragmentViewModel
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : Fragment()  {

    private var blogViewModel: BlogFragmentViewModel? = null
    private var resultAppList = ArrayList<BlogResponseDTO>()
    private lateinit var adapter: BlogItemAdapter

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
        blogViewModel?.callAllApi(1, 10)

        adapter = BlogItemAdapter(resultAppList, activity as MainActivity);
        blogList.setHasFixedSize(true)
        blogList.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        blogList.adapter = adapter
    }

    private val observerPostLiveData = Observer<List<BlogResponseDTO>> { result ->
        resultAppList.addAll(result as ArrayList<BlogResponseDTO>)
        blogList.adapter?.notifyDataSetChanged()
    }


}