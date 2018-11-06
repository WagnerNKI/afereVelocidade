package com.example.wishikawa.aferevelocidade;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsGoogleForm {

    @POST("1FAIpQLScp2JYYP11DnEjSGeZbuzUzKnppdLafuZaAsImjtryU5kYqBA/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.1874836275") String data,
            @Field("entry.1070263035") String bloco,
            @Field("entry.1126101690") String piso,
            @Field("entry.14568489") String hora,
            @Field("entry.843552861") String placa,
            @Field("entry.159014847") String veiculo,
            @Field("entry.322366716") String velocidade,
            @Field("entry.1021540080") String cor,
            @Field("entry.994077834") String cintoSeguranca,
            @Field("entry.514266373") String infratores,
            @Field("entry.187677448") String responsavel
    );

}
