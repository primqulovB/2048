package uz.gita.a2048

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import uz.gita.a2048.model.SideEnum
import uz.gita.a2048.repository.AppRepository
import uz.gita.a2048.settings.Settings
import uz.gita.a2048.utils.BackgroundUtil
import uz.gita.a2048.utils.MyTouchListener

class PlayActivity : AppCompatActivity() {

    private val items: MutableList<TextView> = ArrayList(16)
    private lateinit var mainView: LinearLayoutCompat
    private var repository = AppRepository.getInstance()
    private var settings: Settings = Settings.getInstance()
    private val util = BackgroundUtil()
    private lateinit var level: TextView
    private var record = 0
    private var clickBackCount = 0

    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        level = findViewById(R.id.txt_score)
        mainView = findViewById(R.id.mainView)
        loadViews()
        describeMatrixToViews()

        findViewById<ImageView>(R.id.home).setOnClickListener {
            finish()
        }

        if (clickBackCount == 1 || !repository.isPlaying()) {
            findViewById<ImageView>(R.id.back).alpha = 0.2f
            findViewById<ImageView>(R.id.back).isClickable = false
        }


        val myTouchListener = MyTouchListener(this)
        myTouchListener.setMyMovementSideListener {

            findViewById<ImageView>(R.id.back).alpha = 1f
            findViewById<ImageView>(R.id.back).isClickable = true
            clickBackCount = 0


            when (it) {

                SideEnum.DOWN -> {
                    if (!repository.isClickable()) {
                        openGameOverDialog()
                    }
                    repository.setState(true)
                    repository.moveDown()
                    describeMatrixToViews()
                }

                SideEnum.UP -> {
                    if (!repository.isClickable()) {
                        openGameOverDialog()
                    }
                    repository.setState(true)
                    repository.moveUp()
                    describeMatrixToViews()
                }

                SideEnum.RIGHT -> {
                    if (!repository.isClickable()) {
                        openGameOverDialog()
                    }
                    repository.setState(true)
                    repository.moveToRight()
                    describeMatrixToViews()
                }

                SideEnum.LEFT -> {
                    if (!repository.isClickable()) {
                        openGameOverDialog()
                    }

                    repository.setState(true)
                    repository.moveToLeft()
                    describeMatrixToViews()
                }
            }
        }
        mainView.setOnTouchListener(myTouchListener)
    }


    fun openGameOverDialog() {
        val gameOverDialog = Dialog(this)
        gameOverDialog.setContentView(R.layout.game_over_dialog)

        var level = level.text.toString().toInt()

        gameOverDialog.setCancelable(false)


        gameOverDialog.findViewById<TextView>(R.id.txt_score).text = repository.level.toString()
        gameOverDialog.findViewById<CardView>(R.id.bn_restart).setOnClickListener {
            repository.clear()

            repository = AppRepository.getInstance()
            describeMatrixToViews()
            gameOverDialog.dismiss()
        }
        gameOverDialog.findViewById<CardView>(R.id.bn_homemb).setOnClickListener {
            repository.restart()
            repository = AppRepository.getInstance()
            gameOverDialog.dismiss()
            finish()
        }
        gameOverDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        gameOverDialog.show()
    }


    private fun loadViews() {


        findViewById<ImageView>(R.id.bn_restart).setOnClickListener {
            repository.clear()
            describeMatrixToViews()
        }

        findViewById<ImageView>(R.id.back).setOnClickListener {
            clickBackCount++
            repository.backOldState()
            describeMatrixToViews()
        }
        for (i in 0 until mainView.childCount) {
            val linear = mainView.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until linear.childCount) {
                items.add(linear.getChildAt(j) as TextView)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun describeMatrixToViews() {
        if (clickBackCount == 1 || !repository.isPlaying()) {
            findViewById<ImageView>(R.id.back).alpha = 0.2f
            findViewById<ImageView>(R.id.back).isClickable = false
        }


        var txt_best = findViewById<TextView>(R.id.txt_bestscore)
        level.text = repository.level.toString()
        record = txt_best.text.toString().toInt()
        record = repository.getRecord()
        if (repository.level > record) {
            record = repository.level
        }
        txt_best.text = record.toString()

        val _matrix = repository.matrix
        for (i in _matrix.indices) {
            for (j in _matrix[i].indices) {
                items[i * _matrix.size + j].apply {
                    text = if (_matrix[i][j] == 0) ""
                    else _matrix[i][j].toString()
                    setBackgroundResource(util.colorByCount(_matrix[i][j]))
                }
                if (_matrix[i][j] == 16384) {
                    openGameOverDialog()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        repository.saveRecord(record)
        repository.saveItems()
    }

    override fun onResume() {
        super.onResume()
        /*
                if (clickBackCount == 0 || repository.isPlaying()) {
                    findViewById<ImageView>(R.id.back).alpha = 1f
                    findViewById<ImageView>(R.id.back).isClickable = true
                }
        */
    }
}