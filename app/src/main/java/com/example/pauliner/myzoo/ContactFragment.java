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
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename and change types of parameters

    private EditText tx_name;
    private EditText tx_email;
    private EditText tx_object;
    private EditText tx_message;
    private Button bt_send;
    private View myFragmentView;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Todo:Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_contact, container, false);

        bt_send = (Button) myFragmentView.findViewById(R.id.send_message);

        // Todo: check if form is OK and send Message
        bt_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tx_message = (EditText) myFragmentView.findViewById(R.id.message);
                tx_name = (EditText) myFragmentView.findViewById(R.id.name);
                tx_object = (EditText) myFragmentView.findViewById(R.id.object);
                tx_email = (EditText) myFragmentView.findViewById(R.id.email);

                if (tx_name.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No name", Toast.LENGTH_LONG).show();

                    return;
                }
                if (tx_email.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No email", Toast.LENGTH_LONG).show();

                    return;
                }
                if (tx_object.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No object", Toast.LENGTH_LONG).show();

                    return;
                }
                if (tx_message.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No message", Toast.LENGTH_LONG).show();

                    return;
                }

                Toast.makeText(getContext(), "Message Send", Toast.LENGTH_LONG).show();
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
