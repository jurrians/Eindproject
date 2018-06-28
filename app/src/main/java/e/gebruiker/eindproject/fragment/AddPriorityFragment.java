package e.gebruiker.eindproject.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import e.gebruiker.eindproject.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class AddPriorityFragment extends Fragment implements ScreenShotable{

    protected int res;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_priority, container, false);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}