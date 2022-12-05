package edu.miu.cvbuilderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
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

        val spf = getSharedPreferences("curriculums", MODE_PRIVATE)
        val curriculumJson = spf.getString("curriculums","")
        if(curriculumJson!!.isNotBlank()) {
            val curriculum = Gson().fromJson<HashMap<String, Curriculum>>(
                curriculumJson).values.first()
            binding.homeTv.text = Html.fromHtml(curriculum.careerNote,Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)
        }

    }

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)
}