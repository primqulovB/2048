package uz.gita.a2048

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<ImageView>(R.id.play_game).setOnClickListener {
            startActivity(Intent(this, PlayActivity::class.java))
        }
       /* findViewById<Button>(R.id.about_game).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }*/
    }
}