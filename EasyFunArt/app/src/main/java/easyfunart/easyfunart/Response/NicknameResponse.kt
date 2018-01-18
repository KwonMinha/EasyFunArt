package easyfunart.easyfunart.Response

/**
 * Created by minha on 2018-01-12.
 */
data class NicknameResponse (
        var status: String,
        var code: Int,
        var message: String,
        var checkFlag : Int
)