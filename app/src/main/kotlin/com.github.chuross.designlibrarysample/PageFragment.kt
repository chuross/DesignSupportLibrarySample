package com.github.chuross.designlibrarysample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlin.properties.Delegates


class PageFragment : Fragment() {

    val titleText by Delegates.lazy { getView().findViewById(R.id.text_title) as TextView }
    val list by Delegates.lazy { getView().findViewById(R.id.list) as RecyclerView }

    companion object {
        val ARGUMENT_KEY_ITEM = "argument_key_item"
        val ITEMS = (0..100).map { i -> "position=" + i }

        fun create(item: String): PageFragment {
            val arguments = Bundle()
            arguments.putString(ARGUMENT_KEY_ITEM, item)

            val fragment = PageFragment()
            fragment.setArguments(arguments)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = getArguments().getString(ARGUMENT_KEY_ITEM);
        titleText.setText(item)
        list.setLayoutManager(LinearLayoutManager(getActivity()))
        list.setAdapter(object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun getItemCount(): Int = ITEMS.size()

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val positionText = holder.itemView.findViewById(R.id.text_position) as TextView
                positionText.setText(position.toString())
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_page_list, parent, false)) {
                }
            }

        })
    }
}