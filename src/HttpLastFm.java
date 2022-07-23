import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpLastFm {

    private HttpLastFmParams parametros;
    private String LASTFM_API_ROOT = PropertiesHandler.getProperties("LASTFM_API_ROOT").toString();
    
    public HttpLastFm(HttpLastFmParams parametros){
        this.parametros = parametros;
    }

    public String getResponseJson(String method){


        String params = this.parametros.getParamsString();

        URI endereco = URI.create(LASTFM_API_ROOT+"?method="+method+params);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        
    
        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
            String body = response.body();
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        
    
    }


}
