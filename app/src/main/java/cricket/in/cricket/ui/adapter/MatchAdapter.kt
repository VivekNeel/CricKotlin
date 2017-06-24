package cricket.`in`.cricket.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cricket.`in`.cricket.R
import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.utils.inflateLayout
import kotlinx.android.synthetic.main.list_item_matches.view.*

/**
 * Created by vivek on 24/06/17.
 */
class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    var matches: List<Matches>


    init {
        matches = emptyList()
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches.get(holder.adapterPosition)
        holder.bindItems(match)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(parent.inflateLayout(R.layout.list_item_matches))
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(match: Matches) = with(itemView) {
            var match = match.teamOne.plus(" vs " + match.teamTwo)
            matches.text = match
        }
    }

    fun updateItems(matches: List<Matches>) {
        this.matches = matches
        notifyDataSetChanged()
    }
}