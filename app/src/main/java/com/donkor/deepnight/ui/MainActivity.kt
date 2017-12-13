package com.donkor.deepnight.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.donkor.deepnight.R
import com.donkor.deepnight.ui.fragment.AllFragment
import com.donkor.deepnight.ui.fragment.BosomFragment
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*


/**
 * 主页面
 */
private val TAG: String? = "MainActivity"

class MainActivity : AppCompatActivity() {

    //    val mTabs = arrayListOf<String>("aaa", "bbb", "ccc")
//    lateinit var mFragments: ArrayList<Fragment>
    var allFragment: AllFragment? = null
    var bosomFragment: BosomFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar().barAlpha(0.6f).fitsSystemWindows(true).init()
        val window = window
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params

        /*设置ActionBar*/
        setActionBar()

        /*设置Drawerlayout开关*/
        setDrawerToggle()

        /*设置监听器*/
        setListener()

        /*设置ToolBar标题*/
        initToolBar()

        initFragment(savedInstanceState)

    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is AllFragment) {
                    allFragment = item
                }
                if (item is BosomFragment) {
                    bosomFragment = item
                }
            }
        } else {
            allFragment = AllFragment()
            bosomFragment = BosomFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, allFragment)
            fragmentTrans.add(R.id.fl_content, bosomFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction()
                .show(allFragment)
                .hide(bosomFragment)
                .commit()
    }

    /*设置ActionBar*/
    private fun setActionBar() {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        /*显示Home图标*/
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    /*设置Drawerlayout的开关,并且和Home图标联动*/
    private fun setDrawerToggle() {
        val mToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(mToggle)
        /*同步drawerlayout的状态*/
        mToggle.syncState()
    }

    /*设置监听器*/
    private fun setListener() {
        nav_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item_all -> {
                }
                R.id.nav_item_bosom -> {
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    /*设置ToolBar标题*/
    private fun initToolBar() {
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_bar_title.text = resources.getString(R.string.main_activity_item_all)
    }
}

//    private class MyHandler(activity: SplashActivity) : Handler() {
//        private val mActivity: WeakReference<SplashActivity> = WeakReference(activity)
//
//        override fun handleMessage(msg: Message) {
//            if (mActivity.get() == null) {
//                return
//            }
//            when (msg.what) {
//                SWITCH_MAINACTIVITY -> {
//                    Log.e(TAG, "SplashActivity --- switch_MainActivity")
//                }
//                else -> {
//                }
//            }
//        }
//    }

//    override fun onClick(v: View?) {
//        clearState()
//        when (v?.id) {
//            R.id.rb_home -> {
//                rb_home.isChecked = true
//                rb_home.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
//            }
//            R.id.rb_find -> {
//                rb_find.isChecked = true
//                rb_find.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
//            }
//            R.id.rb_hot -> {
//                rb_hot.isChecked = true
//                rb_hot.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
//            }
//            R.id.rb_mine -> {
//                rb_mine.isChecked = true
//                rb_mine.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
//            }
//        }
//    }
//}