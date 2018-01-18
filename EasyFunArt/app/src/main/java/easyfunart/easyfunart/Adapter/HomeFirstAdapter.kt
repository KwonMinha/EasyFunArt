package easyfunart.easyfunart.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.GET.HomeTopData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.HomeFirstViewHolder

/**
 * Created by minha on 2018-01-05.
 */
class HomeFirstAdapter (var context: Context, var dataList : ArrayList<HomeTopData>?, var requestManager: RequestManager) : RecyclerView.Adapter<HomeFirstViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onBindViewHolder(holder: HomeFirstViewHolder?, position: Int) {
        var holder : HomeFirstViewHolder = holder as HomeFirstViewHolder
        if(dataList!!.get(position).ex_image == null)
            requestManager.load(R.drawable.poster).into(holder!!.homeFirstImage)
        else
            requestManager.load(dataList!!.get(position).ex_image).into(holder!!.homeFirstImage)
        holder!!.homeFirstTitle.text = dataList!!.get(position).ex_title

        //var ex_start_date = dataList!!.get(position).

        // 전시 상세정보 페이지로 넘어가기
//        holder!!.homeFirstImage.setOnClickListener {
////            val intent = Intent(context, SearchActivity::class.java)
////            intent.putExtra("ex_id", dataList!!.get(position).ex_id)
////            context.startActivity(intent)
//
//            // 전시상세보기 클릭
//                val intent2 = Intent(context, DisplayFragment::class.java)
//                val intent = Intent(context, DisplayActivity::class.java)
//                intent.putExtra("ex_id", dataList!!.get(position).ex_id)
//                intent.putExtra("ex_title", dataList!!.get(position).ex_title)
//                intent.putExtra("ex_start_date", dataList!!.get(position).ex_start_date)
//                intent.putExtra("ex_end_date", dataList!!.get(position).ex_end_date)
//                intent.putExtra("gallery_name", dataList!!.get(position).gallery_name)
//                intent.putExtra("gallery_id", dataList!!.get(position).gallery_id)
//                intent.putExtra("ex_average_grade", formatted)
//                intent.putExtra("likeFlag", dataList!!.get(position).likeFlag)
//                intent2.putExtra("ex_id", dataList!!.get(position).ex_id)
//                context.startActivity(intent)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeFirstViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.home_first_item, parent, false)
        mainView.setOnClickListener(onItemClick)

        return HomeFirstViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }
}