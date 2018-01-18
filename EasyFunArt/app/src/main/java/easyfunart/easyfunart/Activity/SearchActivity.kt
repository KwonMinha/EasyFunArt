package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Adapter.SearchAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.GET.SearchData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.SearchResponse
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    private var requestManager: RequestManager? = null
    private var networkService: NetworkService? = null
    private var TAG: String = "LOG::Search"

    private var searchAdapter : SearchAdapter? = null
    private var searchList : ArrayList<SearchData>? = null
    private var searchRecyclerView : RecyclerView?= null

    private var isButtonNum : Int = 1
    private var isSpinnerNum : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // 탭 바 리스너
        tabbar_home_search.setOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
        tabbar_recommend_search.setOnClickListener {
            startActivity(Intent(applicationContext, RecommendActivity::class.java))
            finish()
        }
        tabbar_docent_search.setOnClickListener {
            startActivity(Intent(applicationContext, GalleryListActivity::class.java))
            finish()
        }
        tabbar_search_search.setOnClickListener {
//            startActivity(Intent(applicationContext, SearchIntroActivity::class.java))
//            finish()
        }
        tabbar_mypage_search.setOnClickListener {
            startActivity(Intent(applicationContext, MypageActivity::class.java))
            finish()
        }

        // 스피너 설정
        val adapter = ArrayAdapter.createFromResource(this, R.array.spinner, R.layout.search_spinner_title)
        adapter.setDropDownViewResource(R.layout.search_spinner_item)
        search_spinner.adapter = adapter
       // isSpinnerNum = search_spinner.selectedItemPosition

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val search_text : String = getIntent().getStringExtra("search_text")
        Log.d(TAG, search_text)
        search_editText.setText(search_text)

        searchRecyclerView = findViewById(R.id.search_recyclerView) as RecyclerView
        searchRecyclerView!!.layoutManager = LinearLayoutManager(this)

        // 가장 먼저 검색되었을 때
        getFisrtSearchList(search_text)

        // 돋보기 검색
        search_search_btn.setOnClickListener {
            getFisrtSearchList(search_text)
        }

        // 최신순
        if (search_spinner.selectedItemPosition == 0) {
            // 준비중
            search_preparingBtn.setOnClickListener {
                search_preparingBtn.setBackgroundResource(R.drawable.search_prepare_on)
                search_proceedingBtn.setBackgroundResource(R.drawable.search_ing_off)
                search_completeBtn.setBackgroundResource(R.drawable.search_finish_off)

                getLastedPrepareList(search_text)
            }
            // 완료중
            search_completeBtn.setOnClickListener {
                search_preparingBtn.setBackgroundResource(R.drawable.search_prepare_off)
                search_proceedingBtn.setBackgroundResource(R.drawable.search_ing_off)
                search_completeBtn.setBackgroundResource(R.drawable.search_finish_on)

                getLastedCompleteList(search_text)
            }
            // 진행중
            search_proceedingBtn.setOnClickListener {
                search_preparingBtn.setBackgroundResource(R.drawable.search_prepare_off)
                search_proceedingBtn.setBackgroundResource(R.drawable.search_ing_on)
                search_completeBtn.setBackgroundResource(R.drawable.search_finish_off)

                getLastedProceedList(search_text)
            }
        }
        // 별점순
        else if (search_spinner.selectedItemPosition == 1) {
            // 준비중
            search_preparingBtn.setOnClickListener {
                search_preparingBtn.setBackgroundResource(R.drawable.search_prepare_on)
                search_proceedingBtn.setBackgroundResource(R.drawable.search_ing_off)
                search_completeBtn.setBackgroundResource(R.drawable.search_finish_off)

                getStarPrepareList(search_text)
            }
            // 완료중
            search_completeBtn.setOnClickListener {
                search_preparingBtn.setBackgroundResource(R.drawable.search_prepare_off)
                search_proceedingBtn.setBackgroundResource(R.drawable.search_ing_off)
                search_completeBtn.setBackgroundResource(R.drawable.search_finish_on)

                getStarCompleteList(search_text)
            }
            // 진행중
            search_proceedingBtn.setOnClickListener {
                search_preparingBtn.setBackgroundResource(R.drawable.search_prepare_off)
                search_proceedingBtn.setBackgroundResource(R.drawable.search_ing_on)
                search_completeBtn.setBackgroundResource(R.drawable.search_finish_off)

                getStarProceedingList(search_text)
            }
        }

    }

    // 첫 번째로 보일 뷰 - 진행중인 전시 최신순으로
    fun getFisrtSearchList(search_text : String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!,  search_text, 1, 0)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }

    fun getLastedPrepareList(search_text : String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!, search_text, 2, 0)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }

    fun getLastedCompleteList(search_text: String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!, search_text, 0, 0)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }

    fun getLastedProceedList(search_text: String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!, search_text, 1, 0)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }

    fun getStarPrepareList(search_text: String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!, search_text, 2, 1)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }

    fun getStarCompleteList(search_text: String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!, search_text, 0, 1)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }

    fun getStarProceedingList(search_text: String) {
        val getSearchList = networkService!!.getSearchList(CommonData.user_token!!, search_text, 2, 1)
        getSearchList.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(TAG, "성공일까 실패일까")
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")
                        searchAdapter = SearchAdapter(this@SearchActivity, response.body().data.searchData, requestManager!!)
                        searchList = response.body().data.searchData
                        searchAdapter!!.setOnItemClickListener(this@SearchActivity)
                        searchRecyclerView!!.adapter = searchAdapter

                        if (searchList!!.size == 0) {
                            search_no.visibility = View.VISIBLE
                        }
                        else {
                            search_no.visibility = View.GONE
                        }
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }
        })
    }
}
