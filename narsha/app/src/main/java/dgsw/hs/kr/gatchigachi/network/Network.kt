package dgsw.hs.kr.gatchigachi.network

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.preference.Preference
import org.json.JSONObject

public class Network{
    var code = 100

    public fun networt_Login(id:String, pw:String, myDb:DBHelper, preference: Preference) : Int{
        val json = HashMap<String,String>()
        json.put("id",id)
        json.put("pw", pw)

        var URL = "http://115.68.182.229/go/user/signin"

        URL.httpPost()
                .header(Pair("Content-Type", "application/json"))
                .body(Gson().toJson(json))
                .responseJson { request, response, result ->
                    result.fold(success = {json ->
                        val loginJson = JSONObject(json.content)
                        val json = loginJson.getJSONObject("Data")

                        val user = Gson().fromJson(json.toString(), User::class.java)

                        Log.e("Aa",user.id)

                        preference.setToken(loginJson.getString("Token"))

                        myDb.insertUser(user)


                    }, failure = { error ->
                        Log.e("error ", error.toString())
                    })
                    println("==========================================================================")
                }

        return code
    }

    public fun network_GetAllTeam(myDb: DBHelper, preference : Preference) : Int{

        var URL = "http://115.68.182.229/node/team"

        URL.httpGet()
                .header(pairs = "Authorization" to preference.getToken())
                .responseJson { request, response, result ->
                    result.fold(success = {json ->
                        val teamJson = JSONObject(json.content)
                        val jsonOutput = teamJson.getJSONArray("Data").toString()
                        val listType = object : TypeToken<List<Team>>(){}.type
                        val teams : List<Team> = Gson().fromJson(jsonOutput, listType)

                        myDb.insertTeam(teams)

                        code = teamJson.getInt("code")

                    }, failure = {

                    })
                }

        return code
    }
}