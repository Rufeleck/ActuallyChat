package main.java.chat;

/**
 * 
 * @author alex
 *Modified By Samuel Miller
 */
public interface Responder
{
    /**
     * Reads the configuration file from the system.
     *
     */
    void readConfigFile( String relativePath );

    String respond( String inputSentence );
}
