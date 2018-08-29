package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
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



class TeamGridAdapter (val context: Context, val teamData: ArrayList<Team>) : BaseAdapter() {

    val myDb = DBHelper(context)

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