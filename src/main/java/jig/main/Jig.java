package jig.main;

/**
 * Main class to be used in jar.
 *
 * Takes in Search term and download directory as args. First run will ask user
 * to properly configure the JIG Config file with Account Key and default download
 * directory if it isn't already configured.
 *
 * Preceding runs will download images to the default directory or directory
 * given as argument if the Config's account key is valid.
 *
 */
public class Jig {

    private final String INVALID_ARGUMENTS_ERROR = "Usage: java -jar path/to/jar <search-term> <download-directory>";

    private void run(String[] args) {
        if (args.length == 2) {
            String searchTerm = args[0];
            String downloadDirectory = args[1];
            if (isValidSearchTerm(searchTerm) && isvalidDownloadDirectory(downloadDirectory)) {

            }
        } else {
            System.err.println(INVALID_ARGUMENTS_ERROR);
        }
    }

    private boolean isValidSearchTerm(String searchTerm) {
       return true;
    }

    private boolean isvalidDownloadDirectory(String downloadDiretory) {
        return true;
    }


}
