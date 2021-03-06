package net.minpro.simpletodo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), EditFragment.OnFragmentInteractionListener
                                        , DatePickerDialogFragment.OnDateSetListener {

    //DatePickerDialogFragment.OnDateSetListener#onDataEdited
    override fun onDataEdited() {
        finish()
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
        setContentView(R.layout.activity_edit)
        setSupportActionBar(toolbar)

        //戻るアイコン
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                finish()
            }
        }

        //MainActivityからのインテント受け取り
        val bundle = intent.extras
        val title = bundle.getString(IntentKey.TITLE.name)
        val deadline = bundle.getString(IntentKey.DEADLINE.name)
        val taskDetail = bundle.getString(IntentKey.TASK_DETAIL.name)
        val isCompleted = bundle.getBoolean(IntentKey.IS_COMPLETED.name)
        val mode = bundle.getSerializable(IntentKey.MODE_IN_EDIT.name) as ModeInEdit

        //EditFragmentを開く
        supportFragmentManager.beginTransaction()
            .add(R.id.container_detail, EditFragment.newInstance(title, deadline, taskDetail, isCompleted, mode),
                FragmentTag.EDIT.toString()).commit()
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
