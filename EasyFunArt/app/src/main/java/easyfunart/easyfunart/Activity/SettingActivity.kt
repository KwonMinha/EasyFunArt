package easyfunart.easyfunart.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import easyfunart.easyfunart.R

class SettingActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase : Context){
        //super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    var logoutButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        logoutButton = findViewById(R.id.logout_btn) as Button

        logoutButton!!.setOnClickListener{
            val i = Intent(this, MypageActivity::class.java) //로그인 페이지 부재 ***********
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(i)
        }

    }
}