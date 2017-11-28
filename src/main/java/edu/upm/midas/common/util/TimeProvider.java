package edu.upm.midas.common.util;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Created by gerardo on 04/05/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className CurrentDate
 * @see
 */
@Service("date")
public class TimeProvider {

    private DateFormat sdf;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * @return Retorna la fecha de hoy en formato Date
     */
    public Date getNow(){
        //return new Date(117, 05, 29);
        return new Date(new java.util.Date().getTime());
    }

    public long getTimestampNumber(){
        return new Timestamp( System.currentTimeMillis() ).getTime();
    }

    public String getTime(){
        return String.format(new java.util.Date().toString(), dtf);
    }

    /**
     * @return Retirna la fecha de hoy en formato Timestamp
     */
    public Timestamp getTimestamp(){return new Timestamp(System.currentTimeMillis());}

    public DateFormat getSdf() {
        return this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Timestamp convertStringToTimestamp(String timestamp) throws ParseException {
        java.util.Date parseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS").parse(timestamp);
        return new Timestamp(parseDate.getTime());
    }

}
