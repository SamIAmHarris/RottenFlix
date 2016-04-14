package samiamharris.samlearn.api.gson;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeDeserializer implements JsonDeserializer<BoxOfficeSearchResponse> {

    @Override
    public BoxOfficeSearchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement responseElement = json.getAsJsonObject().get("response");
        return new Gson().fromJson(responseElement, BoxOfficeSearchResponse.class);
    }
}
