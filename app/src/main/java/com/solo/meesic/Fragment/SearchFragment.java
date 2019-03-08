package com.solo.meesic.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.solo.meesic.Adapter.SearchBaiHatAdapter;
import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    FragmentSearchBinding binding;
    private View view;
    private SearchBaiHatAdapter searchBaiHatAdapter;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.fragmentTimkiemToolbarSearch);
        binding.fragmentTimkiemToolbarSearch.setTitle("");
        setHasOptionsMenu(true);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.mnuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }
    private void SearchBaiHat(String tukhoa) {
        WebhostService webhostService = WebhostServiceAPI.getService();
        Call<List<Song>> callback = webhostService.GetSearchBaiHat(tukhoa);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
                if (songArrayList.size() > 0) {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), songArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    binding.fragmentTimkiemRecyclerviewSearch.setLayoutManager(linearLayoutManager);
                    binding.fragmentTimkiemRecyclerviewSearch.setAdapter(searchBaiHatAdapter);
                    binding.fragmentTimkiemTxtNodata.setVisibility(View.GONE);
                    binding.fragmentTimkiemRecyclerviewSearch.setVisibility(View.VISIBLE);
                } else {
                    binding.fragmentTimkiemTxtNodata.setVisibility(View.VISIBLE);
                    binding.fragmentTimkiemRecyclerviewSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }


}
