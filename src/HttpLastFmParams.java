public class HttpLastFmParams {
    private String user;
    private String period;
    private String limit;
    private String format;
    private String apiKey = PropertiesHandler.getProperties("LASTFM_API_KEY").toString();;

    
    public HttpLastFmParams(String user){
        this.user = user;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    
    public String getParamsString(){

        String params = "";
        params += "&user=" + this.user;
        params += "&api_key=" + this.apiKey;
        params += "&format=" + this.format;
        if (!this.limit.isEmpty())
            params += "&limit=" + this.limit;
        if (!this.period.isEmpty())
            params += "&period=" + this.period;

        return params;
    }

    
}
