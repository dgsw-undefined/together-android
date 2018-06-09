package dgsw.hs.kr.gatchigachi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dgsw.hs.kr.gatchigachi.DataService
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.BestTruster

class BestTrusterAdapter (val context: Context, val BestTrusterList : ArrayList<BestTruster>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ResultView : View = LayoutInflater.from(context).inflate(R.layout.best_truster_item, null)

        val bestTrusterName : TextView = ResultView.findViewById(R.id.best_truster_name)
        val TrusterCount : TextView = ResultView.findViewById(R.id.count_truster)

        val BestTrusterList = DataService.BestTrusterData[position]


        bestTrusterName.setText(BestTrusterList.name)
        TrusterCount.setText(BestTrusterList.countTruster)
        return ResultView
    }

    override fun getCount(): Int {
        return DataService.BestTrusterData.size
    }

    override fun getItem(position: Int): Any {
        return DataService.BestTrusterData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return DataService.BestTrusterData.get(position).hashCode().toLong()
    }
}