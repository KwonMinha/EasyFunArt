package easyfunart.easyfunart.Response

import easyfunart.easyfunart.GET.DocentDataResult

/**
 * Created by minha on 2018-01-12.
 */
data class TrackListResponse (
        var status : String,
        var code : Int,
        var message : String,
        var data : DocentDataResult



)