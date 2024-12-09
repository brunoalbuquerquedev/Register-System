package repositiory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager {

    private final String filename = "form.txt";

    public FileManager() {
    }

    /**
     * Get the fields in a file.
     * @return an array list with the file fields.
     */
    public ArrayList<String> getFields() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayList<String> fields = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            fields.add(Arrays.toString(line.split(" ")));
        }
        return fields;
    }

    /**
     * Writes a string in a file.
     * @param str the string which will be written.
     */
    public void writeFile(String str) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(str);
        bw.close();
    }

    /**
     * Writes an array in a file.
     * @param fields the fields to write.
     */
    public void writeFile(ArrayList<String> fields) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        for (String line : fields) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Add a new field to a file.
     * @param str the string which will be added.
     */
    public void addField(String str) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
        bw.write(str);
        bw.close();
    }

    /**
     * Remove a field from a file.
     * @param str The field which will be removed.
     */
    public void deleteField(String str) throws IOException {
        ArrayList<String> fields = getFields();
        fields.removeIf(s -> s.equals(str));
        writeFile(fields);
    }
}
