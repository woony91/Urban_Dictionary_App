package com.example.urbandictionaryapp.View.searchWordFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urbandictionaryapp.R;
import com.example.urbandictionaryapp.View.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchWordFragment extends BaseFragment {


    public SearchWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_word, container, false);
    }

}
