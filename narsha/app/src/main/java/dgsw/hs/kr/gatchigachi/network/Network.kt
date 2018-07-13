package dgsw.hs.kr.gatchigachi.network

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dgsw.hs.kr.gatchigachi.LoginActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import org.json.JSONObject

public class Network{
    var code = 100

    public fun login(id:String, pw:String, myDb:DBHelper, context:Context) {
        val json = HashMap<String,String>()
        json["id"] = id
        json["pw"] = pw

        var url = "http://115.68.182.229/go/user/signin"

        url.httpPost()
                .header("Content-Type" to "application/json")
                .body(Gson().toJson(json), Charsets.UTF_8)
                .responseJson { _, _, result ->
                    when(result){
                        is Result.Success ->{
                            val loginJson = JSONObject(result.get().content)
                            val json = loginJson.getJSONObject("Data")

                            val user = Gson().fromJson(json.toString(), User::class.java)

                            myDb.insertMyinfo(user, loginJson.getString("Token"))

                            (context as LoginActivity).notifyFinish(loginJson.getLong("Code"))
                        }
                        is Result.Failure ->{
                            Log.e("a","a")
                        }

                    }
                    println("==========================================================================")
                }
    }

    public fun getMyTeam(myDb: DBHelper) : Int{

        var URL = "http://115.68.182.229/node/team"

        URL.httpGet()
                .header(pairs = "Authorization" to myDb.selectMyToken())
                .responseJson { request, response, result ->
                    result.fold(success = {json ->
                        val teamJson = JSONObject(json.content)
                        val jsonOutput = teamJson.getJSONArray("Data").toString()
                        val listType = object : TypeToken<List<Team>>(){}.type
                        val teams : List<Team> = Gson().fromJson(jsonOutput, listType)

                        myDb.insertMyTeams(teams)

                        code = teamJson.getInt("Code")

                    }, failure = {

                    })
                }

        return code
        }
}