package com.example.comwhodrivecar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends ActionBarActivity {
    ArrayList<String> alPersons;
    ArrayAdapter<String> adapter;
    EditText tvAddPerson;
    TextView tvCurrent;
    TextView tvMax;
    Button btnAddPerson;
    Button btnStart;
    SeekBar sbAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvPersons = (ListView) findViewById(R.id.listView);

        alPersons = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, alPersons);

        lvPersons.setAdapter(adapter);

        tvAddPerson = (EditText) findViewById(R.id.add_person);
        sbAmount = (SeekBar) findViewById(R.id.amount_seekBar);
        sbAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvCurrent.setText(Integer.toString(sbAmount.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tvMax = (TextView) findViewById(R.id.maxValue);

        tvCurrent = (TextView) findViewById(R.id.currentValue);

        btnAddPerson = (Button) findViewById(R.id.add_button);
            btnAddPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alPersons.add(tvAddPerson.getText().toString());
                    adapter.notifyDataSetChanged();
                    sbAmount.setMax(alPersons.size());
                    tvMax.setText(Integer.toString(sbAmount.getMax()));
                    tvCurrent.setText(Integer.toString(sbAmount.getProgress()));
                }
            });
        btnStart = (Button) findViewById(R.id.start_button);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int iMax = Integer.valueOf(tvCurrent.getText().toString()).intValue();
                    int iMaxPersons = Integer.valueOf(tvMax.getText().toString()).intValue();
                    Random random = new Random();
                    ArrayList<String> alSelection = new ArrayList<String>();
                    ArrayList<String> alTempPersons = (ArrayList<String>) alPersons.clone();

                    for(int i = 0; i< iMax ; i++){
                        int iRandom = random.nextInt(alTempPersons.size());
                        alSelection.add(alTempPersons.get(iRandom));
                        alTempPersons.remove(iRandom);
                    }
                    String sResult = " ";
                    for(int j = 0; j<alSelection.size(); j++){
                        sResult = sResult + alSelection.get(j) +" ";
                    }
                    Toast.makeText(getApplicationContext(),sResult,Toast.LENGTH_LONG).show();
                    Log.i("RESULT",sResult);
                }
            });
        lvPersons.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                alPersons.remove(position);
                adapter.notifyDataSetChanged();
                sbAmount.setMax(alPersons.size());
                tvMax.setText(Integer.toString(sbAmount.getMax()));
                tvCurrent.setText(Integer.toString(sbAmount.getProgress()));
                return true;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    /*
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //return rootView;
        }
    }*/

}
