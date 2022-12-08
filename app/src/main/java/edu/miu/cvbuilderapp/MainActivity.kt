package edu.miu.cvbuilderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import edu.miu.cvbuilderapp.databinding.ActivityMainBinding
import edu.miu.cvbuilderapp.model.User
import com.google.gson.reflect.TypeToken
import edu.miu.cvbuilderapp.model.Curriculum


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var users = HashMap<String,User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setOnClickListeners()

    }

    private fun init() {
        val sharedPF = getSharedPreferences("login", MODE_PRIVATE)
        val credentialsJson = sharedPF.getString("credentials", "")
        if(credentialsJson!!.isBlank()) {
//            users.putIfAbsent("kevin.lee@gmail.com",User("Kevin", "Lee", "kevin.lee@gmail.com", "123456"))
            users.putIfAbsent("1",User("Kevin", "Lee", "1", "1"))
            val sharedPFEdit = sharedPF.edit()
            sharedPFEdit.putString("credentials",Gson().toJson(users))
            sharedPFEdit.apply()
        }else {
            users = Gson().fromJson<HashMap<String,User>>(credentialsJson)
        }


        val curriculumSpf = getSharedPreferences("curriculums", MODE_PRIVATE)
        val curriculumJson = curriculumSpf.getString("curriculums","")
        if(curriculumJson!!.isBlank()){
            val curriculums = HashMap<String,Curriculum>()
            curriculums.putIfAbsent("kevin.lee@gmail.com",
                Curriculum(getString(R.string.career_note_1),getString(R.string.core_competency_1),"AAAA",
                    arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf()
                )
            )

            val curJson = Gson().toJson(curriculums)
            val spfe = curriculumSpf.edit()
            spfe.putString("curriculums",curJson)
            spfe.apply()
        }
    }

    private fun setOnClickListeners() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmailAddress.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty() && users.any { it.key == email && it.value.password == password}) {
                val intent = Intent(this, CVBuilderActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "The username or password is invalid.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)
}