package com.example.pauliner.myzoo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link favoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link favoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View myFragmentView;
    private ArrayList<Animal> animals;
    public AnimalAdapter animalAdapter;
    private ListView lv_favoris;
    private TextView tv_favoris;
    private Context thiscontext;

    private OnFragmentInteractionListener mListener;

    public FavoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Todo:Inflate the layout for this fragment
        thiscontext = container.getContext();
        myFragmentView = inflater.inflate(R.layout.fragment_favories, container, false);

        animals = new ArrayList<>();
        animalAdapter = new AnimalAdapter(thiscontext, animals);

        lv_favoris = (ListView) myFragmentView.findViewById(R.id.lv_favoris);
        tv_favoris = (TextView) myFragmentView.findViewById(R.id.tv_favoris);

        // Todo:load Favoris
        lv_favoris.setAdapter(animalAdapter);
        LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
            @Override
            public void animalLoad(List<Animal> animalsSrv) {
                animals.clear();
                animals.addAll(animalsSrv);
                animalAdapter.notifyDataSetChanged();
                if (animalsSrv.size() == 0) {
                    tv_favoris.setText("Aucun favoris");
                }
                else {
                    if (animalsSrv.size() == 1) {
                        tv_favoris.setText(animalsSrv.size() + " résultat");
                    }
                    else {
                        tv_favoris.setText(animalsSrv.size() + " résultats");
                    }
                }
            }
        }, "", 0);
        async.execute();

        return myFragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
