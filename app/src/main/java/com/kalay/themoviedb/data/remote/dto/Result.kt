package com.kalay.themoviedb.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.kalay.themoviedb.core.util.Constant.API_IMAGE_URL
import java.util.Locale

data class Result(
    @SerializedName("id") val id: Int,

    // MOVIE
    @SerializedName("title") val title: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("revenue") val revenue: Long?,
    @SerializedName("imdb_id") val imdbId: String?,

    // TV SHOW
    @SerializedName("name") val name: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int> = emptyList(),

    // GENERAL
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("genres") val genres: List<Genre> = emptyList(),
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry> = emptyList(),
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage> = emptyList(),
    @SerializedName("origin_country") val originCountry: List<String> = emptyList(),
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: Any?
) {
    val voteAverageFormatted: String
        get() = String.format(Locale.getDefault(), "%.1f", voteAverage ?: 0.0)

    val posterPathFormatted: String
        get() = posterPath?.let { API_IMAGE_URL + it } ?: "Unknown"

    val backdropPathFormatted: String?
        get() = backdropPath?.let { API_IMAGE_URL + it }

    val dateFormatted: String
        get() = releaseDate ?: firstAirDate ?: "Unknown"

    val titleFormatted: String
        get() = title ?: name ?: "Unknown"

}