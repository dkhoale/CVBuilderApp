package edu.miu.cvbuilderapp

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.miu.cvbuilderapp.databinding.FragmentSimpleContentBinding


class SimpleContentFragment : Fragment() {
    private lateinit var binding: FragmentSimpleContentBinding
    private lateinit var title: String
    private lateinit var content: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSimpleContentBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun setContent(content: String){
        this.content = content
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sfTitleTv.text = this.title
        binding.sfContentTv.text = Html.fromHtml(this.content,Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)
    }
}