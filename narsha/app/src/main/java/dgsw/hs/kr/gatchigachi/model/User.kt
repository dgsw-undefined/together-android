package dgsw.hs.kr.gatchigachi.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class User(
        val idx: Long?,
        val name:String,
        val id: String,
        val pw: String,
        val phone: String,
        val tec: ArrayList<String>,
        val inter: String,
        val git: String,
        val field: String,
        val pos: String,
        val mail: String
){
    var token :String = ""

    class Deserializer: ResponseDeserializable<Array<User>> {
       override fun deserialize(content: String): Array<User>? = Gson().fromJson(content, Array<User>::class.java)
   }

}
