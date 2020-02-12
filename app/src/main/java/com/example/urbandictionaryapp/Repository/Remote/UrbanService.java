package com.example.urbandictionaryapp.Repository.Remote;

import com.example.urbandictionaryapp.Model.UrbanResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UrbanService {

    @GET("/define")
    Observable<UrbanResponse> getDefinitions(
            @Query("term") String term
    );

    @GET("/define")
    Single<UrbanResponse> getDefinitionSingle(
            @Query("term") String term
    );
}
