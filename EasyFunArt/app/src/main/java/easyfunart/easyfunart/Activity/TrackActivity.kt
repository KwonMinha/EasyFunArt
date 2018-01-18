package easyfunart.easyfunart.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Adapter.TrackListAdapter

import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.GET.TrackData
import easyfunart.easyfunart.NetworkService

import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.TrackListResponse
import kotlinx.android.synthetic.main.activity_track.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackActivity : AppCompatActivity(), View.OnClickListener {

    private var networkService: NetworkService? = null
    private var trackListAdpater: TrackListAdapter? = null
    private var requestManager : RequestManager? = null
    private var trackData : ArrayList<TrackData>? = null

    var splitList1: ArrayList<TrackData>? = null
    var splitList2: ArrayList<TrackData>? = null
    var splitList3: ArrayList<TrackData>? = null
    var splitList4: ArrayList<TrackData>? = null

    var splitList11: ArrayList<TrackData>? = null
    var splitList22: ArrayList<TrackData>? = null
    var splitList33: ArrayList<TrackData>? = null
    var splitList44: ArrayList<TrackData>? = null

    var splitList111: ArrayList<TrackData>? = null
    var splitList222: ArrayList<TrackData>? = null
    var splitList333: ArrayList<TrackData>? = null
    var splitList444: ArrayList<TrackData>? = null
    var exId : Int? = null
    var exImage : String? = null
    var exTitle : String? =null
    var docentTrack : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)


        networkService = ApplicationController.instance!!.networkService
        trackList_reyclerview.layoutManager = LinearLayoutManager(this)
        requestManager = Glide.with(this)

        exId = intent.getIntExtra("exId",1)
        exImage = intent.getStringExtra("exImage")
        exTitle = intent.getStringExtra("exTitle")
        docentTrack = intent.getIntExtra("docentTrack",0)
        Log.d("exId",exId.toString())
        Log.d("exImage",exImage.toString())
        Log.d("exTitle",exTitle.toString())

//        Log.d("트랙리스트",exImage)

//        splitList1 = ArrayList<TrackData>()
//        splitList2 = ArrayList<TrackData>()
//        splitList3 = ArrayList<TrackData>()
//        splitList4 = ArrayList<TrackData>()
//
//        splitList11 = ArrayList<TrackData>()
//        splitList22 = ArrayList<TrackData>()
//        splitList33 = ArrayList<TrackData>()
//        splitList44 = ArrayList<TrackData>()
//
//        trackData = ArrayList<TrackData>()
//
//        trackData!!.add(TrackData(0,"1F","아이유-밤편지","01"))
//        trackData!!.add(TrackData(0,"1F","아이유-밤편지","02"))
//        trackData!!.add(TrackData(0,"2F","아이유-밤편지","01"))
//        trackData!!.add(TrackData(0,"2F","아이유-밤편지","02"))
//        trackData!!.add(TrackData(0,"3F","아이유-밤편지","01"))
//        trackData!!.add(TrackData(0,"3F","아이유-밤편지","02"))
//
//
////        trackData = response.body().data
//
////                        Log.d("데이터",trackData!!.get(0).docent_floor.toString())
////                        Log.d("데이터",trackData!!.get(1).docent_floor.toString())
////                        Log.d("데이터",trackData!!.get(2).docent_floor.toString())
//
//
//        for (t in trackData!!) {
//            if (t.docent_floor.equals("1F")) {
//                splitList1!!.add(t)
//            } else if (t.docent_floor.equals("2F")) {
//                splitList2!!.add(t)
//            } else if (t.docent_floor.equals("3F")) {
//                splitList3!!.add(t)
//            }
//        }
//
//
//        Log.d("1데이터1", splitList1!!.get(0).toString())
//        Log.d("1데이터1", splitList1!!.get(1).toString())
//        Log.d("1데이터2", splitList2!!.get(0).toString())
//        Log.d("1데이터2",splitList2!!.get(1).toString())
//        Log.d("1데이터3",splitList3!!.get(0).toString())
//        Log.d("1데이터3",splitList3!!.get(1).toString())
//
//        splitList11!!.add(TrackData(0,"1", "1층시작","01"))
////                            Log.d("1층","Asd")
//        splitList22!!.add(TrackData(0,"2", "2층시작","02"))
////                            Log.d("2층","Asd")
//        splitList33!!.add(TrackData(0,"3", "3층시작","03"))
////                            Log.d("3층","Asd")
//
//
//
//        //층 타이틀을 층 데이터 맨앞에 붙임.
//        splitList1!!.addAll(0, splitList11!!)
//        splitList2!!.addAll(0, splitList22!!)
//        splitList3!!.addAll(0, splitList33!!)
//
//        //trackList 초기화
//        trackData = ArrayList<TrackData>()
//        //trackData에 합침.
//        trackData!!.addAll(splitList1!!)
//        trackData!!.addAll(splitList2!!)
//        trackData!!.addAll(splitList3!!)
//
//        Log.d("trackData",trackData!!.toString())
//
//        trackListAdpater = TrackListAdapter(trackData!!, requestManager!!)//requestManager는 glide 쓰기 위해.
//        trackListAdpater!!.setOnItemClickListener(this@TrackActivity)//클릭 이벤트를 위한?
//        trackList_reyclerview.adapter = trackListAdpater//어댑터에 붙여주기

//        detail_page_button.setOnClickListener{//상세 페이지로 이동
//            startActivity(Intent(applicationContext,))
//            intent.putExtra("exId",exId)
//            finish()
//        }

//        Log.d("1층",trackData!!.get(0).docent_floor)
//        Log.d("2층",trackData!!.get(1).docent_floor)
//        Log.d("3층",trackData!!.get(2).docent_floor)
//        Log.d("4층",trackData!!.get(3).docent_floor)
//        Log.d("5층",trackData!!.get(4).docent_floor)
//        Log.d("6층",trackData!!.get(5).docent_floor)
//        Log.d("7층",trackData!!.get(6).docent_floor)
//        Log.d("8층",trackData!!.get(7).docent_floor)
//        Log.d("9층",trackData!!.get(8).docent_floor)
//        Log.d("10층",trackData!!.get(9).docent_floor)
//        Log.d("11층",trackData!!.get(10).docent_floor)
//        Log.d("12층",trackData!!.get(11).docent_floor)
//        Log.d("12층",trackData!!.get(12).docent_floor)
//        Log.d("12층",trackData!!.get(13).docent_floor)

//        CommonData.track_array = ArrayList<Int>()
//        for(i in 0..(9)-3){
//            CommonData.track_array!!.add(0)
//        }
//        Log.d("사이즈test", CommonData.track_array!!.toString())
        getTrackList(exId!!)
    }

    fun getTrackList(exId : Int){

        val getTrack = networkService!!.getTrackList(CommonData.user_token!!, exId)
        getTrack.enqueue(object : Callback<TrackListResponse>{

            override fun onResponse(call: Call<TrackListResponse>?, response: Response<TrackListResponse>?) {
                if (response!!.isSuccessful) {
                    if(response!!.body().status.equals("success")) {
                        Log.d("trackList","성공")
//                        Log.d("데이터",response.body().data.get(0).toString())
//                        Log.d("데이터",response.body().data.get(1).toString())
//                        Log.d("데이터",response.body().data.get(2).toString())
//                        Log.d("데이터",response.body().data.get(3).toString())
//                        Log.d("데이터",response.body().data.get(4).toString())
//                        Log.d("데이터",response.body().data.get(5).toString())
//                        Log.d("데이터",response.body().data.get(6).toString())

                        if(response!!.body().data.ex_state == 0){//끝난전시
//                            startActivity(Intent(applicationContext,Finish_YetActivity))
                            finish_yet_Image.setImageResource(R.drawable.docent_popup_finishexhiition)

                        }else if(response!!.body().data.ex_state == 2){//준비중
                            finish_yet_Image.setImageResource(R.drawable.docent_popup_noprepare)
                        }else if(response!!.body().data.ex_state == 1) {


                            splitList1 = ArrayList<TrackData>()
                            splitList2 = ArrayList<TrackData>()
                            splitList3 = ArrayList<TrackData>()
                            splitList4 = ArrayList<TrackData>()

                            splitList11 = ArrayList<TrackData>()
                            splitList22 = ArrayList<TrackData>()
                            splitList33 = ArrayList<TrackData>()
                            splitList44 = ArrayList<TrackData>()

                            trackData = ArrayList<TrackData>()
                            trackData = response.body().data.docentDataResult

//                        Log.d("데이터",trackData!!.get(0).docent_floor.toString())
//                        Log.d("데이터",trackData!!.get(1).docent_floor.toString())
//                        Log.d("데이터",trackData!!.get(2).docent_floor.toString())


                            for (t in trackData!!) {
                                if (t.docent_floor.equals("1F")) {
                                    splitList1!!.add(t)
                                } else if (t.docent_floor.equals("2F")) {
                                    splitList2!!.add(t)
                                } else if (t.docent_floor.equals("3F")) {
                                    splitList3!!.add(t)
                                }
                            }

                            //아이템의 사이즈를 구하기 위한 변수.
                            splitList111 = trackData
//                            splitList222 = splitList2
//                            splitList333 = splitList3


                            splitList11!!.add(TrackData(0, "1", "1층시작", 1))
//                            Log.d("1층","Asd")
                            splitList22!!.add(TrackData(0, "2", "2층시작", 2))
//                            Log.d("2층","Asd")
                            splitList33!!.add(TrackData(0, "3", "3층시작", 3))
//                            Log.d("3층","Asd")

//                        splitList1!!.addAll(0, splitList11!!)
//                        splitList2!!.addAll(0, splitList22!!)
//                        splitList3!!.addAll(0, splitList33!!)

                            //층 타이틀을 층 데이터 맨앞에 붙임.
                            if (!(splitList1!!.isEmpty())) {
                                splitList1!!.addAll(0, splitList11!!)
                            }
                            if (!(splitList2!!.isEmpty())) {
                                splitList2!!.addAll(0, splitList22!!)
                            }
                            if (!(splitList3!!.isEmpty())) {
                                splitList3!!.addAll(0, splitList33!!)
                            }

                            CommonData.track_array = ArrayList<Int>()
                            for(i in 0..(splitList1!!.size+splitList2!!.size+splitList3!!.size)-1){
                                CommonData.track_array!!.add(0)
                            }

                            //trackList 초기화
                            trackData = ArrayList<TrackData>()
                            //trackData에 합침.
                            trackData!!.addAll(splitList1!!)
                            trackData!!.addAll(splitList2!!)
                            trackData!!.addAll(splitList3!!)


//                            Log.d("배열1", splitList1!!.size.toString())
//                            Log.d("배열2", splitList2!!.size.toString())
//                            Log.d("배열3", splitList3!!.size.toString())
//                            Log.d("트랙어레이", CommonData.track_array!!.size.toString())

//                            Log.d("트랙데이터", trackData.toString())

                            trackListAdpater = TrackListAdapter(this@TrackActivity, trackData!!, requestManager!!, exId, exImage!!, exTitle!!, docentTrack, splitList111!!.size)//requestManager는 glide 쓰기 위해.
                            trackListAdpater!!.setOnItemClickListener(this@TrackActivity)//클릭 이벤트를 위한?
                            trackList_reyclerview.adapter = trackListAdpater//어댑터에 붙여주기

                        }
                    }
                }else{
                    ApplicationController.instance!!.makeToast("fail")
                    Log.d("trackList","실패")

                }
            }
            override fun onFailure(call: Call<TrackListResponse>?, t: Throwable?) {
                Log.d("trackList","통신실패")

            }
        })
    }

    override fun onClick(v: View?) {

        val idx : Int = trackList_reyclerview.getChildAdapterPosition(v)

//        CommonData.track_array = ArrayList<Int>()
//        for(i in 0..(splitList1!!.size+splitList2!!.size+splitList3!!.size)-1){
//            CommonData.track_array!!.add(0)
//        }
        Log.d("온클릭","됨?")
//        trackList_button.setImageResource(R.drawable.docent_playing_on)



//        Log.d("트랙어레이사이즈", track_array!!.size.toString())
//        CommonData.track_array!![idx] = 1

//        for(i in 0..(splitList11!!.size+splitList22!!.size+splitList33!!.size)-3) {
//            if (CommonData.track_array!![i] == 1) {
//                trackList_button.setImageResource(R.drawable.docent_playing_off)
//            }
//        }

//        trackList_button.setImageResource(R.drawable.docent_playing_on)

//        Log.d("배열",CommonData.track_array!!.toString())


//        val intent = Intent(applicationContext, PlaybackScreen::class.java)
//        intent.putExtra("docentTrack", trackData!!.get(idx).docent_track)
//        intent.putExtra("exId", exId)
//        intent.putExtra("exImage", exImage)
//        intent.putExtra("exTitle", exTitle)
//        intent.putExtra("docentTitle", trackData!!.get(idx).docent_title)
//        intent.putExtra("trackSize", splitList111!!.size+splitList222!!.size+splitList333!!.size)
//
//        startActivity(intent)
//        finish()


    }
//

}