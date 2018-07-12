package dgsw.hs.kr.gatchigachi.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.User
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "myDb") {

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
        }
    }

    fun insertUser(user:User){
        Thread {
            val db = this.writableDatabase

            val sql = "INSERT OR REPLACE INTO user VALUES(?,?,?,?,?,?,?,?,?,?)"

            val insertStmt = db.compileStatement(sql)

            insertStmt.clearBindings()

            insertStmt.bindLong(1, user.idx!!)
            insertStmt.bindString(2, user.id)
            insertStmt.bindString(3, user.name)
            insertStmt.bindString(4, user.pw)
            insertStmt.bindString(5, user.mail)
            insertStmt.bindString(6, user.inter)
            insertStmt.bindString(7, user.git)
            insertStmt.bindString(8, user.field)
            insertStmt.bindString(9, user.pos)
            insertStmt.bindString(10, user.phone)

            insertStmt.executeInsert()
        }.run()
    }

    fun insertTeam(teams: List<Team>){
        Thread {
            for (team in teams){
                val db = this.writableDatabase

                val sql = "INSERT OR REPLACE INTO team VALUES(?,?,?,?,?,?,?,?)"

                val insertStmt = db.compileStatement(sql)

                insertStmt.clearBindings()

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
}