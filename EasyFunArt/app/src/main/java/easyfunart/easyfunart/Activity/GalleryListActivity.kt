package easyfunart.easyfunart.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import easyfunart.easyfunart.Adapter.TabAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.GET.GalleryData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import kotlinx.android.synthetic.main.activity_gallery_list.*


class GalleryListActivity : AppCompatActivity() {

    private var networkService: NetworkService? = null
    private var galleryList : ArrayList<GalleryData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_list)

        // 탭 바 리스너
        tabbar_home_g.setOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
        tabbar_recommend_g.setOnClickListener {
            startActivity(Intent(applicationContext, RecommendActivity::class.java))
            finish()
        }
        tabbar_docent_g.setOnClickListener {

        }
        tabbar_search_g.setOnClickListener {
            startActivity(Intent(applicationContext, SearchIntroActivity::class.java))
            finish()
        }
        tabbar_mypage_g.setOnClickListener {
//            startActivity(Intent(applicationContext, MypageActivity::class.java))
//            finish()
        }

        networkService = ApplicationController.instance!!.networkService

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this@GalleryListActivity, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }

//        main2_tab.addTab(main2_tab.newTab().setText("내 주변 전시"))
//        main2_tab.addTab(main2_tab.newTab().setText("찜한 전시"))

        var tabAdapter = TabAdapter(supportFragmentManager, main2_tab.tabCount)
        main2_viewPager.adapter = tabAdapter

        main2_tab.setTabTextColors(Color.BLACK,Color.BLACK)//탭색깔
        main2_tab.setSelectedTabIndicatorColor(Color.parseColor("#fcdc09"))//인디케이터 색깔.

        main2_viewPager.currentItem = 0
        main2_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main2_tab))
        main2_tab.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                main2_viewPager.currentItem = tab!!.position
                Log.v("currnet", tab!!.position.toString())
            }
        })

    }

}