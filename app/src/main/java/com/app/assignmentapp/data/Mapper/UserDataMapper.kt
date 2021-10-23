package com.app.assignmentapp.data.Mapper

import com.app.assignmentapp.data.Responses.ArticleE
import com.app.assignmentapp.data.Responses.DataResponse
import com.app.assignmentapp.data.Responses.DataResponseEntity
import com.app.assignmentapp.data.Responses.ResultE
import retrofit2.Response

class UserDataMapper {
    fun getData(mDataResponse: Response<DataResponse>): DataResponseEntity {
        val mDataResponse: DataResponse? = mDataResponse.body()

        val mResult =mDataResponse?.result
        val mDataEntity = DataResponseEntity()

        mDataEntity.result = ResultE()

        val mListOfArticle = mDataResponse?.result?.article
        val mArticleList = ArrayList<ArticleE>()
        for (i in 0..mArticleList.size){
            val mData = ArticleE()
            mData.author = mListOfArticle?.get(i)?.author
            mData.categoryName = mListOfArticle?.get(i)?.categoryName
            mData.image = mListOfArticle?.get(i)?.image
            mData.name = mListOfArticle?.get(i)?.name
            mArticleList.add(mData)
        }

        mDataEntity.result?.article = mArticleList

        return mDataEntity
    }
}