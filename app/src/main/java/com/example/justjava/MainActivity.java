package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {




    public int quantity = 2;



    public int calculatePrice(Boolean isWhippedcream , Boolean isChocolate) {
         int price = 5;
        if (isChocolate) {
            price = price + 2;
        }
        if (isWhippedcream){
            price = price + 1;
        }
        return quantity * price;

    }

    public void order(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String name = editText.getText().toString();

        //TextView textView = (TextView) findViewById(R.id.orderSummary);
        CheckBox checkBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.chocolateCheckbox);
        Boolean isWhippedCreamChecked = checkBox.isChecked();
        Boolean isChocolateChecked = checkBox1.isChecked();
        String bio = "" + "Name :- " + name + "\nAdd whipped cream?:- " + isWhippedCreamChecked +
                "\nAdd chocolate?:-  " + isChocolateChecked + "\nQuantity :- " + quantity +
                "\nTotal :- " + NumberFormat.getCurrencyInstance().format(calculatePrice(isWhippedCreamChecked , isChocolateChecked)) + "\nThankyou!!";

       // textView.setText(bio);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT , bio);
        startActivity(intent);


    }


    public void increment(View view) {
        TextView textView = (TextView) findViewById(R.id.quantity_textview);
        quantity = quantity + 1;
        textView.setText("" + quantity);

        if (quantity >100){
            quantity = 100;
            textView.setText("" + quantity);
            Toast toast = Toast.makeText(this , "You cannot order more than 100 cups " , Toast.LENGTH_LONG);
            toast.show();
        }


    }


    public void decrement(View view) {
        TextView textView = (TextView) findViewById(R.id.quantity_textview);
        quantity = quantity - 1;
        textView.setText("" + quantity);
        if (quantity < 1){
            quantity = 1;
            textView.setText("" + quantity);
            Toast toast = Toast.makeText(this , "You cannot order less than 1 cup " , Toast.LENGTH_LONG);
            toast.show();

        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}