package maurya.devansh.y2matedownloadhelper

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_y2mate_helper.*

class Y2MateHelperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_y2mate_helper)

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if (intent.type == "text/plain") {
                    openUrlInBrowser(getY2MateDownloaderUrl(
                        intent.getStringExtra(Intent.EXTRA_TEXT) ?: "").toString())
                }
            }
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("com.android.chrome")
        startActivity(intent)
    }

    private fun getY2MateDownloaderUrl(youtubeUrl: String): Uri {
        val videoId = Uri.parse(youtubeUrl).lastPathSegment

        return Uri.Builder()
            .scheme("https")
            .authority("www.y2mate.com")
            .appendPath("youtube")
            .appendPath(videoId)
            .build()
    }
}