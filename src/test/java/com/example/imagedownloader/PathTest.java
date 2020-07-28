package com.example.imagedownloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class PathTest {

	@Test
	void currentPath() {
		Path relativePath = Paths.get("");
		System.out.println(relativePath.toAbsolutePath().toString());
	}

	@Test
	void readFile() throws FileNotFoundException {
		Path filePath = Paths.get("data", "images.csv");
		File file = new File(filePath.toString());
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}
}
