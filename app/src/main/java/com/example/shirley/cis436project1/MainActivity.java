/*

Name: Shirley Li (Yingfei)
Class: CIS436 Mobile App Dev
Project: Slot Machine

Professor: JOHN P. BAUGH

 */

package com.example.shirley.cis436project1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends Activity  implements View.OnClickListener{

    private static final Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetvalue = (Button)findViewById(R.id.btnSetValue);
        btnSetvalue.setOnClickListener(this);

        Button btnNewgame = (Button)findViewById(R.id.btnNewGame);
        btnNewgame.setOnClickListener(this);

        Button btnPulllever = (Button)findViewById(R.id.btnPullLever);
        btnPulllever.setOnClickListener(this);
        btnPulllever.setEnabled(false);

    }

    public void onClick(View v){
        EditText etAmount = (EditText)findViewById(R.id.etAmount);
        TextView tvBank = (TextView)findViewById(R.id.tvBank);
        TextView tvNumber1 = (TextView)findViewById(R.id.tvNumber1);
        TextView tvNumber2 = (TextView)findViewById(R.id.tvNumber2);
        TextView tvNumber3 = (TextView)findViewById(R.id.tvNumber3);

        Button btnSetvalue = (Button)findViewById(R.id.btnSetValue);  //find setvalue button again by id
        Button btnPulllever = (Button)findViewById(R.id.btnPullLever);
        btnSetvalue.setEnabled(true);
        btnPulllever.setEnabled(false);

        switch (v.getId()) {
            case R.id.btnSetValue:

                if (etAmount.getText().toString().trim().length() > 0) {  // this is make sure the setvalue button works
                    if ((Integer.parseInt(etAmount.getText().toString()) >= 100) &
                            ((Integer.parseInt(etAmount.getText().toString()) <= 500))) {
                        tvBank.setText("$"+ etAmount.getText().toString());  // get the amount we input in

                        etAmount.setEnabled(false);
                        btnSetvalue.setEnabled(false);  // grey out the setvalue button
                        btnPulllever.setEnabled(true);

                    } else {
                        Toast.makeText(MainActivity.this, "The values are not within the range 100 to 500", Toast.LENGTH_LONG).show();
                    }
                }

                //otherwise show the player a message that they have to input amount
                else{
                    Toast.makeText(MainActivity.this,"Please input the money you want to play with", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnNewGame:
                Newgame();
                break;

            case R.id.btnPullLever:
                btnSetvalue.setEnabled(false);  // grey out the setvalue button
                btnPulllever.setEnabled(true);

                int num = Integer.valueOf(tvBank.getText().toString().substring(1));
                num = num -5;
                int randInt1 = r.nextInt(9)+1;
                int randInt2 = r.nextInt(9)+1;
                int randInt3 = r.nextInt(9)+1;

                tvBank.setText("$" + Integer.toString(num));  //everytime you click this button, Bank amount loses 5 dollars

                //here are how to dealing with all three tvnumbers (slots)
                tvNumber1.setText(String.valueOf(randInt1)); //set first tv as randon number 1
                tvNumber2.setText(String.valueOf(randInt2));
                tvNumber3.setText(String.valueOf(randInt3));

                //this part was used for maunally testing
//                int a = Integer.valueOf(tvNumber1.getText().toString());
//                int b = Integer.valueOf(tvNumber2.getText().toString());
//                int c = Integer.valueOf(tvNumber3.getText().toString());


                if (((randInt1) == (randInt2)) && ((randInt1) ==(randInt3)) && ((randInt2) ==(randInt3))){
                    if (randInt1<5){
                        num = num +40;
                        tvBank.setText("$" + Integer.toString(num));
                        Toast.makeText(MainActivity.this,"Congrats! You just won $40",Toast.LENGTH_LONG).show();
                    }

                    if (randInt1>=5 && randInt1 <=8){
                        num = num + 100;
                        tvBank.setText("$" + Integer.toString(num));
                        Toast.makeText(MainActivity.this,"Congrats! You just won $100",Toast.LENGTH_LONG).show();
                    }

                    if (randInt1 == 9) {
                        num = num +1000;
                        tvBank.setText("$" + Integer.toString(num));
                        Toast.makeText(MainActivity.this,"Congrats! You just won $1000",Toast.LENGTH_LONG).show();

                    }
                }


                else if (randInt1==randInt2 || randInt1==randInt3 || randInt2==randInt3){
                    num = num + 10;
                    tvBank.setText("$" + Integer.toString(num));

                    Toast.makeText(MainActivity.this,"Two numbers match! You just earned $10",Toast.LENGTH_LONG).show();

                }

                if (num>1000){
                    Toast.makeText(MainActivity.this,"Wow! You have cleared out the slot machine",Toast.LENGTH_LONG).show();
                    Newgame();
                }

                else if (num ==0) {
                    Toast.makeText(MainActivity.this,"Oh man! You lost all your money",Toast.LENGTH_LONG).show();
                    Newgame();
                }
                break;
        }

    }
    private void Newgame(){
        EditText etAmount = (EditText)findViewById(R.id.etAmount);
        TextView tvBank = (TextView)findViewById(R.id.tvBank);
        Button btnSetvalue = (Button)findViewById(R.id.btnSetValue);  //find setvalue button again by id
        Button btnPulllever = (Button)findViewById(R.id.btnPullLever);
        TextView tvNumber1 = (TextView)findViewById(R.id.tvNumber1);
        TextView tvNumber2 = (TextView)findViewById(R.id.tvNumber2);
        TextView tvNumber3 = (TextView)findViewById(R.id.tvNumber3);


        int new_amount = 0;
        tvBank.setText(String.valueOf(new_amount));
        etAmount.setEnabled(true);
        etAmount.getText().clear();
        tvNumber1.setText("");
        tvNumber2.setText("");
        tvNumber3.setText("");
        btnSetvalue.setEnabled(true);  // reset the button
        btnPulllever.setEnabled(false);

    }

}

