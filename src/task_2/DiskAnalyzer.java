package task_2;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class DiskAnalyzer {

    public File findFileWithMaxLetters(String path, char letter) {
        File fileWithMaxLetters = null;
        if (checkDirectory(path)) {
            int mx = 0;
            File directory = new File(path);
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile()) {
                    int localmax = 0;
                    for (int i = 0; i < file.getName().length(); i++) {
                        if (file.getName().toLowerCase().charAt(i) == letter) {
                            localmax++;
                        }
                    }
                    if (localmax > mx) {
                        mx = localmax;
                        fileWithMaxLetters = file;
                    }
                }
            }
        }
        return fileWithMaxLetters;
    }

    public List findFiveBiggestFiles(String path) {
        List<File> fileList = new ArrayList<>();
        if (checkDirectory(path)) {
            File directory = new File(path);
            File[] files = directory.listFiles();
            assert files != null;
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.length() < o2.length())
                        return 1;
                    if (o1.length() > o2.length())
                        return -1;
                    else
                        return 0;
                }
            });
            for (int i = 0, cnt = 0; cnt < 5 && i < files.length; ) {
                while (files[i] != null && files[i].isDirectory()) {
                    i++;
                }
                if (files[i] != null) {
                    fileList.add(files[i]);
                    cnt++;
                    i++;
                }
            }
        }
        return fileList;
    }

    public double findAverageFileSize(String path){
        double averageFileSize = 0;
        if (checkDirectory(path)){
            File directory = new File(path);
            List<File> files = findAllFilesInDirectory(directory);
            for (File file : files) { ;
                averageFileSize += file.length();
            }
            averageFileSize /= files.size();
        }
        return averageFileSize / 1024;
    }

    public Map<Character, GenericTuple> groupingByFirstLetter(String path){
        Map<Character, GenericTuple> letterAndCount = new HashMap<>();
        if(checkDirectory(path)){
            File directory = new File(path);
            for (File file : Objects.requireNonNull(directory.listFiles())){
                if(letterAndCount.get(file.getName().charAt(0)) == null){
                    letterAndCount.put(file.getName().charAt(0), file.isDirectory() ?
                            new GenericTuple<Integer, Integer>(1, 0) : new GenericTuple<Integer, Integer>(0, 1));
                }
                else{
                    GenericTuple last = letterAndCount.get(file.getName().charAt(0));
                    Integer x = (Integer) last.getFirst();
                    Integer y = (Integer) last.getSecond();
                    GenericTuple<Integer, Integer> nov = new GenericTuple<>(x, y);
                    if (file.isDirectory()) {
                        nov.setFirst(nov.getFirst() + 1);
                    } else {
                        nov.setSecond(nov.getSecond() + 1);
                    }
                    letterAndCount.computeIfPresent(file.getName().charAt(0), (key, val) -> val = nov);
                }
            }
        }
        return letterAndCount;
    }

    private List<File> findAllFilesInDirectory(File directory){
        List<File> onlyFiles = new ArrayList<>();
        List<File> allFiles = new ArrayList<>(Arrays.asList(directory.listFiles()));
        for (File file : allFiles){
            if (file.isDirectory()){
                onlyFiles.addAll(findAllFilesInDirectory(file));
            }
            else
                onlyFiles.add(file);
        }
        return onlyFiles;
    }

    private boolean checkDirectory(String path) {
        File file = new File(path);
        if (file.exists())
            return file.isDirectory();
        else
            return false;
    }
}

