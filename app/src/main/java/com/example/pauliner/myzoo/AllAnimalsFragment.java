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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllAnimalsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class AllAnimalsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View myFragmentView;
    private ArrayList<Animal> animals;
    public AnimalAdapter animalAdapter;
    private EditText et;
    private Button bt;
    private Button bt_reset;
    private ListView lv;
    private TextView tv;
    private Context thiscontext;

    public AllAnimalsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        // Todo:Get ViewFragment
        myFragmentView = inflater.inflate(R.layout.fragment_all_animals, container, false);

        // Todo:initialise variables
        animals = new ArrayList<>();
        animalAdapter = new AnimalAdapter(thiscontext, animals);
        et = (EditText) myFragmentView.findViewById(R.id.et);
        bt = (Button) myFragmentView.findViewById(R.id.bt);
        bt_reset = (Button) myFragmentView.findViewById(R.id.bt_reset);
        lv = (ListView) myFragmentView.findViewById(R.id.lv);
        tv = (TextView) myFragmentView.findViewById(R.id.tv);

        // Todo:adapter for all animals
        lv.setAdapter(animalAdapter);
        LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
            @Override
            public void animalLoad(List<Animal> animalsSrv) {
                animals.clear();
                animals.addAll(animalsSrv);
                animalAdapter.notifyDataSetChanged();
                if (animalsSrv.size() == 0) {
                    tv.setText("Aucun résultat");
                }
                else {
                    if (animalsSrv.size() == 1) {
                        tv.setText(animalsSrv.size() + " résultat");
                    }
                    else {
                        tv.setText(animalsSrv.size() + " résultats");
                    }
                }
            }
        }, "");
        async.execute();

        // Todo:click for search animal
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().trim().length() == 0) {
                    Toast.makeText(thiscontext, "Préciser l'animal recherché", Toast.LENGTH_SHORT).show();
                }
                else {
                    lv.setAdapter(animalAdapter);
                    LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
                        @Override
                        public void animalLoad(List<Animal> animalsSrv) {
                            animals.clear();
                            animals.addAll(animalsSrv);
                            animalAdapter.notifyDataSetChanged();
                            if (animalsSrv.size() == 0) {
                                tv.setText("Aucun résultat");
                            }
                            else {
                                if (animalsSrv.size() == 1) {
                                    tv.setText(animalsSrv.size() + " résultat");
                                }
                                else {
                                    tv.setText(animalsSrv.size() + " résultats");
                                }
                            }
                        }
                    }, et.getText().toString().trim());
                    async.execute();
                }
            }
        });

        // Todo:click for reset search
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(animalAdapter);
                LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
                    @Override
                    public void animalLoad(List<Animal> animalsSrv) {
                        animals.clear();
                        animals.addAll(animalsSrv);
                        animalAdapter.notifyDataSetChanged();
                        if (animalsSrv.size() == 0) {
                            tv.setText("Aucun résultat");
                        }
                        else {
                            if (animalsSrv.size() == 1) {
                                tv.setText(animalsSrv.size() + " résultat");
                            }
                            else {
                                tv.setText(animalsSrv.size() + " résultats");
                            }
                        }
                    }
                }, "");
                async.execute();
                et.setText("");
            }
        });

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
