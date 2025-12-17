package co.linxsi.modelo.facturacionelectronica.respuesta;


public class RespuestaWsRecepcionXmlFromERP {
    private String success;
    private String tracer;
    private String errorList;
    private String trackId;
    private String applicationResponseDian;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTracer() {
        return tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    public String getErrorList() {
        return errorList;
    }

    public void setErrorList(String errorList) {
        this.errorList = errorList;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getApplicationResponseDian() {
        return applicationResponseDian;
    }

    public void setApplicationResponseDian(String applicationResponseDian) {
        this.applicationResponseDian = applicationResponseDian;
    }
    
}
