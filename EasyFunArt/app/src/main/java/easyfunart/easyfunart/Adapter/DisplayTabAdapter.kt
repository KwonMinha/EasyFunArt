package easyfunart.easyfunart.Adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import easyfunart.easyfunart.Fragment.DisplayFragment
import easyfunart.easyfunart.Fragment.GalleryFragment
import easyfunart.easyfunart.Fragment.ReviewFragment

//import easyfunart.easyfunart.Fragment.GalleryFragment
//import easyfunart.easyfunart.Fragment.ReviewFragment
//import easyfunart.easyfunart.Fragment.SearchFirstFragment

/**
 * Created by minha on 2018-01-05.
 */
class DisplayTabAdapter (var context: Context, fm : FragmentManager?, var ex_id : Int,
                         var ex_title : String, var gallery_name : String, var gallery_id : Int) : FragmentStatePagerAdapter(fm){
    var tabCount : Int = 0
    var firstTab : DisplayFragment? = null
    var secondTab : GalleryFragment? = null
    var thirdTab : ReviewFragment? = null
    var exId : Int = 0

    constructor(context: Context, fm : FragmentManager?, tabCount : Int, ex_id: Int,
                ex_title: String, gallery_name: String, gallery_id: Int) : this(context, fm, ex_id, ex_title, gallery_name, gallery_id) {
        this.tabCount = tabCount
        this.firstTab = DisplayFragment()
        this.secondTab = GalleryFragment()
        this.thirdTab = ReviewFragment()
        this.exId = ex_id
    }

    override fun getItem(position: Int): Fragment? {

        when(position){
            0-> {
                val bundle = Bundle()
                bundle.putInt("ex_id", exId)
                firstTab!!.arguments = bundle
                return  firstTab
            }
            1-> {
                val bundle = Bundle()
                bundle.putInt("ex_id", exId)
                bundle.putInt("gallery_id", gallery_id)
                secondTab!!.arguments = bundle
                return secondTab
            }
            2-> {
                val bundle = Bundle()
                bundle.putInt("ex_id", exId)
                bundle.putString("ex_title", ex_title)
                bundle.putString("gallery_name", gallery_name)
                thirdTab!!.arguments = bundle
                return thirdTab
            }
        }
        return null
    }

    override fun getCount(): Int = tabCount
}