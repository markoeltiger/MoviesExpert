import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mark.moviesexpert.data.models.Genre

class RvAdapter(
    var genresList: List<Genre>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:  com.mark.moviesexpert.databinding.ViewHolderGenreBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  com.mark.moviesexpert.databinding.ViewHolderGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(genresList[position]){
                binding.name.text = this.name

            }
        }
    }

    override fun getItemCount(): Int {
        return genresList.size
    }
}