package otus.homework.coroutines

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso

class CatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICatsView {

    var presenter: CatsPresenter? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            presenter?.onInitComplete()
        }
    }

    override fun populate(catPresentation: CatPresentation) {
        findViewById<TextView>(R.id.fact_textView).text = catPresentation.fact

        if (catPresentation.imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(catPresentation.imageUrl)
                .into(findViewById<ImageView>(R.id.cat_imageView))
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}

interface ICatsView {

    fun populate(catPresentation: CatPresentation)
    fun showError(message: String)
}