package easyfunart.easyfunart.GET

/**
 * Created by minha on 2018-01-13.
 */
data class MyPageUserData (
        var user_nickname : String,
        var user_profile : String,
        var user_place : ArrayList<Int>,
        var user_genre : ArrayList<Int>,
        var user_mood : ArrayList<Int>,
        var user_subject : ArrayList<Int>
)