package servlet.jetty;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonOutputter  {


    public static final String ERROR_RESPONSE = "{\"error\": \"Exception writing command response to JSON\"}";

    private ObjectMapper mapper;

    public JsonOutputter() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public String getContentType() {
        return "application/json";
    }

    public void output(CommandResponse response, PrintWriter pw) {
        try {
            mapper.writeValue(pw, response.toMap());
        } catch (JsonGenerationException e) {
            pw.write(ERROR_RESPONSE);
        } catch (JsonMappingException e) {
            pw.write(ERROR_RESPONSE);
        } catch (IOException e) {
            pw.write(ERROR_RESPONSE);
        }
    }

}
