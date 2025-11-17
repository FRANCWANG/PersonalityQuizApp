package com.example.personalityapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    interface OnAnswerSelectedListener {
        fun onAnswerSelected(optionIndex: Int)
    }

    private var listener: OnAnswerSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAnswerSelectedListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // 从 arguments 里取出问题文字和选项
        val questionText = arguments?.getString(ARG_QUESTION_TEXT) ?: ""
        val options = arguments?.getStringArray(ARG_OPTIONS) ?: emptyArray()

        val txt = view.findViewById<TextView>(R.id.txt_question)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_options)

        txt.text = questionText
        radioGroup.removeAllViews()

        // 动态创建单选项
        options.forEachIndexed { index, optionText ->
            val rb = RadioButton(requireContext())
            rb.id = View.generateViewId()
            rb.text = optionText
            rb.tag = index
            radioGroup.addView(rb)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<RadioButton>(checkedId)
            val idx = rb.tag as Int
            listener?.onAnswerSelected(idx)
        }

        return view
    }

    companion object {
        private const val ARG_QUESTION_TEXT = "arg_question_text"
        private const val ARG_OPTIONS = "arg_options"

        // 用这个函数来创建带参数的 Fragment
        fun newInstance(questionText: String, options: Array<String>): QuestionFragment {
            val frag = QuestionFragment()
            val args = Bundle()
            args.putString(ARG_QUESTION_TEXT, questionText)
            args.putStringArray(ARG_OPTIONS, options)
            frag.arguments = args
            return frag
        }
    }
}
