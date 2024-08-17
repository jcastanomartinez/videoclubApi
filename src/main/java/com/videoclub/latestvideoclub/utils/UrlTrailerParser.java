package com.videoclub.latestvideoclub.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlTrailerParser {


    private static final String USER_AGENT = "Chrome/126.0.0.0";

    public static void main(String[] args) {
        String peliculaBuscada="avatar";

        String url = "https://api.themoviedb.org/3/search/movie?query="+peliculaBuscada+"&include_adult=false&language=es-ES&page=1";
        String id = getIdJson(getJsonResponse(url));
        String key = getKeyJson(getJsonResponse("https://api.themoviedb.org/3/movie/" + id + "/videos?language=es-ES"));
        String urlTrailer = "https://www.youtube.com/embed/" + key;
        System.out.println(urlTrailer);
        String urlImage="https://api.themoviedb.org/3/movie/"+id+"/images?language=es";
        getImagePath(getJsonResponse(urlImage));
    }

    private static String getJsonResponse(String url) {
        String jsonResponse = null;
        try {
            // Crear la URL y la conexión
            String bearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxY2U1N2RkYWYwMjYxNzMzMDIzZGRmZDhlMzYwMzM4OCIsIm5iZiI6MTcyMDE3MjAxMy4yOTA3MzQsInN1YiI6IjUxM2I3ZmVmMTljMjk1MDFkYjA4MzE4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Czv2u24RZ6C7v_2JvjMkA9FT09quknnAelZ2I2hXF3E";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurar el método de la solicitud (GET)
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Authorization", "Bearer " + bearerToken);

            // Obtener la respuesta
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // éxito
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Imprimir la respuesta en formato JSON
                jsonResponse = response.toString();
                System.out.println("Respuesta JSON: " + jsonResponse);
            } else {
                System.out.println("Solicitud GET fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }


    private static String getIdJson(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        String id = null;
        try {


            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");
            if (resultsNode.isArray()) {
                JsonNode idNode = resultsNode.get(0).get("id");
                id = idNode.asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private static String getKeyJson(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        String key = null;
        try {


            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");
            if (resultsNode.isArray()) {
                for (JsonNode item : resultsNode) {
                    String name = item.path("name").asText();
                    if (name.toLowerCase().contains("tráiler") || name.toLowerCase().contains("trailer")) {
                        key = item.path("key").asText();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private static String getImagePath(String jsonResponse){
        ObjectMapper objectMapper = new ObjectMapper();
        String imagePath = null;
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("posters");
            if (resultsNode.isArray()) {
                JsonNode file_path = resultsNode.get(0).get("file_path");
                imagePath = file_path.asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("https://image.tmdb.org/t/p/original"+imagePath);
        return "https://image.tmdb.org/t/p/original"+imagePath;
    }

}



