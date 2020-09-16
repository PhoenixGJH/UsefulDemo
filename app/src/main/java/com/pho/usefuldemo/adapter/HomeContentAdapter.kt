package com.pho.usefuldemo.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pho.usefuldemo.R
import com.pho.usefuldemo.bean.HomeContentBean

/**
 * 首页列表适配器
 */
class HomeContentAdapter :
    BaseQuickAdapter<HomeContentBean, BaseViewHolder>(R.layout.item_home_content) {
    override fun convert(holder: BaseViewHolder, item: HomeContentBean) {
        holder.setText(R.id.tv_content, item.name)
    }
}