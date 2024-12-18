package repository;

import model.User;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {

    private final String filenamePath = "form.txt";
    private final String directoryPath = "data";

    public FileManager() {
    }

    public String getFilenamePath() {
        return filenamePath;
    }

    /**
     * Create the form file and the directory data.
     */
    public void initiateSystemRepos() {
        try {
            File file = new File(filenamePath);
            File directory = new File(directoryPath + "\\" + filenamePath);

            if (!file.exists())
                addField("name", "e-mail", "age", "height");

            if (!directory.exists())
                createParentDirectory(directory);

        } catch (IOException e) {
            System.out.println("Can't create a new form file." + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Get the fields.
     * @return an array list with the file fields.
     */
    public TreeMap<Integer, String[]> getFields() {
        try (BufferedReader br = new BufferedReader(new FileReader(filenamePath))) {
            TreeMap<Integer, String[]> fields = new TreeMap<>();
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                fields.put(index, line.split(" "));
                index++;
            }
            return fields;
        } catch (Exception e) {
            System.out.println("Error to get fields from form.txt " + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    /**
     * Writes a string in a file.
     * @param fields the fields to write.
     */
    public void writeForm(TreeMap<Integer, String[]> fields) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filenamePath))) {
            for (Map.Entry<Integer, String[]> entry : fields.entrySet()) {
                String line = String.join(" ", entry.getValue());
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println("Write file error." + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Reads file's lines.
     * @param file the file to read.
     * @return a <code>User</code> object that contains all file's lines.
     */
    public User<Object> readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            User<Object> p = new User<>(new TreeMap<>());
            String s;
            int index = 0;

            while ((s = br.readLine()) != null) {
                p.field().put(index, s);
                index++;
            }
            return p;
        } catch (IOException e) {
            System.out.println("Can't read file: " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.out.println("Read file error." + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    /**
     * Get the number of fields the form has.
     * @return the number of fields.
     */
    public Integer getFieldsNumber() {
        try (BufferedReader br = new BufferedReader(new FileReader(filenamePath))) {
            ArrayList<String> f = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                f.add(line);
            }

            if (f.isEmpty())
                return 0;

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
    public void addField(String... str) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filenamePath, true))) {
            Integer index = getFieldsNumber() + 1;
            for (String s : str) {
                s = index + ". Enter your " + s + ": ";
                bw.write(s);
                bw.newLine();
                bw.flush();
                index++;
            }
        }
    }

    /**
     * Remove a field from a file.
     * @param key the field's key, which will be removed.
     */
    public void deleteField(Integer key) {
        try {
            TreeMap<Integer, String[]> fields = getFields();
            fields.remove(key);
            deleteForm();
            writeForm(fields);
        } catch (Exception e) {
            System.out.println("Delete file error." + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Delete the form file.
     */
    private void deleteForm() {
        File file = new File(filenamePath);

        if (file.delete()) {
            System.out.println("File successfully deleted.");
        } else {
            System.out.println("File couldn't be deleted.");
        }
    }

    /**
     * Creates a new text file with the person data.
     * @param user user data.
     */
    public void createNewFile(User<Object> user) throws IOException {
        try {
            Object nameField = user.field().get(0);
            if (nameField == null)
                throw new IllegalArgumentException("File name is null.");

            Integer filesQuantity = getFilesNumber();

            if (filesQuantity == null)
                return;
            else
                filesQuantity++;

            String name = nameField.toString().replaceAll(" ", "").toUpperCase();
            String filepath = ("data\\").concat(filesQuantity + "-").concat(name).concat(".txt");
            File file = new File(filepath);
            createParentDirectory(file);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Integer index : user.field().keySet()) {
                    Object value = user.field().get(index);

                    if (value != null) {
                        bw.write(value.toString());
                        bw.newLine();
                        bw.flush();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Create new file error. " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Create a parent directory.
     * @param file the reference file.
     */
    private void createParentDirectory(File file) {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new RuntimeException("Could not create a new directory.");
            }
        }
    }

    /**
     * Get the data inside the fold "data".
     * @return a list of all user's data.
     */
    public ArrayList<User<Object>> getData() {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Data directory does not exist or is not valid.");
            return null;
        }

        File[] files = directory.listFiles(f -> f.isFile() && f.getName().endsWith(".txt"));
        ArrayList<User<Object>> users = new ArrayList<>();

        for (File f : Objects.requireNonNull(files)) {
            User<Object> p = readFile(f);
            users.add(p);
        }
        return users;
    }

    /**
     * Get the number of files in data.
     */
    private Integer getFilesNumber() {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles(f -> f.isFile() && f.getName().endsWith(".txt"));

        if (files == null)
            return null;
        else if (files.length == 0)
            return 0;

        ArrayList<Integer> n = new ArrayList<>();
        for (File f : files) {
            String s = f.getName();

            if (!Character.isDigit(s.charAt(0)))
                throw new NumberFormatException("Digit not found.");
            else
                n.add(Integer.parseInt(String.valueOf(f.getName().charAt(0))));
        }
        return n.stream().mapToInt(Integer::intValue).max().orElseThrow(
                () -> new IllegalArgumentException("Digit not found"));
    }

    /**
     * Get the name or e-mail, based on what the player chose.
     */
    private ArrayList<String> getNameOrEmail(String str) {
        ArrayList<User<Object>> users = getData();
        ArrayList<String> arr = new ArrayList<>();
        int index = 0;

        if (str.chars().anyMatch(c -> c == '@'))
            index = 1;

        for (User<Object> p : users) {
            String r = String.valueOf(p.field().get(index));
            String[] n = r.trim().split("\\s+");

            if (n.length > 1)
                r = n[0] + " " + n[n.length - 1];

            arr.add(r);
        }
        return arr;
    }

    public List<String> userSearch(String str) {
        try {
            ArrayList<String> arr = getNameOrEmail(str);
            return arr.stream()
                    .filter(s -> s.toLowerCase().contains(str.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("User search error." + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }
}
