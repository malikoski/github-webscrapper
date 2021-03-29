package br.com.malikoski.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TesteConnection {

    public static final String URL = "http://github.com/malikoski/playmusic";







    public static void main(String[] args) throws IOException, InterruptedException {

        String[] url = new String[85];

       url[0] = "https://github.com/malikoski/playmusic";
       url[1] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api";
       url[2] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main";
       url[3] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/java/com/marcelsouzav/udemy/customer/save/api";
       url[4] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/java/com/marcelsouzav/udemy/customer/save/api/config";
       url[5] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/java/com/marcelsouzav/udemy/customer/save/api/gateway";
       url[6] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/java/com/marcelsouzav/udemy/customer/save/api/gateway/http";
       url[7] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/java/com/marcelsouzav/udemy/customer/save/api/gateway/json";
       url[8] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/java/com/marcelsouzav/udemy/customer/save/api/service";
       url[9] = "https://github.com/malikoski/playmusic/tree/master/customer-save-api/src/main/resources";
       url[10] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service";
       url[11] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main";
       url[12] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service";
       url[13] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service/config";
       url[14] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service/domain";
       url[15] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service/gateway";
       url[16] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service/gateway/json";
       url[17] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service/gateway/repository";
       url[18] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/java/com/marcelsouzav/udemy/customer/save/service/service";
       url[19] = "https://github.com/malikoski/playmusic/tree/master/customer-save-service/src/main/resources";
       url[20] = "https://github.com/malikoski/playmusic/tree/master/docker";
       url[21] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api";
       url[22] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main";
       url[23] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/java/com/marcelsouzav/udemy/listener/music/api";
       url[24] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/java/com/marcelsouzav/udemy/listener/music/api/config";
       url[25] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/java/com/marcelsouzav/udemy/listener/music/api/gateway";
       url[26] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/java/com/marcelsouzav/udemy/listener/music/api/gateway/http";
       url[27] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/java/com/marcelsouzav/udemy/listener/music/api/gateway/json";
       url[28] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/java/com/marcelsouzav/udemy/listener/music/api/service";
       url[29] = "https://github.com/malikoski/playmusic/tree/master/listener-music-api/src/main/resources";
       url[30] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service";
       url[31] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main";
       url[32] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service";
       url[33] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service/config";
       url[34] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service/domain";
       url[35] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service/gateway";
       url[36] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service/gateway/json";
       url[37] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service/gateway/repository";
       url[38] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/java/com/marcelsouzav/udemy/listener/music/service/service";
       url[39] = "https://github.com/malikoski/playmusic/tree/master/listener-music-service/src/main/resources";
       url[40] = "https://github.com/malikoski/playmusic/tree/master/music-add-api";
       url[41] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main";
       url[42] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/java/com/marcelsouzav/udemy/music/add/api";
       url[43] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/java/com/marcelsouzav/udemy/music/add/api/config";
       url[44] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/java/com/marcelsouzav/udemy/music/add/api/gateway";
       url[45] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/java/com/marcelsouzav/udemy/music/add/api/gateway/http";
       url[46] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/java/com/marcelsouzav/udemy/music/add/api/gateway/json";
       url[47] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/java/com/marcelsouzav/udemy/music/add/api/service";
       url[48] = "https://github.com/malikoski/playmusic/tree/master/music-add-api/src/main/resources";
       url[49] = "https://github.com/malikoski/playmusic/tree/master/music-add-service";
       url[50] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main";
       url[51] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service";
       url[52] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/config";
       url[53] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/domain";
       url[54] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/enums";
       url[55] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/gateway";
       url[56] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/gateway/json";
       url[57] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/gateway/repository";
       url[58] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/java/com/marcelsouzav/udemy/music/add/service/service";
       url[59] = "https://github.com/malikoski/playmusic/tree/master/music-add-service/src/main/resources";
       url[60] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api";
       url[61] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main";
       url[62] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/java/com/marcelsouzav/udemy/music/upload/api";
       url[63] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/java/com/marcelsouzav/udemy/music/upload/api/config";
       url[64] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/java/com/marcelsouzav/udemy/music/upload/api/gateway";
       url[65] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/java/com/marcelsouzav/udemy/music/upload/api/gateway/http";
       url[66] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/java/com/marcelsouzav/udemy/music/upload/api/gateway/json";
       url[67] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/java/com/marcelsouzav/udemy/music/upload/api/service";
       url[68] = "https://github.com/malikoski/playmusic/tree/master/music-upload-api/src/main/resources";
       url[69] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service";
       url[70] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main";
       url[71] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main/java/com/marcelsouzav/udemy/music/upload/service";
       url[72] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main/java/com/marcelsouzav/udemy/music/upload/service/config";
       url[73] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main/java/com/marcelsouzav/udemy/music/upload/service/enums";
       url[74] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main/java/com/marcelsouzav/udemy/music/upload/service/gateway/json";
       url[75] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main/java/com/marcelsouzav/udemy/music/upload/service/service";
       url[76] = "https://github.com/malikoski/playmusic/tree/master/music-upload-service/src/main/resources";
       url[77] = "https://github.com/malikoski/playmusic/tree/master/play-music";
       url[78] = "https://github.com/malikoski/playmusic/tree/master/play-music/src";
       url[79] = "https://github.com/malikoski/playmusic/tree/master/play-music/src/main";
       url[80] = "https://github.com/malikoski/playmusic/tree/master/play-music/src/main/java/com/marcelsouzav/udemy/playmusic/playmusic";
       url[81] = "https://github.com/malikoski/playmusic/tree/master/play-music/src/main/resources";
       url[82] = "https://github.com/malikoski/playmusic/tree/master/play-music/src/main/resources/mp3";
       url[83] = "https://github.com/malikoski/playmusic/tree/master/play-music/src/test/java/com/marcelsouzav/udemy/playmusic/playmusic";
       url[84] = "https://github.com/malikoski/playmusic/tree/master/test-api";



        HttpClient client = HttpClient.newHttpClient();

        var start = System.currentTimeMillis();
        System.out.println(start);
        HttpRequest request;
        for (String r: url) {
   /*         request = HttpRequest.newBuilder().uri(URI.create(r)).build();
            HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());*/
            try (InputStream in = new URL(r).openStream()) {
                var bytes = in.readAllBytes();
//                var text = new String(bytes, StandardCharsets.UTF_8);
            }
        }
        var end = System.currentTimeMillis();
        System.out.println("Finished: " + (end - start));


    }

/*        var start = System.currentTimeMillis();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://github.com/jhy/jsoup/tree/master/src")).build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

     //   System.out.println(response.body());
        var end = System.currentTimeMillis();
        System.out.println("Tempo: " + (end - start));
        System.out.println("-----------------------------------------------------------------------------------------");

        start = System.currentTimeMillis();

        request = HttpRequest.newBuilder()
                .uri(URI.create("https://github.com/jhy/jsoup/tree/master/src/main/java/org/jsoup"))
                .build();

        System.out.println(response.body());

        end = System.currentTimeMillis();
        System.out.println("Tempo: " + (end - start));
        System.out.println("-----------------------------------------------------------------------------------------");

    }*/


    /*public static void main(String[] args) throws IOException, InterruptedException {

        final StringBuffer buffer = new StringBuffer(2048);
        String line = null;
        String message = new String();

        long start = System.currentTimeMillis();
        URL link = new URL(URL);
        BufferedReader read = new BufferedReader(new InputStreamReader(link.openStream()));

        while ((line = read.readLine()) != null) {
            // buffer.append(line);
            message += line;
        }

        System.out.println("Took: " + (System.currentTimeMillis()-start));

        System.out.println("--------------------------------------------------------------------------------------");


        long startTime = System.currentTimeMillis();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("https://github.com/malikoski/playmusic"))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        long endTime = System.currentTimeMillis();
        long time_seconds = endTime - startTime;

        System.out.println(time_seconds);
        //System.out.println(response.body());

        System.out.println("--------------------------------------------------------------------------------------");

        startTime = System.currentTimeMillis();
        try (InputStream in = new URL(URL).openStream()) {
            var bytes = in.readAllBytes();
            var text = new String(bytes, StandardCharsets.UTF_8);
        }
        endTime = System.currentTimeMillis();
        time_seconds = endTime - startTime;

        System.out.println(time_seconds);

        System.out.println("--------------------------------------------------------------------------------------");




    }
*/
}
