package easyfunart.easyfunart.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Adapter.ReviewAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.GET.ReviewLatestReview
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.ReviewResponse
import kotlinx.android.synthetic.main.fragment_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minha on 2018-01-10.
 */
class ReviewFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {

    }
    private var networkService: NetworkService? = null
    private var requestManager : RequestManager? = null

    private var reviewAdapter : ReviewAdapter? = null
    private var reviewList : ArrayList<ReviewLatestReview>? = null
    private var reviewRecyclerView : RecyclerView?= null

    private var write_review_btn : Button ? = null

    val TAG : String = " LOG::ReviewFragment"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_review, container, false)
        Log.v(TAG, "onCreateView")

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        reviewRecyclerView = v.findViewById(R.id.review_recylerView) as RecyclerView
        reviewRecyclerView!!.layoutManager = LinearLayoutManager(v.context)
        getReviewList()

        write_review_btn = v.findViewById(R.id.write_review) as Button

//        write_review_btn!!.setOnClickListener {
//            var ex_Id =  arguments.getInt("ex_id")
//            var ex_title = arguments.getString("ex_title")
//            var gallery_name = arguments.getString("gallery_name")
//
//            val intent = Intent(context, WriteReview::class.java)
//            intent.putExtra("ex_id", ex_Id)
//            intent.putExtra("ex_title", ex_title)
//            intent.putExtra("gallery_name", gallery_name)
//
//            startActivity(intent)
//        }

        return v
    }

    fun getReviewList() {
        Log.d(TAG, "ex_id = " + arguments.getInt("ex_id").toString())
        val ex_id : Int = arguments.getInt("ex_id")
        val getReviewList = networkService!!.getReviewList(ex_id)
        getReviewList.enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }

            override fun onResponse(call: Call<ReviewResponse>?, response: Response<ReviewResponse>?) {
                if (response!!. isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")){
                        Log.d(TAG, "성공2")

                        reviewAdapter = ReviewAdapter(view!!.context, response.body().data.latestReview, requestManager!!)
                        reviewList = response.body().data.latestReview
                        reviewAdapter!!.setOnItemClickListener(this@ReviewFragment)
                        review_recylerView.adapter = reviewAdapter
                    }
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
        getReviewList()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.v(TAG, "onAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }

    override fun onPause() {
        super.onPause()
//        EventBus.getInstance().unregister(this)
        Log.v(TAG, "onPause")
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v(TAG, "onViewCreated")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v(TAG, "onDetach")
    }

}
