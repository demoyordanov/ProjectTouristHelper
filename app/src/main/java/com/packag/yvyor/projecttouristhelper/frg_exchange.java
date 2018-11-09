package com.packag.yvyor.projecttouristhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class frg_exchange extends Fragment {
    public static String rawCurrency;
    private ArrayList<CountryItem> countryItems;
    private CountryAdapter countryAdapter;
    TextView currencyTextOutput;
    ImageView refreshButton;
    EditText currencyTextInput;
    Spinner currencyAtHand, changeToCurrency;

    public static void setRawCurrency(String currency)
    {
        rawCurrency = currency;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frg_exchange,null);
    }

    @Override //same as OnCreate in Activity ; GetActivity, instead of "this" or "Context"
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countryItems = new ArrayList<CountryItem>();
        currencyTextInput = view.findViewById(R.id.currencyInput);
        currencyTextOutput = view.findViewById(R.id.currencyOutput);
        currencyAtHand = view.findViewById(R.id.spinnerInput);
        changeToCurrency = view.findViewById(R.id.spinnerOutput);
        refreshButton = view.findViewById(R.id.exchangeRefresh);
        populateList();
        countryAdapter = new CountryAdapter(getActivity(), countryItems);
        currencyAtHand.setAdapter(countryAdapter);
        changeToCurrency.setAdapter(countryAdapter);
        final frg_exchange frgExch = this;
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryItem currency1 = (CountryItem) currencyAtHand.getSelectedItem();
                CountryItem currency2 = (CountryItem) changeToCurrency.getSelectedItem();
                jsonCurrency currency = new jsonCurrency(currency1.getCountryCurrency(), currency2.getCountryCurrency(), frgExch);
                currency.execute();

            }
        });
    }

    public void displayResults(String result)
    {
        if(currencyTextInput.getText().toString().length()<=0)
        {
            currencyTextOutput.setText(result);
        }
        else {
            double amount = Double.parseDouble(result);
            double multiplier = Double.parseDouble(currencyTextInput.getText().toString());
            double multiplication = amount*multiplier;
            currencyTextOutput.setText(String.format("%.2f", multiplication));
        }
    }
    private void populateList() {
        countryItems.add(new CountryItem("Euro","EUR", R.drawable.flag_european_union, 0, 0, 0));
        countryItems.add(new CountryItem("Dollar","USD", R.drawable.flag_usa, 0, 0, 0));
        countryItems.add(new CountryItem("Lev","BGN", R.drawable.flag_bulgaria, 0, 0, 0));
        countryItems.add(new CountryItem("Koruna","CZK", R.drawable.flag_czech_republic, 0, 0, 0));
        countryItems.add(new CountryItem("Krone","DKK", R.drawable.flag_denmark, 0, 0, 0));
        countryItems.add(new CountryItem("Pound","GBP", R.drawable.flag_united_kingdom, 0, 0, 0));
        countryItems.add(new CountryItem("Forint","HUF", R.drawable.flag_hungary, 0, 0, 0));
        countryItems.add(new CountryItem("Zloty","PLN", R.drawable.flag_poland, 0, 0, 0));
        countryItems.add(new CountryItem("Leu","RON", R.drawable.flag_romania, 0, 0, 0));
        countryItems.add(new CountryItem("Krona","SEK", R.drawable.flag_sweden, 0, 0, 0));
        countryItems.add(new CountryItem("Frank","CHF", R.drawable.flag_switzerland, 0, 0, 0));
        countryItems.add(new CountryItem("Króna","ISK", R.drawable.flag_iceland, 0, 0, 0));
        countryItems.add(new CountryItem("krone","NOK", R.drawable.flag_norway, 0, 0, 0));
        countryItems.add(new CountryItem("Kuna","HRK", R.drawable.flag_croatia, 0, 0, 0));
        countryItems.add(new CountryItem("Ruble","RUB", R.drawable.flag_russia, 0, 0, 0));
        countryItems.add(new CountryItem("Lira","TRY", R.drawable.flag_turkey, 0, 0, 0));
    }

}
