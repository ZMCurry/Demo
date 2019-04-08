package com.example.wan_android.retrofit

import com.example.wan_android.base.BaseResponse
import com.example.wan_android.ui.model.ArticleEntity
import com.example.wan_android.ui.model.BannerItem
import com.example.wan_android.ui.model.HomeEntity
import com.example.wan_android.ui.model.KnowledgeEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit请求api
 */
interface RetrofitService {

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     * @param page page
     */

    @GET("/article/list/{page}/json")
    fun getHomeListAsync(
        @Path("page") page: Int
    ): Deferred<BaseResponse<HomeEntity>>

    /**
     * 首页Banner
     * @return BannerResponse
     */
    @GET("/banner/json")
    fun getBannerAsync(): Deferred<BaseResponse<List<BannerItem>>>

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("/tree/json")
    fun getKnowledgeAsync(): Deferred<KnowledgeEntity>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page page
     * @param cid cid
     */
    @GET("/article/list/{page}/json")
    fun getArticleListAsync(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Deferred<BaseResponse<ArticleEntity>>
//
//    /**
//     * 常用网站
//     * http://www.wanandroid.com/friend/json
//     */
//    @GET("/friend/json")
//    fun getFriendList(): Deferred<FriendListResponse>
//
//    /**
//     * 大家都在搜
//     * http://www.wanandroid.com/hotkey/json
//     */
//    @GET("/hotkey/json")
//    fun getHotKeyList(): Deferred<HotKeyResponse>
//
//    /**
//     * 搜索
//     * http://www.wanandroid.com/article/query/0/json
//     * @param page page
//     * @param k POST search key
//     */
//    @POST("/article/query/{page}/json")
//    @FormUrlEncoded
//    fun getSearchList(
//        @Path("page") page: Int,
//        @Field("k") k: String
//    ): Deferred<HomeListResponse>
//
//    /**
//     * 登录
//     * @param username username
//     * @param password password
//     * @return Deferred<LoginResponse>
//     */
//    @POST("/user/login")
//    @FormUrlEncoded
//    fun loginWanAndroid(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ): Deferred<LoginResponse>
//
//    /**
//     * 注册
//     * @param username username
//     * @param password password
//     * @param repassword repassword
//     * @return Deferred<LoginResponse>
//     */
//    @POST("/user/register")
//    @FormUrlEncoded
//    fun registerWanAndroid(
//        @Field("username") username: String,
//        @Field("password") password: String,
//        @Field("repassword") repassowrd: String
//    ): Deferred<LoginResponse>
//
//    /**
//     * 获取自己收藏的文章列表
//     * @param page page
//     * @return Deferred<HomeListResponse>
//     */
//    @GET("/lg/collect/list/{page}/json")
//    fun getLikeList(
//        @Path("page") page: Int
//    ): Deferred<HomeListResponse>
//
//    /**
//     * 收藏文章
//     * @param id id
//     * @return Deferred<HomeListResponse>
//     */
//    @POST("/lg/collect/{id}/json")
//    fun addCollectArticle(
//        @Path("id") id: Int
//    ): Deferred<HomeListResponse>
//
//    /**
//     * 收藏站外文章
//     * @param title title
//     * @param author author
//     * @param link link
//     * @return Deferred<HomeListResponse>
//     */
//    @POST("/lg/collect/add/json")
//    @FormUrlEncoded
//    fun addCollectOutsideArticle(
//        @Field("title") title: String,
//        @Field("author") author: String,
//        @Field("link") link: String
//    ): Deferred<HomeListResponse>
//
//    /**
//     * 删除收藏文章
//     * @param id id
//     * @param originId -1
//     * @return Deferred<HomeListResponse>
//     */
//    @POST("/lg/uncollect/{id}/json")
//    @FormUrlEncoded
//    fun removeCollectArticle(
//        @Path("id") id: Int,
//        @Field("originId") originId: Int = -1
//    ): Deferred<HomeListResponse>
//

//
//    /**
//     * 我的常用网址
//     * @return FriendListResponse
//     */
//    @GET("/lg/collect/usertools/json")
//    fun getBookmarkList(): Deferred<FriendListResponse>
}