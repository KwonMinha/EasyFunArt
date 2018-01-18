package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Adapter.RecommendAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.GET.RecoData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.RecoResponse
import kotlinx.android.synthetic.main.activity_recommend.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
//        when (v) {
//            tabbar_home -> {
//                startActivity(Intent(applicationContext, HomeActivity::class.java))
//            }
//            tabbar_recommend -> {
//                //startActivity(Intent(applicationContext, RecommendActivity::class.java))
//            }
//            tabbar_docent -> {
//                //startActivity(Intent(applicationContext, HomeActivity::class.java))
//            }
//            tabbar_search -> {
//                startActivity(Intent(applicationContext, SearchIntroActivity::class.java))
//            }
//            tabbar_mypage -> {
//                //startActivity(Intent(applicationContext, HomeActivity::class.java))
//            }
//        }
    }

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var recoAdapter: RecommendAdapter? = null
    private var recoList: ArrayList<RecoData>? = null

    private var TAG = "LOG::Recommend"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        // 탭 바 리스너
        tabbar_home_reco.setOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
        tabbar_recommend_reco.setOnClickListener {
            //startActivity(Intent(applicationContext, RecommendActivity::class.java))
//            finish()
        }
        tabbar_docent_reco.setOnClickListener {
            startActivity(Intent(applicationContext, GalleryListActivity::class.java))
            finish()
        }
        tabbar_search_reco.setOnClickListener {
            startActivity(Intent(applicationContext, SearchIntroActivity::class.java))
            finish()
        }
        tabbar_mypage_reco.setOnClickListener {
            startActivity(Intent(applicationContext, MypageActivity::class.java))
            finish()
        }

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)
        recomend_recyclerView!!.layoutManager = LinearLayoutManager(this)

        getRecoList()
    }

    fun getRecoList() {
        val getRecoList = networkService!!.getRecoList(CommonData.user_token!!)
        getRecoList.enqueue(object : Callback<RecoResponse> {
            override fun onResponse(call: Call<RecoResponse>?, response: Response<RecoResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        recoAdapter = RecommendAdapter(this@RecommendActivity, response.body().data, requestManager!!)
                        recoList = response.body().data
                        recoAdapter!!.setOnItemClickListener(this@RecommendActivity)
                        recomend_recyclerView.adapter = recoAdapter
                    }
                }else {
                    Log.d(TAG,"실패")
                }
            }
            override fun onFailure(call: Call<RecoResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }


//    override fun onFailure(call: Call<RecoResponse>?, t: Throwable?) {
//        ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
//        Log.d(TAG,"통신실패")
//    }
//    override fun onResponse(call: Call<RecoResponse>?, response: Response<RecoResponse>?) {
//        if (response!!.isSuccessful){
//            Log.d(TAG,"성공1")
//            if(response.body().status.equals("success")){
//                Log.d(TAG,"성공2")
//                recoAdapter = RecommendAdapter(this@RecommendActivity, response.body().data, requestManager!!)
//                recoList = response.body().data
//                recoAdapter!!.setOnItemClickListener(this@RecommendActivity)
//                recomend_recyclerView.adapter = recoAdapter
//            }
//        }else{
//            Log.d(TAG,"실패")
//        }
//    }


//    fun getHomeList(){
//        val getHomeList = networkService!!.getHomeList(CommonData.user_token!!)
//
//        getHomeList.enqueue(object : Callback<HomeResponse>{
//
//            override fun onResponse(call: Call<HomeResponse>?, response: Response<HomeResponse>?) {
//                if (response!!.isSuccessful){
//                    Log.d(TAG,"성공")
//                    if(response.body().status.equals("succes")){
//                        Log.d(TAG,"성공")
//                        firstAdapter = HomeFirstAdapter(this@HomeActivity, response.body().data.topData, requestManager!!)
//                        firstList = response.body().data.topData
//                        firstAdapter!!.setOnItemClickListener(this@HomeActivity)
//                        main_recyclerView.adapter = firstAdapter
//
//                        main2_textView.text = response.body().data.bottomResult.theme1.get(0).theme_title
//                        home2FirstAdapter = Home2FirstAdapter(this@HomeActivity, response.body().data.bottomResult.theme1, requestManager!!)
//                        home2FirstList = response.body().data.bottomResult.theme1
//                        home2FirstAdapter!!.setOnItemClickListener(this@HomeActivity)
//                        main2_recyclerView1.adapter = home2FirstAdapter
//
//                        main2_textView2.text = response.body().data.bottomResult.theme2.get(0).theme_title
//                        home2SecondAdapter = Home2SecondAdapter(this@HomeActivity, response.body().data.bottomResult.theme2, requestManager!!)
//                        home2SecondList = response.body().data.bottomResult.theme2
//                        home2SecondAdapter!!.setOnItemClickListener(this@HomeActivity)
//                        main2_recyclerView2.adapter = home2SecondAdapter
//
//                        main2_textView3.text = response.body().data.bottomResult.theme3.get(0).theme_title
//                        home2ThirdAdapter = Home2ThirdAdapter(this@HomeActivity, response.body().data.bottomResult.theme3, requestManager!!)
//                        home2ThirdList = response.body().data.bottomResult.theme3
//                        home2ThirdAdapter!!.setOnItemClickListener(this@HomeActivity)
//                        main2_recyclerView3.adapter = home2ThirdAdapter
//                    }
//                }else{
//                    Log.d(TAG,"실패")
//                }
//            }
//            override fun onFailure(call: Call<HomeResponse>?, t: Throwable?) {
//                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
//                Log.d(TAG,"통신실패")
//            }
//        })
//    }
}
