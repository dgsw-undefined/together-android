package dgsw.hs.kr.gatchigachi.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import java.time.temporal.TemporalAccessor
import java.util.*
import kotlin.collections.ArrayList


class DBHelper(context: Context) : SQLiteOpenHelper(context, "undefined.db", null,4) {

    private val dbManager : DatabaseManager = DatabaseManager()

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("PRAGMA foreign_keys = on;")
            db.execSQL(dbManager.CreateTableAlert)
            db.execSQL(dbManager.CreateTableTeam)
            db.execSQL(dbManager.CreateTableTeamMember)
            db.execSQL(dbManager.CreateTableTec)
            db.execSQL(dbManager.CreateTableTruster)
            db.execSQL(dbManager.CreateTableUser)
            db.execSQL(dbManager.CreateTableMy)
            db.execSQL(dbManager.CreateTableMyTeam)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null){
            db.execSQL("DROP TABLE IF EXISTS " + "alert")
            db.execSQL("DROP TABLE IF EXISTS " + "team")
            db.execSQL("DROP TABLE IF EXISTS " + "team_member")
            db.execSQL("DROP TABLE IF EXISTS " + "tec")
            db.execSQL("DROP TABLE IF EXISTS " + "truster")
            db.execSQL("DROP TABLE IF EXISTS " + "user")
            db.execSQL("DROP TABLE IF EXISTS " + "my")
            db.execSQL("DROP TABLE IF EXISTS " + "my_team")
        }
        onCreate(db)
    }

    fun insertMyinfo(user:User, token:String){
//        Thread {
            val db = this.writableDatabase

            val sql = "INSERT OR REPLACE INTO my VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"

            val insertStmt = db.compileStatement(sql)

            insertStmt.clearBindings()

            insertStmt.bindString(1, token)
            insertStmt.bindLong(2, user.idx!!)
            insertStmt.bindString(3, user.id)
            insertStmt.bindString(4, user.name)
            insertStmt.bindString(5, user.pw)
            insertStmt.bindString(6, user.mail)
            insertStmt.bindString(7, user.inter)
            insertStmt.bindString(8, user.git)
            insertStmt.bindString(9, user.tec.toString())
            insertStmt.bindString(10, user.field)
            insertStmt.bindString(11, user.pos)
            insertStmt.bindString(12, user.phone)

            insertStmt.executeInsert()
//        }.run()
    }

    fun insertMyTeams(teams: List<Team>){
        Thread {
            for (team in teams){
                val db = this.writableDatabase

                val sql = "INSERT OR REPLACE INTO my_team VALUES(?,?,?,?,?,?,?,?)"

                val insertStmt = db.compileStatement(sql)

                insertStmt.bindLong(1, team.id.toLong())
                insertStmt.bindString(2, team.name)
                insertStmt.bindString(3, team.subject)
                insertStmt.bindString(4, team.area)
                insertStmt.bindString(5, team.docs)
                insertStmt.bindLong(6, team.leader_id.toLong())
                insertStmt.bindLong(7, team.member_limit.toLong())
                insertStmt.bindLong(8, team.member_count.toLong())

                insertStmt.executeInsert()
            }
        }.run()
    }

    fun selectAllTeam():ArrayList<Team>{
        val teams = java.util.ArrayList<Team>()
        val db = this.writableDatabase

        val res = db.rawQuery("SELECT * FROM my_team ;",
                null)

        while (res.moveToNext()) {

            val id = res.getInt(res.getColumnIndex("id"))
            val name = res.getString(res.getColumnIndex("name"))
            val subject = res.getString(res.getColumnIndex("subject"))
            val area = res.getString(res.getColumnIndex("area"))
            val docs = res.getString(res.getColumnIndex("docs"))
            val leaderId = res.getInt(res.getColumnIndex("leader_id"))
            val memberLimit = res.getInt(res.getColumnIndex("member_limit"))
            val memberCount = res.getInt(res.getColumnIndex("member_count"))

            val teamTemp = Team(id,name,subject,area,docs,leaderId,memberLimit,memberCount)

            teams.add(teamTemp)
        }

        res.close()


        return teams
    }

    fun selectMyInfo() : User? {
        val db = this.writableDatabase

        val res = db.rawQuery("SELECT * FROM my ORDER BY idx DESC LIMIT 1",
                null)

        while (res.moveToNext()){

            val token = res.getString(res.getColumnIndex("token"))
            val idx = res.getLong(res.getColumnIndex("idx"))
            val id = res.getString(2)
            val name = res.getString(res.getColumnIndex("name"))
            val pw = res.getString(res.getColumnIndex("pw"))
            val email = res.getString(res.getColumnIndex("email"))
            val interested = res.getString(res.getColumnIndex("interested"))
            val github = res.getString(res.getColumnIndex("github"))
            val tec = res.getString(res.getColumnIndex("tec"))
            val field = res.getString(res.getColumnIndex("field"))
            val position = res.getString(res.getColumnIndex("position"))
            val phone = res.getString(res.getColumnIndex("phone"))

            res.close()

            val tecArray = tec.split(" ".toRegex())

            return User(idx,id,name,pw,email,interested,github,field,tecArray,position,phone)
        }

        return null
    }

    fun selectMyToken():String{
        val db = this.writableDatabase

        val res = db.rawQuery("SELECT token FROM my ORDER BY idx DESC LIMIT 1",
                null)

        val buffer = StringBuffer()

        while (res.moveToNext()) {
            buffer.append(res.getString(0))
        }

        val token = buffer.toString()

        res.close()

        return token

    }

    fun selectTeamById(teamId:Int):Team?{
        val db = this.writableDatabase

        val res = db.rawQuery("SELECT * FROM my_team WHERE id = $teamId",
                null)

        while (res.moveToNext()) {

            val id = res.getInt(res.getColumnIndex("id"))
            val name = res.getString(res.getColumnIndex("name"))
            val subject = res.getString(res.getColumnIndex("subject"))
            val area = res.getString(res.getColumnIndex("area"))
            val docs = res.getString(res.getColumnIndex("docs"))
            val leaderId = res.getInt(res.getColumnIndex("leader_id"))
            val memberLimit = res.getInt(res.getColumnIndex("member_limit"))
            val memberCount = res.getInt(res.getColumnIndex("member_count"))

            res.close()

            return Team(id,name,subject,area,docs,leaderId,memberLimit,memberCount)

        }

        res.close()

        return null
    }

}