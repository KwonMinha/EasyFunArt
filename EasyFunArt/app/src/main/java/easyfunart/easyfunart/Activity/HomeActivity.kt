package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Adapter.Home2FirstAdapter
import easyfunart.easyfunart.Adapter.Home2SecondAdapter
import easyfunart.easyfunart.Adapter.Home2ThirdAdapter
import easyfunart.easyfunart.Adapter.HomeFirstAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.GET.HomeTheme1
import easyfunart.easyfunart.GET.HomeTheme2
import easyfunart.easyfunart.GET.HomeTheme3
import easyfunart.easyfunart.GET.HomeTopData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.HomeResponse
import easyfunart.easyfunart.Response.SerialResponse
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
//        val idx : Int = pokemonList!!.getChildAdapterPosition(p0)
//        val name : String? = pokemonDatas!!.get(idx).pokemonType
//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var firstAdapter: HomeFirstAdapter? = null
    private var firstList: ArrayList<HomeTopData>? = null

//    private var secondAdapter : HomeRecyclerAdpater? = null
//    private var secondList : HomeBottomResult? = null

    private var home2FirstAdapter: Home2FirstAdapter? = null
    private var home2SecondAdapter: Home2SecondAdapter? = null
    private var home2ThirdAdapter: Home2ThirdAdapter? = null

    private var home2FirstList: ArrayList<HomeTheme1>? = null
    private var home2SecondList: ArrayList<HomeTheme2>? = null
    private var home2ThirdList: ArrayList<HomeTheme3>? = null

    private var TAG = "LOG::Main"

    var getText : String ? = null

    // 이건 리사이클러뷰랑 관련 X
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 검색 에딧텍스트 enter -> search / 엔터이벤트
        main_editText.imeOptions = EditorInfo.IME_ACTION_SEARCH // 안먹힘
        main_editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                 getText = main_editText.text.toString()

                getSerialList(getText!!)
                Log.d("입력1",getText.toString())

                true
            } else {
                false
            }
        }
        // 검색 에딧택스트 클릭 시 힌트 사라지게
        main_editText.setOnClickListener {
            main_editText.setHint("")
        }

        tabbar_search_home.setOnClickListener {
            startActivity(Intent(applicationContext, SearchIntroActivity::class.java))

        }

        tabbar_recommend_home.setOnClickListener {
            startActivity(Intent(applicationContext, RecommendActivity::class.java))
            //finish()
        }

        tabbar_mypage_home.setOnClickListener {
            startActivity(Intent(applicationContext, WriteTestActivity::class.java))
            //
            // \///finish()
        }

        tabbar_docent_home.setOnClickListener {
            startActivity(Intent(applicationContext, GalleryListActivity::class.java))
            //finish()
        }

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)
        main_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        main2_recyclerView.layoutManager = LinearLayoutManager(this)
        main2_recyclerView1!!.layoutManager = LinearLayoutManager(this)
        main2_recyclerView2.layoutManager = LinearLayoutManager(this)
        main2_recyclerView3!!.layoutManager = LinearLayoutManager(this)
        getHomeList()
    }

    fun getSerialList(getText: String) {

        val getSerialList = networkService!!.getSerialList(getText)
        getSerialList.enqueue(object : Callback<SerialResponse> {
            override fun onResponse(call: Call<SerialResponse>?, response: Response<SerialResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공")
                    if (response.body().status.equals("success")) {

                        Log.d("입력1.5",getText)
                        Log.d(TAG, "성공")

                        val intent = Intent(this@HomeActivity, TrackActivity::class.java)
                        intent.putExtra("exId", response.body().data.ex_id)
                        intent.putExtra("exImage",response.body().data.ex_image)
                        intent.putExtra("exTitle", response.body().data.ex_title)
                        Log.d("입력2", response.body().data.ex_id.toString())

                        startActivity(intent)


//
//                        var builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
//                        var inflater: LayoutInflater = LayoutInflater.from(this@HomeActivity)
//                        var view: View = inflater.inflate(R.layout.home2_docent_dialog, null)
//                        builder.setView(view)
//                        var dialog: Dialog = builder.create()
//                        view.dialog_docent_title.setText(response.body().data.ex_title)
//                        if (response.body().data.ex_image == null)
//                            requestManager!!.load(R.drawable.img_poster_default).into(view.dialog_docent_image)
//                        else
//                            requestManager!!.load(response.body().data.ex_image).into(view.dialog_docent_image)
//                        view.dialog_docent_place.setText("")
//                        dialog.show()
//                        // dialog 크기변환
//                        var dm: DisplayMetrics = view.resources.displayMetrics
//                        var px_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240.0f, dm) // float형
//                        var px_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 342.0f, dm)
//
//                        val px_width_int = px_width.toInt() // int형으로 변환
//                        val px_height_int = px_height.toInt()
//                        dialog!!.window.setLayout(px_width_int, px_height_int)
//
//                        dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                        //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                        // 팝업창 이외 터치 / 백 버튼 금지
//                        dialog.setCanceledOnTouchOutside(false)
//                        dialog.setCancelable(false)
//
//                        val animation = AnimationUtils.loadAnimation(this@HomeActivity, R.anim.scale) // 안될 땐 clean project
//                        view.startAnimation(animation)
//
//                        view.dialog_docent_xButton.setOnClickListener {
//                            dialog.dismiss()
//                        }
//
//                        view.dialog_docent_ok.setOnClickListener {
//                            val intent = Intent(this@HomeActivity, TrackActivity::class.java)
//                            intent.putExtra("exId", response.body().data.ex_id)
//                            intent.putExtra("exImage",response.body().data.ex_image)
//                            intent.putExtra("exTitle", response.body().data.ex_title)
//                            finish()
//                        }
                    }
                    else{
                        Toast.makeText(applicationContext, "일치하는 일련번호가 없습니다", Toast.LENGTH_SHORT).show()
                        //
//                        var builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
//                        var inflater: LayoutInflater = LayoutInflater.from(this@HomeActivity)
//                        var view: View = inflater.inflate(R.layout.docent_no_search, null)
//                        builder.setView(view)
//                        var dialog: Dialog = builder.create()
//
//                        dialog.show()
//                        //            // dialog 크기변환
//                        var dm: DisplayMetrics = view.resources.displayMetrics
//                        var px_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600.0f, dm) // float형
//                        var px_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600.0f, dm)
//
//                        val px_width_int = px_width.toInt() // int형으로 변환
//                        val px_height_int = px_height.toInt()
//                        dialog!!.window.setLayout(px_width_int, px_height_int)
//
//                        dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                        //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                        // 팝업창 이외 터치 / 백 버튼 금지
//                        dialog.setCanceledOnTouchOutside(false)
//                        dialog.setCancelable(false)
//                        // 좋아요 팝업 애니메이션 효과
//                        val animation = AnimationUtils.loadAnimation(this@HomeActivity, R.anim.scale) // 안될 땐 clean project
//                        view.startAnimation(animation)
//                        // 좋아요 팝업 애니메이션 지속 시간
//                        var handler = Handler()
//                        handler.postDelayed({
//                            dialog.dismiss()
//                        }, 1000)

                    }
                }else {
                        Log.d(TAG, "실패")
                    }
                }
                override fun onFailure(call: Call<SerialResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                    Log.d(TAG, "통신실패")
                }

        })
    }
    fun getHomeList (){
            val getHomeList = networkService!!.getHomeList(CommonData.user_token!!)

            getHomeList.enqueue(object : Callback<HomeResponse> {

                override fun onResponse(call: Call<HomeResponse>?, response: Response<HomeResponse>?) {
                    if (response!!.isSuccessful) {
                        Log.d(TAG, "성공")
                        if (response.body().status.equals("succes")) {
                            Log.d(TAG, "성공")
                            firstAdapter = HomeFirstAdapter(this@HomeActivity, response.body().data.topData, requestManager!!)
                            firstList = response.body().data.topData
                            firstAdapter!!.setOnItemClickListener(this@HomeActivity)
                            main_recyclerView.adapter = firstAdapter

                            main2_textView.text = response.body().data.bottomResult.theme1.get(0).theme_title
                            home2FirstAdapter = Home2FirstAdapter(this@HomeActivity, response.body().data.bottomResult.theme1, requestManager!!)
                            home2FirstList = response.body().data.bottomResult.theme1
                            home2FirstAdapter!!.setOnItemClickListener(this@HomeActivity)
                            main2_recyclerView1.adapter = home2FirstAdapter

                            main2_textView2.text = response.body().data.bottomResult.theme2.get(0).theme_title
                            home2SecondAdapter = Home2SecondAdapter(this@HomeActivity, response.body().data.bottomResult.theme2, requestManager!!)
                            home2SecondList = response.body().data.bottomResult.theme2
                            home2SecondAdapter!!.setOnItemClickListener(this@HomeActivity)
                            main2_recyclerView2.adapter = home2SecondAdapter

                            main2_textView3.text = response.body().data.bottomResult.theme3.get(0).theme_title
                            home2ThirdAdapter = Home2ThirdAdapter(this@HomeActivity, response.body().data.bottomResult.theme3, requestManager!!)
                            home2ThirdList = response.body().data.bottomResult.theme3
                            home2ThirdAdapter!!.setOnItemClickListener(this@HomeActivity)
                            main2_recyclerView3.adapter = home2ThirdAdapter
                        }
                    } else {
                        Log.d(TAG, "실패")
                    }
                }

                override fun onFailure(call: Call<HomeResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                    Log.d(TAG, "통신실패")
                }
            })
        }

    ;
}
