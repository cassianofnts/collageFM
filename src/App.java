import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;



public class App {

	public static void main(String[] args) throws Exception {


        var properties = new Properties();
        var configFile = new FileInputStream(new File("local.properties"));

        properties.load(configFile);

        String API_KEY = properties.getProperty("API_KEY");
        String API_ROOT = properties.getProperty("API_ROOT");
        
        Scanner sc = new Scanner(System.in);

        System.out.println("usuario: ");
        String usuario = sc.nextLine();
        sc.close();

        ArrayList<Album> listaDeAlbuns = new ArrayList<Album>();

        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("api_key", API_KEY);
        parametros.put("user", usuario);
        parametros.put("period", "1month");
        parametros.put("limit", "25");
        parametros.put("format", "json");

        var metodo = "?method=user.gettopalbums";

        for(Map.Entry<String,String> param: parametros.entrySet()){
            metodo += "&"+param.getKey()+"="+param.getValue();
        };

		URI endereco = URI.create(API_ROOT+metodo);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
    
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
        
        listaDeAlbuns = JsonHandler.jsonAlbuns(body);

        Imagens.generateCollage(listaDeAlbuns, usuario);
        
	}
}
