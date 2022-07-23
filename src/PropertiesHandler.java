import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler {
    
    public static Object getProperties(String chave){
        var properties = new Properties();
        InputStream configFile;
        
        try {
            
            configFile = new FileInputStream(new File("local.properties"));
            properties.load(configFile);
            var value = properties.get(chave);
            configFile.close();

            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
