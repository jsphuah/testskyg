/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import jp.airlink.common.constants.Const;

import org.apache.log4j.Logger;

/**
 * Class to read the file, create a Properties.
 *
 * @author SIS
 */
public class SystemProperties {
    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(SystemProperties.class);

    /** Property class. */
    private static CProperties com;

    /** objects java.util.Propertes. */
    private static Properties common;

    /**
     * Main method.
     *
     * <pre>
     * Based on the name of the file that was passed to hold the keys and values ​​to the member variables of the class itself
     * </pre>
     *
     * @param args Read file name array of string (absolute path)
     * @throws Exception If the file does not exist, if an unexpected exception occurred
     */
    public static void main(
        String[] args)
        throws Exception {
        com = new CProperties(args[0]);
        common = com.get();
    }

    /**
     * Based on a string key to obtain the corresponding value.
     *
     * @param key String key that corresponds to the value you want
     * @return String corresponding to the key
     */
    static String getEnv(
        String key) {
        if (common == null) {
            logger.trace("[SystemProperties.getEnv] common is null at key[" + key + "]");

            return Const.EMPTY_STR;
        } else if (key == null) {
            logger.trace("[SystemProperties.getEnv] key is null");

            return Const.EMPTY_STR;
        } else if (Const.EMPTY_STR.equals(key)) {
            logger.trace("[SystemProperties.getEnv] key is empty");

            return Const.EMPTY_STR;
        }

        Object tmp = common.get(key);

        if (tmp == null) {
            logger.trace("[SystemProperties.getEnv] value is null at key[" + key + "]");

            return Const.EMPTY_STR;
        } else if (!(tmp instanceof String)) {
            logger.trace("[SystemProperties.getEnv] value is " + tmp.getClass().getName() + " at key[" + key + "]");

            return Const.EMPTY_STR;
        }

        return tmp.toString();
    }

    /**
     * Method for reloading.
     *
     * @throws Exception If an unexpected exception occurred
     */
    public static synchronized void reload()
        throws Exception {
        com.reload();
        common = com.get();
    }

    /**
     * Pass a string key, returns a string containing the value of the corresponding instance after it has been read from the properties file.
     *
     * @param key String key that corresponds to the value
     * @return If there is no corresponding value that corresponds to the key string, returns an empty string instead of null
     */
    static String get(
        String key) {
        return getEnv(key);
    }
}

/**
 * extended class of java.lang.Properties.
 *
 * @author SIS
 */
class CProperties {

    /** Property held for its own class Class (extended). */
    private CJProperties mPro = new CJProperties();

    /** Name of the file to be read. */
    private String mFilename = null;

    /**
     * Read from the File, create a Properties.
     *
     * @param fileName Properties file name
     * @throws Exception If an unexpected exception occurred
     */
    public CProperties(
                       String fileName)
                                       throws Exception {
        synchronized (this) {
            String osName = (String) System.getProperties().get("os.name");
            mFilename = fileName;

            if (osName.indexOf("Linux") != -1) {
                // Linux文字化け対応
                InputStreamReader in = new InputStreamReader(new FileInputStream(fileName), "ISO_8859_1");
                mPro.load(in);
            } else {
                mPro.load(new FileReader(fileName));
            }
        }
    }

    /**
     * Read from the File, create a Properties.
     *
     * @throws Exception If an unexpected exception occurred
     */
    public void reload()
        throws Exception {
        synchronized (this) {
            mPro.clear();

            String osName = (String) System.getProperties().get("os.name");

            if (osName.indexOf("Linux") != -1) {
                InputStreamReader in = new InputStreamReader(new FileInputStream(mFilename), "ISO_8859_1");
                mPro.load(in);
            } else {
                mPro.load(new FileReader(mFilename));
            }
        }
    }

    /**
     * Read from the File, create a Properties (reload).
     *
     * @param path Name of the file to be reloaded (absolute path)
     * @throws Exception If an unexpected exception occurred
     */
    public void reload(
        String path)
        throws Exception {
        mFilename = path;
        this.reload();
    }

    /**
     * I get a Properties object.
     *
     * @return Java.util.Properties class object that was extended for Japanese reading
     */
    public CJProperties get() {
        return mPro;
    }
}

/**
 * Expansion of Japanese class java.util.Properties class.
 *
 * @author SIS
 */
class CJProperties extends Properties {
    /** use serialVersionUID from JDK 1.1.X for interoperability. */
    private static final long serialVersionUID = 4112578634029874840L;

    /** Delimited string [=: \ t \ r \ n \ f]. */
    private static final String keyValueSeparators = "=: \t\r\n\f";

    /** Delimited string [=:]. */
    private static final String strictKeyValueSeparators = "=:";

    /** Delimited string [\ t \ r \ n \ f]. */
    private static final String whiteSpaceChars = " \t\r\n\f";

    /** A property list that contains default values for any keys not found in this property list. */
    private CJProperties defaults;

    /** Creates an empty property list with no default values. */
    public CJProperties() {
        this(null);
    }

    /**
     * Creates an empty property list with the specified defaults.
     *
     * @param defaults the defaults
     */
    public CJProperties(
                        CJProperties defaults) {
        super(defaults);
        this.defaults = defaults;
    }

    /**
     * Reads a property list (key and element pairs) from the input stream.
     * <p>
     * Every property occupies one line of the input stream. Each line is terminated by a line terminator (<code>\n</code> or <code>\r</code> or <code>\r\n</code>). Lines from the
     * input stream are processed until end of file is reached on the input stream.
     * <p>
     * A line that contains only whitespace or whose first non-whitespace character is an ASCII <code>#</code> or <code>!</code> is ignored (thus, <code>#</code> or <code>!</code>
     * indicate comment lines).
     * <p>
     * Every line other than a blank line or a comment line describes one property to be added to the table (except that if a line ends with \, then the following line, if it
     * exists, is treated as a continuation line, as described below). The key consists of all the characters in the line starting with the first non-whitespace character and up
     * to, but not including, the first ASCII <code>=</code>, <code>:</code>, or whitespace character. All of the key termination characters may be included in the key by preceding
     * them with a \. Any whitespace after the key is skipped; if the first non-whitespace character after the key is <code>=</code> or <code>:</code>, then it is ignored and any
     * whitespace characters after it are also skipped. All remaining characters on the line become part of the associated element string. Within the element string, the ASCII
     * escape sequences <code>\t</code>, <code>\n</code>, <code>\r</code>, <code>\\</code>, <code>\"</code>, <code>\'</code>, <code>\ &#32;</code> &#32;(a backslash and a space),
     * and <code>\\u</code> <i>xxxx</i> are recognized and converted to single characters. Moreover, if the last character on the line is <code>\</code>, then the next line is
     * treated as a continuation of the current line; the <code>\</code> and line terminator are simply discarded, and any leading whitespace characters on the continuation line
     * are also discarded and are not part of the element string.
     * <p>
     * As an example, each of the following four lines specifies the key <code>"Truth"</code> and the associated element value <code>"Beauty"</code>:
     * <p>
     *
     * <pre>
     * Truth = Beauty
     * Truth:Beauty
     * Truth                        :Beauty
     * </pre>
     *
     * As another example, the following three lines specify a single property:
     * <p>
     *
     * <pre>
     * fruits                                apple, banana, pear, \
     *                                  cantaloupe, watermelon, \
     *                                  kiwi, mango
     * </pre>
     *
     * The key is <code>"fruits"</code> and the associated element is:
     * <p>
     *
     * <pre>
     * &quot;apple, banana, pear, cantaloupe, watermelon,kiwi, mango&quot;
     * </pre>
     *
     * Note that a space appears before each <code>\</code> so that a space will appear after each comma in the final result; the <code>\</code>, line terminator, and leading
     * whitespace on the continuation line are merely discarded and are <i>not</i> replaced by one or more other characters.
     * <p>
     * As a third example, the line:
     * <p>
     *
     * <pre>
     * cheeses
     * </pre>
     *
     * specifies that the key is <code>"cheeses"</code> and the associated element is the empty string.
     * <p>
     *
     * @param inStream the input stream.
     * @exception IOException if an error occurred when reading from the input stream.
     */

    public synchronized void load(
        InputStreamReader inStream)
        throws IOException {
        BufferedReader in = new BufferedReader(inStream);

        while (true) {
            // Get next line
            String line = in.readLine();

            if (line == null) {
                return;
            }

            if (line.length() > 0) {
                // Continue lines that end in slashes if they are not comments
                char firstChar = line.charAt(0);

                if ((firstChar != '#') && (firstChar != '!')) {
                    while (continueLine(line)) {
                        String nextLine = in.readLine();

                        if (nextLine == null) {
                            nextLine = new String(Const.EMPTY_STR);
                        }

                        String loppedLine = line.substring(0, line.length() - 1);

                        // Advance beyond whitespace on new line
                        int startIndex = 0;

                        for (startIndex = 0; startIndex < nextLine.length(); startIndex++) {
                            if (whiteSpaceChars.indexOf(nextLine.charAt(startIndex)) == -1) {
                                break;
                            }
                        }

                        nextLine = nextLine.substring(startIndex, nextLine.length());
                        line = new String(loppedLine + nextLine);
                    }

                    // Find start of key
                    int len = line.length();
                    int keyStart;

                    for (keyStart = 0; keyStart < len; keyStart++) {
                        if (whiteSpaceChars.indexOf(line.charAt(keyStart)) == -1) {
                            break;
                        }
                    }

                    // Find separation between key and value
                    int separatorIndex;

                    for (separatorIndex = keyStart; separatorIndex < len; separatorIndex++) {
                        char currentChar = line.charAt(separatorIndex);

                        if (currentChar == '\\') {
                            separatorIndex++;
                        } else if (keyValueSeparators.indexOf(currentChar) != -1) {
                            break;
                        }
                    }

                    // Skip over whitespace after key if any
                    int valueIndex;

                    for (valueIndex = separatorIndex; valueIndex < len; valueIndex++) {
                        if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1) {
                            break;
                        }
                    }

                    // Skip over one non whitespace key value separators if any
                    if (valueIndex < len) {
                        if (strictKeyValueSeparators.indexOf(line.charAt(valueIndex)) != -1) {
                            valueIndex++;
                        }
                    }

                    // Skip over white space after other separators if any
                    while (valueIndex < len) {
                        if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1) {
                            break;
                        }

                        valueIndex++;
                    }

                    String key = line.substring(keyStart, separatorIndex);
                    String value = Const.EMPTY_STR;

                    if (separatorIndex < len) {
                        value = line.substring(valueIndex, len);
                    }

                    String osName = (String) System.getProperties().get("os.name");

                    if (osName.indexOf("Linux") != -1) {
                        key = new String(key.getBytes("ISO-8859-1"), "Shift-JIS");
                        value = new String(value.getBytes("ISO-8859-1"), "Shift-JIS");
                    }

                    key = loadConvert(key);
                    value = loadConvert(value);
                    put(key, value);
                }
            }
        }
    }

    /**
     * Returns true if the given line is a line that must be appended to the next line.
     *
     * @param line String current line being read
     * @return Presence or absence of the next line
     */
    private boolean continueLine(
        String line) {
        int slashCount = 0;
        int index = line.length() - 1;

        while ((index >= 0) && (line.charAt(index--) == '\\')) {
            slashCount++;
        }

        return ((slashCount % 2) == 1);
    }

    /**
     * I changed to their original form by changing the char to unicode char to \ "uxxxx were coded, stored special.
     *
     * @param theString The string to be
     * @return The modified string
     */
    private String loadConvert(
        String theString) {
        final int ctlNum4 = 4;
        final int ctlNum10 = 10;

        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);

            if (aChar == '\\') {
                aChar = theString.charAt(x++);

                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    final int loopNum = 4;

                    for (int i = 0; i < loopNum; i++) {
                        aChar = theString.charAt(x++);

                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = ((value << ctlNum4) + aChar) - '0';

                            break;

                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = ((value << ctlNum4) + ctlNum10 + aChar) - 'a';

                            break;

                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = ((value << ctlNum4) + ctlNum10 + aChar) - 'A';

                            break;

                        default:
                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }

                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }

                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }

        return outBuffer.toString();
    }

    /**
     * Searches for the property with the specified key in this property list. If the key is not found in this property list, the default property list, and its defaults,
     * recursively, are then checked. The method returns <code>null</code> if the property is not found.
     *
     * @param key the property key.
     * @return the value in this property list with the specified key value.
     */
    public String getProperty(
        String key) {
        Object oval = super.get(key);
        String sval = null;

        if (oval instanceof String) {
            sval = (String) oval;
        }

        String ret = sval;

        if ((sval == null) && (defaults != null)) {
            ret = defaults.getProperty(key);
        }

        return ret;
    }
}
