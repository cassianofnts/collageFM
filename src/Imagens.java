import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Font;

import javax.imageio.ImageIO;

public class Imagens {

    public static BufferedImage geraImagemAlbum(Album album){
        URI endereco = URI.create(album.getCoverImage());

        BufferedImage imagem, novaImagem;
        try {
            imagem = ImageIO.read(endereco.toURL());
            novaImagem = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TRANSLUCENT);
            Graphics2D graphics = novaImagem.createGraphics();
            graphics.drawImage(imagem, 0, 0, null);
            
            var fonte = new Font("Arial", Font.BOLD, 12);
            graphics.setFont(fonte);
            graphics.drawString(album.getArtistName(), 10, 270);
            graphics.drawString(album.getAlbumName(), 10, 290);
            return novaImagem;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        
    }

    public static void generateCollage(ArrayList<Album> listaDeAlbuns, String usuario) {
        BufferedImage collage = new BufferedImage(1500, 1500, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = collage.createGraphics();
        var largura = 0;
        var altura = 0;

        for(Album album : listaDeAlbuns){

            graphics.drawImage(album.getInfoImage(), largura, altura, null);
            largura += 300;
            
            if (largura == 1500){
                largura = 0;
                altura += 300;
            }
        }

        File arquivo = new File("saida", usuario+".png");
        arquivo.mkdir();

        try {
            ImageIO.write(collage, "png", arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
