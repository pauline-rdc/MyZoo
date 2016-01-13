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
 * Use the {@link AllAnimalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllAnimalsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View myFragmentView;
    private ArrayList<Animal> animals;
    private List<Animal> allAnimals;
    public AnimalAdapter animalAdapter;
    private EditText et;
    private Button bt;
    private Button bt_reset;
    private ListView lv;
    private TextView tv;
    private Context thiscontext;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllAnimalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllAnimalsFragment newInstance(String param1, String param2) {
        AllAnimalsFragment fragment = new AllAnimalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AllAnimalsFragment() {
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
        thiscontext = container.getContext();
        myFragmentView = inflater.inflate(R.layout.fragment_all_animals, container, false);

        animals = new ArrayList<>();
        animalAdapter = new AnimalAdapter(thiscontext, animals);

        et = (EditText) myFragmentView.findViewById(R.id.et);
        bt = (Button) myFragmentView.findViewById(R.id.bt);
        bt_reset = (Button) myFragmentView.findViewById(R.id.bt_reset);
        lv = (ListView) myFragmentView.findViewById(R.id.lv);
        tv = (TextView) myFragmentView.findViewById(R.id.tv);

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

        bt_reset.setOnClickListener(new View.OnClickListener() {
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
                    }, "");
                    async.execute();
                    et.setText("");
                }
            }
        });

        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
