/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Micha
 */
public class txtToCsv {
    public static void main(String[] args) throws IOException {
        final Path path = (Path) Paths.get("path", "to", "folder");
        final Path txt = path.resolve("C:\\Users\\Micha\\Desktop\\Books.txt");
        final Path csv = path.resolve("C:\\Users\\Micha\\Desktop\\Books.csv");
        try (
                final Stream<String> lines = Files.lines(txt);
                final PrintWriter pw = new PrintWriter(Files.newBufferedWriter(csv, StandardOpenOption.CREATE_NEW))) {
            lines.map((line) -> line.split("\\|")).
                    map((line) -> Stream.of(line).collect(Collectors.joining(","))).
                    forEach(pw::println);
        }
    }
}
