package alektas.tinkoffnews

import alektas.tinkoffnews.ui.NewsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_container, NewsFragment.newInstance(), "newsFragment")
            .commitNow()
    }
}
