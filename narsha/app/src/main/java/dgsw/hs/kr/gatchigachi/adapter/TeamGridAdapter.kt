package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.DetailTeamActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.id.team_name
import dgsw.hs.kr.gatchigachi.TrustActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.Team2

class TeamGridAdapter (val context: Context, val teamData: ArrayList<Team>) : BaseAdapter() {

    val myDb = DBHelper(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val teamView : View = LayoutInflater.from(context).inflate(R.layout.team_list_item, null)

        val teamName : TextView = teamView.findViewById(R.id.team_name)

        val teamId = teamData[position].id.toString()

        teamName.text = teamData[position].name

        teamView.setOnClickListener {
            val nextIntent = Intent(context, DetailTeamActivity::class.java)
            nextIntent.putExtra("teamId", teamId)
            context.startActivity(nextIntent)
        }

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