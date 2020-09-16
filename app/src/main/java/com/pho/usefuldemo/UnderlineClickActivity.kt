package com.pho.usefuldemo

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_target.*
import kotlinx.android.synthetic.main.activity_underline_click.*
import me.saket.bettermovementmethod.BetterLinkMovementMethod

/**
 * 带有下划线点击事件的TextView（包含TextView的子类，如Button、CheckBox等）
 * 参考文章：https://stackoverflow.com/questions/8184597/how-do-i-make-a-portion-of-a-checkboxs-text-clickable
 */
class UnderlineClickActivity : AppCompatActivity(R.layout.activity_underline_click) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        one()
        two()
        three()
    }

    /**
     * 直接跳转网页
     * 或者通过IntentFilter跳转指定Activity
     */
    private fun one() {
        val str = "I agree to all the <a href='https://www.baid.com/'>Terms and Conditons</a>"
        val strFilter = "I agree to all the <a href='phoenix://useful/demo'>Agreement</a>"
        tv_one.apply {
            text = Html.fromHtml(strFilter)
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun two() {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Prevent CheckBox state from being toggled when link is clicked
                widget.cancelPendingInputEvents()

                Toast.makeText(this@UnderlineClickActivity, "show", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                // Show links with underlines (optional)
                ds.isUnderlineText = true
            }
        }

        // 注意 template 中的 "^1"
        val agreement =
            TextUtils.expandTemplate(
                "是否同意协议^1",
                SpannableString("《XXX协议内容》").also {
                    it.setSpan(
                        clickableSpan,
                        0,
                        it.length,
                        SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                })
        tv_two.apply {
            text = agreement
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    /**
     * 通过三方库跳转
     * 地址：https://github.com/saket/Better-Link-Movement-Method
     */
    private fun three() {
        BetterLinkMovementMethod.linkifyHtml(tv_three)
            .setOnLinkClickListener { _, url ->
                Toast.makeText(this@UnderlineClickActivity, url, Toast.LENGTH_SHORT).show()
                true
            }
    }

    class TargetActivity : AppCompatActivity(R.layout.activity_target) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            tv_intent?.text = intent?.data?.toString()
        }
    }
}