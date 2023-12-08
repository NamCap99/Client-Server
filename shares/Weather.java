package shares;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The weather entity class contains properties such as id, state, timeZone,...
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String id;
    private String name;
    private String state;
    @JsonProperty("time_zone")
    private String timeZone;
    private double lat;
    private double lon;
    @JsonProperty("local_date_time")
    private String localDateTime;
    @JsonProperty("local_date_time_full")
    private long localDateTimeFull;
    @JsonProperty("air_temp")
    private double airTemp;
    @JsonProperty("apparent_t")
    private double apparentT;
    private String cloud;
    private double dewpt;
    private double press;
    @JsonProperty("rel_hum")
    private long relHum;
    @JsonProperty("wind_dir")
    private String windDir;
    @JsonProperty("wind_spd_kmh")
    private long windSpdKmh;
    @JsonProperty("wind_spd_kt")
    private long windSpdKt;

    public Weather(String id, String name, String state, String timeZone, double lat, double lon, String localDateTime, long localDateTimeFull, double airTemp, double apparentT, String cloud, double dewpt, double press, long relHum, String windDir, long windSpdKmh, long windSpdKt) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.timeZone = timeZone;
        this.lat = lat;
        this.lon = lon;
        this.localDateTime = localDateTime;
        this.localDateTimeFull = localDateTimeFull;
        this.airTemp = airTemp;
        this.apparentT = apparentT;
        this.cloud = cloud;
        this.dewpt = dewpt;
        this.press = press;
        this.relHum = relHum;
        this.windDir = windDir;
        this.windSpdKmh = windSpdKmh;
        this.windSpdKt = windSpdKt;
    }

    public Weather() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getLocalDateTimeFull() {
        return localDateTimeFull;
    }

    public void setLocalDateTimeFull(long localDateTimeFull) {
        this.localDateTimeFull = localDateTimeFull;
    }

    public double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(double airTemp) {
        this.airTemp = airTemp;
    }

    public double getApparentT() {
        return apparentT;
    }

    public void setApparentT(double apparentT) {
        this.apparentT = apparentT;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public double getDewpt() {
        return dewpt;
    }

    public void setDewpt(double dewpt) {
        this.dewpt = dewpt;
    }

    public double getPress() {
        return press;
    }

    public void setPress(double press) {
        this.press = press;
    }

    public long getRelHum() {
        return relHum;
    }

    public void setRelHum(long relHum) {
        this.relHum = relHum;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public long getWindSpdKmh() {
        return windSpdKmh;
    }

    public void setWindSpdKmh(long windSpdKmh) {
        this.windSpdKmh = windSpdKmh;
    }

    public long getWindSpdKt() {
        return windSpdKt;
    }

    public void setWindSpdKt(long windSpdKt) {
        this.windSpdKt = windSpdKt;
    }
}
