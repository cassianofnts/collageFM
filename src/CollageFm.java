import java.util.ArrayList;
import java.util.Scanner;



public class CollageFm {

	public static void main(String[] args) throws Exception {

        String usuario;
        String period;
        String limit;

        Scanner sc = new Scanner(System.in);

        System.out.println("Usuario(obrigatório): ");
        usuario = sc.next();
        System.out.println("period: ");
        period = sc.next();
        System.out.println("limit: ");
        limit = sc.next();

        sc.close();

        HttpLastFmParams parametros = new HttpLastFmParams(usuario);
        parametros.setLimit(limit);
        parametros.setPeriod(period);
        parametros.setFormat("json");

        HttpLastFm httpLastFm = new HttpLastFm(parametros);
        
        ArrayList<Album> listaDeAlbuns = new ArrayList<Album>();
        listaDeAlbuns = JsonHandler.jsonAlbuns(httpLastFm.getResponseJson("user.getTopAlbums"));

        Imagens.generateCollage(listaDeAlbuns, usuario);
        
	}
}
