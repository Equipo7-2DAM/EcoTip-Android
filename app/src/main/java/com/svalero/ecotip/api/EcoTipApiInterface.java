package com.svalero.ecotip.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EcoTipApiInterface {

    @GET("ecosistemas")
    Call<List<Ecosistema>> getEcosistemas();

    @GET("ecosistemas/{id}")
    Call<Ecosistema> getEcosistema(@Path("id") long id);

    @POST("ecosistemas")
    Call<Ecosistema> registerEcosistema(@Body Ecosistema ecosistema);

    @PUT("ecosistemas/{id}")
    Call<Ecosistema> modifyEcosistema(@Path("id") long id, @Body Ecosistema ecosistema);

    @DELETE("ecosistemas/{id}")
    Call<Ecosistema> deleteEcosistema(@Path("id") long id);

    @GET("animales")
    Call<List<Animal>> getAnimal();

    @GET("animales/{id}")
    Call<Animal> getAnimal(@Path("id") long id);

    @POST("animales")
    Call<Animal> registerAnimal(@Body Animal animal);

    @PUT("animales/{id}")
    Call<Animal> modifyAnimal(@Path("id") long id, @Body Animal animal);

    @DELETE("animales/{id}")
    Call<Animal> deleteAnimal(@Path("id") long id);
}