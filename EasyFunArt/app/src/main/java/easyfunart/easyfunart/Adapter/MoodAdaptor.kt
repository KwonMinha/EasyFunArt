package easyfunart.easyfunart.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import easyfunart.easyfunart.Activity.ResultActivity_place2
import easyfunart.easyfunart.Activity.ResultActivity_title4
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Data.MoodData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.MoodFootViewHolder
import easyfunart.easyfunart.ViewHolder.MoodHeadViewHolder
import easyfunart.easyfunart.ViewHolder.MoodViewHolder

/**
 * Created by minha on 2018-01-12.
 */
class MoodAdaptor (var dataList : ArrayList<MoodData>, var curContext : Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val TYPE_HEADER : Int =1
    val TYPE_ITEM: Int = 2
    val TYPE_FOOTER: Int = 3

    private var onItemClick: View.OnClickListener? = null

    var moodcheck = arrayOf(R.drawable.prefrence_love_on_1, R.drawable.prefrence_love_on_2, R.drawable.prefrence_love_on_3,
            R.drawable.prefrence_love_on_4, R.drawable.prefrence_love_on_5, R.drawable.prefrence_love_on_6, R.drawable.prefrence_love_on_7,
            R.drawable.prefrence_love_on_8, R.drawable.prefrence_love_on_9,R.drawable.prefrence_love_on_10, R.drawable.prefrence_love_on_11
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {


        //뷰 타입이 아이템일 경우
        if (holder is MoodViewHolder) {
            val baseViewHolder: MoodViewHolder? = holder
            baseViewHolder!!.mood_image.setImageResource(dataList!!.get(position - 1).mood_image)

            baseViewHolder!!.mood_image.setOnClickListener {
                var dataint: Int = holder!!.adapterPosition
                Log.e("여기 dataint", dataint.toString())

                baseViewHolder!!.mood_image.setImageResource(moodcheck[dataint - 1])


                val idx: Int = dataList!!.get(position - 1).mood_image
                CommonData.moodIntlist!![position - 1] = 1
                Log.e("여기 commongenreintlist 개", CommonData.genreIntlist.toString())
                //서버에게 붙일 변수 string 으로 변환d
                CommonData.sendmood = CommonData.moodIntlist!!.toString()
                CommonData.sendmood = CommonData.sendmood!!.substring(1, 32)


                var splitList: List<String>? = null
                splitList = CommonData.sendmood!!.split(" ")

                var splitstr: String? = null
                for (i in 0..10)
                    splitstr += splitList[i]
                splitstr = splitstr!!.substring(4, 25)

                CommonData.sendgenre = splitstr

                Log.e("여기genre완성", CommonData.sendgenre)

                baseViewHolder!!.mood_image.setOnClickListener {
                    var dataint: Int = holder!!.adapterPosition
                    Log.e("여기 dataint", dataint.toString())

                    baseViewHolder!!.mood_image.setImageResource(moodcheck[dataint - 1])


                }
            }
        }

        else if (holder is MoodFootViewHolder){
            val footViewHolder : MoodFootViewHolder?=holder
            footViewHolder!!.nextButton?.setOnClickListener{
                val intent= Intent(curContext, ResultActivity_title4::class.java)
                startActivity(curContext, intent, null)
            }
            footViewHolder!!.prevButton?.setOnClickListener {
                val intent = Intent(curContext, ResultActivity_place2::class.java)
                startActivity(curContext, intent, null)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        //item()에 헤더를 붙인다
        if (viewType == TYPE_HEADER) {
            Log.e("g", "여기1")
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.mood_header, parent, false)
            v.setOnClickListener(onItemClick)
            return MoodHeadViewHolder(v)

        } else if (viewType == TYPE_FOOTER) {
            Log.e("g", "여기2")
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.mood_footer, parent, false)
            return MoodFootViewHolder(v)
        } else  {
            val mainView: View = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.mood_items, parent, false)
            Log.e("g", "여기3")
            mainView.setOnClickListener(onItemClick)
            return MoodViewHolder(mainView)
        }
    }

    override fun getItemCount(): Int = dataList!!.size+2 // header 와 putter 각각 한개씩 붙으므로 +2 해준다

    override fun getItemViewType(position: Int): Int {
        Log.e("size", dataList.size.toString())
        if(position==0){
            return TYPE_HEADER
        } else if (position==dataList.size+1) {
            return TYPE_FOOTER
        }
        return TYPE_ITEM
    }


    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l

    }

}






