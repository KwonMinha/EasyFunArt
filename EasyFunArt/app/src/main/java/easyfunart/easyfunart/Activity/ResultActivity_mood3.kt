package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import easyfunart.easyfunart.Adapter.MoodAdaptor
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Data.MoodData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R

class ResultActivity_mood3 : AppCompatActivity(), View.OnClickListener{

    private var networkService: NetworkService? = null



    /*리스트 클릭했을 시 코드 구현해 지윤*/
    override fun onClick(p0: View?) {
        val idx : Int = moodList!!.getChildAdapterPosition(p0)
        CommonData.moodIntlist!![idx]=1

        //서버에게 전달할 변수 string 으로 변환
        CommonData.sendmood = CommonData.moodIntlist!!.toString()
        Log.e("여기1sendmoodintlist", CommonData.moodIntlist.toString())

        CommonData.sendmood = CommonData.sendmood!!.substring(1,32)
        Log.e("여기2 substring", CommonData.sendmood.toString())
        //CommonData.str_mood+=(moodDatass!![idx].mood_image_toString)


        //공백 없애주기
        var splitmoodList : List<String>? = null
        splitmoodList = CommonData.sendmood!!.split(" ")
        var splitmoodstr : String?=null

        for (i in 0..10)
            splitmoodstr+=splitmoodList[i]
        Log.e("여기splitmoodstr", splitmoodstr.toString())
        splitmoodstr!!.trim()
        splitmoodstr = splitmoodstr.substring(4,25)
        splitmoodstr.trim()

        CommonData.sendmood = splitmoodstr
        Log.e("여기mood 완성", CommonData.sendmood)
        /* 1. view 버튼 바꾸기*/
        Toast.makeText(this,CommonData.str_mood.toString(), Toast.LENGTH_SHORT ).show()

        Log.e("GET", moodDatass!![idx].mood_image_toString)



    }




    private var moodList : RecyclerView?=null
    private var moodDatass : ArrayList<MoodData>?=null
    private var adaptor : MoodAdaptor?=null



    var nextButton : Button?=null //다음 버튼
    var prevButton : Button?=null //이전 버튼

    private var OnItemClick : View.OnClickListener?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_mood)

        initActivity()

        moodList = findViewById(R.id.mood_list_recycler) as RecyclerView
        moodList!!.layoutManager=LinearLayoutManager(this)
        moodDatass=ArrayList<MoodData>()

        //어댑터 이용 정보 넣기
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_1, "적막감"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_2, "환상적인"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_3, "세련된"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_4, "편안한"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_5, "강렬한"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_6, "따뜻한"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_7, "슬픈"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_8, "유유자적한"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_9, "우아한"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_10, "시원한"))
        moodDatass!!.add(MoodData(R.drawable.prefrence_love_off_11, "사실적인"))

        //어댑터 등록
        adaptor = MoodAdaptor(moodDatass!!, this)
        adaptor!!.setOnItemClickListener(this)
        moodList!!.adapter=adaptor



        /*이전, 다음 버튼 누르면 화면 전환*/

        prevButton?.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_place2::class.java)
            startActivity(intent)
        } //이전 버튼을 누르면 result_place2로 가도록 함

        nextButton?.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_title4::class.java)
            startActivity(intent)
        } //다음 버튼을 누르면 result_title4로 가도록 함

    }

    fun initActivity(){

        nextButton = findViewById(R.id.resultmood_nextButton_btn) as? Button
        prevButton = findViewById(R.id.resultmood_prevButton_btn) as? Button
    }
}