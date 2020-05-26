package task_2;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer();

        System.out.println("File With Max Letters s - " + diskAnalyzer.findFileWithMaxLetters("D:\\Logo from alot off little balls - Copy", 's').getName());
        System.out.println();

        System.out.println("Five Biggest Files:");
        List<File> list = diskAnalyzer.findFiveBiggestFiles("D:\\Logo from alot off little balls - Copy");
        for(File file : list){
            System.out.println(file.getName() + " " + file.length()/1024 + " KB");
        }
        System.out.println();

        System.out.print("Average File Size - ");
        System.out.printf("%.2f", diskAnalyzer.findAverageFileSize("D:\\Logo from alot off little balls - Copy"));
        System.out.println(" KB\n");

        System.out.println("grouping By First Letter:");
        for (Map.Entry<Character, GenericTuple> map : diskAnalyzer.groupingByFirstLetter("D:\\Logo from alot off little balls - Copy").entrySet()){
            System.out.println(map.getKey() + " =" + map.getValue().toString());
        }
    }
}
