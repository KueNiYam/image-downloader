package com.example.imagedownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImageDownloaderApplication {
	static final Path imageDir = Paths.get("images");

	public static void main(String[] args) throws IOException {
		Path filePath = Paths.get("data", "images.csv");
		File file = new File(filePath.toString());

		Scanner scanner = new Scanner(file);

		Map<String, URL> nameImageMap = new HashMap<>();
		while (scanner.hasNextLine()) {
			String raw = scanner.nextLine();
			String[] split = raw.split(",");
			nameImageMap.put(split[0] + ".png", new URL(split[1]));
		}

		for (String name : nameImageMap.keySet()) {
			try {
				HttpURLConnection http = (HttpURLConnection)nameImageMap.get(name).openConnection();
				http.setRequestProperty("User-Agent", "Mozilla/5.0");
				http.setRequestMethod("GET");

				byte[] bytes = http.getInputStream().readAllBytes();
				FileOutputStream fileOutputStream = new FileOutputStream(
					Paths.get(imageDir.toString(), name).toString());
				fileOutputStream.write(bytes);
			} catch (Exception e) {
				System.err.println(name + " 저장 도중 오류가 발생했습니다.");
			}
		}
	}
}
