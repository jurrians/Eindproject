package e.gebruiker.eindproject.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import e.gebruiker.eindproject.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class AddIncomeFragment extends Fragment implements ScreenShotable{

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_income, container, false);
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