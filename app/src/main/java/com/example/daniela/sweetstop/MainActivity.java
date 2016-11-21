package com.example.daniela.sweetstop;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;

import com.example.daniela.sweetstop.adapter.ViewPagerAdapter;
import com.example.daniela.sweetstop.fragment.CategoriaFragment;
import com.example.daniela.sweetstop.fragment.PedidoFragment;
import com.example.daniela.sweetstop.fragment.ReservaFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpager;
    TabLayout tabLayout;
    int[] tabIcons = {
            R.drawable.ic_sillon,
            R.drawable.ic_menu,
            R.drawable.ic_pedidos
    };
    View iconSillon, iconMenu, iconPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        iconSillon = getLayoutInflater().inflate(R.layout.custom_icon_tab, null);
        iconSillon.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_sillon);

        iconMenu = getLayoutInflater().inflate(R.layout.custom_icon_tab, null);
        iconMenu.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_menu);

        iconPedidos = getLayoutInflater().inflate(R.layout.custom_icon_tab, null);
        iconPedidos.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_pedidos);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewpager);
        setupTabIcons();
        tabLayout.getTabAt(1).select();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PedidoFragment(), "Pedidos");
        adapter.addFragment(new CategoriaFragment(), "Categorias");
        adapter.addFragment(new ReservaFragment(), "Reservas");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setCustomView(iconSillon);
        tabLayout.getTabAt(1).setCustomView(iconMenu);
        tabLayout.getTabAt(2).setCustomView(iconPedidos);
    }
}
