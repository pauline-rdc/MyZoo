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
 * {@link TAnimalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TAnimalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TAnimalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;

    private View myFragmentView;
    private ArrayList<Animal> animals;
    public AnimalAdapter animalAdapter;
    private EditText et_tanimal;
    private Button bt_tanimal;
    private Button bt_reset_tanimal;
    private ListView lv_tanimal;
    private TextView tv_tanimal;
    private Context thiscontext;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TAnimalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TAnimalFragment newInstance(int param1) {
        TAnimalFragment fragment = new TAnimalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public TAnimalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        myFragmentView = inflater.inflate(R.layout.fragment_tanimal, container, false);

        animals = new ArrayList<>();
        animalAdapter = new AnimalAdapter(thiscontext, animals);

        et_tanimal = (EditText) myFragmentView.findViewById(R.id.et_tanimal);
        bt_tanimal = (Button) myFragmentView.findViewById(R.id.bt_tanimal);
        bt_reset_tanimal = (Button) myFragmentView.findViewById(R.id.bt_reset_tanimal);
        lv_tanimal = (ListView) myFragmentView.findViewById(R.id.lv_tanimal);
        tv_tanimal = (TextView) myFragmentView.findViewById(R.id.tv_tanimal);

        lv_tanimal.setAdapter(animalAdapter);
        LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
            @Override
            public void animalLoad(List<Animal> animalsSrv) {
                animals.clear();
                animals.addAll(animalsSrv);
                animalAdapter.notifyDataSetChanged();
                if (animalsSrv.size() == 0) {
                    tv_tanimal.setText("Aucun résultat");
                }
                else {
                    if (animalsSrv.size() == 1) {
                        tv_tanimal.setText(animalsSrv.size() + " résultat");
                    }
                    else {
                        tv_tanimal.setText(animalsSrv.size() + " résultats");
                    }
                }
            }
        }, "");
        async.execute();

        bt_tanimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_tanimal.getText().toString().trim().length() == 0) {
                    Toast.makeText(thiscontext, "Préciser l'animal recherché", Toast.LENGTH_SHORT).show();
                }
                else {
                    lv_tanimal.setAdapter(animalAdapter);
                    LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
                        @Override
                        public void animalLoad(List<Animal> animalsSrv) {
                            animals.clear();
                            animals.addAll(animalsSrv);
                            animalAdapter.notifyDataSetChanged();
                            if (animalsSrv.size() == 0) {
                                tv_tanimal.setText("Aucun résultat");
                            }
                            else {
                                if (animalsSrv.size() == 1) {
                                    tv_tanimal.setText(animalsSrv.size() + " résultat");
                                }
                                else {
                                    tv_tanimal.setText(animalsSrv.size() + " résultats");
                                }
                            }
                        }
                    }, et_tanimal.getText().toString().trim());
                    async.execute();
                }
            }
        });

        bt_reset_tanimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_tanimal.setAdapter(animalAdapter);
                LoadAnimal async = new LoadAnimal(new LoadAnimal.CallBack() {
                    @Override
                    public void animalLoad(List<Animal> animalsSrv) {
                        animals.clear();
                        animals.addAll(animalsSrv);
                        animalAdapter.notifyDataSetChanged();
                        if (animalsSrv.size() == 0) {
                            tv_tanimal.setText("Aucun résultat");
                        }
                        else {
                            if (animalsSrv.size() == 1) {
                                tv_tanimal.setText(animalsSrv.size() + " résultat");
                            }
                            else {
                                tv_tanimal.setText(animalsSrv.size() + " résultats");
                            }
                        }
                    }
                }, "");
                async.execute();
                et_tanimal.setText("");
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
