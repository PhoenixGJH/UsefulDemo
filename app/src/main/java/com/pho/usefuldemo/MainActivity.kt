package com.pho.usefuldemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pho.usefuldemo.adapter.HomeContentAdapter
import com.pho.usefuldemo.bean.HomeContentBean
import com.zhangyue.we.x2c.X2C
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.activity_main.*

@Xml(layouts = ["activity_main"])
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HomeContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        X2C.setContentView(this, R.layout.activity_main)

        initRecyclerView()
        initContentData()
    }

    /**
     * 初始化列表
     */
    private fun initRecyclerView() {
        adapter = HomeContentAdapter().also {
            it.setOnItemClickListener { _, view, position ->
                val item = adapter.getItem(position)
                startActivity(Intent(this, item.clazz))
            }
        }
        rv_demo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    /**
     * 初始化页面数据
     */
    private fun initContentData() {
        val list = mutableListOf(
            HomeContentBean("EditText", EditTextActivity::class.java),
            HomeContentBean("Underline Click", UnderlineClickActivity::class.java)
        )
        adapter.setNewInstance(list)
    }
}