package easyfunart.easyfunart.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.GET.MyPageUserLikeData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.MyinfoViewHolder

/**
 * Created by minha on 2018-01-13.
 */
class MyinfoAdapter(var dataList : ArrayList<MyPageUserLikeData>?, var requestManager: RequestManager) : RecyclerView.Adapter<MyinfoViewHolder>() {
    private var onItemClick : View.OnClickListener? = null

    override fun onBindViewHolder(holder: MyinfoViewHolder?, position: Int) {
        if(dataList!!.get(position).ex_image == null)
            requestManager.load(R.drawable.pic1).into(holder!!.exhibitImage)
        else
            requestManager.load(dataList!!.get(position).ex_image).into(holder!!.exhibitImage)
        holder!!.exhibitName.text=dataList!!.get(position).ex_title
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyinfoViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.myinfo_items, parent, false)
        mainView.setOnClickListener(onItemClick)

        return MyinfoViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }
}