package sg.edu.rp.c346.id19011909.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //1. Creating Variable,
    EditText Total_Amount;
    EditText Total_People;
    EditText Discount;

    ToggleButton Service_Charge;
    ToggleButton GST_Charge;
    RadioGroup Payment_Choice;
    Button Reset;
    Button Split_Payment;

    TextView Total_Bill;
    TextView Individual_Payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Linking Variable,
        Total_Amount = findViewById(R.id.amount_et);
        Total_People = findViewById(R.id.pax_et);
        Discount = findViewById(R.id.discount_et);

        Service_Charge = findViewById(R.id.serviceButton) ;
        GST_Charge = findViewById(R.id.gstButton);
        Payment_Choice = findViewById(R.id.payment_rg);
        Reset = findViewById(R.id.resetButton);
        Split_Payment = findViewById(R.id.splitButton);

        Individual_Payment = findViewById(R.id.individual_payment);
        Total_Bill = findViewById(R.id.total_bill);

        //3. Creating Action,
        Split_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checker,
                String Amount_Checker = Total_Amount.getText().toString();
                String People_Checker = Total_People.getText().toString();
                String Discount_Checker = Discount.getText().toString();


                if(TextUtils.isEmpty(Amount_Checker))
                {
                    Total_Amount.setError("Please Input a Value");
                    return;
                }

                if(TextUtils.isEmpty(People_Checker))
                {
                    Total_People.setError("Please Input a Value");
                    return;
                }

                if(TextUtils.isEmpty(Discount_Checker))
                {
                    Discount.setError("Please Input a Value");
                    return;
                }

                //Other Variable,
                Double DAmount = Double.parseDouble(Total_Amount.getText().toString());
                Double DPeople = Double.parseDouble(Total_People.getText().toString());
                Double DDiscount = Double.parseDouble(Discount.getText().toString());

                Double Data1 = 0.0;

                if(DAmount > DPeople)
                {
                    if(DPeople > 0)
                    {
                        if(Service_Charge.isChecked())
                        {
                            if(GST_Charge.isChecked())
                            {   Data1 = DAmount*1.17;       }

                            else
                            {   Data1 = DAmount*1.1;        }
                        }

                        else
                        {
                            if(GST_Charge.isChecked())
                            {   Data1 = DAmount*1.07;       }

                            else
                            {   Data1 = DAmount;          }
                        }

                        Data1 = Data1*((100-DDiscount)/100);
                        Double Divided_Cost = Data1/DPeople;

                        int RadioChecker = Payment_Choice.getCheckedRadioButtonId();

                        if(RadioChecker == R.id.cash_rb)
                        {
                            Total_Bill.setText(String.format("Total Bill : $%.2f", Data1));
                            Individual_Payment.setText(String.format("Each Pays: $%.2f in cash", Divided_Cost));
                        }

                        else
                        {
                            Total_Bill.setText(String.format("Total Bill : $%.2f", Data1));
                            Individual_Payment.setText(String.format("Each Pays: $%.2f via PayNow to 912345678", Divided_Cost));
                        }
                    }

                    else
                    {   Total_People.setError("Please Input a Valid Value");        }

                }

                else
                {   Total_Amount.setError("Please Input a Valid Value");        }


            }
        });

        //Reset Button,
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                boolean Button_Reset = false;
                Total_Amount.setText("");
                Total_People.setText("");
                Discount.setText("");
                Total_Bill.setText("");
                Individual_Payment.setText("");
                Service_Charge.setChecked(Button_Reset);
                GST_Charge.setChecked(Button_Reset);
            }
        });


    }
}