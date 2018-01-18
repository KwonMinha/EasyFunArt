package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import easyfunart.easyfunart.R

class ResultActivity_last : AppCompatActivity(){

    var yesButton: Button?=null //알았어요 버튼

    override fun onCreate(savedInstanceState: Bundle?){

        initActivity()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_last)

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }, 2000) //3초 후에 login액티비티로 넘어감



    }

    fun initActivity(){
//        yesButton = findViewById(R.id.result6_yesButton_btn) as Button
    }



}