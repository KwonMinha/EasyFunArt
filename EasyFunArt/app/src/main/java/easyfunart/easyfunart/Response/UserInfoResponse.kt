package easyfunart.easyfunart.Response

import easyfunart.easyfunart.POST.userinfotoken

/**
 * Created by minha on 2018-01-12.
 */
data class UserInfoResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : userinfotoken

)