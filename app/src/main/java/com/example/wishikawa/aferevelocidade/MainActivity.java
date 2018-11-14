package com.example.wishikawa.aferevelocidade;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private String plateLetterText;
    private String plateNumberText;
    private String velocityText;
    private String colorText;
    private String selectedBlock;
    private String selectedFloor;
    private String selectedResponsible;
    private String offenderText;
    private String beltText;
    private String vehicleText;
    private String plateComplete;
    private String currentDate;
    private String currentTime;

    //declaring the name of the file in internal storage to have data saved and read
    private String filename = "DadosVelocidade";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //using Retrofit 2 as the Http web service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();
        final QuestionsGoogleForm questionsGoogleForm = retrofit.create(QuestionsGoogleForm.class);

        //associating the spinner
        final Spinner blockSpinner = (Spinner) findViewById(R.id.spinner_block);
        final Spinner floorSpinner = (Spinner) findViewById(R.id.spinner_floor);
        final Spinner responsibleSpinner = (Spinner) findViewById(R.id.spinner_responsible);
        final EditText plateLetter = (EditText) findViewById(R.id.plate_letter);
        final EditText plateNumber = (EditText) findViewById(R.id.plate_number);
        final EditText velocity = (EditText) findViewById(R.id.velocity);
        final int sizePlateLetter = 3;
        final int sizePlateNumber = 4;
        final int sizeVelocity = 2;

        //strings with the values for each level and block
        final String[] block = new String[]{
                "",
                "A1",
                "Atrium",
                "Maternidade"
        };

        final String[] a1Floor = new String[]{
                "",
                "G1",
                "G2",
                "G3"
        };

        final String[] mateFloor = new String[]{
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

        final String[] respMaternidade = new String[]{
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
        final List<String> blockList = new ArrayList<>(Arrays.asList(block));

        final ArrayAdapter<String> blockSpinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, blockList) {
            //disabling the first option (blank option)
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

        blockSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        blockSpinner.setAdapter(blockSpinnerArrayAdapter);

        blockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            //changing the string in the floor and responsible spinners depending on the block spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBlock = (String) parent.getItemAtPosition(position);

                if (position > 0) {


                    String floor[] = new String[]{};
                    String responsible[] = new String[]{};

                    if (selectedBlock == "A1") {

                        floor = a1Floor;
                        responsible = respA1;

                    } else if (selectedBlock == "Atrium") {

                        floor = pisoAtrium;
                        responsible = respAtrium;

                    } else if (selectedBlock == "Maternidade") {

                        floor = mateFloor;
                        responsible = respMaternidade;
                    }

                    final List<String> floorList = new ArrayList<>(Arrays.asList(floor));
                    final List<String> respList = new ArrayList<>(Arrays.asList(responsible));

                    final ArrayAdapter<String> floorSpinnerArrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this, R.layout.spinner_item, floorList) {

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

                    floorSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                    floorSpinner.setAdapter(floorSpinnerArrayAdapter);

                    floorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                    {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedFloor = (String) parent.getItemAtPosition(position);
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
                    responsibleSpinner.setAdapter(spinnerRespArrayAdapter);

                    responsibleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedResponsible = (String) parent.getItemAtPosition(position);
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

        //Auto complete to color field
        final AutoCompleteTextView autoCompleteColor = (AutoCompleteTextView) findViewById(R.id.cor);
        String[] colorsList = getResources().getStringArray(R.array.cores);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorsList);
        autoCompleteColor.setAdapter(adapterColor);

        //Auto complete to vehicle field
        final AutoCompleteTextView autoCompleteVehicle = (AutoCompleteTextView) findViewById(R.id.veiculo);
        final String[] vehiclesList = getResources().getStringArray(R.array.veiculos);
        ArrayAdapter<String> adapterVehicle = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vehiclesList);
        autoCompleteVehicle.setAdapter(adapterVehicle);

        //in the EditText, the focus (selected field) changes when each field reaches its max value
        plateLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                plateLetterText = plateLetter.getText().toString();

                if (plateLetterText.length() == sizePlateLetter) {
                    plateNumber.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        plateNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                plateNumberText = plateNumber.getText().toString();

                if (plateNumberText.length() == sizePlateNumber) {
                    autoCompleteVehicle.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        velocity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (velocity.getText().toString().length() == sizeVelocity) {
                    autoCompleteColor.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //vehicle field has no maximum caracter limit, so a click listener is set when a suggestion is selected
        autoCompleteVehicle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                velocity.requestFocus();

            }
        });


        //getting the values of each RadioButton selected
        final RadioGroup radioGroupBelt = (RadioGroup) findViewById(R.id.belt);

        radioGroupBelt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                RadioButton belt = (RadioButton) findViewById(radioGroupBelt.getCheckedRadioButtonId());

                //when the radio button is checked, get the value of it, so,
                // when the radio group is unchecked, it will try to get a value, but there won't be one
                if (belt != null) {
                    beltText = belt.getText().toString();
                }
            }
        });

        final RadioGroup radioGroupOffender = (RadioGroup) findViewById(R.id.infrator);
        radioGroupOffender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton offender = (RadioButton) findViewById(radioGroupOffender.getCheckedRadioButtonId());

                if (offender != null) {
                    offenderText = offender.getText().toString();
                }
            }
        });


        Button btnSaveData = (Button) findViewById(R.id.btn_recordData);

        btnSaveData.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                //in case vehicle and color are not completed using auto complete,
                //get the values written
                vehicleText = autoCompleteVehicle.getText().toString();
                colorText = autoCompleteColor.getText().toString();
                velocityText = velocity.getText().toString();

                //in case some values are empty, display an alert show which ones need to be completed
                String emptyValues = "";
                if (selectedBlock == "" || selectedFloor == "" || plateLetterText == null
                        || plateNumberText == null || vehicleText.isEmpty() || velocityText == null
                        || offenderText == null) {

                    if (selectedBlock == "") {
                        emptyValues += "Bloco, ";
                    }

                    if (selectedFloor == null) {
                        emptyValues += "Piso, ";
                    }

                    if (plateLetterText == null || plateNumberText == null) {
                        emptyValues += "Placa, ";
                    }

                    if (vehicleText.isEmpty()) {
                        emptyValues += "Veículo, ";
                    }

                    if (velocityText == null) {
                        emptyValues += "Velocidade, ";
                    }
                    if (offenderText == null) {
                        emptyValues += "Infrator.";
                    }

                    //showing alert in case any of the above values is empty
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Falta completar:");
                    builder.setMessage(emptyValues);

                    builder.setPositiveButton("Ok", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                    textView.setTextSize(30);

                } else {

                    plateComplete = plateLetterText + "-" + plateNumberText;

                    //getting date and time from the system when the button is pressed and
                    //the minimum information is complete
                    java.util.Date currentTimeandDate = Calendar.getInstance().getTime();

                    //getting the time using 24h time format
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    currentTime = timeFormat.format(currentTimeandDate);

                    //getting the date
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    currentDate = dateFormat.format(currentTimeandDate);

                    Call<Void> completeQuestionnaireCall = questionsGoogleForm.completeQuestionnaire(currentDate,
                            selectedBlock, selectedFloor, currentTime, plateComplete, vehicleText, velocityText, colorText,
                            beltText, offenderText, selectedResponsible);

                    completeQuestionnaireCall.enqueue(callCallback);


                    //erasing the values in some fields
                    radioGroupBelt.clearCheck();
                    radioGroupOffender.clearCheck();
                    plateLetter.getText().clear();
                    plateNumber.getText().clear();
                    autoCompleteVehicle.getText().clear();
                    velocity.getText().clear();
                    autoCompleteColor.getText().clear();


                }

            }
        });

        Button exportData = (Button) findViewById(R.id.btn_exportData);

        exportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fileInputStream = openFileInput(filename);
                    int c;

                    String temp = "";
                    ArrayList<String> dataArray = new ArrayList<>();

                    while ((c = fileInputStream.read()) != -1) {
                        String current = Character.toString((char) c);

                        if (!current.contentEquals(";")) {
                            temp += current;
                        }

                        if (current.contentEquals(";")) {
                            dataArray.add(temp);

                            temp = "";

                            System.out.println("Line read");
                        }

                        if (current.contentEquals("\n")) {
                            String savedDate = dataArray.get(0);
                            String savedBlock = dataArray.get(1);
                            String savedFloor = dataArray.get(2);
                            String savedTime = dataArray.get(3);
                            String savedPlate = dataArray.get(4);
                            String savedVehicle = dataArray.get(5);
                            String savedVelocity = dataArray.get(6);
                            String savedColor = dataArray.get(7);
                            String savedBelt = dataArray.get(8);
                            String savedOffender = dataArray.get(9);
                            String savedResp = dataArray.get(10);



                        }

                    }

                    System.out.println(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        //receiving the callback from the server, if there are any errors, it will save data in the internal memory
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("CallbackGoogle", "onResponse: Submited " + response);
            Toast.makeText(getApplicationContext(), "Dados Salvos na Planilha", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("CallbackGoogle", "onFailure: Failed", t);

            //setting the writable string and the file where data will be saved
            String dataSave = currentDate + ";" + selectedBlock + ";" + selectedFloor + ";" +
                    currentTime + ";" + plateComplete + ";" + vehicleText + ";" + velocityText + ";" +
                    colorText + ";" + beltText + ";" + offenderText + ";" + selectedResponsible + "\n";
            FileOutputStream outputStream;

            try {
                //writting data to the file
                outputStream = openFileOutput(filename, Context.MODE_APPEND);
                outputStream.write(dataSave.getBytes());
                outputStream.close();

                Toast.makeText(getApplicationContext(), "Dados Salvos na Memória", Toast.LENGTH_LONG).show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };
}
