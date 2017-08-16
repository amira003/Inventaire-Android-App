package com.inventaire2.inventaire2.Application;

/**
 * Created by Fal on 04/08/2017.
 */
import com.inventaire2.inventaire2.Models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


// Github
public interface GsonService {

    @GET("produits")
    Call<List<Article>> groupList();

    @POST("produits")
    @FormUrlEncoded
    Call<List<Article>> group2List(@Field("Name") String Name,
                                   @Field("Price") int Price,
                                   @Field("Quantite") int Quantite
    );

    @PUT("produits/{Id}")
    @FormUrlEncoded
    Call<List<Article>> group3List(@Path("Id") int Id, @Field("Name") String Name,
                                   @Field("Price") int Price
    );

    @DELETE("produits/{Id}")
    Call<List<Article>> group4List(@Path("Id") int Id);

}