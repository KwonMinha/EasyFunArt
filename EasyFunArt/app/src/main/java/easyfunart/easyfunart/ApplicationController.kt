package easyfunart.easyfunart

import android.app.Application
import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by minha on 2018-01-05.
 */
class ApplicationController : Application() {

    var networkService: NetworkService? = null
        private set
    val baseUrl = "http://13.124.97.161:3000/"
    var appContext: Context? = null
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        instance = this

        buildNetwork()
    }

    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var instance: ApplicationController? = null
    }
}