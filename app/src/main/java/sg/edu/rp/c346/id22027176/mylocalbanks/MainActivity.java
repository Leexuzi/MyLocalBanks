package sg.edu.rp.c346.id22027176.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDBS;
    Button btnOCBC;
    Button btnUOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDBS = findViewById(R.id.buttonDBS);
        btnOCBC = findViewById(R.id.buttonOCBC);
        btnUOB = findViewById(R.id.buttonUOB);

        registerForContextMenu(btnDBS);
        registerForContextMenu(btnOCBC);
        registerForContextMenu(btnUOB);

        btnDBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent("DBS", "web");
            }
        });
        btnOCBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent("OCBC", "web");
            }
        });
        btnUOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent("UOB", "web");
            }
        });
    }

    public void intent(String bank, String place) { //if i could just use the numbers as int rather than strings, i maybe could overload
        if(place.equalsIgnoreCase("web")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("");
            if (bank.equalsIgnoreCase("DBS")) {
                uri = Uri.parse("https://www.dbs.com.sg");
            } else if (bank.equalsIgnoreCase("OCBC")) {
                uri = Uri.parse("https://www.ocbc.com");
            } else {
                uri = Uri.parse("https://www.uob.com.sg");
            }
            intent.setData(uri);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("");
            if (bank.equalsIgnoreCase("DBS")) {
                uri = Uri.parse("tel:"+"1800 111 1111");
            } else if (bank.equalsIgnoreCase("OCBC")) {
                uri = Uri.parse("tel:"+"1800 363 3333");
            } else {
                uri = Uri.parse("tel:"+"1800 222 2121");
            }
            intent.setData(uri);
            startActivity(intent);
        }
    }

    //making menu for the buttons
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==btnDBS){
            menu.add(0,0,0,R.string.website);
            menu.add(0,1,1,R.string.contact_the_bank);
            menu.add(0,2,2,R.string.toggle_favourite);
        }
        else if(v==btnOCBC){
            menu.add(1,0,0,R.string.website);
            menu.add(1,1,1,R.string.contact_the_bank);
            menu.add(0,2,2,R.string.toggle_favourite);
        }
        else{
            menu.add(2,0,0,R.string.website);
            menu.add(2,1,1,R.string.contact_the_bank);
            menu.add(0,2,2,R.string.toggle_favourite); //repetitive. wonder if i could just use a loop to add
        }
    }

    //menu for the buttons selection handling
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getGroupId()==0){
            if(item.getItemId()==0){
                intent("DBS", "web");
            }
            else if(item.getItemId()==1){
                intent("DBS", "ph");
            }
            else{
                btnDBS.setTextColor(Color.RED);//repeats, wonder if i can make into method too. too tired rn
            }
        }
        else if(item.getGroupId()==1){
            if(item.getItemId()==0){
                intent("OCBC", "web");
            }
            else if(item.getItemId()==1){
                intent("OCBC", "ph");
            }
            else{
                btnOCBC.setTextColor(Color.RED);//repeats, wonder if i can make into method too. too tired rn
            }
        }
        else{
            if(item.getItemId()==0){
                intent("UOB", "web");
            }
            else if(item.getItemId()==1){
                intent("UOB", "ph");
            }
            else{
                btnUOB.setTextColor(Color.RED);//repeats, wonder if i can make into method too. too tired rn
            }
        }
        return super.onContextItemSelected(item);
    }

    //enhancement

    //making menu for the language change
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lang_menu, menu);
        return true;
    }

    //making the tns function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.eng) {
            btnDBS.setText(R.string.dbs);
            btnOCBC.setText(R.string.ocbc);
            btnUOB.setText(R.string.uob);
            return true;
        } else if (id == R.id.chi) {
            btnDBS.setText(R.string.dbsTransed);
            btnOCBC.setText(R.string.ocbcTransed);
            btnUOB.setText(R.string.uobTransed);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}