package david.moreno.amador.pestanias;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.ToolbarPrincipal);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.TabLayoutPrincipal);
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
    }
}
