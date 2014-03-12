package main.java.chat;

/**
 * 
 * @author alex
 *
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
