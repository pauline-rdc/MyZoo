package com.example.pauliner.myzoo;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Stéphane.e on 13/01/2016.
 */
public class LoadAnimal extends AsyncTask {

    protected List<Animal> animalsSrv;
    private CallBack callback;
    private String name;

    public LoadAnimal(CallBack callback, String name) {
        this.callback = callback;
        this.name = name;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        animalsSrv = ZooBDD.getAnimalByName(this.name);
        return null;
    }

    protected void onPostExecute(final Object object) {
        super.onPostExecute(object);
        callback.animalLoad(animalsSrv);
    }

    public interface CallBack {
        void animalLoad(List<Animal> animals);
    }
}
