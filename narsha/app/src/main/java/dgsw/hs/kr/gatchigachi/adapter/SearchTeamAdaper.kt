package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.TeamSearch

class SearchTeamAdaper(val context: Context, val SearchTeamData : ArrayList<TeamSearch>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ResultView : View = LayoutInflater.from(context).inflate(R.layout.search_result_team, null)

        val TeamName : TextView = ResultView.findViewById(R.id.team_name_search)
        val ViewInfo : Button = ResultView.findViewById(R.id.btn_searchteam_info)
        val Date : TextView = ResultView.findViewById(R.id.date_team_search)
        val TeamProfile : ImageView = ResultView.findViewById(R.id.team_profile_search)
        val TeamSearch = SearchTeamData[position]

        TeamProfile.background = ShapeDrawable(OvalShape())
        TeamProfile.clipToOutline = true

        TeamName.setText(TeamSearch.name)
        Date.setText(TeamSearch.date)



        return ResultView
    }

    override fun getCount(): Int {
        return SearchTeamData.size
    }

    override fun getItem(position: Int): Any {
        return SearchTeamData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return SearchTeamData.get(position).hashCode().toLong()
    }
}