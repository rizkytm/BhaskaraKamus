package com.rizkytm.bhaskarakamus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class KamusFragment<OnViewCreated> extends Fragment {

    private String value = "Hello everyone!!!";
    private FragmentListener listener;
    ArrayAdapter<String> adapter;
    ListView dicList;

    public KamusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kamus, container, false);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button myButton = (Button)view.findViewById(R.id.myBtn);
//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                    listener.onItemClick(value);
//            }
//        });

        dicList = view.findViewById(R.id.dictionaryList);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getListOfWords());
        dicList.setAdapter(adapter);
        dicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null)
                    listener.onItemClick(getListOfWords()[position]);
            }
        });
    }

    public void resetDataSource(String[] source) {
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, source);
        dicList.setAdapter(adapter);
    }

    public void filterValue(String value) {
//      adapter.getFilter().filter(value);
        int size = adapter.getCount();
        for (int i=0; i<size; i++) {
            if (adapter.getItem(i).startsWith(value)) {
                dicList.setSelection(i);
                break;
            }
        }
    }

    String[] getListOfWords() {
        String[] source = new String[] {
          "a"
          ,"ab"
          ,"abc"
          ,"abcd"
          ,"abcde"
        };
        return source;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmentListener(FragmentListener listener) {
        this.listener = listener;
    }
}
