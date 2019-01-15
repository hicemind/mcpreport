package org.hm.demo.mcpreport.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hm.demo.mcpreport.model.Call;
import org.hm.demo.mcpreport.model.Communication;
import org.hm.demo.mcpreport.model.Message;
import org.hm.demo.mcpreport.model.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ExternalRepository implements IExternalRepository{

    Logger logger = LoggerFactory.getLogger(ExternalRepository.class);

    private final String URL = "https://raw.githubusercontent.com/vas-test/test1/master/logs/MCP_DATE.json";

    private final String EMPTY_STRING = "\"\"";
    private final String [] communicationFieldNames= {"message_type","timestamp","origin","destination"};
    private final String [] callFieldNames= {"duration","status_code","status_description"};
    private final String [] messageFieldNames= {"message_content","message_status"};

    @Override
    public Boolean checkIfDataExistsToThisDate(LocalDate date) {
        try {
            return checkIfUrlWithDateExists(date);
        } catch (IOException e) {
            logger.error("Something is wrong... the date is not valid");
            return false;
        }
    }

    @Override
    public AbstractMap.SimpleEntry<Collection<Communication>, Metrics> getDataForThisDate(LocalDate localDate) {
        Metrics metrics = new Metrics();
        try {
            InputStream input = getDataFromHttpServer(localDate);
            ObjectMapper objectMapper = new ObjectMapper();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
            return new AbstractMap.SimpleEntry<Collection<Communication>, Metrics>(buffer.lines()
                    .map(json -> mapJson(objectMapper, json, metrics))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet()), metrics);
        }catch (IOException e){
            //TODO re-throw a business exception, to be transformed in a rest exception to the rest client
            return null; //TODO to be removed
        }
    }

    /**
     * Convert json line in Call or Message and ignore the bad json lines
     * @param objectMapper
     * @param json
     * @return
     */
    private Communication mapJson(ObjectMapper objectMapper, String json, Metrics metrics) {
        Boolean error = false;
        //Validate json
        if (json.contains(EMPTY_STRING)){
            logger.error("Parsing error in: " + json + ", with the error: blank value found");
            metrics.incrementNumberBlankContent();
            error = true;
        }
        boolean communicationFieldsFound = Arrays.stream(communicationFieldNames).allMatch(field -> json.contains(field));
        boolean callFieldsFound = Arrays.stream(callFieldNames).allMatch(field -> json.contains(field));
        boolean messageFieldsFound = Arrays.stream(messageFieldNames).allMatch(field -> json.contains(field));

        if (!communicationFieldsFound || (!callFieldsFound && !messageFieldsFound)){
            logger.error("Parsing error in: " + json + ", with the error: missing field found");
            metrics.incrementNumberMissingFields();
            error = true;
        }

        if (error){
            return null;//If entry contains errors, it'll be ignored
        }

        if (!json.contains("CALL") && !json.contains("MSG")){
            logger.error("Parsing error in: " + json + ", with the error: message_type invalid");
            metrics.incrementNumberRowFieldsErrors();
            return null;//If entry contains errors, it'll be ignored
        }

        try {
            return json.contains("CALL") ? objectMapper.readValue(json, Call.class) : objectMapper.readValue(json, Message.class);
        } catch (JsonParseException e) {
            logger.error("Parsing error in: " + json + ", with the error: "+ e.getOriginalMessage());
            metrics.incrementNumberRowFieldsErrors();
            return null;//If entry contains errors, it'll be ignored
        } catch (JsonMappingException e) {
            logger.error("Parsing error in: " + json + ", with the error: "+ e.getOriginalMessage());
            metrics.incrementNumberRowFieldsErrors();
            return null;//If entry contains errors, it'll be ignored
        } catch (IOException e) {
            logger.error("Parsing error in: " + json, e);
            return null;//If entry contains errors, it'll be ignored
        }
    }

    /**
     * Get Raw data from remote http server
     * @param date
     * @return
     * @throws IOException
     */
    private InputStream getDataFromHttpServer(LocalDate date) throws IOException {
        URL url = getUrl(date);
        URLConnection conn = url.openConnection();

        return conn.getInputStream();
    }

    /**
     * Check if the url exists
     * @param date
     * @return
     * @throws IOException
     */
    private Boolean checkIfUrlWithDateExists(LocalDate date) throws IOException {
        URL url = getUrl(date);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() == 200){
            return true;
        }
        return false;
    }

    /**
     * Create URL with requested date
     * @param date
     * @return
     * @throws IOException
     */
    private URL getUrl(LocalDate date) throws IOException {
        return new URL(URL.replace("DATE",date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))));
    }

}
