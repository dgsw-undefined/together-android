package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import dgsw.hs.kr.gatchigachi.DetailTeamActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.id.team_name
import dgsw.hs.kr.gatchigachi.TrustActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.model.Team2
import kotlinx.android.synthetic.main.team_list_item.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions



class TeamGridAdapter (val context: Context, var teamData: ArrayList<Team>) : BaseAdapter(),Filterable {

    var team : Team? = null
    var valueFilter: ValueFilter? = null
    val myDb = DBHelper(context)
    val teamListTeam = teamData

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val teamView : View = LayoutInflater.from(context).inflate(R.layout.team_list_item, null)

        val teamName : TextView = teamView.findViewById(R.id.team_name)
        val teamImage : ImageView = teamView.findViewById(R.id.team_image)

        val team = teamData[position]

        teamName.text = team.name

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(50))

        Glide.with(context)
                .load(team.profile)
                .apply(requestOptions)
                .into(teamImage)

        teamView.setOnClickListener {
            val nextIntent = Intent(context, DetailTeamActivity::class.java)
            nextIntent.putExtra("teamId", team.id)
            context.startActivity(nextIntent)
        }

        teamView.team_image.background =  ShapeDrawable(OvalShape())
        teamView.team_image.clipToOutline = true

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

    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }

    inner class ValueFilter : Filter() {

        protected override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            if (constraint != null && constraint.length > 0) {
                val temp = constraint.toString().toLowerCase()
                val filterList = java.util.ArrayList<Team>()
                for (i in 0 until teamListTeam.size) {
                    val data = teamListTeam.get(i).name
                    if (data!!.toLowerCase().startsWith(temp.toString())) {

                        val team = teamListTeam[i]
                        filterList.add(team)
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = teamListTeam.size
                results.values = teamListTeam
            }
            return results

        }

        protected override fun publishResults(constraint: CharSequence,
                                              results: FilterResults) {
            teamData = results.values as java.util.ArrayList<Team>
            notifyDataSetChanged()
        }

    }

}