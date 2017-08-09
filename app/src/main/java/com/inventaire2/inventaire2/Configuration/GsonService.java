package com.inventaire2.inventaire2.Configuration;

/**
 * Created by Fal on 04/08/2017.
 */
import com.inventaire2.inventaire2.Models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


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
}
