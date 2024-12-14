package repository;

import model.Person;

import java.io.*;
import java.util.*;

public class FileManager {

    private final String filename = "form.txt";
    private final String directoryPath = "data";
    private int fileNumber = 1;

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

    public Person<Object> readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Person<Object> p = new Person<>(new TreeMap<>());
            String s;
            int index = 0;
            while ((s = br.readLine()) != null) {
                p.field().put(index, s);
                index++;
            }
            return p;
        } catch (IOException e) {
            System.out.println("Can't read file: " + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    public Integer getFieldsNumber() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> f = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                f.add(line);
            }
            String lastField = f.getLast();
            return Integer.parseInt(String.valueOf(lastField.charAt(0)));
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    /**
     * Add a new field to a file.
     * @param str the string which will be added.
     */
    public void addField(String str) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {

            Integer index = getFieldsNumber() + 1;
            String s = index + ". Enter your " + str + ":";
            bw.write(s);
            bw.newLine();
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

    /**
     * Creates a new text file with the person data.
     * @param person person data.
     */
    public void createNewFile(Person<Object> person) throws IOException {
        Object nameField = person.field().get(0);
        if (nameField == null) {
            throw new IllegalArgumentException();
        }

        int filesQuantity = getFilesQuantity();
        String name = nameField.toString().replaceAll(" ", "").toUpperCase();
        String filepath = ("data\\").concat(filesQuantity + "-").concat(name).concat(".txt");
        File file = new File(filepath);
        createParentDirectory(file);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Integer index : person.field().keySet()) {
                Object value = person.field().get(index);
                if (value != null) {
                    bw.write(value.toString());
                    bw.newLine();
                }
            }
        }
    }

    /**
     * Create a parent directory if it does not exist.
     * @param file the reference file.
     */
    private void createParentDirectory(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new IOException("Could not create a new directory.");
            }
        }
    }

    public ArrayList<Person<Object>> getData(FileManager manager) throws IOException {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Data directory does not exist or is not valid.");
            return null;
        }

        File[] files = directory.listFiles(f -> f.isFile() && f.getName().endsWith(".txt"));
        ArrayList<Person<Object>> persons = new ArrayList<>();

        for (File f : Objects.requireNonNull(files)) {
            Person<Object> p = manager.readFile(f);
            persons.add(p);
        }
        return persons;
    }

    public Integer getFilesQuantity() {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles(f -> f.isFile() && f.getName().endsWith(".txt"));

        if (files == null || files.length == 0)
            return 0;

        ArrayList<String> filesNames = new ArrayList<>();

        for (File f : files) {
            filesNames.add(f.getName());
        }
        String lastFileName = filesNames.getLast();
        return Integer.parseInt(String.valueOf(lastFileName.charAt(0)));
    }
}
