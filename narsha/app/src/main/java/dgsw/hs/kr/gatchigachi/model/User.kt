package dgsw.hs.kr.gatchigachi.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class User(
        val idx: Long?,
        val id: String,
        val name:String,
        val pw: String,
        val email: String,
        val interested: String,
        val github: String,
        var profile:String,
        val field: String,
        var tec: List<String>,
        val position: String,
        val phone: String
        ){
    var token :String = ""
    var isMe : Int = 0


    class Deserializer: ResponseDeserializable<Array<User>> {
       override fun deserialize(content: String): Array<User>? = Gson().fromJson(content, Array<User>::class.java)
   }

}
