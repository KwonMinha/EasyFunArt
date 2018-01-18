package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import easyfunart.easyfunart.Adapter.TitleAdaptor
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Data.TitleData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.POST.TitlePost
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.TitleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity_title4 : AppCompatActivity(), View.OnClickListener{



    private var titleList : RecyclerView?=null
    private var titleDatas: ArrayList<TitleData>?=null
    private var adaptor : TitleAdaptor?=null


    var nextButton : Button?=null //다음 버튼
    var prevButton : Button?=null //이전 버튼

    var titleButton_1 : Button?=null
    var titleButton_2 : Button? = null
    var titleButton_3 : Button? = null
    var titleButton_4 : Button? = null
    var titleButton_5 : Button? = null
    var titleButton_6 : Button? = null
    var titleButton_7 : Button? = null
    var titleButton_8 : Button? = null
    var titleButton_9 : Button? = null
    var titleButton_10 : Button? = null
    var titleButton_11 : Button? = null
    var titleButton_12 : Button? = null

    private var OnItemClick : View.OnClickListener?=null


    /*서버 관련 변수*/
    private var networkService : NetworkService?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_title)


        initActivity()
        networkService = ApplicationController.instance!!.networkService


        //  각 버튼을 누르면 CommonData 에 정보 저장

        titleButton_1!!.setOnClickListener{

            CommonData.titleIntlist!![0]=1
            change()
            titleButton_1!!.setBackgroundResource(R.drawable.prefrence_subject_on_1)

        }
        titleButton_2!!.setOnClickListener{

            CommonData.titleIntlist!![1]=1
            titleButton_2!!.setBackgroundResource(R.drawable.prefrence_subject_on_2)


            change()

        }
        titleButton_3!!.setOnClickListener{
            CommonData.titleIntlist!![2]=1
            titleButton_3!!.setBackgroundResource(R.drawable.prefrence_subject_on_3)


            change()
        }
        titleButton_4!!.setOnClickListener{
            CommonData.titleIntlist!![3]=1
            titleButton_4!!.setBackgroundResource(R.drawable.prefrence_subject_on_4)


            change()
        }
        titleButton_5!!.setOnClickListener{
            CommonData.titleIntlist!![4]=1
            titleButton_5!!.setBackgroundResource(R.drawable.prefrence_subject_on_5)


            change()
        }
        titleButton_6!!.setOnClickListener{
            CommonData.titleIntlist!![5]=1
            titleButton_6!!.setBackgroundResource(R.drawable.prefrence_subject_on_6)


            change()
        }
        titleButton_7!!.setOnClickListener{
            CommonData.titleIntlist!![6]=1
            titleButton_7!!.setBackgroundResource(R.drawable.prefrence_subject_on_7)


            change()
        }
        titleButton_8!!.setOnClickListener{
            CommonData.titleIntlist!![7]=1
            titleButton_8!!.setBackgroundResource(R.drawable.prefrence_subject_on_8)


            change()
        }
        titleButton_9!!.setOnClickListener{
            CommonData.titleIntlist!![8]=1
            titleButton_9!!.setBackgroundResource(R.drawable.prefrence_subject_on_9)


            change()
        }
        titleButton_10!!.setOnClickListener{
            CommonData.titleIntlist!![9]=1
            titleButton_10!!.setBackgroundResource(R.drawable.prefrence_subject_on_10)


            change()
        }
        titleButton_11!!.setOnClickListener{
            CommonData.titleIntlist!![10]=1
            titleButton_11!!.setBackgroundResource(R.drawable.prefrence_subject_on_11)


            change()
        }

        titleButton_12!!.setOnClickListener{
            CommonData.titleIntlist!![11]=1
            titleButton_12!!.setBackgroundResource(R.drawable.prefrence_subject_on_12)


            change()
        }
//서버에게 보낼 변수 string으로 변환
//        CommonData.sendtitle = CommonData.titleIntlist!!.toString()
//        Log.e("여기sendtitle",CommonData.sendtitle)
//
//    CommonData.sendtitle=CommonData.sendtitle!!.substring(1,35)
//
//        var splittitlelist : List<String>?=null
//        splittitlelist=CommonData.sendtitle!!.split(" ")
//Log.e("여기titlelist", splittitlelist.toString())
//
//        var splittitlestr : String? = null
//        for (i in 0..11)
//            splittitlestr +=splittitlelist[i]
//        Log.e("여기splittitlestr", splittitlestr)
//       // splittitlestr=splittitlestr!!.substring

        prevButton!!.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_mood3::class.java)
            startActivity(intent)
        } //이전 버튼을 누르면 result_4로 가도록 함


        nextButton!!.setOnClickListener{

            send() /*서버에 전송하는 함수 구현*/
            val intent= Intent(applicationContext, ResultActivity_last::class.java)
            startActivity(intent)
        } //다음 버튼을 누르면 result_last로 가도록 함


    }

    fun change() {

        CommonData.sendtitle = CommonData.titleIntlist!!.toString()
        Log.e("여기sendtitle", CommonData.sendtitle)

        CommonData.sendtitle = CommonData.sendtitle!!.substring(1, 35)

        var splittitlelist: List<String>? = null
        splittitlelist = CommonData.sendtitle!!.split(" ")
        Log.e("여기titlelist", splittitlelist.toString())

        var splittitlestr: String? = null
        for (i in 0..11)
            splittitlestr += splittitlelist[i]
        splittitlestr=splittitlestr!!.substring(4,27)

        splittitlestr.trim()
        CommonData.sendtitle = splittitlestr
        Log.e("여기splittitlestr완성", CommonData.sendtitle)

    }

    fun initActivity(){

        titleButton_1 = findViewById(R.id.resulttitle_btn_1) as Button
        titleButton_2 = findViewById(R.id.resulttitle_btn_2) as Button
        titleButton_3 = findViewById(R.id.resulttitle_btn_3) as Button
        titleButton_4 = findViewById(R.id.resulttitle_btn_4) as Button
        titleButton_5 = findViewById(R.id.resulttitle_btn_5) as Button
        titleButton_6 = findViewById(R.id.resulttitle_btn_6) as Button
        titleButton_7 = findViewById(R.id.resulttitle_btn_7) as Button
        titleButton_8 = findViewById(R.id.resulttitle_btn_8) as Button
        titleButton_9 = findViewById(R.id.resulttitle_btn_9) as Button
        titleButton_10 = findViewById(R.id.resulttitle_btn_10) as Button
        titleButton_11 = findViewById(R.id.resulttitle_btn_11) as Button
        titleButton_12 = findViewById(R.id.resulttitle_btn_12) as Button

        nextButton = findViewById(R.id.resulttitle_nextButton_btn) as Button
        prevButton = findViewById(R.id.resulttitle_prevButton_btn) as Button
    }

    override fun onClick(p0: View?) {
        //리스트의아이템 클릭 시 어쩌구저쩌구


    }
    /*서버에게 선호도 전송*/
    fun send(){

        val token : String = CommonData.infoStr.toString()

        val sendinfo = TitlePost(CommonData.sendplace.toString(),CommonData.sendmood.toString(),
                CommonData.sendgenre.toString(), CommonData.sendtitle.toString())

        Log.e("여기 titlepost", TitlePost(CommonData.sendplace.toString(),CommonData.sendmood.toString(),
                CommonData.sendgenre.toString(), CommonData.sendtitle.toString()).toString())

        Log.e("여기 sendinfo", sendinfo.toString())

        Log.e("여기 infostr" ,token)
        val titleResponse = networkService!!.sendpreference(token, sendinfo)

        titleResponse?.enqueue(object : Callback<TitleResponse>{
            override fun onResponse(call: Call<TitleResponse>?, response: Response<TitleResponse>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){

                        /*commonData에 response값 저장*/
                        CommonData.user_token = response.body().data.token
                        Log.e("여기 titleStr", CommonData.user_token)

                    }
                }else{
                    Log.d("e","정보가 정확")
                }
            }

            override fun onFailure(call: Call<TitleResponse>?, t: Throwable?) {
                Log.d("e", "연결에 실패했습니다")
            }
        })

    }
}