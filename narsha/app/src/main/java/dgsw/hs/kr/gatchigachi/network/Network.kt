package dgsw.hs.kr.gatchigachi.network

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dgsw.hs.kr.gatchigachi.DetailTeamActivity
import dgsw.hs.kr.gatchigachi.LoginActivity
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.TeamMember
import dgsw.hs.kr.gatchigachi.model.User
import org.json.JSONObject
import java.util.*

class Network{
    var code = 100

    fun login(id:String, pw:String, myDb:DBHelper, context:Context) {
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
                            val token = loginJson.getString("Token").toString()

                            Log.e("a",token)

                            val user = Gson().fromJson(json.toString(), User::class.java)

                            user.isMe = 1

                            myDb.insertToken(token)
                            myDb.insertUser(user)

                            (context as LoginActivity).notifyFinish(loginJson.getLong("Code"))
                        }
                        is Result.Failure ->{
                            Log.e("a","a")
                        }

                    }
                    println("==========================================================================")
                }
    }

    fun getTeam(myDb: DBHelper, userIdx:Int, context: Context) : Int{

        var URL = "http://115.68.182.229/node/team/user/$userIdx"

        URL.httpGet()
                .header(pairs = "Authorization" to myDb.selectToken())
                .responseJson { _, _, result ->
                    result.fold(success = {json ->
                        val teamJson = JSONObject(json.content)
                        val jsonOutput = teamJson.getJSONArray("Data").toString()
                        val listType = object : TypeToken<List<Team>>(){}.type
                        val teams : List<Team> = Gson().fromJson(jsonOutput, listType)

                        myDb.insertMyTeams(teams)

                        code = teamJson.getInt("Code")

                        if (myDb.selectMyInfo()!!.idx!!.toInt() != userIdx)
                            (context as MainActivity).notifyFinish()

                    }, failure = {

                    })
                }

        return code
    }

    fun getTeamMember(teamId:Int ,myDb: DBHelper, context: Context) : Int{

        var URL = "http://115.68.182.229/node/team/member/$teamId"

        URL.httpGet()
                .header(pairs = "Authorization" to myDb.selectToken())
                .responseJson { _, _, result ->
                    result.fold(success = {json ->
                            val teamMemberJson = JSONObject(json.content)
                            val jsonOutput = teamMemberJson.getJSONArray("Data").toString()
                            val listType = object : TypeToken<List<TeamMember>>(){}.type
                            val teamMembers : List<TeamMember> = Gson().fromJson(jsonOutput, listType)

                            myDb.insertTeamMembers(teamMembers)

                            code = teamMemberJson.getInt("Code")

//                            (context as DetailTeamActivity).notifyFinish(teamMemberJson.getLong("Code"))
                    }, failure = {
                    })
                }
        return code
    }

    fun getUserByIdx(idx: Long?, myDb:DBHelper, context: Context) : Int{

        var URL = "http://115.68.182.229/node/user/$idx"

        URL.httpGet()
                .header(pairs = "Authorization" to myDb.selectToken())
                .responseJson { _, _, result ->
                    result.fold(
                            success = {json ->
                                val userJson = JSONObject(json.content)
                                val jsonOutput = userJson.getJSONArray("Data")[0]

                                val user : User = Gson().fromJson(jsonOutput.toString(), User::class.java)

                                myDb.insertUser(user)

                                val a = getTeam(myDb, user.idx!!.toInt(),context)

                    }, failure = {
                    })
                }
        return code
    }

    fun signUpNt(user: User){
        val URL = "http://115.68.182.229/go/user/signup"
        URL.httpPost()
                .header(Pair("Content-Type", "application/json"))
                .body(Gson().toJson(user))
                .responseObject(User.Deserializer()) { request, response, result ->
                    println(response.toString())
                    println(request.toString())
                }
    }

    fun teamRegistration(team:Team,teamLeaberFiled:String,myDb:DBHelper){

        val URL = "http://115.68.182.229/node/team"

        val json = Gson().toJson(team)

        URL.httpPost()
                .header("Content-Type" to "application/json")
                .body(Gson().toJson(json), Charsets.UTF_8)
                .responseJson { _, _, result ->
                    when(result){
                        is Result.Success ->{
                            val loginJson = JSONObject(result.get().content)
                            val json = loginJson.getJSONObject("Data")
                            val token = loginJson.getString("Token").toString()

                            Log.e("a",token)

                            val user = Gson().fromJson(json.toString(), User::class.java)

                            user.isMe = 1

                            myDb.insertToken(token)
                            myDb.insertUser(user)

//                            (context as LoginActivity).notifyFinish(loginJson.getLong("Code"))
                        }
                        is Result.Failure ->{
                            Log.e("a","a")
                        }

                    }
                    println("==========================================================================")
                }
    }

}