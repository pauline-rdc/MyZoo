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
    private int theme;

    public LoadAnimal(CallBack callback, String name) {
        this.callback = callback;
        this.name = name;
        this.theme = -1;
    }

    public LoadAnimal(CallBack callback, String name, int theme) {
        this.callback = callback;
        this.name = name;
        this.theme = theme;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (this.theme > 0) {
            animalsSrv = ZooBDD.getAnimalByTheme(this.theme, this.name);
        }
        else {
            if (this.theme != 0) {
                animalsSrv = ZooBDD.getAnimalByName(this.name);
            }
            else {
                animalsSrv = ZooBDD.getAnimalFavoris();
            }
        }
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
