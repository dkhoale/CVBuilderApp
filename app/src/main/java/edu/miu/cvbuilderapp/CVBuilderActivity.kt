package edu.miu.cvbuilderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.miu.cvbuilderapp.adapter.MainPageAdapter
import edu.miu.cvbuilderapp.databinding.ActivityCvbuilderBinding
import edu.miu.cvbuilderapp.model.Curriculum
import edu.miu.cvbuilderapp.model.User
import edu.miu.cvbuilderapp.util.GsonUtil

class CVBuilderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvbuilderBinding
    private var curriculum = Curriculum("","",
        "", arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf())
    private lateinit var currentLogin: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCvbuilderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentLogin = intent.getSerializableExtra("currentLogin") as User

        val spf = getSharedPreferences("curriculums", MODE_PRIVATE)
        val curriculumJson = spf.getString("curriculums","")
        if(curriculumJson!!.isNotBlank()) {
            curriculum = GsonUtil.fromJson<HashMap<String, Curriculum>>(
                curriculumJson)[currentLogin.email]!!
        }

        val myPageAdapter = MainPageAdapter(this,curriculum, currentLogin)
        binding.vpager.adapter = myPageAdapter
        binding.tlayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tlayout,binding.vpager){tab,position->
            when(position){
                0->{
                    tab.text= getString(R.string.home_tab_title)
                    tab.setIcon(R.drawable.home)
                }
                1->{
                    tab.text=getString(R.string.about_me_tab_title)
                    tab.setIcon(R.drawable.help)
                }
                2->{
                    tab.text=getString(R.string.work_tab_title)
                    tab.setIcon(R.drawable.work)
                }
                3->{
                    tab.text = getString(R.string.contact_tab_title)
                    tab.setIcon(R.drawable.contact)
                }
            }
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.linkedInMenu -> {
                val intent = Intent(this,WebViewActivity::class.java)
                val loadUrl = curriculum.contacts.first { contact -> contact.type.contains("LinkedIn") }.content
                intent.putExtra("url",loadUrl)
                startActivity(intent)
            }
            R.id.githubMenu -> {
            val intent = Intent(this,WebViewActivity::class.java)
            val loadUrl = curriculum.contacts.first { contact -> contact.type.contains("github",true) }.content
            intent.putExtra("url",loadUrl)
            startActivity(intent)
        }
        }
        return super.onOptionsItemSelected(item)
    }
}