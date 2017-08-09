package com.inventaire2.inventaire2.Configuration;

/**
 * Created by Fal on 04/08/2017.
 */
import com.inventaire2.inventaire2.Models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


// Github
public interface GsonService {

    @GET("produits")
    Call<List<Article>> groupList();
}
