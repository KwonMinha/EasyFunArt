package easyfunart.easyfunart.Response

import easyfunart.easyfunart.POST.facebooktoken

/**
 * Created by minha on 2018-01-12.
 */
data class SendResponse (

        var message: String,
        var status : String,
        var code : Int,
        var data : facebooktoken

)