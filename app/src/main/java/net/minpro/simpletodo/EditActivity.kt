package net.minpro.simpletodo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(toolbar)

        //戻るアイコン
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.apply {
            findItem(R.id.menu_delete).isVisible = false
            findItem(R.id.menu_edit).isVisible = false
            findItem(R.id.menu_register).isVisible = false
        }
        return true
    }

}
