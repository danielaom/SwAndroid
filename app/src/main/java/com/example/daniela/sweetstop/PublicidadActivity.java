package com.example.daniela.sweetstop;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniela.sweetstop.model.Publicidad;
import com.example.daniela.sweetstop.tindercard.FlingCardListener;
import com.example.daniela.sweetstop.tindercard.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes.CONNECTION_TIMEOUT;
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes.READ_TIMEOUT;
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_PUBLICIDAD;

/**
 * Created by gonzalopro on 12/11/16.
 */

public class PublicidadActivity extends AppCompatActivity implements FlingCardListener.ActionDownInterface{

    public static MyAppAdapter myAppAdapter;
    private ArrayList<Publicidad> publicidads;
    private SwipeFlingAdapterView flingContainer;
    public static ViewHolder viewHolder;
    private ImageView imageViewExit;
    private Random random = new Random();

    /*public static void removeBackground() {
        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicidad);
        imageViewExit = (ImageView) findViewById(R.id.iv_exit_publicidad);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        publicidads = new ArrayList<>();

        /*Random randomGenerator = new Random();

        int randomInt = randomGenerator.nextInt(5);
        System.out.println("\nrandom :" +randomInt );
        switch (randomInt) {
            case 1:
                publicidads.add(new Publicidad("one", "Recuerda que un buen desayuno es para estar muy fuerte todo el dia."));
                publicidads.add(new Publicidad("two", "Se te antoja unos huevos con tocino, pancakes, fruta y mucho más...\nTodo eso puede estar en minutos en tu casa Pedidos al 4255261"));
                configure(publicidads);
                break;
            case 2:
                publicidads.add(new Publicidad("four", "Evita ensuciar tu cocina, utiliza el teléfono para tener esta delicia en tu mesa!!! \nAtendemos hasta las 22:00 "));
                publicidads.add(new Publicidad("five", "Por la carita que ponen cuando tienen que salir a comprar algo "));
                configure(publicidads);
                break;
            case 3:
                publicidads.add(new Publicidad("seven", "Dos platos dos bebidas.. Sólo faltan ustedes"));
                publicidads.add(new Publicidad("eight", "Estas a una llamada de tener los antojos en tu mesa!!!"));
                configure(publicidads);
                break;
            case 4:
                publicidads.add(new Publicidad("nine", "Se te antoja unos huevos con tocino, pancakes, fruta y mucho más..."));
                publicidads.add(new Publicidad("teen", "A la oficina o a casa, Nosotros somos quien te lo enviamos "));
                configure(publicidads);
                break;
            case 5:
                publicidads.add(new Publicidad("three", "Sabores dulce y salados pueden llegar a tu casa"));
                publicidads.add(new Publicidad("six", "Estas en correteos por las fiestas??? Solo llama y tendrás lo que desees en tu mesa "));
                configure(publicidads);
                break;
        }*/


        publicidads.add(new Publicidad("one", "Recuerda que un buen desayuno es para estar muy fuerte todo el dia."));
        //publicidads.add(new Publicidad("two", "Se te antoja unos huevos con tocino, pancakes, fruta y mucho más...\nTodo eso puede estar en minutos en tu casa Pedidos al 4255261"));
        //publicidads.add(new Publicidad("three", "Sabores dulce y salados pueden llegar a tu casa"));
        //publicidads.add(new Publicidad("four", "Evita ensuciar tu cocina, utiliza el teléfono para tener esta delicia en tu mesa!!! \nAtendemos hasta las 22:00 "));
        //publicidads.add(new Publicidad("five", "Por la carita que ponen cuando tienen que salir a comprar algo "));
        //publicidads.add(new Publicidad("six", "Estas en correteos por las fiestas??? Solo llama y tendrás lo que desees en tu mesa "));
        //publicidads.add(new Publicidad("seven", "Dos platos dos bebidas.. Sólo faltan ustedes"));
        //publicidads.add(new Publicidad("eight", "Estas a una llamada de tener los antojos en tu mesa!!!"));
        //publicidads.add(new Publicidad("nine", "Se te antoja unos huevos con tocino, pancakes, fruta y mucho más..."));
        publicidads.add(new Publicidad("teen", "A la oficina o a casa, Nosotros somos quien te lo enviamos "));

        /*publicidads.add(new Publicidad("one", "But I must expand I will give you a completelorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must ."));
        publicidads.add(new Publicidad("one", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must expand I will give you a completelorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must ."));
        publicidads.add(new Publicidad("one", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        publicidads.add(new Publicidad("one", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));*/




        //PublicidadActivity publicidadActivity = new PublicidadActivity();
        /*for (int i = 0; i < 2; i++) {
            randomPublicity.add(getRandomPublicity(publicidads));

        }*/


        myAppAdapter = new MyAppAdapter(publicidads, PublicidadActivity.this);
        flingContainer.setAdapter(myAppAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                publicidads.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                publicidads.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                //View view = flingContainer.getSelectedView();
                //view.findViewById(R.id.background).setAlpha(0);
                //
                // view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0); view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });
        //new ObtenerPublicidad(flingContainer).execute();



    }

    @Override
    protected void onStart() {
        super.onStart();





        imageViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void configure(final List<Publicidad> publicidads) {

    }
    private Publicidad getRandomPublicity(List<Publicidad> publicidads) {
        System.out.println("\nPU :" + publicidads.size() );
        int index = random.nextInt(publicidads.size());
        System.out.println("\nIndex :" + index );
        return publicidads.get(index);
    }

    @Override
    public void onActionDownPerform() {

    }


    /** Adapter **/
    public static class ViewHolder {
        public  FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;

    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Publicidad> parkingList;
        public Context context;

        private MyAppAdapter(List<Publicidad> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.cell_publicidad, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");
            viewHolder.cardImage.setImageDrawable(getDrawable(getResources().getIdentifier(parkingList.get(position).getImagePath(), "drawable", getPackageName())));

            //Glide.with(PublicidadActivity.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }

    public class ObtenerPublicidad extends AsyncTask<Void,Void,Void> implements FlingCardListener.ActionDownInterface {

        private SwipeFlingAdapterView flingContainer;
        //public  MyAppAdapter myAppAdapter;

        private List<Publicidad> publicidads;
        private URL url;
        private HttpURLConnection httpURLConnection;
        private StringBuilder result;

        public ObtenerPublicidad(SwipeFlingAdapterView swipeFlingAdapterView) {
            this.flingContainer = swipeFlingAdapterView;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(_URL_PUBLICIDAD);
            } catch (MalformedURLException e){
                e.printStackTrace();
            }

            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(READ_TIMEOUT);
                httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                int response_code = httpURLConnection.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                } else {
                    Log.d("Async", "error async");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            publicidads = new ArrayList<>();
            try {
                JSONObject group_info = new JSONObject(String.valueOf(result));

                JSONArray jsonArray = group_info.getJSONArray("productos_info");
                for (int i = 0; i < jsonArray.length() ; i++) {

                    JSONObject jsonGroup = jsonArray.getJSONObject(i);
                    publicidads.add(new Publicidad(jsonGroup.getString("imagen"), jsonGroup.getString("nombre") + ", " + jsonGroup.getString("descripcion")));

                    for (Publicidad publicidad: publicidads) {
                        Log.d("Async", "publicidad: " + publicidad.getDescription());
                    }



                    myAppAdapter = new MyAppAdapter(publicidads, PublicidadActivity.this);
                    flingContainer.setAdapter(myAppAdapter);


                    flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                        @Override
                        public void removeFirstObjectInAdapter() {

                        }

                        @Override
                        public void onLeftCardExit(Object dataObject) {
                            publicidads.remove(0);
                            myAppAdapter.notifyDataSetChanged();
                            Log.d("debug ", "Action was left");
                        }

                        @Override
                        public void onRightCardExit(Object dataObject) {
                            publicidads.remove(0);
                            myAppAdapter.notifyDataSetChanged();
                            Log.d("debug ", "Action was right");

                        }

                        @Override
                        public void onAdapterAboutToEmpty(int itemsInAdapter) {

                        }

                        @Override
                        public void onScroll(float scrollProgressPercent) {
                            View view = flingContainer.getSelectedView();
                            view.findViewById(R.id.background).setAlpha(0);
                            view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                            view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                        }
                    });


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onActionDownPerform() {


        }
    }
}
