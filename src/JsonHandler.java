import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {
    
    public static ArrayList<Album> jsonAlbuns(String body){
        ArrayList<Album> listaDeAlbuns = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        
        JsonNode JsonBody;
        try {
            JsonBody = objectMapper.readTree(body);
            JsonNode topAlbunsNode = JsonBody.path("topalbums");
            JsonNode albunsNode   = topAlbunsNode.path("album");
    
            Iterator<JsonNode> albumList = albunsNode.elements();
    
            while(albumList.hasNext()){
    
                JsonNode albumNode = albumList.next();
                Album album = new Album();
    
                album.setAlbumName(albumNode.get("name").textValue());
                album.setPlaycount(albumNode.get("playcount").textValue());
                album.setAlbum(albumNode.get("url").textValue());
    
                
                JsonNode artistNode = albumNode.get("artist");
                album.setArtistName(artistNode.get("name").textValue());
    
                
                JsonNode imageNode  = albumNode.get("image");
                Iterator<JsonNode> imageList = imageNode.elements();
    
                 while(imageList.hasNext()){
                    JsonNode image = imageList.next();
                    if(image.get("size").textValue().equalsIgnoreCase("extralarge"))
                        album.setCoverImage(image.get("#text").textValue());
                }
                
                album.setInfoImage(Imagens.geraImagemAlbum(album));
                listaDeAlbuns.add(album);
            }
            return listaDeAlbuns;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
   }
}
