package edu.miu.cvbuilderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.miu.cvbuilderapp.databinding.ActivityCvbuilderBinding
import edu.miu.cvbuilderapp.databinding.ActivityMainBinding
import edu.miu.cvbuilderapp.model.Curriculum

class CVBuilderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvbuilderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCvbuilderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var curriculum : Curriculum = Curriculum("","")
        val spf = getSharedPreferences("curriculums", MODE_PRIVATE)
        val curriculumJson = spf.getString("curriculums","")
        if(curriculumJson!!.isNotBlank()) {
            curriculum = Gson().fromJson<HashMap<String, Curriculum>>(
                curriculumJson).values.first()
        }


        val myPageAdapter = MainPageAdapter(this,curriculum)
        // Set the Adapter to your Viewpager UI
        binding.vpager.adapter = myPageAdapter
        // Will align the space according to the Screen size to equally spread
        binding.tlayout.tabGravity = TabLayout.GRAVITY_FILL
        /* Setting up Tab Layout with the ViewPageg2 is handled by the TabLayoutMediator
       * by passing your tablayout id and viewpager id*/
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

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)
}