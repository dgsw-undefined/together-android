package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.DetailTeamActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.TeamSearch

class SearchTeamAdaper(val context: Context, val teams : ArrayList<Team>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ResultView : View = LayoutInflater.from(context).inflate(R.layout.search_result_team, null)

        val teamName : TextView = ResultView.findViewById(R.id.team_name_search)
        val viewInfo : Button = ResultView.findViewById(R.id.btn_search_team_info)
        val date : TextView = ResultView.findViewById(R.id.date_team_search)
        val teamProfile : ImageView = ResultView.findViewById(R.id.team_profile_search)
        val team= teams[position]


        teamProfile.background = ShapeDrawable(OvalShape())
        teamProfile.clipToOutline = true

        viewInfo.setOnClickListener {
            val nextIntent = Intent(context, DetailTeamActivity::class.java)
            nextIntent.putExtra("teamId",team.id)
            context.startActivity(nextIntent)
        }

        teamName.text = team.name
        date.text = team.area

        return ResultView
    }

    override fun getCount(): Int {
        return teams.size
    }

    override fun getItem(position: Int): Any {
        return teams.get(position)
    }

    override fun getItemId(position: Int): Long {
        return teams.get(position).hashCode().toLong()
    }
}