package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.Team

class TeamGridAdapter (val context: Context, val teamData: ArrayList<Team>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val teamView : View = LayoutInflater.from(context).inflate(R.layout.team_list_item, null)

        val teamName : TextView = teamView.findViewById(R.id.team_name)

        val team = teamData[position]

        teamName.setText(team.name)

        return teamView
    }

    override fun getItem(position: Int): Any {
        return teamData[position]
    }

    override fun getItemId(position: Int): Long {
        return  0
    }

    override fun getCount(): Int {
        return teamData.size
    }
}