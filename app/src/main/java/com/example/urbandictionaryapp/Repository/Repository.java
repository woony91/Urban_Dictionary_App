package com.example.urbandictionaryapp.Repository;

import com.example.urbandictionaryapp.Model.UrbanResponse;
import com.example.urbandictionaryapp.Repository.Remote.ServiceInstance;

import io.reactivex.Observable;

public class Repository {

    private Repository() {
    }

    private static class RepositoryInstanceHolder {
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryInstanceHolder.INSTANCE;
    }

    public Observable<UrbanResponse> getDefinitions(String term) {
        return ServiceInstance.getUrbanService().getDefinitions(term);
    }

}
