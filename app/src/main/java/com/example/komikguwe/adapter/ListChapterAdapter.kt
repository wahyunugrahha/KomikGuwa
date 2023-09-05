package com.example.komikguwe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komikguwe.R
import com.example.komikguwe.data.Chapter


class ListChapterAdapter(
    private val chapterList: List<Chapter>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ListChapterAdapter.ChapterViewHolder>() {

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chapterTitle: TextView = itemView.findViewById(R.id.rv_chapter)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(chapterList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chapter_row_komik, parent, false)
        return ChapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.chapterTitle.text = chapter.no
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    interface OnItemClickListener {
        fun onItemClick(chapter: Chapter)
    }
}


