package th.ac.kku.cis.final_readbook_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLogTags
import android.util.Log
import android.view.View
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listbooks.*

class listbooks : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    var toDoItemList: MutableList<ToDo>? = null
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        mDatabase = FirebaseDatabase.getInstance().reference
        listViewItems = findViewById<View>(R.id.listdata) as ListView

        toDoItemList = mutableListOf<ToDo>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listdata.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, EventLogTags.Description::class.java)
            val selectedItem = parent.getItemAtPosition(position) as ToDo
            intent.putExtra("name",selectedItem.name.toString())
            intent.putExtra("namebook",selectedItem.namebook.toString())
            intent.putExtra("title",selectedItem.title.toString())
            intent.putExtra("description",selectedItem.description.toString())
            intent.putExtra("comment",selectedItem.comment.toString())
            startActivity(intent)
        }
    }
    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("Student"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
        private fun addDataToList(dataSnapshot: DataSnapshot) {
            val items = dataSnapshot.children.iterator()
            var email = getIntent().getStringExtra("email")
            // Check if current database contains any collection
            if (items.hasNext()) {


                // check if the collection has any to do items or not
                while (items.hasNext()) {
                    // get current item
                    val currentItem = items.next()
                    val map = currentItem.getValue() as HashMap<String, Any>
                    // add data to object

                    val todoItem = ToDo.create()
                    todoItem.name = map.get("name") as String
                    todoItem.namebook = map.get("namebook") as String
                    todoItem.title = map.get("title") as String
                    todoItem.description = map.get("description") as String
                    todoItem.comment = map.get("comment") as String
                    toDoItemList!!.add(todoItem);
                }
            }

            adapter.notifyDataSetChanged()
        }
    }
}

