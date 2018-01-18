package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.MyPageData

/**
 * Created by minha on 2018-01-13.
 */
data class MyPageResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : MyPageData
)