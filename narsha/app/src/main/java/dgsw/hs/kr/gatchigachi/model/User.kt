package dgsw.hs.kr.gatchigachi.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class User(
        val idx: Long?,
        val id: String,
        val name:String,
        val pw: String,
        val mail: String,
        val inter: String,
        val git: String,
        val field: String,
        val tec: List<String>,
        val pos: String,
        val phone: String
        ){
    var token :String = ""
    var isMe : Int = 0


    class Deserializer: ResponseDeserializable<Array<User>> {
       override fun deserialize(content: String): Array<User>? = Gson().fromJson(content, Array<User>::class.java)
   }

}
