package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dgsw.hs.kr.gatchigachi.DetailTeamActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.TeamSearch
import dgsw.hs.kr.gatchigachi.model.User

class SearchTeamAdaper(val context: Context, var teams : ArrayList<Team>) : BaseAdapter(),Filterable {
    var valueFilter: SearchPersonAdapter.ValueFilter? = null
    var team : Team? = null

    override fun getFilter(): Filter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ResultView : View = LayoutInflater.from(context).inflate(R.layout.search_result_team, null)

        val teamName : TextView = ResultView.findViewById(R.id.team_name_search)
        val viewInfo : Button = ResultView.findViewById(R.id.btn_search_team_info)
        val date : TextView = ResultView.findViewById(R.id.date_team_search)
        val teamProfile : ImageView = ResultView.findViewById(R.id.team_profile_search)
        team = teams[position]


        teamProfile.background = ShapeDrawable(OvalShape())
        teamProfile.clipToOutline = true

        viewInfo.setOnClickListener {
            val nextIntent = Intent(context, DetailTeamActivity::class.java)
            nextIntent.putExtra("teamId",team!!.id)
            context.startActivity(nextIntent)
        }

        teamName.text = team!!.name
        date.text = team!!.area

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

    inner class ValueFilter : Filter() {

        protected override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            if (constraint != null && constraint.length > 0) {
                val filterList = java.util.ArrayList<Team>()
                for (i in 0 until teams.size) {
                    if (teams[i].name!!.toUpperCase()
                                    .contains(constraint.toString().toUpperCase())) {

                        val team = teams[i]
                        filterList.add(team)
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = teams.size
                results.values = teams
            }
            return results

        }

        protected override fun publishResults(constraint: CharSequence,
                                              results: FilterResults) {
            teams = results.values as java.util.ArrayList<Team>
            notifyDataSetChanged()
        }

    }
}