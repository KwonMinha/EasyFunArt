package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import easyfunart.easyfunart.Adapter.GenreAdaptor
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Data.GenreData
import easyfunart.easyfunart.R

class ResultActivity_genre1 : AppCompatActivity(), View.OnClickListener{


    /*recycler 관련 변수*/
    private var genreList : RecyclerView?=null
    private var genreDatas : ArrayList<GenreData>?=null
    private var adaptor : GenreAdaptor?=null

    private var OnItemClick : View.OnClickListener?=null


    var nextButton : Button?=null //다음 버튼
    var prevButton : Button?=null //이전 버튼
    /*서버에 보내는 것도 구현 여기에 해야해 지윤*/

    override fun onClick(p0: View?) {


        var genrecheck = arrayOf(R.drawable.genre_on_1, R.drawable.genre_on_2, R.drawable.genre_on_3,
                R.drawable.genre_on_4, R.drawable.genre_on_5, R.drawable.genre_on_6, R.drawable.genre_on_7,
                R.drawable.genre_on_8, R.drawable.genre_on_9,R.drawable.genre_on_10, R.drawable.genre_on_11,R.drawable.genre_on_12 )



        val idx : Int = genreList!!.getChildAdapterPosition(p0)
        CommonData.genreIntlist!![idx]=1
        Log.e("여기 commongenreintlist 개", CommonData.genreIntlist.toString())
        //서버에게 붙일 변수 string 으로 변환d
        CommonData.sendgenre = CommonData.genreIntlist!!.toString()
        CommonData.sendgenre = CommonData.sendgenre!!.substring(1,38)



        var splitList : List<String>?= null
        splitList=CommonData.sendgenre!!.split(" ")

        var splitstr : String?=null
        for(i in 0..12)
            splitstr += splitList[i]
        splitstr=splitstr!!.substring(4, 27)

        CommonData.sendgenre=splitstr

        Log.e("여기genre완성", CommonData.sendgenre)

    }



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result_genre)

        initActivity()

        var place = arrayOf(0,0,0,0,0,0,0,0,0,0,0)


        genreList = findViewById(R.id.genre_list_recycler) as RecyclerView
        genreList!!.layoutManager = LinearLayoutManager(this)
        genreDatas = ArrayList<GenreData>()

        genreDatas!!.add(GenreData(R.drawable.genre_off_1,"동양화"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_2, "서양화"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_3,"도예"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_4,"금속"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_5,"일러스트"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_6,"목공"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_7,"현대미술"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_8,"팝아트"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_9,"풍경화"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_10, "카툰"))
        genreDatas!!.add(GenreData(R.drawable.genre_off_11,"인물화"))




        //어댑터 등록
        adaptor = GenreAdaptor(genreDatas!!, this)
        adaptor!!.setOnItemClickListener(this)
        genreList!!.adapter=adaptor



        //   이전, 다음 버튼 누르면 화면 전환
        prevButton!!.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_2::class.java)
            startActivity(intent)
        } //이전 버튼을 누르면 result_info로 가도록 함

        nextButton!!.setOnClickListener{
            val intent= Intent(applicationContext, ResultActivity_place2::class.java)
            startActivity(intent)
        } //다음 버튼을 누르면 result_place로 가도록 함

    }

    fun initActivity(){

        nextButton = findViewById(R.id.resultgenre_nextButton_btn) as Button
        prevButton = findViewById(R.id.resultgenre_prevButton_btn) as Button
    }


}