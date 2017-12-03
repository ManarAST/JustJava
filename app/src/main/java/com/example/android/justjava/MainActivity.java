/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 **/
package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText NameFeild = (EditText) findViewById(R.id.Name);
        String name = NameFeild.getText().toString();

        CheckBox WhippedCream = (CheckBox) findViewById(R.id.WhippingCreamCheckBox);
        boolean hasWhippedCream = WhippedCream.isChecked();

        CheckBox Chocolate = (CheckBox) findViewById(R.id.ChocolateCheckBox);
        boolean hasChocolate = Chocolate.isChecked();

        String summery = createOrderSummery(name, calculatePrice(hasChocolate, hasWhippedCream), hasWhippedCream, hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");

        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject));
        intent.putExtra(Intent.EXTRA_TEXT,summery );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public String createOrderSummery(String name, int OrderPrice, boolean addWhippedCream, boolean addChocolate) {
        String summery = getString(R.string.NameBox) + name;
        summery += "\n " + getString(R.string.addWhippedCream) + addWhippedCream + "\n " + getString(R.string.AddChocolate) + addChocolate;
        summery += "\n " + getString(R.string.quantityBox) + quantity;
        summery += "\n " + getString(R.string.TotalBox) + OrderPrice;
        summery += "\n " + getString(R.string.ThankYou);
        return summery;
    }

    /**
     * This method calculates the price
     */
    public int calculatePrice(boolean choco, boolean cream) {
       int basePrice = 5;
        if (cream)
            basePrice += 1;
        if (choco)
            basePrice += 2;
        return basePrice * quantity;

    }

    public void increment(View view) {
        if(quantity == 100 ){
            Toast.makeText(this, getString(R.string.moreToast), Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        display(quantity);


    }

    public void decrement(View view) {
        if(quantity == 1 ){
            Toast.makeText(this, getString(R.string.lessToast), Toast.LENGTH_SHORT).show();
            return;}
        quantity--;
        display(quantity);
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}