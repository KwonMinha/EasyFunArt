package easyfunart.easyfunart.Response

import easyfunart.easyfunart.POST.jytitle

/**
 * Created by minha on 2018-01-12.
 */
data class TitleResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : jytitle
)