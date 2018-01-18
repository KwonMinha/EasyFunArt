package easyfunart.easyfunart.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Created by minha on 2018-01-13.
 */
class PopupDialog(myContext : Context?,
                  private var content : String?, private var cancelListener : View.OnClickListener?,
                  private var okListener : View.OnClickListener?, private var okContent : String?) : Dialog(myContext, android.R.style.Theme_Translucent_NoTitleBar){


    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val lpWindow = WindowManager.LayoutParams()
//        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
//        lpWindow.dimAmount = 0.6f
//        window!!.attributes = lpWindow
//        setContentView(R.layout.popup_dialog)
//
//        if (okListener != null && cancelListener != null) {
//            ok.setOnClickListener(okListener)
//            cancel.setOnClickListener(cancelListener)
//        } else {
//
//        }
//
//        //change_title.text = content
    }

}