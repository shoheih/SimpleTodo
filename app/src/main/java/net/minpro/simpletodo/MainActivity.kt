package net.minpro.simpletodo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EditFragment.OnFragmentInteractionListener
                                        , DatePickerDialogFragment.OnDateSetListener {

    var isTwoPane: Boolean = false

    //DatePickerDialogFragment.OnDateSetListener#onDataEdited
    override fun onDataEdited() {
        //TODO リストの更新処理

    }

    //DatePickerDialogFragment.OnDateSetListener#onDateSelected
    override fun onDateSelected(dateString: String) {
        val inputDateText = findViewById<EditText>(R.id.inputDateText)
        inputDateText.setText(dateString)
    }

    //EditFragment.OnFragmentInteractionListener#onDatePickerLaunched
    override fun onDatePickerLaunched() {
        DatePickerDialogFragment().show(supportFragmentManager, FragmentTag.DATE_PICKER.toString())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // SmartPhon or Tablet
        if (R.id.container_detail != null) isTwoPane = true

        supportFragmentManager.beginTransaction()
            .add(R.id.container_master, MasterFragment.newInstance(1),
                    FragmentTag.MASTER.toString()).commit()

        // タスクの新規登録
        fab.setOnClickListener { view ->
            goEditScreen("", "", "",false, ModeInEdit.NEW_ENTRY)
        }

    }

    private fun goEditScreen(
        title: String,
        deadline: String,
        taskDetail: String,
        isCompleted: Boolean,
        mode: ModeInEdit) {

        if (isTwoPane) {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.container_detail, EditFragment.newInstance("1", "1"))
//            fragmentTransaction.commit()

            supportFragmentManager.beginTransaction()
                .add(R.id.container_detail, EditFragment.newInstance(title, deadline, taskDetail, isCompleted, mode),
                    FragmentTag.EDIT.toString()).commit()

            return
        }

        val intent = Intent(this@MainActivity, EditActivity::class.java).apply {
            putExtra(IntentKey.TITLE.name, title)
            putExtra(IntentKey.DEADLINE.name, deadline)
            putExtra(IntentKey.TASK_DETAIL.name, taskDetail)
            putExtra(IntentKey.IS_COMPLETED.name, isCompleted)
            putExtra(IntentKey.MODE_IN_EDIT.name, mode)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.apply {
            findItem(R.id.menu_delete).isVisible = false
            findItem(R.id.menu_edit).isVisible = false
            findItem(R.id.menu_register).isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
