package th.ac.kku.cis.final_readbook_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        mDatabase = FirebaseDatabase.getInstance().reference

        Btnsave.setOnClickListener{
            save()
            startActivity(Intent(this@AddBookActivity,Menu_Activity::class.java))
        }

        mBack_btn.setOnClickListener {
            startActivity(Intent(this@AddBookActivity,Menu_Activity::class.java))
        }

    }
    private fun save(){
        var name = mP.text.toString().trim()
        var namebook = mPasswordTextview.text.toString().trim()
        var title = mTitle.text.toString().trim()
        var description = myy.text.toString().trim()
        var comment = mComment.text.toString().trim()
        var  todoItem = ToDo.create()
        var email = getIntent().getStringExtra("email")
        val newItem = mDatabase.child("Book").push()
        // add new key to todoobject
        todoItem.id = newItem.key
        todoItem.name = name
        todoItem.namebook = namebook
        todoItem.title = title
        todoItem.description = description
        todoItem.comment = comment
        newItem.setValue(todoItem)
        finish()
    }
}
