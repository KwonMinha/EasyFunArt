package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.RecoData

/**
 * Created by minha on 2018-01-09.
 */
data class RecoResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : ArrayList<RecoData>
)