package repository;

import model.Person;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FileManager {

    private final String filename = "form.txt";

    public FileManager() {

    }

    public String getFilename() {
        return filename;
    }

    /**
     * Get the fields in a file.
     * @return an array list with the file fields.
     */
    public TreeMap<Integer, String[]> getFields() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            TreeMap<Integer, String[]> fields = new TreeMap<>();
            String line;
            int index = 0;

            while ((line = br.readLine()) != null) {
                fields.put(index, line.split(" "));
                index++;
            }
            return fields;
        }
    }

    /**
     * Writes a string in a file.
     * @param fields the fields to write.
     */
    public void writeFile(TreeMap<Integer, String[]> fields) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<Integer, String[]> entry : fields.entrySet()) {
                String line = String.join(" ", entry.getValue());
                bw.write(line);
                bw.newLine();
            }
        }
    }

    /**
     * Writes an array in a file.
     * @param fields the fields to write.
     */
    public void writeFile(ArrayList<String> fields) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String line : fields) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    /**
     * Add a new field to a file.
     * @param str the string which will be added.
     */
    public void addField(String str) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(str);
        }
    }

    /**
     * Remove a field from a file.
     * @param key The field's key, which will be removed.
     */
    public void deleteField(Integer key) throws IOException {
        TreeMap<Integer, String[]> fields = getFields();
        fields.remove(key);
        writeFile(fields);
    }

    /**
     * Create a new file.
     * @param filepath the path of the new file.
     * @param str the content which will be written in the file.
     */
    public void createNewFile(String filepath, ArrayList<String> str) throws IOException {
        File file = new File(filepath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String s : str) {
                bw.write(s);
                bw.newLine();
            }
        }
    }

    /**
     * Delete a file.
     * @param pathname the path of the file which will be deleted.
     */
    public void deleteFile(String pathname) {
        File file = new File(pathname);

        if (file.delete()) {
            System.out.println("File successfully deleted.");
        } else {
            System.out.println("File couldn't be deleted.");
        }
    }

    public void createNewFile(String name, Person person) throws IOException {
        DecimalFormat df = new DecimalFormat("#.##");
        String filepath = ("data\\").concat(name).concat(".txt");
        File file = new File(filepath);

        if (!createParentDirectory(file)) {
            throw new IOException("Could not create a new directory.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(person.name());
            bw.newLine();
            bw.write(person.email());
            bw.newLine();
            bw.write(df.format(person.age()));
            bw.newLine();
            bw.write(df.format(person.height()));
            bw.newLine();
        }
    }

    public boolean createParentDirectory(File file) {
        if (!file.getParentFile().exists()) {
            return file.getParentFile().mkdirs();
        }
        return false;
    }
}
