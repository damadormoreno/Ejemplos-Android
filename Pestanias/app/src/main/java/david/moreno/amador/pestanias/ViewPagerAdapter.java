package david.moreno.amador.pestanias;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import david.moreno.amador.pestanias.fragments.Fragment1;
import david.moreno.amador.pestanias.fragments.Fragment2;
import david.moreno.amador.pestanias.fragments.Fragment3;

/**
 * Created by David on 19/12/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    //Número de pestañas
    private int numTabs;
    Context context;
    //Typeface fontAwesomeFont = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");

    public ViewPagerAdapter(FragmentManager fm, int numTabs, Context context) {
        super(fm);
        this.numTabs=numTabs;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position){
            case 0:
                fragment= new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        //devolvemos el número de pestañas.
        return numTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String titulo=null;
        //String icon = context.getResources().getString(R.string.fa_icon_address_book);
        switch (position){

            case 0:
                titulo= "Pestaña 1";
                break;
            case 1:
                titulo="Pestaña 2";
                break;
            case 2:
                titulo = "Pestaña 3";
                break;
            default:
                titulo = null;
        }
        return titulo;
    }
}
