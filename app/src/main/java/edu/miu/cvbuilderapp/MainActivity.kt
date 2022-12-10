package edu.miu.cvbuilderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import edu.miu.cvbuilderapp.databinding.ActivityMainBinding
import com.google.gson.reflect.TypeToken
import edu.miu.cvbuilderapp.adapter.LocalDateAdapter
import edu.miu.cvbuilderapp.model.*
import java.time.LocalDate


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
            users.putIfAbsent("kevin.lee@gmail.com",User("Kevin", "Lee", "kevin.lee@gmail.com", "123456"))
//            users.putIfAbsent("1",User("Kevin", "Lee", "1", "1"))
            val sharedPFEdit = sharedPF.edit()
            sharedPFEdit.putString("credentials",Gson().toJson(users))
            sharedPFEdit.apply()
        }else {
            users = fromJson<HashMap<String,User>>(credentialsJson)
        }

        val curriculumSpf = getSharedPreferences("curriculums", MODE_PRIVATE)
        val curriculumJson = curriculumSpf.getString("curriculums","")
        if(curriculumJson!!.isBlank()){
            val curriculums = HashMap<String,Curriculum>()

            val educations = arrayListOf<Education>(
                Education("Maharishi University","Master of Science in Computer Science", R.drawable.maharishi),
                Education("Harvard University","Bachelor of Science in Electricity", R.drawable.harvard))
            val certs = arrayListOf(
                Certification("PMI Agile Certified Practitioner",R.drawable.pmi_acp_cert),
                Certification("AWS Certified Solutions Architect",R.drawable.aws_cert),
                Certification("Oracle Certified Professional: Java 11", R.drawable.java_oracle_cert)
            )
            val workExperiences = arrayListOf<WorkExperience>(
                WorkExperience("Software Developer", CompanyMapping.VISA,
                    LocalDate.of(2020,2,1), LocalDate.of(2022,9,1),"Texas, US", "Lead dev team to develop commercehub.")
            )
            val contacts = arrayListOf(Contact("(641) 233 2385","Mobile",R.drawable.phone_icon),
                Contact("ldkhoa83@gmail.com","Email",R.drawable.mail_icon)
            )
            curriculums.putIfAbsent("kevin.lee@gmail.com",
                Curriculum(getString(R.string.career_note_1),getString(R.string.core_competency_1),getString(R.string.about_me_1),
                    educations, certs, workExperiences, contacts
                )
            )

            val curJson = toJson(curriculums)
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
                intent.putExtra("currentLogin", email)
                startActivity(intent)
            } else {
                Toast.makeText(this, "The username or password is invalid.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inline fun <reified T> fromJson(json: String) = Gson().fromJson<T>(json, object: TypeToken<T>() {}.type)
    fun toJson(objectJson: Any): String = GsonBuilder().registerTypeAdapter(LocalDate::class.java,LocalDateAdapter())
        .create().toJson(objectJson)
}