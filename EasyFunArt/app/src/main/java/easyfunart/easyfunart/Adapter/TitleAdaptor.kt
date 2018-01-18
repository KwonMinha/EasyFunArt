package easyfunart.easyfunart.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import easyfunart.easyfunart.Data.TitleData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.TitleFootViewHolder
import easyfunart.easyfunart.ViewHolder.TitleHeadViewHolder
import easyfunart.easyfunart.ViewHolder.TitleViewHolder

/**
 * Created by minha on 2018-01-13.
 */
class TitleAdaptor (var dataList : ArrayList<TitleData>, var curContext : Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val TYPE_HEADER : Int =0
    val TYPE_ITEM: Int = 1
    val TYPE_FOOTER: Int = 2

    private var onItemClick: View.OnClickListener? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        //뷰 타입이 아이템일 경우
        if (holder is TitleViewHolder){
            val baseViewHolder : TitleViewHolder? = holder
            baseViewHolder!!.title_image.setImageResource(dataList!!.get(position - 1).title_image)
        } else if (holder is TitleFootViewHolder){
            // val footViewHolder : TitleFootViewHolder?=holder
//            footViewHolder!!.nextButton.setOnClickListener{
//                val intent= Intent(curContext, ResultActivity_select::class.java)
//                ContextCompat.startActivity(curContext, intent, null)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        //item()에 헤더를 붙인다
        if (viewType == TYPE_HEADER) {
            Log.e("g", "여기1")
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.title_header, parent, false)
            v.setOnClickListener(onItemClick)
            return TitleHeadViewHolder(v)

        } else if (viewType == TYPE_FOOTER) {
            Log.e("g", "여기2")
            val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.title_footer, parent, false)
            return TitleFootViewHolder(v)
        } else  {
            val mainView: View = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.title_items, parent, false)
            Log.e("g", "여기3")
            mainView.setOnClickListener(onItemClick)
            return TitleViewHolder(mainView)
        }
    }

    override fun getItemCount(): Int = dataList!!.size+2 // header 와 putter 각각 한개씩 붙으므로 +2 해준다

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return TYPE_HEADER
        } else if (position==dataList.size+1) {
            return TYPE_FOOTER
        }
        return TYPE_ITEM
    }


    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }
}