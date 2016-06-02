package jako.jocantaro.android.tipcalc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jako.jocantaro.android.tipcalc.R;
import jako.jocantaro.android.tipcalc.adapters.TipAdapter;
import jako.jocantaro.android.tipcalc.models.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyvlerview;

    private TipAdapter adapter;


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history_list,container,false);
        ButterKnife.bind(this,view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter() {

        if (adapter == null){

            adapter = new TipAdapter(getActivity().getApplicationContext(), new ArrayList<TipRecord>());
        }


    }


    private void initRecyclerView () {

        recyvlerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyvlerview.setAdapter(adapter);


    }

    @Override
    public void addToList(TipRecord record) {
        adapter.add (record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }
}
