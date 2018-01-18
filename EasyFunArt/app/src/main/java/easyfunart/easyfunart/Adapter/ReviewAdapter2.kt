package easyfunart.easyfunart.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.GET.MyPageUserReviewData
import easyfunart.easyfunart.R
import easyfunart.easyfunart.ViewHolder.ReviewViewHolder2

/**
 * Created by minha on 2018-01-13.
 */
class ReviewAdapter2(var dataList: ArrayList<MyPageUserReviewData>?, var requestManager: RequestManager) : RecyclerView.Adapter<ReviewViewHolder2>() {

    private var onItemClick: View.OnClickListener? = null

    override fun onBindViewHolder(holder: ReviewViewHolder2?, position: Int) {

        if (dataList!!.get(position).review_image == null)
            requestManager.load(R.drawable.img_profie_basic).into(holder!!.reviewImg)
        else
            requestManager.load(dataList!!.get(position).review_image).into(holder!!.reviewImg)

        holder!!.reviewName.text = dataList!!.get(position).user_nickname
        holder!!.reviewContent.text = dataList!!.get(position).review_content

        if (dataList!!.get(position).review_grade == 0)
            holder!!.reviewStar.setImageResource(R.drawable.detail_review_star_0)
        else if (dataList!!.get(position).review_grade == 1)
            holder!!.reviewStar.setImageResource(R.drawable.detail_review_star_1)
        else if (dataList!!.get(position).review_grade == 2)
            holder!!.reviewStar.setImageResource(R.drawable.detail_review_star_2)
        else if (dataList!!.get(position).review_grade == 3)
            holder!!.reviewStar.setImageResource(R.drawable.detail_review_star_3)
        else if (dataList!!.get(position).review_grade == 4)
            holder!!.reviewStar.setImageResource(R.drawable.detail_review_star_4)
        else if (dataList!!.get(position).review_grade == 5)
            holder!!.reviewStar.setImageResource(R.drawable.detail_review_star_5)

        //holder!!.reviewStar.setImageResource(dataList!!.get(position).review_grade)

        holder!!.reviewDate.text = dataList!!.get(position).review_watch_date
        holder!!.beforeDate.text = dataList!!.get(position).review_date
        holder!!.reviewTitle.text = dataList!!.get(position).ex_title

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReviewViewHolder2 {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.review_item, parent, false)
        mainView.setOnClickListener(onItemClick)

        return ReviewViewHolder2(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }

}
