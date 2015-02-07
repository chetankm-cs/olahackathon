package vedant.olahackathon.network;


/**
 * Created by CHETAN on 20/10/14.
 */
public class URL {

    public static String getFullURL(String webServiceName){
        return WebServicesConstants.baseURL + webServiceName;
    }
}
