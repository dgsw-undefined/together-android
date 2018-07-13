package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.Team2

class TeamGridAdapter (val context: Context, val teamData: ArrayList<Team2>) : BaseAdapter() {

    val myDb = DBHelper(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val teamView : View = LayoutInflater.from(context).inflate(R.layout.team_list_item, null)

        val teamName : TextView = teamView.findViewById(R.id.team_name)

        val team : Team2 = teamData[position]

        val teams = myDb.selectAllTeam()

        teamName.text = teams[0].name

        return teamView
    }

    override fun getItem(position: Int): Any {
        return teamData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return teamData.get(position).hashCode().toLong()
    }

    override fun getCount(): Int {
        return teamData.size
    }
}