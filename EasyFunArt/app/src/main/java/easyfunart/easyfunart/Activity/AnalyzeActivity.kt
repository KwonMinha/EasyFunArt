package easyfunart.easyfunart.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ImageView
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.PUT.MyPagePrefModiPut
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.MyPagePrefModiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnalyzeActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase : Context){
        //super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    private var networkService: NetworkService? = null
    var token : String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjkwLCJ1c2VyTGV2ZWwiOjUwLCJpYXQiOjE1MTU2NjQ4MjEsImV4cCI6MTUyNDMwNDgyMSwiaXNzIjoiRWFzeUZ1bkFydCJ9.BLcg7erablQSztZPV4PfOyyJyQYC3zzJfUHur6qVkQg"
    var checkButton : ImageButton? = null

    var sex = arrayOf(0,0)

    var boyButton : ImageButton? = null
    var girlButton : ImageButton? = null

    var age = arrayOf(0,0,0,0,0,0)

    var age1Button : ImageButton? = null
    var age2Button : ImageButton? = null
    var age3Button : ImageButton? = null
    var age4Button : ImageButton? = null
    var age5Button : ImageButton? = null
    var age6Button : ImageButton? = null

    var genre = arrayOf(0,0,0,0,0,0,0,0,0,0,0,0)

    var genre1Button : ImageButton? = null
    var genre2Button : ImageButton? = null
    var genre3Button : ImageButton? = null
    var genre4Button : ImageButton? = null
    var genre5Button : ImageButton? = null
    var genre6Button : ImageButton? = null
    var genre7Button : ImageButton? = null
    var genre8Button : ImageButton? = null
    var genre9Button : ImageButton? = null
    var genre10Button : ImageButton? = null
    var genre11Button : ImageButton? = null
    var genre12Button : ImageButton? = null

    var place = arrayOf(0,0,0,0,0,0,0,0,0)

    var place1Button : ImageButton? = null
    var place2Button : ImageButton? = null
    var place3Button : ImageButton? = null
    var place4Button : ImageButton? = null
    var place5Button : ImageButton? = null
    var place6Button : ImageButton? = null
    var place7Button : ImageButton? = null
    var place8Button : ImageButton? = null
    var place9Button : ImageButton? = null

    var love = arrayOf(0,0,0,0,0,0,0,0,0,0,0)

    var love1Button : ImageButton? = null
    var love2Button : ImageButton? = null
    var love3Button : ImageButton? = null
    var love4Button : ImageButton? = null
    var love5Button : ImageButton? = null
    var love6Button : ImageButton? = null
    var love7Button : ImageButton? = null
    var love8Button : ImageButton? = null
    var love9Button : ImageButton? = null
    var love10Button : ImageButton? = null
    var love11Button : ImageButton? = null

    var subject = arrayOf(0,0,0,0,0,0,0,0,0,0,0,0)

    var subject1Button : ImageButton? = null
    var subject2Button : ImageButton? = null
    var subject3Button : ImageButton? = null
    var subject4Button : ImageButton? = null
    var subject5Button : ImageButton? = null
    var subject6Button : ImageButton? = null
    var subject7Button : ImageButton? = null
    var subject8Button : ImageButton? = null
    var subject9Button : ImageButton? = null
    var subject10Button : ImageButton? = null
    var subject11Button : ImageButton? = null
    var subject12Button : ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)

        networkService = ApplicationController.instance!!.networkService

        checkButton = findViewById(R.id.check_btn) as ImageButton

        checkButton!!.setOnClickListener{

            var AgeInt : Int? = null
            if(age[0]==1)
                AgeInt = 0  //남자
            else
                AgeInt = 1  //여자

            var SexInt : Int? = null
            for(i in 0..5)
                if(age[i]==1)
                    SexInt =10*(i+1)

            var genreString : String? = null
            genreString = genre[0].toString()
            for(i in 1..11){
                genreString += ","
                genreString += genre[i].toString()
            }

            var placeString : String? = null
            placeString = place[0].toString()
            for(i in 1..8){
                placeString += ","
                placeString += place[i].toString()
            }

            var loveString : String? = null
            loveString = love[0].toString()
            for(i in 1..10){
                loveString += ","
                loveString += love[i].toString()
            }

            var subjectString : String? = null
            subjectString = subject[0].toString()
            for(i in 1..11){
                subjectString += ","
                subjectString += subject[i].toString()
            }

            // 취향 바꾸기
            val putMyPagePrefModiResponse = networkService!!.putMyPagePrefModi(CommonData.user_token!!,
                    MyPagePrefModiPut(SexInt!!, AgeInt, placeString, loveString, genreString, subjectString))

            putMyPagePrefModiResponse.enqueue(object : Callback<MyPagePrefModiResponse> {
                override fun onResponse(call: Call<MyPagePrefModiResponse>?, response: Response<MyPagePrefModiResponse>?) {
                    if (response!!.isSuccessful) {
                        if (response!!.body().status == "success") {
                        }
                    }
                }
                override fun onFailure(call: Call<MyPagePrefModiResponse>?, t: Throwable?) {
                    //응답 실패
                    ApplicationController.instance!!.makeToast("통신 확인")
                }
            })
            var intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }

        boyButton = findViewById(R.id.preference_boy) as ImageButton
        girlButton = findViewById(R.id.preference_girl) as ImageButton

        boyButton!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_boy) as ImageView

            if(sex[0]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_boy_on)
                sex[0]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_boy_off)
                sex[0]=0
            }
        }
        girlButton!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_girl) as ImageView

            if(sex[1]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_girl_on)
                sex[1]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_girl_off)
                sex[1]=0
            }
        }


        age1Button = findViewById(R.id.preference_age1) as ImageButton
        age2Button = findViewById(R.id.preference_age2) as ImageButton
        age3Button = findViewById(R.id.preference_age3) as ImageButton
        age4Button = findViewById(R.id.preference_age4) as ImageButton
        age5Button = findViewById(R.id.preference_age5) as ImageButton
        age6Button = findViewById(R.id.preference_age6) as ImageButton

        age1Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_age1) as ImageView

            if(age[0]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_on_1)
                age[0]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_off_1)
                age[0]=0
            }
        }
        age2Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_age2) as ImageView

            if(age[1]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_on_2)
                age[1]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_off_2)
                age[1]=0
            }
        }
        age3Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_age3) as ImageView

            if(age[2]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_on_3)
                age[2]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_off_3)
                age[2]=0
            }
        }
        age4Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_age4) as ImageView

            if(age[3]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_on_4)
                age[3]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_off_4)
                age[3]=0
            }
        }
        age5Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_age5) as ImageView

            if(age[4]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_on_5)
                age[4]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_off_5)
                age[4]=0
            }
        }
        age6Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_age6) as ImageView

            if(age[5]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_on_6)
                age[5]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_age_off_6)
                age[5]=0
            }
        }


        genre1Button = findViewById(R.id.preference_genre1) as ImageButton
        genre2Button = findViewById(R.id.preference_genre2) as ImageButton
        genre3Button = findViewById(R.id.preference_genre3) as ImageButton
        genre4Button = findViewById(R.id.preference_genre4) as ImageButton
        genre5Button = findViewById(R.id.preference_genre5) as ImageButton
        genre6Button = findViewById(R.id.preference_genre6) as ImageButton
        genre7Button = findViewById(R.id.preference_genre7) as ImageButton
        genre8Button = findViewById(R.id.preference_genre8) as ImageButton
        genre9Button = findViewById(R.id.preference_genre9) as ImageButton
        genre10Button = findViewById(R.id.preference_genre10) as ImageButton
        genre11Button = findViewById(R.id.preference_genre11) as ImageButton
        genre12Button = findViewById(R.id.preference_genre12) as ImageButton

        genre1Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre1) as ImageView

            if(genre[0]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_1)
                genre[0]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_1)
                genre[0]=0
            }
        }
        genre2Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre2) as ImageView

            if(genre[1]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_2)
                genre[1]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_2)
                genre[1]=0
            }
        }
        genre3Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre3) as ImageView

            if(genre[2]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_3)
                genre[2]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_3)
                genre[2]=0
            }
        }
        genre4Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre4) as ImageView

            if(genre[3]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_4)
                genre[3]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_4)
                genre[3]=0
            }
        }
        genre5Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre5) as ImageView

            if(genre[4]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_5)
                genre[4]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_5)
                genre[4]=0
            }
        }
        genre6Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre6) as ImageView

            if(genre[5]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_6)
                genre[5]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_6)
                genre[5]=0
            }
        }
        genre7Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre7) as ImageView

            if(genre[6]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_7)
                genre[6]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_7)
                genre[6]=0
            }
        }
        genre8Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre8) as ImageView

            if(genre[7]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_8)
                genre[7]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_8)
                genre[7]=0
            }
        }
        genre9Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre9) as ImageView

            if(genre[8]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_9)
                genre[8]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_9)
                genre[8]=0
            }
        }
        genre10Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre10) as ImageView

            if(genre[9]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_10)
                genre[9]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_10)
                genre[9]=0
            }
        }
        genre11Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre11) as ImageView

            if(genre[10]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_11)
                genre[10]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_11)
                genre[10]=0
            }
        }
        genre12Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_genre12) as ImageView

            if(genre[11]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_on_12)
                genre[11]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_genre_off_12)
                genre[11]=0
            }
        }

        place1Button = findViewById(R.id.preference_place1) as ImageButton
        place2Button = findViewById(R.id.preference_place2) as ImageButton
        place3Button = findViewById(R.id.preference_place3) as ImageButton
        place4Button = findViewById(R.id.preference_place4) as ImageButton
        place5Button = findViewById(R.id.preference_place5) as ImageButton
        place6Button = findViewById(R.id.preference_place6) as ImageButton
        place7Button = findViewById(R.id.preference_place7) as ImageButton
        place8Button = findViewById(R.id.preference_place8) as ImageButton
        place9Button = findViewById(R.id.preference_place9) as ImageButton

        place1Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place1) as ImageView

            if(place[0]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_1)
                place[0]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_1)
                place[0]=0
            }
        }
        place2Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place2) as ImageView

            if(place[1]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_2)
                place[1]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_2)
                place[1]=0
            }
        }
        place3Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place3) as ImageView

            if(place[2]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_3)
                place[2]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_3)
                place[2]=0
            }
        }
        place4Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place4) as ImageView

            if(place[3]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_4)
                place[3]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_4)
                place[3]=0
            }
        }
        place5Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place5) as ImageView

            if(place[4]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_5)
                place[4]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_5)
                place[4]=0
            }
        }
        place6Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place6) as ImageView

            if(place[5]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_6)
                place[5]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_6)
                place[5]=0
            }
        }
        place7Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place7) as ImageView

            if(place[6]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_7)
                place[6]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_7)
                place[6]=0
            }
        }
        place8Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place8) as ImageView

            if(place[7]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_8)
                place[7]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_8)
                place[7]=0
            }
        }
        place9Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_place9) as ImageView

            if(place[8]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_on_9)
                place[8]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_place_off_9)
                place[8]=0
            }
        }



        love1Button = findViewById(R.id.preference_love1) as ImageButton
        love2Button = findViewById(R.id.preference_love2) as ImageButton
        love3Button = findViewById(R.id.preference_love3) as ImageButton
        love4Button = findViewById(R.id.preference_love4) as ImageButton
        love5Button = findViewById(R.id.preference_love5) as ImageButton
        love6Button = findViewById(R.id.preference_love6) as ImageButton
        love7Button = findViewById(R.id.preference_love7) as ImageButton
        love8Button = findViewById(R.id.preference_love8) as ImageButton
        love9Button = findViewById(R.id.preference_love9) as ImageButton
        love10Button = findViewById(R.id.preference_love10) as ImageButton
        love11Button = findViewById(R.id.preference_love11) as ImageButton

        love1Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love1) as ImageView

            if(love[0]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_1)
                love[0]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_1)
                love[0]=0
            }
        }
        love2Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love2) as ImageView

            if(love[1]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_2)
                love[1]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_2)
                love[1]=0
            }
        }
        love3Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love3) as ImageView

            if(love[2]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_3)
                love[2]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_3)
                love[2]=0
            }
        }
        love4Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love4) as ImageView

            if(love[3]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_4)
                love[3]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_4)
                love[3]=0
            }
        }
        love5Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love5) as ImageView

            if(love[4]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_5)
                love[4]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_5)
                love[4]=0
            }
        }
        love6Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love6) as ImageView

            if(love[5]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_6)
                love[5]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_6)
                love[5]=0
            }
        }
        love7Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love7) as ImageView

            if(love[6]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_7)
                love[6]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_7)
                love[6]=0
            }
        }
        love8Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love8) as ImageView

            if(love[7]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_8)
                love[7]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_8)
                love[7]=0
            }
        }
        love9Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love9) as ImageView

            if(love[8]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_9)
                love[8]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_9)
                love[8]=0
            }
        }
        love10Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love10) as ImageView

            if(love[9]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_10)
                love[9]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_10)
                love[9]=0
            }
        }
        love11Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_love11) as ImageView

            if(love[10]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_on_11)
                love[10]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_love_off_11)
                love[10]=0
            }
        }
        subject1Button = findViewById(R.id.preference_subject1) as ImageButton
        subject2Button = findViewById(R.id.preference_subject2) as ImageButton
        subject3Button = findViewById(R.id.preference_subject3) as ImageButton
        subject4Button = findViewById(R.id.preference_subject4) as ImageButton
        subject5Button = findViewById(R.id.preference_subject5) as ImageButton
        subject6Button = findViewById(R.id.preference_subject6) as ImageButton
        subject7Button = findViewById(R.id.preference_subject7) as ImageButton
        subject8Button = findViewById(R.id.preference_subject8) as ImageButton
        subject9Button = findViewById(R.id.preference_subject9) as ImageButton
        subject10Button = findViewById(R.id.preference_subject10) as ImageButton
        subject11Button = findViewById(R.id.preference_subject11) as ImageButton
        subject12Button = findViewById(R.id.preference_subject12) as ImageButton

        subject1Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject1) as ImageView

            if(subject[0]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_1)
                subject[0]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_1)
                subject[0]=0
            }
        }
        subject2Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject2) as ImageView

            if(subject[1]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_2)
                subject[1]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_2)
                subject[1]=0
            }
        }
        subject3Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject3) as ImageView

            if(subject[2]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_3)
                subject[2]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_3)
                subject[2]=0
            }
        }
        subject4Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject4) as ImageView

            if(subject[3]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_4)
                subject[3]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_4)
                subject[3]=0
            }
        }
        subject5Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject5) as ImageView

            if(subject[4]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_5)
                subject[4]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_5)
                subject[4]=0
            }
        }
        subject6Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject6) as ImageView

            if(subject[5]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_6)
                subject[5]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_6)
                subject[5]=0
            }
        }
        subject7Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject7) as ImageView

            if(subject[6]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_7)
                subject[6]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_7)
                subject[6]=0
            }
        }
        subject8Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject8) as ImageView

            if(subject[7]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_8)
                subject[7]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_8)
                subject[7]=0
            }
        }
        subject9Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject9) as ImageView

            if(subject[8]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_9)
                subject[8]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_9)
                subject[8]=0
            }
        }
        subject10Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject10) as ImageView

            if(subject[9]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_10)
                subject[9]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_10)
                subject[9]=0
            }
        }
        subject11Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject11) as ImageView

            if(subject[10]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_11)
                subject[10]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_11)
                subject[10]=0
            }
        }
        subject12Button!!.setOnClickListener{
            var image : ImageView? = null
            image=findViewById(R.id.preference_subject12) as ImageView

            if(subject[11]==0){
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_on_12)
                subject[11]=1
            }
            else{
                image!!.setBackgroundResource(R.drawable.mypage_preference_subject_off_12)
                subject[11]=0
            }
        }



    }

}