package easyfunart.easyfunart.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.POST.PostReviewResponse
import easyfunart.easyfunart.R
import kotlinx.android.synthetic.main.activity_write_review.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class WriteReview : AppCompatActivity() {
    private val REQ_CODE_SELECT_IMAGE = 100
    private var data: Uri? = null
    private var image: MultipartBody.Part? = null
    private var networkService: NetworkService? = null

    private var TAG = "LOG::Review Write"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        networkService = ApplicationController.instance!!.networkService

        val ex_id : Int = intent.getIntExtra("ex_id", 100)
        val ex_id_string : String = ex_id.toString()




//        emptyImageSet()

        review_write_ok.setOnClickListener {
            writeContent(ex_id_string)
        }

        review_write_gallery.setOnClickListener {
            changeImage()
        }

    }

    fun emptyImageSet(){
        try {
            //if(ApplicationController.getInstance().is)

            val options = BitmapFactory.Options()

            var input: InputStream? = null // here, you need to get your context.
            try {
                input = contentResolver.openInputStream(Uri.parse(""))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
            // val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

            ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
            // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!
            image = MultipartBody.Part.createFormData("image", "", photoBody)
            //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);
            Log.v("이미지", image.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!
                    image = MultipartBody.Part.createFormData("image", photo.name, photoBody)
                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(review_write_img)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }



    fun writeContent(ex_id_string : String) {

//        val user_token = RequestBody.create(MediaType.parse("text/plain"), CommonData.user_token)
        val exId = RequestBody.create(MediaType.parse("text/plain"), ex_id_string)
        val reviewContent = RequestBody.create(MediaType.parse("text/plain"), review_write_content.toString())
        val reviewGrade = RequestBody.create(MediaType.parse("text/plain"), ratingbar2.rating.toString())
        val reviewWatchDate = RequestBody.create(MediaType.parse("text/plain"), "")


        Log.d("토큰1",CommonData.user_token!!)
        Log.d("토큰2",reviewContent.toString())
        Log.d("토큰3",reviewGrade.toString())
        Log.d("토큰4",reviewWatchDate.toString())

        var postContent = networkService!!.postReview(CommonData.user_token!!, exId, reviewContent, reviewGrade, reviewWatchDate, image)
        postContent.enqueue(object : Callback<PostReviewResponse> {
            override fun onResponse(call: Call<PostReviewResponse>?, response: Response<PostReviewResponse>?) {
                Log.d(TAG, "성공")
                if (response!!.isSuccessful) {
                    if (response!!.body().status.equals("success")) {
                        Log.d(TAG, "진짜성공")
                        ApplicationController.instance!!.makeToast("작성 완료")
                        finish()
                    } else {
                        ApplicationController.instance!!.makeToast("작성 실패")
                    }
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("네트워크 상태 확인")
                Log.d(TAG, "통신실패ㅠㅠ")
            }
        })
    }
}
