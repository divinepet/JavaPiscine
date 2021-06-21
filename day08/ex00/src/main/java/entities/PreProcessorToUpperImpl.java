package entities;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcessorMsg(String msg) {
        return msg.toUpperCase(Locale.ROOT);
    }
}
