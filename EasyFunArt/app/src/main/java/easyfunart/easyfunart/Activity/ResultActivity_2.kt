package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.POST.UserInfoPost
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.NicknameResponse
import easyfunart.easyfunart.Response.UserInfoResponse
import kotlinx.android.synthetic.main.activity_result_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity_2  :AppCompatActivity() {

    /*화면 구성 관련 변수*/
    var sexButton_woman : Button? = null //성별 선택 여성 버튼
    var sexButton_man : Button? = null //성별 선택 남성 버튼

    var ageButton_10 : Button?=null //연령대 선택 10대 버튼
    var ageButton_20 : Button?=null //연령대 선택 20대 버튼
    var ageButton_30 : Button?=null //연령대 선택 30대 버튼
    var ageButton_40 : Button?=null //연령대 선택 40대 버튼
    var ageButton_50 : Button?=null //연령대 선택 50대 버튼
    var ageButton_60 : Button?=null //연령대 선택 60대 버튼

    var nextButton : Button?=null // 다음 버튼

    var isage = arrayOf(0,0,0,0,0,0)
    var isSex : Int? = 0
    /*서버 관련 변수*/
    private var networkService : NetworkService?=null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_info)

        initActivity()
        networkService = ApplicationController.instance!!.networkService




//        if(editNickname.text.toString()!=null){
//            CommonData.level=10
//            Log.e("여기 commonDataleve", CommonData.level.toString())
////            if(usernickName==서버가 괜찮다고 하면)
////           else if (usernickName 서버가 괜찮다고 안하면)
////                nicknameok.setText("이미 존재하는 닉네임입니다 :(")
//
//
//        }

        networkService = ApplicationController.instance!!.networkService

        /*각 버튼을 누르면 정보 저장*/

        /*각 버튼을 누르면 정보 저장*/
        sexButton_man!!.setOnClickListener{
            if (isSex == 0){ // 제일 처음 버튼을 누르는 상태
                sexButton_man!!.setBackgroundResource(R.drawable.prefrence_information_boy_on)
                isSex = 1
                CommonData.str_sex = 0
                Log.e("여기 onclick 남자", CommonData.str_sex.toString())
            }
            else if (isSex == 1) { // 남자버튼을 눌렀는데 또 누른다 그러면 해제
                sexButton_man!!.setBackgroundResource(R.drawable.prefrence_information_boy_off)
                isSex = 0
                CommonData.str_sex=0
            }
            else if (isSex == 2) { //이전에 여자버튼을 누르고 남자버튼을 눌렀을 때
                sexButton_man!!.setBackgroundResource(R.drawable.prefrence_information_boy_on)
                sexButton_woman!!.setBackgroundResource(R.drawable.prefrence_information_girl_off)
                isSex = 1
                CommonData.str_sex = 0
            }

        } //남성

        sexButton_woman!!.setOnClickListener{
            if (isSex == 0){
                sexButton_woman!!.setBackgroundResource(R.drawable.prefrence_information_girl_on)
                isSex = 2
                CommonData.str_sex = 1
            }
            else if(isSex == 1) { // 이전에 남자 버튼을 눌렀다면 남자 버튼을 오프하고 여자버튼을 온
                sexButton_man!!.setBackgroundResource(R.drawable.prefrence_information_boy_off)
                sexButton_woman!!.setBackgroundResource(R.drawable.prefrence_information_girl_on)
                isSex = 2
                CommonData.str_sex = 1
            }
            else if (isSex == 2){ // 이전에 여자버튼을 눌렀는데 또 여자버튼을 누르면 여자버튼을 오프 - 값 없음 선택해줘야함
                sexButton_man!!.setBackgroundResource(R.drawable.prefrence_information_boy_on)
                sexButton_woman!!.setBackgroundResource(R.drawable.prefrence_information_girl_off)
                isSex = 0
                CommonData.str_sex = 1
            }
        }//여성

//        //남자 누름
//        sexButton_man!!.setOnClickListener {
//            CommonData.str_sex = 0
//            Log.e("여기 onclick 남자", CommonData.str_sex.toString())
//            sexButton_man!!.setBackgroundResource(R.drawable.prefrence_information_boy_on)
//        }
//
//        //여자 누름
//        sexButton_woman!!.setOnClickListener{
//           CommonData.str_sex = 1
//           sexButton_woman!!.setBackgroundResource(R.drawable.prefrence_information_girl_on)
//
//        }//여성


        ageButton_10!!.setOnClickListener{
            CommonData.str_age = 10

            isage[0]=1
            ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_on)

            Log.e("여기 isage1", isage.toString())
            if(isage[1]==1 && isage[0]==1){
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_off)
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_on)
            }
            if(isage[2]==1 && isage[0]==1){
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_off)
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_on)
            }
            if(isage[3]==1 && isage[0]==1){
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_off)
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_on)
            }
            if(isage[4]==1 && isage[0]==1){
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_off)
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_on)
            }
            if(isage[5]==1 && isage[0]==1){
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_off)
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_on)
            }



            Log.e("여기 isage", isage.toString())

        } //10대


        ageButton_20!!.setOnClickListener{
            CommonData.str_age = 20

            isage[1]=1
            ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_on)
            Log.e("여기 isage1", isage.toString())
            if(isage[0]==1 && isage[1]==1){
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_off)
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_on)
            }
            if(isage[2]==1 && isage[1]==1){
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_off)
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_on)
            }
            if(isage[3]==1 && isage[1]==1){
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_off)
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_on)
            }
            if(isage[4]==1 && isage[1]==1){
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_off)
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_on)
            }
            if(isage[5]==1 && isage[1]==1){
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_off)
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_on)
            }





        } //20대


        ageButton_30!!.setOnClickListener{
            isage[2]=1
            ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_on)
            CommonData.str_age = 30
            isage[0]=1
            Log.e("여기 isage1", isage.toString())
            if(isage[0]==1 && isage[2]==1){
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_off)
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_on)
            }
            if(isage[1]==1 && isage[2]==1){
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_off)
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_on)
            }
            if(isage[3]==1 && isage[2]==1){
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_off)
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_on)
            }
            if(isage[4]==1 && isage[2]==1){
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_off)
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_on)
            }
            if(isage[5]==1 && isage[2]==1){
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_off)
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_on)
            }


        } //30대

        ageButton_40!!.setOnClickListener{
            CommonData.str_age = 40
            ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_on)

            isage[3]=1

            if(isage[0]==1 && isage[3]==1){
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_off)
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_on)
            }
            if(isage[1]==1 && isage[3]==1){
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_off)
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_on)
            }
            if(isage[2]==1 && isage[3]==1){
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_off)
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_on)
            }
            if(isage[4]==1 && isage[3]==1){
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_off)
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_on)
            }
            if(isage[5]==1 && isage[3]==1){
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_off)
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_on)
            }


        } //40대

        ageButton_50!!.setOnClickListener{
            isage[4]=1
            ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_on)
            CommonData.str_age = 50

            if(isage[0]==1 && isage[0]==1){
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_off)
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_on)
            }
            if(isage[1]==1 && isage[0]==1){
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_off)
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_on)
            }
            if(isage[2]==1 && isage[0]==1){
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_off)
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_on)
            }
            if(isage[3]==1 && isage[0]==1){
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_off)
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_on)
            }
            if(isage[5]==1 && isage[0]==1){
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_off)
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_on)
            }


        } //50대

        ageButton_60!!.setOnClickListener{
            isage[5]=1
            ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_on)
            CommonData.str_age = 60

            if(isage[0]==1 && isage[5]==1){
                ageButton_10!!.setBackgroundResource(R.drawable.prefrence_information_10_off)
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_on)
            }
            if(isage[1]==1 && isage[5]==1){
                ageButton_20!!.setBackgroundResource(R.drawable.prefrence_information_20_off)
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_on)
            }
            if(isage[2]==1 && isage[5]==1){
                ageButton_30!!.setBackgroundResource(R.drawable.prefrence_information_30_off)
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_on)
            }
            if(isage[3]==1 && isage[5]==1){
                ageButton_40!!.setBackgroundResource(R.drawable.prefrence_information_40_off)
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_on)
            }
            if(isage[4]==1 && isage[5]==1){
                ageButton_50!!.setBackgroundResource(R.drawable.prefrence_information_50_off)
                ageButton_60!!.setBackgroundResource(R.drawable.prefrence_information_60_on)
            }



        } //60대


        nextButton!!.setOnClickListener{

            //입력한 닉네임 받아옴
            var usernickName  = editNickname.text.toString()
            Log.d("여기1", usernickName)


            CommonData.nickname =usernickName
            Log.e("여기2 CommonData.nickname", CommonData.nickname)
//            Log.e("usernickName", usernickName)


            /*서버에 성별, 닉네임 값 전송*/
            senduserInfo()
            sendNickname()
            val intent= Intent(applicationContext, ResultActivity_genre1::class.java)
            startActivity(intent)
        } //다음 버튼을 누르면 resultActivity_genre1으로 가도록 함

    }

    fun initActivity(){
        sexButton_man = findViewById(R.id.result2_manButton_btn) as Button
        sexButton_woman=findViewById(R.id.result2_womanButton_btn) as Button

        ageButton_10=findViewById(R.id.result2_ageButton10_btn) as Button
        ageButton_20=findViewById(R.id.result2_ageButton20_btn) as Button
        ageButton_30=findViewById(R.id.result2_ageButton30_btn) as Button
        ageButton_40=findViewById(R.id.result2_ageButton40_btn) as Button
        ageButton_50=findViewById(R.id.result2_ageButton50_btn) as Button
        ageButton_60=findViewById(R.id.result2_ageButton60_btn) as Button

        nextButton = findViewById(R.id.result2_nextButton_btn) as Button
        //액티비티에서 사용할 뷰들을 연결


    }

    fun senduserInfo(){

        val token : String = CommonData.loginStr.toString()

        val sendinfo = UserInfoPost(CommonData.str_sex!!, CommonData.str_age!!, CommonData.nickname.toString())

        val userinfoResponse = networkService!!.senduserInfo(token,sendinfo)
        Log.e("tag", "nicknameResponse")

        userinfoResponse?.enqueue(object : Callback<UserInfoResponse> {
            override fun onFailure(call: Call<UserInfoResponse>?, t: Throwable?) {
                Log.d("e", "연결에 실패했습니다")}

            override fun onResponse(call: Call<UserInfoResponse>?, response: Response<UserInfoResponse>?) {
                if(response!!.isSuccessful){
                    Log.e("여기 responseSuccess", response.body().data.token)
                    CommonData.infoStr = response.body().data.token
                    Log.e("여기 loginstr2 .infostr", CommonData.infoStr.toString())
                }else{
                    Log.d("e","정보가 정확")
                }

            }
        })

    }

    /*서버에게 GET NICKNAME*/
    fun sendNickname(){


        val token : String = CommonData.loginStr.toString()
        Log.e("jy", "token")

//            val sendnick = NicknamePost(CommonData.nickname!!)


        Log.e("jy token" ,token)
        val nickNameResponse = networkService!!.getNickname(CommonData.nickname!!)
        Log.e("jy", "nicknameResponse")

        nickNameResponse?.enqueue(object : Callback<NicknameResponse>{
            override fun onResponse(call: Call<NicknameResponse>?, response: Response<NicknameResponse>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){
                        CommonData.checkflag= response.body().checkFlag
                    }
                } else{
                    Log.d("e","정보가 정확")
                }
            }

            override fun onFailure(call: Call<NicknameResponse>?, t: Throwable?) {
                Log.d("e", "연결에 실패했습니다")
            }
        })

    }

}

