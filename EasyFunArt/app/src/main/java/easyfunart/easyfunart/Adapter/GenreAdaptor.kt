package easyfunart.easyfunart.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import easyfunart.easyfunart.Activity.ResultActivity_2
import easyfunart.easyfunart.Activity.ResultActivity_place2
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Data.GenreData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.GenreFootViewHolder
import easyfunart.easyfunart.ViewHolder.GenreHeadViewHolder
import easyfunart.easyfunart.ViewHolder.GenreViewHolder

/**
 * Created by minha on 2018-01-12.
 */
class GenreAdaptor (var dataList : ArrayList<GenreData>, var curContext : Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_HEADER: Int = 0
    val TYPE_ITEM: Int = 1
    val TYPE_FOOTER: Int = 2

    var intint :Int? = null


    var splitList : List<String>?= null



    private var onItemClick: View.OnClickListener? = null


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        intint=holder!!.adapterPosition
        Log.e("여기 adapterposition", intint.toString())

        if (holder is GenreViewHolder) {
            val baseViewHolder: GenreViewHolder = holder
            var genrecheck = arrayOf(R.drawable.genre_on_1, R.drawable.genre_on_2, R.drawable.genre_on_3,
                    R.drawable.genre_on_4, R.drawable.genre_on_5, R.drawable.genre_on_6, R.drawable.genre_on_7,
                    R.drawable.genre_on_8, R.drawable.genre_on_9,R.drawable.genre_on_10, R.drawable.genre_on_11,R.drawable.genre_on_12 )


            baseViewHolder!!.genre_image.setBackgroundResource(dataList!!.get(position-1).genre_image)
            baseViewHolder!!.genre_layout.setOnClickListener{

                val idx : Int = dataList!!.get(position-1).genre_image
                CommonData.genreIntlist!![position-1]=1
                Log.e("여기 commongenreintlist 개", CommonData.genreIntlist.toString())
                //서버에게 붙일 변수 string 으로 변환d
                CommonData.sendgenre = CommonData.genreIntlist!!.toString()
                CommonData.sendgenre = CommonData.sendgenre!!.substring(1,38)

                var splitList : List<String>?= null
                splitList= CommonData.sendgenre!!.split(" ")

                var splitstr : String?=null
                for(i in 0..12)
                    splitstr += splitList[i]
                splitstr=splitstr!!.substring(4, 27)

                CommonData.sendgenre=splitstr

                Log.e("여기genre완성", CommonData.sendgenre)

                var dataint : Int= holder!!.adapterPosition
                baseViewHolder!!.genre_image.setImageResource(genrecheck[dataint-1])

//                baseViewHolder!!.genre_image.setOnClickListener{
//                    var dataint : Int= holder!!.adapterPosition
//                    Log.e("여기 dataint", dataint.toString())
//
//                    baseViewHolder!!.genre_image.setImageResource(genrecheck[dataint-1])
//
//                }

            }


        }      else if (holder is GenreFootViewHolder) {
            val footViewHolder: GenreFootViewHolder? = holder
            footViewHolder!!.nextButton!!.setOnClickListener {
                val intent = Intent(curContext, ResultActivity_place2::class.java)
                startActivity(curContext, intent, null)
            }
            footViewHolder.prevButton!!.setOnClickListener {
                val intent = Intent(curContext, ResultActivity_2::class.java)
            }
        }
        else if(holder is GenreHeadViewHolder){
            val headViewHolder : GenreHeadViewHolder = holder
        }
    }

    override fun getItemCount(): Int = dataList!!.size+2

    fun setOnItemClickListener(l: View.OnClickListener) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_HEADER) {
            Log.e("TAG", "TYPE HEADER")
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.genre_header, parent, false)
            v.setOnClickListener(onItemClick)
            return GenreHeadViewHolder(v)

        } else if (viewType == TYPE_FOOTER) {
            Log.e("g", "여기2")
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.genre_footer, parent, false)
            return GenreFootViewHolder(v)
        } else {
            val mainView: View = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.genre_items, parent, false)
            Log.e("g", "여기3")
            //  mainView.setOnClickListener(onItemClick)


            return GenreViewHolder(mainView)


        }

    }
    override fun getItemViewType(position: Int): Int {
        Log.e("size", dataList.size.toString())
        if(position==0){
            return TYPE_HEADER
        } else if (position==dataList.size+1) {
            return TYPE_FOOTER
        }
        return TYPE_ITEM
    }

//    fun onClick(view:View) {
//        val itemPosition = .getChildLayoutPosition(view)
//        val item = GenreData.get(itemPosition)
//        Toast.makeText(Context, item, Toast.LENGTH_LONG).show()
//    }
}