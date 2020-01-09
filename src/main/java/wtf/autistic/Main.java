package wtf.autistic;

import wtf.autistic.checker.Checker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class Main {

    public Main(String[] args) {
        if (args.length == 0) {
            System.out.println("Example: java -jar DomainChecker.jar path/to/file.txt");
        } else {
            try {
                String available = new File(args[0]).getAbsoluteFile().getParent() + "\\available.txt";
                Path file = Paths.get(available);
                List<String> domains = Files.readAllLines(new File(args[0]).toPath(), StandardCharsets.UTF_8);
                domains.forEach(e -> {
                    if (Checker.isAvailable(e)) {
                        System.out.println(e + " is available.");
                        try {
                            Files.write(file, Collections.singleton(e), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
