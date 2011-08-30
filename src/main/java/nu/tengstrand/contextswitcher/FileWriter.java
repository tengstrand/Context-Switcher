package nu.tengstrand.contextswitcher;

/**
 * This class is used to write arbitrary text data to a file.
 */
public class FileWriter {
    private String filename;

    public FileWriter(String filename) {
        this.filename = filename;
    }

    public void appendToFile(String rowInFile) {
        // Fakes a "append row to file" operation.
        System.out.println("  '" + rowInFile + "' was appended to file '" + filename + "'");
    }
}
