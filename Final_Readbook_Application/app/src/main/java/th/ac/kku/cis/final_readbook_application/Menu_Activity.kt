package th.ac.kku.cis.final_readbook_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu_.*

class Menu_Activity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val TAG:String = "Result Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_)
        mAuth = FirebaseAuth.getInstance()

        mLogout.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@Menu_Activity,MainActivity::class.java))
            finish()
        }

        Btnadd.setOnClickListener{
            startActivity(Intent(this@Menu_Activity,AddBookActivity::class.java))
            finish()
        }

        Btnlist.setOnClickListener{
            startActivity(Intent(this@Menu_Activity,listbooks::class.java))
            finish()
        }
    }

}
