package com.veselove.myapplication210114;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("MyDB.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employeeInfo (name TEXT, phoneNumber TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS salaryAndPositionInfo (salary INTEGER, position TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS additionalInfo (familyStatus TEXT, dateOfBirth TEXT, placeOfResidence TEXT)");

        db.execSQL("INSERT INTO employeeInfo VALUES ('Kevin Parker', '+375291254822')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Bryan Collins', '+375295864472')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Jonathan Green', '+375259862528')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Timothy Flores', '+375298982540')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Aaron Miller', '+375297805264')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Seth Davis', '+375292658457')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Christian Hayes', '+375293656585')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('Evan Allen', '+375291520500')");
        db.execSQL("INSERT INTO employeeInfo VALUES ('James Morgan', '+375291598753')");

        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('1000', 'Chief director')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('600', 'Manager')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('350', 'Worker')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('550', 'Manager')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('370', 'Worker')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('340', 'Worker')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('350', 'Worker')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('700', 'Manager')");
        db.execSQL("INSERT INTO salaryAndPositionInfo VALUES ('350', 'Worker')");

        db.execSQL("INSERT INTO additionalInfo VALUES ('Married', '12.03.1971', 'Lozhinskaya str. 4-23')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Single', '25.09.1984', 'Rokossovsky av. 65-211')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Married', '06.05.1978', 'Slobodskaya str. 56-88')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Married', '11.10.1977', 'Rafieva str. 40-158')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Single', '29.01.1993', 'Kabushkin str. 18-90')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Single', '17.08.1989', 'Plekhanov str. 121-48')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Married', '23.04.1995', 'Golodeda str. 48-12')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Married', '26.02.1970', 'Pushkina av. 150-112')");
        db.execSQL("INSERT INTO additionalInfo VALUES ('Married', '06.12.1975', 'Gromova street 47-38')");

        TextView textView = findViewById(R.id.textView);

        switch(view.getId()) {
            case R.id.button1:
                textView.setText("");
                Cursor query1 = db.rawQuery("SELECT * FROM employeeInfo;", null);

                while(query1.moveToNext()) {
                    String name = query1.getString(0);
                    String phoneNumber = query1.getString(1);
                    textView.append("Name: " + name + ", phone: " + phoneNumber + "\n");
                }
                query1.close();
                db.close();
                break;
            case R.id.button2:
                textView.setText("");
                Cursor query2 = db.rawQuery("SELECT * FROM salaryAndPositionInfo;", null);
                while(query2.moveToNext()) {
                    String salary = query2.getString(0);
                    String position = query2.getString(1);
                    textView.append("Salary: " + salary + ", position: " + position + "\n");
                }
                query2.close();
                db.close();
                break;
            case R.id.button3:
                textView.setText("");
                Cursor query3 = db.rawQuery("SELECT * FROM additionalInfo;", null);
                while(query3.moveToNext()) {
                    String familyStatus = query3.getString(0);
                    String dateOfBirth = query3.getString(1);
                    textView.append("Family status: " + familyStatus + ", date of birth: " + dateOfBirth + "\n");
                }
                query3.close();
                db.close();
                break;
        }


    }

}