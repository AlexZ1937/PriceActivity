package com.example.priceactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.LauncherActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.EditText;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentDateTime=((TextView)findViewById(R.id.textView));
        setInitialDateTime();


        ListView listView = (ListView) findViewById(R.id.text_view);
        final LinkedList<String> zametki = new LinkedList<>();







        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, zametki);







//        final LinkedList<Map<String,String>> zametki2 = new LinkedList<>();
        //  final ArrayAdapter<Map<String,String>> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, zametki2);
//        SimpleAdapter adapter = new SimpleAdapter(this, zametki2,
//                android.R.layout.simple_list_item_2,
//                new String[] {"title", "date"},
//                new int[] {android.R.id.text1,
//                        android.R.id.text2});
//        listView.setAdapter(adapter);


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
//                    zametki2.add(0, new HashMap<String, String>(){
//                        {
//                            put(((TextView)findViewById(R.id.editTextTextPersonName2)).getText().toString().replace("/","|")+
//                                    " / "+ ((EditText)findViewById(R.id.editTextNumberDecimal)).getText()+"$",
//                                    ((TextView)findViewById(R.id.textView)).getText().toString());
//                        }
//                    });

                    zametki.add(0, ((TextView)findViewById(R.id.editTextTextPersonName2)).getText().toString().replace("/","|")+
                            " / "+ ((EditText)findViewById(R.id.editTextNumberDecimal)).getText()+"$");

                    adapter.notifyDataSetChanged();
                    ((TextView)findViewById(R.id.editTextTextPersonName2)).setText("");
                    ((EditText)findViewById(R.id.editTextNumberDecimal)).setText("");
                    setInitialDateTime();
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
//                ((EditText)findViewById(R.id.editTextNumberDecimal)).setText(number);
//


                zametki.remove (position);

                adapter.notifyDataSetChanged();



//                ((TextView)findViewById(R.id.editTextTextPersonName2)).setText( parent.getItemAtPosition(position).toString());
//                ((EditText)findViewById(R.id.editTextNumberDecimal)).setText(Integer.valueOf(number));




            }


        });
    }





    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }


    };

        DatePickerDialog.OnDateSetListener dt = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, month);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setInitialDateTime();
            }
        };

    public void setDate(View v){

        new DatePickerDialog(MainActivity.this,
                dt,
                dateAndTime.get(Calendar.YEAR),

                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    public void setTime(View v){
        new TimePickerDialog(MainActivity.this,
                t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),

                dateAndTime.get(Calendar.MINUTE),
               true
        ).show();
    }

    private void setInitialDateTime(){
        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE  | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
    }







}