package com.example.myapptp1

data class ModelListMovie(
    val page: Int,
    val results: List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)


data class ModelMovie(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val credits: Credits,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Genre(
    val id: Int,
    val name: String
)


data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class GenreMovie(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: Any,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguageMovie(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class ResultMovie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)



data class ModelListPerson(
    val page: Int,
    val results: List<ResultPerson>,
    val total_pages: Int,
    val total_results: Int
)

data class ModelPerson(
    val adult: Boolean,
    val also_known_as: List<String>,
    val biography: String  = "",
    val birthday: String?,
    val credits: CreditsPerson,
    val deathday: Any,
    val gender: Int,
    val homepage: Any,
    val id: Int,
    val imdb_id: String,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String?,
    val popularity: Double,
    val profile_path: String?
)

data class CreditsPerson(
    val cast: List<CastPerson>,
    val crew: List<CrewPerson>
)

data class CastPerson(
    val adult: Boolean,
    val backdrop_path: String,
    val character: String,
    val credit_id: String,
    val genre_ids: List<Int>,
    val id: Int,
    val order: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class CrewPerson(
    val adult: Boolean,
    val backdrop_path: String,
    val credit_id: String,
    val department: String,
    val genre_ids: List<Int>,
    val id: Int,
    val job: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class ResultPerson(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownForPerson>,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class KnownForPerson(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class ModelListTV(
    val page: Int,
    val results: List<ResultTV>,
    val total_pages: Int,
    val total_results: Int
)

data class ModelTV(
    val adult: Boolean,
    val backdrop_path: String,
    val created_by: List<CreatedBy>,
    val credits: CreditsTV,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<GenreTV>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyTV>,
    val production_countries: List<ProductionCountryTV>,
    val seasons: List<Season>,
    val spoken_languages: List<SpokenLanguageTV>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Float,
    val vote_count: Int
)

data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val original_name: String,
    val profile_path: Any
)

data class CreditsTV(
    val cast: List<CastTV>,
    val crew: List<CrewTV>
)

data class GenreTV(
    val id: Int,
    val name: String
)

data class LastEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val episode_type: String,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Int,
    val season_number: Int,
    val show_id: Int,
    val still_path: Any,
    val vote_average: Float,
    val vote_count: Int
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCompanyTV(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountryTV(
    val iso_3166_1: String,
    val name: String
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Float
)



data class CastTV(
    val adult: Boolean,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class CrewTV(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: Any
)

data class SpokenLanguageTV(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)
data class ResultTV(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)