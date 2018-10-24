package com.example.wishikawa.aferevelocidade;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //associating the spinner
        final Spinner spinnerBloco = (Spinner) findViewById(R.id.spinner_bloco);
        final Spinner spinnerPiso = (Spinner) findViewById(R.id.spinner_piso);
        final Spinner spinnerResp = (Spinner) findViewById(R.id.spinner_responsavel);

        //strings with the values for each level and block
        String[] bloco = new String[]{
                "",
                "A1",
                "Atrium",
                "Maternidade"
        };

        final String[] pisoA1 = new String[]{
                "",
                "G1",
                "G2",
                "G3"
        };

        final String[] pisoMate = new String[]{
                "",
                "-1",
                "-2",
                "-3"
        };

        final String[] pisoAtrium = new String[]{
                "",
                "P.A."
        };
        final String[] respA1 = new String[]{
                "",
                "Lydson",
                "Artur",
                "Jackson",
                "Willian Messias",
                "Willian Vieira",
                "Tiago",
        };

        final String[] respAtrium = new String[]{
                "",
                "Jefferson",
                "Gilson",
                "Américo",
                "Wesley",
                "Maurílio"
        };

        final String[] resplMaternidade = new String[]{
                "",
                "Joel",
                "Silvano",
                "Wagner",
                "Manoel Marcos",
                "Pacheco",
                "Miranda",
                "Rogério"
        };
        //associating the spinner and the string
        final List<String> blocoList = new ArrayList<>(Arrays.asList(bloco));

        final ArrayAdapter<String> spinnerBlocoArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, blocoList) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;

                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerBlocoArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerBloco.setAdapter(spinnerBlocoArrayAdapter);

        spinnerBloco.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

                if (position > 0) {

                    String piso[] = new String[]{};
                    String responsavel[] = new String[]{};

                    if (position == 1) {

                        piso = pisoA1;
                        responsavel = respA1;

                    } else if (position == 2) {

                        piso = pisoAtrium;
                        responsavel = respAtrium;

                    } else if (position == 3) {

                        piso = pisoMate;
                        responsavel = resplMaternidade;
                    }

                    final List<String> pisoList = new ArrayList<>(Arrays.asList(piso));
                    final List<String> respList = new ArrayList<>(Arrays.asList(responsavel));

                    final ArrayAdapter<String> spinnerPisoArrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this, R.layout.spinner_item, pisoList) {

                        @Override
                        public boolean isEnabled(int position) {
                            if (position == 0) {
                                return false;
                            } else {
                                return true;
                            }
                        }

                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView textView = (TextView) view;

                            if (position == 0) {
                                textView.setTextColor(Color.GRAY);
                            } else {
                                textView.setTextColor(Color.BLACK);
                            }
                            return view;
                        }
                    };

                    spinnerPisoArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                    spinnerPiso.setAdapter(spinnerPisoArrayAdapter);

                    spinnerPiso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                    {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItemText = (String) parent.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    final ArrayAdapter<String> spinnerRespArrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this, R.layout.spinner_item, respList) {

                        @Override
                        public boolean isEnabled(int position) {
                            if (position == 0) {
                                return false;
                            } else {
                                return true;
                            }
                        }

                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView textView = (TextView) view;

                            if (position == 0) {
                                textView.setTextColor(Color.GRAY);
                            } else {
                                textView.setTextColor(Color.BLACK);
                            }
                            return view;
                        }
                    };

                    spinnerRespArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                    spinnerResp.setAdapter(spinnerRespArrayAdapter);

                    spinnerResp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //Auto completar para o campo Cor
        AutoCompleteTextView textViewCor = (AutoCompleteTextView) findViewById(R.id.cor);
        String[] cores = getResources().getStringArray(R.array.cores);
        ArrayAdapter<String> adapterCor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cores);
        textViewCor.setAdapter(adapterCor);

        //Auto completar para o campo Veiculo
        AutoCompleteTextView textViewVeiculo = (AutoCompleteTextView) findViewById(R.id.veiculo);
        String[] veiculos = getResources().getStringArray(R.array.veiculos);
        ArrayAdapter<String> adapterVeiculo = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,veiculos);
        textViewVeiculo.setAdapter(adapterVeiculo);
    }
}
