package entities;

import java.util.Locale;

public class PreProcessorToLower implements PreProcessor {

    @Override
    public String preProcessorMsg(String msg) {
        return msg.toLowerCase(Locale.ROOT);
    }
}
