package main.java.chat.util;

/**
 * 
 * @author alex
 *Modified By Samuel Miller
 */
public interface VariableInjector
{
    String inject( String inputSentence, String... variables );
}
