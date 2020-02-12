package com.example.urbandictionaryapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.urbandictionaryapp.Model.Definition;
import com.example.urbandictionaryapp.Model.UrbanResponse;
import com.example.urbandictionaryapp.Repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchWordViewModel extends AndroidViewModel {

    private MutableLiveData<List<Definition>> definitions = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgressBar = new MutableLiveData<>();

    private Repository repo = Repository.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();
    public SearchWordViewModel(@NonNull Application application) {
        super(application);
    }

    public void getDefinitionsObservable(String term){
        repo.getDefinitions(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrbanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //SHOW PROGRESS BAR
                        showProgressBar.setValue(true);

                    }

                    @Override
                    public void onNext(UrbanResponse urbanResponse) {
                        //RECEIVE DATA HERE
                        definitions.setValue(urbanResponse.getList());

                    }

                    @Override
                    public void onError(Throwable e) {
                        //SOMETHING WENT WRONG

                    }

                    @Override
                    public void onComplete() {
                        //HIDE PROGRESS BAR
                        showProgressBar.setValue(false);

                    }
                });
    }

    public void getDefinitionsObservable2(String term) {
        Disposable defDisposable = repo.getDefinitions(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UrbanResponse>() {
                    @Override
                    public void accept(UrbanResponse urbanResponse) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        disposable.add(defDisposable);
    }

    public void getDefinitionsObservable2Lamda(String term) {
        showProgressBar.setValue(true);
        Disposable defDisposable = repo.getDefinitions(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(urbanResponse -> {
                    definitions.setValue(urbanResponse.getList());
                    showProgressBar.setValue(false);

                }, throwable -> {
                    showProgressBar.setValue(false);

                });
        disposable.add(defDisposable);
    }

    public LiveData<List<Definition>> getDefinitions() {
        return definitions;
    }

    public LiveData<Boolean> getShowProgressBar() {
        return showProgressBar;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
