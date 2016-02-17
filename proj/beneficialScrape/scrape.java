import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class scrape{
	
	public static List<String> scrapeEntire(String inUrl) throws IOException {

        // Make a URL to the web page
        URL url = new URL(inUrl);

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();


        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
       
        List<String> keep = new ArrayList<String>();
        while ((line = br.readLine()) != null) {

            keep.add(line);
        }
        return keep;

    }

    public static void main(String[] args) throws IOException {
    	System.out.print(scrapeEntire("http://www.htmldog.com/examples/").get(12));
    }
}