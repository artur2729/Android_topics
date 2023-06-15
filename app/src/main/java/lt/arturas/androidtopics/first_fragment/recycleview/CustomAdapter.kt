package lt.arturas.androidtopics.first_fragment.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import lt.arturas.androidtopics.first_fragment.CustomViewHolder
import lt.arturas.androidtopics.repository.newsapi.Article

class CustomAdapter(
    private val onClick: (Article) -> Unit
) : ListAdapter<Article, CustomViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        lt.arturas.androidtopics.databinding.FragmentArticleListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
    class Comparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}