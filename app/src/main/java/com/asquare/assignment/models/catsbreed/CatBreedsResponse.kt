package com.asquare.assignment.models.catsbreed

import com.google.gson.annotations.SerializedName

data class CatBreedsResponse(

	@field:SerializedName("first_page_url")
	val firstPageUrl: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<CatItem>? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("last_page_url")
	val lastPageUrl: String? = null,

	@field:SerializedName("next_page_url")
	val nextPageUrl: String? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("prev_page_url")
	val prevPageUrl: Any? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class CatItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coat")
	val coat: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("pattern")
	val pattern: String? = null,

	@field:SerializedName("breed")
	val breed: String? = null
)
