package com.example.radiofranceapp.domain.model

data class Shows(
    val edges: List<ShowEdge>
) {
    public data class ShowEdge(
        val cursor: String?,
        val node: Show
    ) {
        public data class Show(
            val id: String,
            val title: String,
            val url: String,
            val standFirst: String
        )
    }
}
