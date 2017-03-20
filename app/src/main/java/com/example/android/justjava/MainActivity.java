package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.NumberFormat;

import static com.example.android.justjava.R.id.quantity_text_view;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;
    public static final int RC_PHOTO_PICKER = 2;
    public static final String FRIENDLY_MSG_LENGTH_KEY = "friendly_msg_length";

    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;


    int numberOfCoffees = 0;
    int numberOfCoffeesOrdered = 0;
    int pricePerCoffee = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createWeatherMessage(-25, "Madison");
    }

    /**
     * Create a new message that specifies the city and temperature.
     *
     * @param temperature of the location.
     */
    private String createWeatherMessage(int temperature, String location) {
        return "Welcome to " + location + " where the temperature is " + temperature + " degrees out.";
    }

    /**
     * This method is called when the decrement total quantity is presed.
     */
    public void incrementTotalQuantity(View view) {
        numberOfCoffees = numberOfCoffees += 1;
        displayQuantity(numberOfCoffees);
    }

    /**
     * This method is called when the increment total quantity button is pressed.
     */
    public void decrementTotalQuantity(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees = numberOfCoffees -= 1;
            displayQuantity(numberOfCoffees);
        }
    }

    /**
    * This method displays the given quantity value on the screen.
    */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        quantityTextView.setText("You ordered " + number + " coffees.");
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        calculatePrice(numberOfCoffees, pricePerCoffee);
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("You just spent " + NumberFormat.getCurrencyInstance().format(number) + " for " + numberOfCoffees + " cups of coffee.\n Thank you!");
    }

    /**
     *
     */
    private void calculatePrice(int quantity, int price) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        int orderTotal = quantity * price;
        priceTextView.setText("You just spent " + NumberFormat.getCurrencyInstance().format(orderTotal) + " for " + quantity + " cups of coffee.\n Thank you!");
    }

    /**
     *
     */
    private String createOrderSummary(int orderTotal) {
        String message = "Name : Rockwell \n Quantity : 3 \n Total : " + orderTotal + "\n Thank you!";
        return message;
    }
}




