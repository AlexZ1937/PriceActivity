package com.example.priceactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.LauncherActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity implements Removable {

    TextView currentDateTime;
    LinkedList<String> zametki;
    ArrayAdapter<String> adapter;
    Calendar dateAndTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentDateTime=((TextView)findViewById(R.id.textView));
        setInitialDateTime();


        ListView listView = (ListView) findViewById(R.id.text_view);
        zametki = new LinkedList<String>();




        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, zametki);
        listView.setAdapter(adapter);


        (findViewById(R.id.editTextTextPersonName2)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //
            }
        });
        (findViewById(R.id.button)).setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if((((TextView)findViewById(R.id.editTextTextPersonName2)).getText().toString().length())>0 &&
                        ((EditText)findViewById(R.id.editTextNumberDecimal)).getText().toString().length()>0)
                {
                    zametki.add(0, ((TextView)findViewById(R.id.editTextTextPersonName2)).getText().toString().replace("/","|")+
                            " / "+ ((EditText)findViewById(R.id.editTextNumberDecimal)).getText()+"$");
                    adapter.notifyDataSetChanged();
                    ((TextView)findViewById(R.id.editTextTextPersonName2)).setText("");
                    ((EditText)findViewById(R.id.editTextNumberDecimal)).setText("");
                }

            }


        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String iteral=" / ";
//                String tmp=parent.getItemAtPosition(position).toString().split(iteral)[1];
//                String number="";
//
//                char ch='0';
//                int posAlt=0;
//                while(ch!='$')
//                {
//                    number+=tmp.toCharArray()[posAlt];
//                    posAlt++;
//                    ch=tmp.toCharArray()[posAlt];
//                }
//
//
//
//                ((TextView)findViewById(R.id.editTextTextPersonName2)).setText(parent.getItemAtPosition(position).toString().split(iteral)[0]);

                CancelDialog fragment = new CancelDialog();
                Bundle bundle = new Bundle();
                bundle.putString("deleteitem", zametki.get(position).toString());
                bundle.putInt("deleteitemkey",position);
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "custom");
//                ((TextView)findViewById(R.id.editTextTextPersonName2)).setText( parent.getItemAtPosition(position).toString());
//                ((EditText)findViewById(R.id.editTextNumberDecimal)).setText(Integer.valueOf(number));


//               ((EditText)findViewById(R.id.editTextNumberDecimal)).setText(number);

            }


        });
    }









    public void setTime(View v){

        new DatePickerDialog(MainActivity.this,
                t,
                dateAndTime.get(Calendar.YEAR),

                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void setInitialDateTime(){
        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE  | DateUtils.FORMAT_SHOW_YEAR));
    }

    DatePickerDialog.OnDateSetListener t = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, month);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }


    };


    @Override
    public void Remove(int key) {
        zametki.remove (key);
        adapter.notifyDataSetChanged();
    }
}