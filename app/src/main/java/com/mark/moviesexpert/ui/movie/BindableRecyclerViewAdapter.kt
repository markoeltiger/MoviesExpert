import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mark.moviesexpert.BR
import com.mark.moviesexpert.data.models.Genre

class RvAdapter(
    var genresList: MutableList<Genre>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    var onCLick: ((String) -> Unit)? = null



fun onGenreCLick(listener: (String) -> Unit) {
    onCLick = listener
}
    inner class ViewHolder(val binding:  com.mark.moviesexpert.databinding.ViewHolderGenreBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  com.mark.moviesexpert.databinding.ViewHolderGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text = genresList.get(position).name

        with(holder){
            with(genresList[position]){
                binding.name.text = this.name

            }
        }
    //    holder.binding.setVariable(BR.movie, genresList.get(position))
        holder.binding.root.setOnClickListener {
            onCLick?.let {
                it(genresList.get(position).id.toString())
            }
        }

     }

    override fun getItemCount(): Int {
        return genresList.size
    }
}