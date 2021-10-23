package com.app.assignmentapp.data.Responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DataResponseEntity:Serializable {
    @SerializedName("result")
    @Expose
    var result: ResultE? = null
}
class ResultE {
    @SerializedName("category")
    @Expose
    var category: ArrayList<CategoryE>? = null

    @SerializedName("article")
    @Expose
    var article: ArrayList<ArticleE>? = null
}
class CategoryE {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null
}
class ArticleE {
    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("categoryName")
    @Expose
    var categoryName: String? = null

    @SerializedName("type")
    @Expose
    var type: Int? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("categoryId")
    @Expose
    var categoryId: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("url")
    @Expose
    var url: Any? = null

    @SerializedName("createdOn")
    @Expose
    var createdOn: String? = null

    @SerializedName("image")
    @Expose
    var image: Any? = null

    @SerializedName("articleTime")
    @Expose
    var articleTime: Int? = null

    @SerializedName("ranking")
    @Expose
    var ranking: Int? = null
}