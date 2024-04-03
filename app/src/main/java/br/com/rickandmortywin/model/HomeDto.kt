package br.com.rickandmortywin.model

data class HomeDto(
    var enterpriseId: Long,
    var recentPosts: List<RecentPost>
)

