package lt.arturas.androidtopics.first_fragment

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import lt.arturas.androidtopics.repository.news_api.Article
import lt.arturas.androidtopics.databinding.FragmentArticleListItemBinding

class CustomViewHolder(
    private val binding: FragmentArticleListItemBinding,
    private val onClick: (Article) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentArticle: Article? = null

    init {
        binding.root.setOnClickListener {
            currentArticle?.let { result -> onClick(result) }

//            if (currentArticle != null){
//                onClick(currentArticle!!)
//            }
        }
    }

    fun bind(article: Article) {
        currentArticle = article
        binding.apply {
            titleTextView.text = article.title
            dateTextView.text = article.publishedAt
            descriptionTextView.text = article.description
            val photoPath = article.urlToImage
            articleIImageView.load(photoPath) {
                crossfade(enable = true)
                crossfade(durationMillis = 500)
                size(ViewSizeResolver(articleIImageView))
            }
        }
    }
}