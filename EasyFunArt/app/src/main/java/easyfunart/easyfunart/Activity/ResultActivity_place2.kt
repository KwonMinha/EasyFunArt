package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.R

class ResultActivity_place2 : AppCompatActivity() {
    var placeButton_seochon : Button? = null //장소 선택 서촌 버튼
    var placeButton_gangnam : Button? = null //장소 선택 강남 버튼
    var placeButton_hongdae : Button? = null //장소 선택 홍대 버튼
    var placeButton_insadong : Button? = null//장소 선택 인사동 버튼
    var placeButton_itaewon : Button? = null//장소 선택 이태원 버튼
    var placeButton_chungmuro : Button? = null//장소 선택 충무로 버튼
    var placeButton_daehakro : Button? = null//장소 선택 대학로 버튼
    var placeButton_bukchon : Button? = null//장소 선택 북촌 버튼
    var placeButton_jonggak : Button?=null //장소 선택 종각 버튼

    var nextButton : Button?=null //다음 버튼
    var prevButton : Button?=null //이전 버튼

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_place)

        initActivity()

        /*각 버튼을 누르면 정보 저장*/
        placeButton_seochon!!.setOnClickListener{
            CommonData.placeIntlist!![0]=1
            change()
            placeButton_seochon!!.setBackgroundResource(R.drawable.prefrence_place_off_1)

        }

        placeButton_gangnam!!.setOnClickListener{
            CommonData.placeIntlist!![1]=1
            change()
            placeButton_gangnam!!.setBackgroundResource(R.drawable.prefrence_place_off_2)


        }

        placeButton_hongdae!!.setOnClickListener{
            CommonData.placeIntlist!![2]=1
            change()
            placeButton_hongdae!!.setBackgroundResource(R.drawable.prefrence_place_off_3)

        }

        placeButton_insadong!!.setOnClickListener{
            CommonData.placeIntlist!![3]=1
            change()
            placeButton_insadong!!.setBackgroundResource(R.drawable.prefrence_place_off_4)
        }

        placeButton_itaewon!!.setOnClickListener{
            CommonData.placeIntlist!![4]=1
            change()
            placeButton_itaewon!!.setBackgroundResource(R.drawable.prefrence_place_off_5)

        }

        placeButton_chungmuro!!.setOnClickListener{
            CommonData.placeIntlist!![5]=1
            change()
            placeButton_chungmuro!!.setBackgroundResource(R.drawable.prefrence_place_off_6)

        }

        placeButton_daehakro!!.setOnClickListener{
            CommonData.placeIntlist!![6]=1
            change()
            placeButton_daehakro!!.setBackgroundResource(R.drawable.prefrence_place_off_7)

        }

        placeButton_jonggak!!.setOnClickListener{
            CommonData.placeIntlist!![7]=1
            change()
            placeButton_jonggak!!.setBackgroundResource(R.drawable.prefrence_place_off_8)

        }

        placeButton_bukchon!!.setOnClickListener{
            CommonData.placeIntlist!![8]=1
            change()
            placeButton_bukchon!!.setBackgroundResource(R.drawable.prefrence_place_off_9)

        }




        /*이전, 다음 버튼 누르면 화면 전환*/
        prevButton!!.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_genre1::class.java)
            startActivity(intent)
        } //이전 버튼을 누르면 result_4로 가도록 함

        nextButton!!.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_mood3::class.java)
            startActivity(intent)
        } //다음 버튼을 누르면 result_mood로 가도록 함

    }

    fun change() {

        CommonData.sendplace = CommonData.placeIntlist!!.toString()
        Log.e("여기sendplace", CommonData.sendplace)

        CommonData.sendplace = CommonData.sendplace!!.substring(1, 26)
        Log.e("여기 sendplace자름", CommonData.sendplace)
        var splitplacelist: List<String>? = null
        splitplacelist = CommonData.sendplace!!.split(" ")
        Log.e("여기placelist", splitplacelist.toString())
//
        var splitplacestr: String? = null
        for (i in 0..8)
            splitplacestr += splitplacelist[i]
        Log.e("여기 strplacestr", splitplacestr)
        splitplacestr=splitplacestr!!.substring(4,21)
        CommonData.sendplace = splitplacestr
        Log.e("여기splitplacestr완성", CommonData.sendplace)
//        splittitlestr.trim()

    }

    fun initActivity(){
        placeButton_seochon = findViewById(R.id.resultplace_seochonButton_btn) as Button
        placeButton_gangnam= findViewById(R.id.resultplace_gangnamButton_btn) as Button
        placeButton_hongdae= findViewById(R.id.resultplace_hongdaeButton_btn) as Button
        placeButton_insadong=findViewById(R.id.resultplace_insadongButton_btn) as Button
        placeButton_itaewon= findViewById(R.id.resultplace_itaewonButton_btn) as Button
        placeButton_chungmuro= findViewById(R.id.resultplace_chungmuroButton_btn) as Button
        placeButton_daehakro= findViewById(R.id.resultplace_daehakroButton_btn) as Button
        placeButton_jonggak=findViewById(R.id.resultplace_jonggak_btn) as Button
        placeButton_bukchon= findViewById(R.id.resultplace_bukchonButton_btn) as Button

        nextButton = findViewById(R.id.resultplace_nextButton_btn) as Button
        prevButton = findViewById(R.id.resultplace_prevButton_btn) as Button
        //액티비티에서 사용할 뷰들을 연결
    }
}