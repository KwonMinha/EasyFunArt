package easyfunart.easyfunart.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import easyfunart.easyfunart.R

class SearchIntroActivity : AppCompatActivity() ,View.OnClickListener{
    override fun onClick(v: View?) {

    }
    private var TAG: String = "LOG::SearchIntro"
    private var editText : EditText ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_intro)

        editText = findViewById(R.id.search_intro_editText) as EditText
        editText!!.imeOptions = EditorInfo.IME_ACTION_SEARCH
        editText!!.inputType = InputType.TYPE_CLASS_TEXT
        editText!!.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var text = editText!!.text
                if(text.length < 2) {
                    val toast = Toast.makeText(this, "길이가 너무 짧아요", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("search_text", editText!!.text.toString())
                    Log.d(TAG, "텍스트 값 : "+ editText!!.text.toString())
                    startActivity(intent)
                    finish()
                }
                true
            } else {
                false
            }
        }

//        search_intro_editText.imeOptions = EditorInfo.IME_ACTION_SEARCH
//        search_intro_editText.inputType(InputType.TYPE_CLASS_TEXT)
//        //editText.setInputType(InputType.TYPE_CLASS_TEXT);
//        search_intro_editText.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                startActivity(Intent(applicationContext, SearchActivity::class.java))
//                true
//            } else {
//                false
//            }
//        }





    }
}
