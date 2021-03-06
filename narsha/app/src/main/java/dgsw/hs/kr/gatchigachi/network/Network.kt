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
import dgsw.hs.kr.gatchigachi.MakeTeamActivity
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.TeamMember
import dgsw.hs.kr.gatchigachi.model.User
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

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

                            myDb.deleteMyInfo()
                            myDb.insertToken(token)

                            if(user.tec == null){
                                user.tec = ArrayList()
                            }
                            myDb.insertUser(user)

                            (context as LoginActivity).notifyFinish(user.idx!!.toLong())
                        }
                        is Result.Failure ->{
                            Log.e("a","a")
                        }

                    }
                    println("==========================================================================")
                }
    }

    fun getTeam(myDb: DBHelper, userIdx:Int, context: Context, isMyTeam: Boolean,type:Int) : Int{

        var URL = "http://115.68.182.229/node/team/user/$userIdx"
        val token = myDb.selectToken()

        val a = URL.httpGet()
                .header(pairs = *arrayOf("Authorization" to token))
                .responseJson { res, req, result ->
                    result.fold(success = {json ->
                        val teamJson = JSONObject(json.content)
                        println(req)
                        if (teamJson.getInt("Code") >= 202 ){
                            if (isMyTeam)
                                (context as LoginActivity).notifyFinish(10000)
                        }else{
                            val jsonOutput = teamJson.getJSONArray("Data").toString()
                            val listType = object : TypeToken<List<Team>>(){}.type
                            val teams : List<Team> = Gson().fromJson(jsonOutput, listType)

                            var i = 0
                            if (isMyTeam) {
                                for (team in teams) {
                                    teams[i++].isMyTeam = 1
                                }
                            }

                            myDb.insertTeams(teams)

                            code = teamJson.getInt("Code")

                            if (type == 1){
                                (context as MakeTeamActivity).notifyFinish(code)
                            }else {
                                (context as LoginActivity).notifyFinish(code)
                            }
                        }
                    }, failure = {
                        Log.e("a","A")
                    })
                }

        return code
    }

    fun getTeamMember(teamId:Int ,myDb: DBHelper, context: Context, type: Int) : Int{

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

                        if(type == 1){
                            (context as MakeTeamActivity).notifyFinish()
                        }else if(type == 2){
                            (context as DetailTeamActivity).notifyFinish()
                        }
                    }, failure = {
                    })
                }
        return code
    }

//    fun getUserByIdx(idx: Long?, myDb:DBHelper, context: Context) : Int{
//
//        var URL = "http://115.68.182.229/node/user/$idx"
//
//        URL.httpGet()
//                .header(pairs = "Authorization" to myDb.selectToken())
//                .responseJson { _, _, result ->
//                    result.fold(
//                            success = {json ->
//                                val userJson = JSONObject(json.content)
//                                val jsonOutput = userJson.getJSONArray("Data")[0]
//
//                                val user : User = Gson().fromJson(jsonOutput.toString(), User::class.java)
//
//                                myDb.insertUser(user)
//
//                                val int = 0
//
//                                val a = getTeam(myDb, user.idx!!.toInt(),context,false)
//
//                    }, failure = {
//                    })
//                }
//        return code
//    }

    fun signUpNt(user: User){
        val URL = "http://115.68.182.229/go/user/signup"

        val json = Gson().toJson(user)
        URL.httpPost()
                .header(Pair("Content-Type", "application/json"))
                .body(json)
                .responseObject(User.Deserializer()) { request, response, result ->
                    println(response.toString())
                    println(request.toString())
                }
    }

    fun teamRegistration(team:Team,myDb:DBHelper, context: Context){

        val URL = "http://115.68.182.229/node/team"

        val json = Gson().toJson(team)

        URL.httpPost()
                .header("Content-Type" to "application/json", "Authorization" to myDb.selectToken())
                .body(json, Charsets.UTF_8)
                .responseJson { _, _, result ->
                    when(result){
                        is Result.Success ->{
                            val json = JSONObject(result.get().content)
                            val data = json.getJSONObject("Data")

                            (context as MakeTeamActivity).notifyFinish(data.getLong("id"))
                        }
                        is Result.Failure ->{
                            Log.e("a","a")
                        }

                    }
                    println("==========================================================================")
                }
    }

    fun getTeamList(myDb: DBHelper) {
        var URL = "http://115.68.182.229/node/test/team_list"

        URL.httpGet()
                .header()
                .responseJson { _, _, result ->
                    result.fold(success = {json ->
                        val teamJson = JSONObject(json.content)
                        val jsonOutput = teamJson.getJSONArray("Data").toString()
                        val listType = object : TypeToken<List<Team>>(){}.type
                        val teams : List<Team> = Gson().fromJson(jsonOutput, listType)

                        val myTeam = myDb.selectAllMyTeam()
                        var i = 0
                        var j = 0
                        while (i < teams.size){
                            j=0
                            while (j < myTeam.size){
                                if(teams[i].id == myTeam[j].id){
                                    teams[i].isMyTeam = 1
                                }
                                j++
                            }
                            i++
                        }

                        myDb.insertTeams(teams)

                    }, failure = {

                    })
                }
    }

    fun getUserList(myDb: DBHelper) {
        var URL = "http://115.68.182.229/node/user/list/1"

        URL.httpGet()
                .header()
                .responseJson { _, _, result ->
                    result.fold(success = {json ->
                        val teamJson = JSONObject(json.content)
                        val jsonOutput = teamJson.getJSONArray("Data").toString()
                        val listType = object : TypeToken<List<User>>(){}.type
                        val users : List<User> = Gson().fromJson(jsonOutput, listType)

                        for (user in users){
                            if(user.tec == null){
                                user.tec = ArrayList<String>()
                            }
                            if (user.idx ==  myDb.selectMyInfo()!!.idx){
                                user.isMe = 1
                            }
                            myDb.insertUser(user)
                        }

                    }, failure = {

                    })
                }
    }

    fun addTeamMember(teamMember: TeamMember,myDb:DBHelper, context: Context){

        val URL = "http://115.68.182.229/node/team/join"

        val json = Gson().toJson(teamMember)

        URL.httpPost()
                .header("Content-Type" to "application/json", "Authorization" to myDb.selectToken())
                .body(json, Charsets.UTF_8)
                .responseJson { _, res, result ->
                    when(result){
                        is Result.Success ->{
                            println(res)
//                            val data = json.getJSONObject("Data")

//                            (context as MakeTeamActivity).notifyFinish(data.getLong("id"))
                        }
                        is Result.Failure ->{
                            Log.e("a","a")
                        }

                    }
                    println("==========================================================================")
                }
    }


}