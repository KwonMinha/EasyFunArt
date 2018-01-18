package easyfunart.easyfunart.Activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Adapter.DisplayTabAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Fragment.DisplayFragment
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.POST.StarPost
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.StarPostResponse
import easyfunart.easyfunart.Response.StarResponse
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.content_display_info.*
import kotlinx.android.synthetic.main.home2_heart_dialog.view.*
import kotlinx.android.synthetic.main.score.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayActivity : AppCompatActivity() {
    private var requestManager: RequestManager? = null
    private var networkService: NetworkService? = null
    private var TAG: String = "LOG::Display"
    private var fragment1: DisplayFragment? = null

    var isLike: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        // setContentView(R.layout.content_display_info)

        if (savedInstanceState == null) {                   //savedInstanceState 동일한 액티비티가 재실행 될 때 저장된 값이 있는지 판단!
            //물론 이 예제에서 다루지 않을것이지만 다룬다 하여도 최초실행시에는 저장된 것이 없겠죠??
            val bundle = Bundle()
            // bundle.putString("title", main2_first_btn!!.text.toString())
        }

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)
        if (getIntent().getStringExtra("ex_image") == null)
            requestManager!!.load(R.drawable.img_poster_default).into(display_imageView)
        else
            requestManager!!.load(getIntent().getStringExtra("ex_image")).into(display_imageView)

        val ex_image = getIntent().getStringExtra("ex_image")
        val ex_id: Int = getIntent().getIntExtra("ex_id", 100)
        val ex_title: String = getIntent().getStringExtra("ex_title")
        val gallery_name: String = getIntent().getStringExtra("gallery_name")
        val gallery_id: Int = getIntent().getIntExtra("gallery_id", 100)

//        scrollView_display.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if (event!!.action === MotionEvent.ACTION_UP)
//                    display_viewPager.requestDisallowInterceptTouchEvent(false)
//                else
//                    display_viewPager.requestDisallowInterceptTouchEvent(true)
//                return false
//            }
//        })

        initView()

        display_tab.addTab(display_tab.newTab().setText("전시정보"))
        display_tab.addTab(display_tab.newTab().setText("갤러리정보"))
        display_tab.addTab(display_tab.newTab().setText("리뷰"))

        getStarList(ex_id)

        display_tab.setSelectedTabIndicatorColor(Color.parseColor("#FCDC09"))

        display_back_button.setOnClickListener { finish() }

        // 별점 평가 팝업
        display_star_button.setOnClickListener {
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            var inflater: LayoutInflater = LayoutInflater.from(this)
            var view: View = inflater.inflate(R.layout.score, null)
            builder.setView(view)
            var dialog: Dialog = builder.create()
            view.dialog_evaluation_title.setText(getIntent().getStringExtra("ex_title"))

            var score: String = my_score.text.toString()
            var score_float: Float = score.toFloat()

            Log.d(TAG, score_float.toString())


            if (score_float.equals(0.0f))
                view.ratingbar.rating = 0.0f
            else if (score_float > 0.0f && score_float < 1.0f)
                view.ratingbar.rating = 0.5f
            else if (score_float.equals(1.0f))
                view.ratingbar.rating = 1.0f
            else if (score_float > 1.0f && score_float < 2.0f)
                view.ratingbar.rating = 1.5f
            else if (score_float.equals(2.0f))
                view.ratingbar.rating = 2.0f
            else if (score_float > 2.0f && score_float < 3.0f)
                view.ratingbar.rating = 2.5f
            else if (score_float.equals(3.0f))
                view.ratingbar.rating = 3.0f
            else if (score_float > 3.0f && score_float < 4.0f)
                view.ratingbar.rating = 3.5f
            else if (score_float.equals(4.0f))
                view.ratingbar.rating = 4.0f
            else if (score_float > 4.0f && score_float < 5.0f)
                view.ratingbar.rating = 5.5f
            else if (score_float.equals(5.0f))
                view.ratingbar.rating = 5.0f

            dialog.show()

            // dialog 크기변환
            var dm: DisplayMetrics = view.resources.displayMetrics
            var px_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250.0f, dm) // float형
            var px_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 220.0f, dm)

            val px_width_int = px_width.toInt() // int형으로 변환
            val px_height_int = px_height.toInt()
            dialog!!.window.setLayout(px_width_int, px_height_int)

            dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            // 팝업창 이외 터치 / 백 버튼 금지
            dialog.setCanceledOnTouchOutside(false)
            //dialog.setCancelable(false)

            val animation = AnimationUtils.loadAnimation(this, R.anim.scale) // 안될 땐 clean project
            view.startAnimation(animation)

            view.dialog_evaluation_ok.setOnClickListener {
                var ratingValue: Float? = null
                ratingValue = view.ratingbar.rating

                var postStar = networkService!!.postStar(CommonData.user_token!!, ex_id, StarPost(ratingValue))
                postStar.enqueue(object : Callback<StarPostResponse> {
                    override fun onFailure(call: Call<StarPostResponse>?, t: Throwable?) {
                        ApplicationController.instance!!.makeToast("네트워크 상태 확인")
                    }

                    override fun onResponse(call: Call<StarPostResponse>?, response: Response<StarPostResponse>?) {
                        if (response!!.isSuccessful) {
                            if (response!!.body().status.equals("success")) {
                                dialog.dismiss()
                                getStarList(ex_id)
                            }
                        }
                    }
                })
                dialog.dismiss()
            }
        }

        var tabAdapter = DisplayTabAdapter(this, supportFragmentManager, display_tab.tabCount, ex_id, ex_title, gallery_name, gallery_id)
        display_viewPager.adapter = tabAdapter

        display_viewPager.currentItem = 0
        display_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(display_tab))
        display_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                display_viewPager.currentItem = tab!!.position
                Log.v("current", tab!!.position.toString())

                when (tab!!.position) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putInt("ex_id", ex_id)
                        bundle.putString("ex_title", intent.getStringExtra("ex_title"))
                        bundle.putString("ex_place", intent.getStringExtra("gallery_name"))
//                        val bundle = Bundle()
//                        bundle.putInt("ex_id", exId)
//                        firstTab!!.arguments = bundle
//                        return  firstTab
                    }
                    1 -> {
                        val bundle = Bundle()
                        bundle.putInt("ex_id", ex_id)
                        bundle.putString("ex_title", intent.getStringExtra("ex_title"))
                        bundle.putString("ex_place", intent.getStringExtra("gallery_name"))
                        bundle.putInt("gallery_id", intent.getIntExtra("gallery_id", 100))
                    }
                    2 -> {
                        val bundle = Bundle()
                        bundle.putInt("ex_id", ex_id)
                        bundle.putString("ex_title", intent.getStringExtra("ex_title"))
                        bundle.putString("ex_place", intent.getStringExtra("gallery_name"))
                    }
                }
            }
        })
        display_heart!!.setOnClickListener {
            //여기서부터 통신
//
//            networkService = ApplicationController.instance!!.networkService
//            //  board_content_list.layoutManager = LinearLayoutManager(this)
//            requestManager = Glide.with(this)
//            //getCheckHeart()
            var checkHeart: Int = 0
            if (checkHeart == 0) {
                display_heart!!.setBackgroundResource(R.drawable.btn_like_yellow)
                checkHeart = 1
                var builder: AlertDialog.Builder = AlertDialog.Builder(this)

                var inflater: LayoutInflater = LayoutInflater.from(this)
                var view: View = inflater.inflate(R.layout.home2_heart_dialog, null)
                builder.setView(view)
                var dialog: Dialog = builder.create()
                view.dialog_heart_title.setText(display_share_button!!.text.toString())

                dialog.show()

                // dialog 크기변환
                var dm: DisplayMetrics = view.resources.displayMetrics
                var px_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240.0f, dm) // float형
                var px_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 175.0f, dm)

                val px_width_int = px_width.toInt() // int형으로 변환
                val px_height_int = px_height.toInt()
                dialog!!.window.setLayout(px_width_int, px_height_int)

                dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                val animation = AnimationUtils.loadAnimation(this, R.anim.scale) // 안될 땐 clean project
                view.startAnimation(animation)
            } else {
                display_heart!!.setBackgroundResource(R.drawable.detailpg_heart_off)
                checkHeart = 0
            }
        }

        display_audio_button.setOnClickListener {
            val intent = Intent(this, TrackActivity::class.java)
            intent.putExtra("exId", ex_id)
            intent.putExtra("exImage", ex_image)
            intent.putExtra("exTitle", ex_title)
            startActivity(intent)

        }

        display_share_button!!.setOnClickListener {
            val msg = Intent(Intent.ACTION_SEND)
            val title = display_title.text.toString()
            val date = display_date.text.toString()
            var place = display_place.text.toString()
            msg.addCategory(Intent.CATEGORY_DEFAULT)

            msg.putExtra(Intent.EXTRA_SUBJECT, "[Easy Fun Art: 당신의 일상에 전시를 더하다]\n나랑 보러갈래? \n" + title) //전시장소 가져와서 넣기

            msg.putExtra(Intent.EXTRA_TEXT, "\n기간 : " + date)
            msg.type = "text/plain"

            startActivity(Intent.createChooser(msg, "공유"))
        }
    }

    // 내 평점 불러오기
    fun getStarList(ex_id: Int) {
        val getStarList = networkService!!.getStarList(CommonData.user_token!!, ex_id)
        getStarList.enqueue(object : Callback<StarResponse> {
            override fun onFailure(call: Call<StarResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }

            override fun onResponse(call: Call<StarResponse>?, response: Response<StarResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")) {
                        Log.d(TAG, "성공2")

                        my_score.text = response.body().data.review_grade.toString()
                    }
                } else {
                    Log.d(TAG, "실패")
                }
            }
        })
    }

    fun initView() {
        //1.  통신을 한다
        //2. 통신 성공하면 각 뷰에 맞게 데이터를 넣는다.

        //---------호출 시점----------
        //1. OnCreate
        //2. 다이얼로그에서 OK 눌렀을 떄

        val intent = Intent(applicationContext, HomeActivity::class.java)
        display_title!!.text = getIntent().getStringExtra("ex_title")
        if (getIntent().getStringExtra("ex_image") == null)
            requestManager!!.load(R.drawable.img_poster_default).into(display_imageView)
        else
            requestManager!!.load(getIntent().getStringExtra("ex_image")).into(display_imageView)
        display_date!!.text = getIntent().getStringExtra("ex_start_date")
        display_date2!!.text = getIntent().getStringExtra("ex_end_date")
        display_place!!.text = getIntent().getStringExtra("gallery_name")
        all_score!!.text = getIntent().getStringExtra("ex_average_grade")
        val ex_id: Int = getIntent().getIntExtra("ex_id", 1)
        Log.d(TAG, "ex_id : " + ex_id)

        isLike = getIntent().getIntExtra("LikeFlag", 2)
        if (isLike == 1)
            display_heart.setBackgroundResource(R.drawable.like_heart_on)
        else
            display_heart.setBackgroundResource(R.drawable.like_heart_off)
    }


}
