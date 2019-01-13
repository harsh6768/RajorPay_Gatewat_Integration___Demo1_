package com.technohack.rajorpay_gatewat_integration_demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {


    private Button payAmountBtn;
    private EditText amount;

    int paymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        payAmountBtn=findViewById(R.id.pay_Id);
        amount=findViewById(R.id.amount_Id);

        payAmountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //calling startPayment method for redirecting the customer to payment page
                startPayment();

            }
        });

    }

    private void startPayment() {

     paymentAmount=Integer.parseInt(amount.getText().toString().trim());

     Checkout checkout=new Checkout();

     //image for checkout page we add any image according to the requirement
     checkout.setImage(R.mipmap.ic_launcher);


        JSONObject options=new JSONObject();

        try {

            options.put("description","Order #123456");
            options.put("Currency","INR");
            //we need to multiply with the 100 to make it rupee
            //edit text will take the amount in paise
            options.put("amount",paymentAmount*100);

            //to open the payment gateway option we need to call the method open
            checkout.open(MainActivity.this,options);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(MainActivity.this, "Payment is Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(MainActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();

    }
}
