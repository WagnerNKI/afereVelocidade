package com.example.wishikawa.aferevelocidade;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        final EditText placaLetra = (EditText) findViewById(R.id.placa_letra);
        final EditText placaNumero = (EditText) findViewById(R.id.placa_numero);
        final EditText velocidade = (EditText)findViewById(R.id.velocidade);
        final int sizePlacaLetra = 3;
        final int sizePlacaNumero = 4;
        final int sizeVelocidade = 2;
        final String[] placaLetraTexto = new String[]{};
        final String[] placaNumeroTexto = new String[]{};
        final String[] velocidadeTexto = new String[]{};
        final String[] corTexto = new String[]{};

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
                String selectedItemBloco = (String) parent.getItemAtPosition(position);

                if (position > 0) {

                    String piso[] = new String[]{};
                    String responsavel[] = new String[]{};

                    if (selectedItemBloco== "A1") {

                        piso = pisoA1;
                        responsavel = respA1;

                    } else if (selectedItemBloco== "Atrium") {

                        piso = pisoAtrium;
                        responsavel = respAtrium;

                    } else if (selectedItemBloco == "Maternidade") {

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
                            String selectedItemPiso = (String) parent.getItemAtPosition(position);
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
                            String selectedItemResp = (String) parent.getItemAtPosition(position);
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
        final AutoCompleteTextView textViewCor = (AutoCompleteTextView) findViewById(R.id.cor);
        String[] listaCores = getResources().getStringArray(R.array.cores);
        ArrayAdapter<String> adapterCor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaCores);
        textViewCor.setAdapter(adapterCor);

        //Auto completar para o campo Veiculo
        final AutoCompleteTextView textViewVeiculo = (AutoCompleteTextView) findViewById(R.id.veiculo);
        final String[] listaVeiculos = getResources().getStringArray(R.array.veiculos);
        ArrayAdapter<String> adapterVeiculo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaVeiculos);
        textViewVeiculo.setAdapter(adapterVeiculo);

        //nos EditText, o foco muda qunado atingido o número máx de caracteres
        placaLetra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (placaLetra.getText().toString().length()==sizePlacaLetra){
                    placaNumero.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        placaNumero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (placaNumero.getText().toString().length()==sizePlacaNumero){
                    textViewVeiculo.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        velocidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (velocidade.getText().toString().length()==sizeVelocidade){
                    textViewCor.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //como veiculo tem caracter máximo que pode não ser atingido, mas possui um lista de sugestões,
        //utiliza-se o click listener para saber quando alguém clicou em uma sugestão
        textViewVeiculo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                velocidade.requestFocus();
            }
        });
        //ajustando para pegar a resposta dos itens de multipla escolha
        final RadioGroup radioGroupCinto = (RadioGroup) findViewById(R.id.cinto);

        radioGroupCinto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String usaCinto = ((RadioButton) findViewById(radioGroupCinto.getCheckedRadioButtonId())).getText().toString();
                Toast.makeText(getApplicationContext(), usaCinto, Toast.LENGTH_LONG).show();

            }
        });

        final RadioGroup radioGroupInfrator = (RadioGroup) findViewById(R.id.infrator);
        radioGroupInfrator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String infrator = ((RadioButton) findViewById(radioGroupInfrator.getCheckedRadioButtonId())).getText().toString();
                Toast.makeText(getApplicationContext(), infrator, Toast.LENGTH_LONG).show();
            }
        });

        Button btnGravarDados = (Button) findViewById(R.id.btn_gravaDados);
        btnGravarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
