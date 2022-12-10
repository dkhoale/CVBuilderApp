package edu.miu.cvbuilderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import edu.miu.cvbuilderapp.adapter.LocalDateAdapter
import edu.miu.cvbuilderapp.adapter.MainPageAdapter
import edu.miu.cvbuilderapp.databinding.ActivityCvbuilderBinding
import edu.miu.cvbuilderapp.model.Curriculum
import java.time.LocalDate

class CVBuilderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvbuilderBinding
    private var curriculum = Curriculum("","",
        "", arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf())
    private lateinit var currentLogin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCvbuilderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentLogin = intent.getStringExtra("currentLogin").toString()

        val spf = getSharedPreferences("curriculums", MODE_PRIVATE)
        val curriculumJson = spf.getString("curriculums","")
        if(curriculumJson!!.isNotBlank()) {
            curriculum = fromJson<HashMap<String, Curriculum>>(
                curriculumJson)[currentLogin]!!
        }

        val myPageAdapter = MainPageAdapter(this,curriculum, currentLogin)
        binding.vpager.adapter = myPageAdapter
        binding.tlayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tlayout,binding.vpager){tab,position->
            when(position){
                0->{
                    tab.text="Home"
                    tab.setIcon(R.drawable.home)
                }
                1->{
                    tab.text="About Me"
                    tab.setIcon(R.drawable.help)
                }
                2->{
                    tab.text="Work"
                    tab.setIcon(R.drawable.work)
                }
                3->{
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.contact)
                }
            }
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    inline fun <reified T> fromJson(json: String) = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateAdapter()).create().fromJson<T>(json, object: TypeToken<T>() {}.type)
}