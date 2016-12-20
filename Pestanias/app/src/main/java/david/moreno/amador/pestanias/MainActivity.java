package david.moreno.amador.pestanias;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.ToolbarPrincipal);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.TabLayoutPrincipal);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());



        viewPager = (ViewPager)findViewById(R.id.ViewPagerPrincipal);
        //A nuestro adaptador le pasamos el manager y el método que nos cuenta los fragmentos del tablayout
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),this);
        //Asignamos al viewPager nuestro adaptador
        viewPager.setAdapter(viewPagerAdapter);
        //Asignamos al tablayout el viewpager
        tabLayout.setupWithViewPager(viewPager);
        //Asignamos el icono de cada Tab
        TabLayout.Tab tabCall=tabLayout.getTabAt(0);
        TabLayout.Tab linux=tabLayout.getTabAt(1);
        TabLayout.Tab motorbike=tabLayout.getTabAt(2);

        tabCall.setIcon(R.drawable.selectorbox);
        linux.setIcon(R.drawable.selectorlinux);
        motorbike.setIcon(R.drawable.selectorbike);

        //inflamos el menu al que queremos añadir eventos de click, posteriormente
        //le decimos a qué actividad tiene que apuntar el click.
        toolbar.inflateMenu(R.menu.menu_moto);
        toolbar.setOnMenuItemClickListener(this);

    }

    //Implementamos Toolbar.OnMenuItemClickListener y su método, onMenu..., de este sacamos el
    //item que han pulsado y hacemos lo que tengamos que hacer.
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_linux:
                Toast.makeText(this, "Linux rules", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_moto:
                Toast.makeText(this, "Precaución!", Toast.LENGTH_SHORT).show();
                return true;
        }

        return true;

    }
}
