package david.moreno.amador.bottomnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        fragmentManager = getSupportFragmentManager();
        //Establecemos el fragment Home en el contenedor (para que no aparezca la vista vacía).
        fragmentManager.beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.sol:
                        fragment = new PlanetsFragment();
                        break;
                    case R.id.luna:
                        fragment = new MoonsFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                //addToBackStack para que al dar al botón de atrás no cierre la aplicación si no que vaya
                //al fragmento anterior.
                transaction.replace(R.id.main_container, fragment).addToBackStack("").commit();
                return true;
            }
        });
    }
}
