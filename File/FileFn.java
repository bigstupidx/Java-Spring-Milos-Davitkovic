package com.java.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Controller
public class FileFn {

	public FileFn() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String listFilesInDefaultFolder() throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(""))) {
			String joined = stream
					.map(String::valueOf)
					.filter(path -> !path.startsWith("."))
					.sorted()
					.collect(Collectors.joining(" "));
			System.out.println("List: " + joined);
			return joined;
		}
	}

	/**
	 * 
	 * @param folderName
	 * @return
	 * @throws IOException
	 */
	public String listFilesInSpecificFolder(String folderName) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(folderName))) {
			String joined = stream
					.map(String::valueOf)
					.filter(path -> !path.startsWith("."))
					.sorted()
					.collect(Collectors.joining(" "));
			System.out.println("List: " + joined);
			return joined;
		}
	}

	/**
	 * Return String of founded files which is collection of items separated by space
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public String findSpecificFilesInWholeSystem(String fileName) throws IOException {
		Path start = Paths.get("");
		int maxDepth = 30;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			//			System.out.println("Found: " + joined);
			return joined;
		}
	}

	public String[] findSpecificFilesArrayInWholeSystem(String fileName) throws IOException {
		Path start = Paths.get("");
		int maxDepth = 30;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			System.out.println("Found: " + joined);
			return joined.split(" ");
		}
	}

	public List<Path> findSpecificFilesInWholeSystemBefore(String folderName, String fileExtension, long unixTime) throws IOException {
		//		long cut = LocalDateTime.now().minusWeeks(1).toEpochSecond(ZoneOffset.UTC);
		long cut = unixTime;
		List<Path> outputFiles = new ArrayList<>();
		List<Path> filteredFiles = new ArrayList<>();
		//		Path path = findFolderPath("CSV");
		Path path = findFolderPath(folderName);
		Files.list(path)
		.filter(n -> {
			try {
				return Files.getLastModifiedTime(n)
						.to(TimeUnit.SECONDS) < cut;
			} catch (IOException ex) {
				//handle exception
				return false;
			}
		})
		.filter(n -> String.valueOf(n).endsWith(fileExtension)
				)
		.forEach(n -> {
			//		                Files.delete(n);
			outputFiles.add(n);
		});
		System.out.println("List of Paths: " + outputFiles);
		System.out.println("Number: " + outputFiles.size());
		return outputFiles;

		//		List<Path> filteredFiles = outputFiles.stream()
		//				.filter(path -> String.valueOf(path).endsWith(".csv"))
		//				.collect(Collectors.toList());
		//				Stream<Path> outputPaths = outputFiles.stream()
		//						.filter(x -> x.endsWith(fileExtension));
		//			System.out.println(outputPaths.toString());
		//		String joined = outputPaths
		//				.sorted()
		//				.map(String::valueOf)
		//				.collect(Collectors.joining(" "));
		//		System.out.println(joined);
		
	}

	public String[] findFolder(String folderName) throws IOException {
		//		File[] directories = new File(".").listFiles(File::isDirectory);
		//		System.out.println("Found 1: " + directories);
		Path start = Paths.get("");
		int maxDepth = 30;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(folderName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			if (!joined.isEmpty()) {
				System.out.println("Folder: " + joined + " has found");
			}
			else {
				System.out.println("Folder: " + folderName + " doesn't exist!");
			}
			return joined.split(" ");
		}
	}

	/**
	 * 
	 * @param folderName
	 * @return Path of specified folder
	 * @throws IOException
	 */
	public Path findFolderPath(String folderName) throws IOException {
		String[] inputList = findFolder(folderName);
		Path outputPath = Paths.get(inputList[0]);
		return outputPath;
	}

	public Path findFilePath(String fileName) throws IOException {
		Path outputPath = findSpecificFilePathInWholeSystem(fileName, 0);
		return outputPath;
	}

	/**
	 * 
	 * @param folderName
	 * @return 
	 * @throws IOException
	 */
	public String findFolderPathStr(String folderName) throws IOException {
		String[] inputList = findFolder(folderName);
		String outputPath = inputList[0];
		return outputPath;
	}



	public String findSpecificFileInWholeSystem(String fileName, Integer ordinalNumberOfItem) throws IOException {
		//		System.out.println("findSpecificFileInWholeSystem ");
		String input = findSpecificFilesInWholeSystem(fileName);
		//		System.out.println("Input: " + input);
		String[] output = input.split(" ");
		//		System.out.println("Output: " + output);
		return output[ordinalNumberOfItem];
	}

	/**
	 * Find List of Specified Files in Whole System and select one specified from the list
	 * @param fileName
	 * @param ordinalNumberOfItem
	 * @return Path of desirable file
	 * @throws IOException
	 */
	public Path findSpecificFilePathInWholeSystem(String fileName, Integer ordinalNumberOfItem) throws IOException {
		Path fileNamePath = null;
		String input = findSpecificFilesInWholeSystem(fileName);
		String[] output = input.split(" ");
		fileNamePath = Paths.get(output[ordinalNumberOfItem]);
		return fileNamePath;
	}

	public Path findSpecificFilePathInSpecificFolder(String fileName, String folderName, Integer ordinalNumberOfItem) throws IOException {
		Path fileNamePath = null;
		String input = findSpecificFilesInSpecificFolder(fileName, folderName);
		String[] output = input.split(" ");
		fileNamePath = Paths.get(output[ordinalNumberOfItem]);
		return fileNamePath;
	}

	public String findSpecificFileInSpecificFolder(String fileName, String folderName) throws IOException {
		String fileNamePath = null;
		String input = findSpecificFilesInSpecificFolder(fileName, folderName);
		String[] output = input.split(" ");
		fileNamePath = output[0];
		return fileNamePath;
	}

	/**
	 * Function find all files in specified folder and subfolders (30 is max depth) and filter specified file names in string as space as delimiter between.
	 * @param fileName
	 * @param folderName
	 * @return String of founded files which is collection of items separated by space
	 * @throws IOException
	 */
	public String findSpecificFilesInSpecificFolder(String fileName, String folderName) throws IOException {
		//		Path start = Paths.get(folderName);
		//		int maxDepth = 30;
		//		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		//		String.valueOf(path).endsWith(fileName))) {
		//			String joined = stream
		//					.sorted()
		//					.map(String::valueOf)
		//					.collect(Collectors.joining(" "));
		//			System.out.println("Found: " + joined);
		//			return findSpecificFilessInSpecificFolder(fileName, folderName, " ");
		//		}
		return findSpecificFilessInSpecificFolder(fileName, folderName, " ");
	}

	/**
	 *  
	 * @param fileName
	 * @param folderName
	 * @param delimiter
	 * @return String of founded files which is collection of items separated by specified delimiter
	 * @throws IOException
	 */
	public String findSpecificFilessInSpecificFolder(String fileName, String folderName, String delimiter) throws IOException {
		Path start = Paths.get(folderName);
		int maxDepth = 30;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(delimiter));
			System.out.println("Found: " + joined);
			return joined;
		}
	}

	/**
	 * Return String of founded files which is collection of items separated by specified delimiter
	 * @param fileName
	 * @param folderName
	 * @return
	 * @throws IOException
	 */
	public String findSpecificFilesInSpecificFolder(String fileName, String folderName, String delimiter) throws IOException {
		Path start = Paths.get(folderName);
		int maxDepth = 30;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(delimiter));
			System.out.println("Found: " + joined);
			return joined;
		}
	}

	/**
	 * Return String of founded files which is collection of items separated by space
	 * @param fileName
	 * @param folderName
	 * @param maximalDepth
	 * @return
	 * @throws IOException
	 */
	public String findSpecificFilesInSpecificFolder(String fileName, String folderName, Integer maximalDepth) throws IOException {
		Path start = Paths.get(folderName);
		int maxDepth = maximalDepth;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			System.out.println("Found: " + joined);
			return joined;
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return List of String with all lines of specified file
	 * @throws IOException
	 */
	public List<String> readAllLinesOfFile(String fileName) throws IOException {
		List<String> outputLines = Files.readAllLines(findSpecificFilePathInWholeSystem(fileName, 0));
		return outputLines;
	}

	/**
	 * 
	 * @param fileName
	 * @return String with whole content of specified file
	 * @throws IOException
	 */
	public String readAllLinesOfFileStr(String fileName) throws IOException {
		String outputString = "";
		List<String> outputLines = Files.readAllLines(findSpecificFilePathInWholeSystem(fileName, 0));
		for (String it : outputLines) {
			outputString += it;
		}
		//		System.out.println("File Content: " + outputString);
		return outputString;
	}

	/**
	 * Write specified inputText into specified fileName if thats file exist, 
	 * if that isn't a case, create new file with that file name in root and write into it
	 * @param fileName
	 * @param inputText
	 * @throws IOException
	 */
	public void writeInFile(String fileName, String inputText) throws IOException {
		String listOfFiles = findSpecificFilesInWholeSystem(fileName);
		System.out.println("listOfFiles: " + listOfFiles);
		List<String> lines = new ArrayList<String>();
		lines.add(inputText);
		if (listOfFiles.isEmpty()) {
			try {   // this is for monitoring runtime Exception within the block 

				String content = inputText; // content to write into the file

				File file = new  File(fileName); // here file not created here

				// if file doesnt exists, then create it
				if (!file.exists()) {   // checks whether the file is Exist or not
					file.createNewFile();   // here if file not exist new file created 
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); // creating fileWriter object with the file
				BufferedWriter bw = new BufferedWriter(fw); // creating bufferWriter which is used to write the content into the file
				bw.write(content); // write method is used to write the given content into the file
				bw.close(); // Closes the stream, flushing it first. Once the stream has been closed, further write() or flush() invocations will cause an IOException to be thrown. Closing a previously closed stream has no effect. 

				System.out.println("Writing has Done");

			} catch (IOException e) { // if any exception occurs it will catch
				e.printStackTrace();
			}
		}else {
			Path outputFile = findSpecificFilePathInWholeSystem(fileName, 0);
			Files.write(outputFile, lines, UTF_8, APPEND, CREATE);
		}
		System.out.println("Text put in file: " + fileName);
	}

	/**
	 * Write specified inputText into specified fileName in specific folder if thats file exist, 
	 * if that isn't a case, create new file with that file name in root and write into it
	 * @param fileName
	 * @param folderName
	 * @param inputText
	 * @throws IOException
	 */
	public void writeInFile(String fileName, String folderName, String inputText) throws IOException {
		//		String listOfFiles = findSpecificFilePathInSpecificFolder(fileName, folderName, 0);
		//		String filderPath = "./" + folderName + "/";
		//		Path filderPath = findFolderPath(folderName);
		List<String> lines = new ArrayList<String>();
		lines.add(inputText);

		System.out.println("Current directory" +  System.getProperty("user.dir"));
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + folderName);
			boolean result = false;

			try{
				theDir.mkdir();
				result = true;
			} 
			catch(SecurityException se){
				//handle it
			}        
			if(result) {    
				System.out.println("DIR created");  
			}
		}
		File outputFile = new File (theDir, fileName);
		System.out.println(outputFile.toString());
		//		FileWriter writeOutputFile = new FileWriter(outputFile, true);
		//		System.out.println(writeOutputFile);
		//		String filePath = "\\" + theDir.toPath() + "\\" + fileName;

		Files.write(outputFile.toPath(), lines, UTF_8, APPEND, CREATE);

		//		try {
		//			String filePath = findSpecificFileInSpecificFolder(fileName, folderName);
		//			System.out.println("filePath: " + filePath);
		//			if (filePath.isEmpty()) {
		//				String folderPath = findFolderPathStr(folderName);
		//				Path outputFilePath = Paths.get(folderPath);
		//				System.out.println("folderPath: " + folderPath);
		//				Files.write(outputFilePath, lines, UTF_8, APPEND, CREATE);
		//			}else {
		//				Path outputFilePath = Paths.get(filePath);
		//				System.out.println("filePath: " + filePath);
		//				Files.write(outputFilePath, lines, UTF_8, APPEND, CREATE);
		//			}
		//		}catch (Exception e) {
		//			// TODO: handle exception
		//			Files.createDirectories(Paths.get("/" + folderName));
		//			String folderPath = findFolderPathStr(folderName);
		//			Path outputFilePath = Paths.get(folderPath);
		//			System.out.println("folderPath: " + folderPath);
		//			Files.write(outputFilePath, lines, UTF_8, APPEND, CREATE);
		//		}
		//		

		//		String outputFile = filderPath.toString() + fileName;
		//		System.out.println("outputFile: " + outputFile);
		//		Files.write(filderPath, lines, UTF_8, APPEND, CREATE);
		//		
		//		
		//		if (listOfFiles.isEmpty()) {
		//			try {   // this is for monitoring runtime Exception within the block 
		//
		//		        String content = inputText; // content to write into the file
		//
		//		        File file = new File(fileName); // here file not created here
		//
		//		        // if file doesnt exists, then create it
		//		        if (!file.exists()) {   // checks whether the file is Exist or not
		//		            file.createNewFile();   // here if file not exist new file created 
		//		        }
		//
		//		        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); // creating fileWriter object with the file
		//		        BufferedWriter bw = new BufferedWriter(fw); // creating bufferWriter which is used to write the content into the file
		//		        bw.write(content); // write method is used to write the given content into the file
		//		        bw.close(); // Closes the stream, flushing it first. Once the stream has been closed, further write() or flush() invocations will cause an IOException to be thrown. Closing a previously closed stream has no effect. 
		//
		//		        System.out.println("Writing has Done");
		//
		//		    } catch (IOException e) { // if any exception occurs it will catch
		//		        e.printStackTrace();
		//		    }
		//		}else {
		//			Path outputFile = findSpecificFilePathInWholeSystem(fileName, 0);
		//			Files.write(outputFile, lines, UTF_8, APPEND, CREATE);
		//		}
		//		System.out.println("Text put in file: " + fileName);
	}

	/**
	 * 
	 * @param fileName
	 * @param folderName
	 */
	public void deleteFileInSpecificFolder(String fileName, String folderName) {
		System.out.println("This is non-recovery operation!!! ");
		File theDir = new File(folderName);
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			File outputFile = new File (theDir, fileName);
			if (!outputFile.isDirectory()) {
				outputFile.delete();
				System.out.println("I deleted file: " + fileName + " in directory: " + folderName);
			}
			//			System.out.println(outputFile.toString());
			//			Path outputFilePath = outputFile.toPath();	
			//			Arrays.stream(new File(outputFilePath.toString()).listFiles()).forEach(file->file.delete());
		}
	}

	public void deleteFile(Path filePath) {
		System.out.println("This is non-recovery operation!!! ");
		File outputFile = new File (filePath.toString());
		if (!outputFile.isDirectory()) {
			outputFile.delete();
			System.out.println("I deleted file with path: " + filePath);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @param folderName
	 * @throws IOException 
	 */
	public void deleteFileInSpecificFolderWholeSystemSearch(String fileName, String folderName) throws IOException {
		System.out.println("Delete is non-recovery operation!!! ");
		Path dirPath = findFolderPath(folderName);
		System.out.println("dirPath: " + dirPath);
		File theDir = new File(dirPath.toString());
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			File outputFile = new File (theDir, fileName);
			if (!outputFile.isDirectory()) {
				outputFile.delete();
				System.out.println("I deleted file: " + fileName + " in directory: " + folderName);
			}
			//			System.out.println(outputFile.toString());
			//			Path outputFilePath = outputFile.toPath();	
			//			Arrays.stream(new File(outputFilePath.toString()).listFiles()).forEach(file->file.delete());
		}
	}

	/**
	 * This deletes only files from specified folder (sub-directories are untouched)
	 * @param fileName
	 * @param folderName
	 */
	public void deleteAllFilesInSpeceficFolder(String folderName) {
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			//			File outputFile = new File (theDir, fileName);
			//			System.out.println(outputFile.toString());
			//			Path outputFilePath = outputFile.toPath();	
			Arrays.stream(new File(theDir.toString()).listFiles()).forEach(file->file.delete());
			System.out.println("I deleted directory: " + folderName);
		}
	}


	/**
	 * This deletes specified file from specified folder and sub-directories
	 * @param fileName
	 * @param folderName
	 * @throws IOException 
	 */
	public void deleteAllFilesInSpeceficFolderAndSubFolders(String fileName, String folderName) throws IOException {
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			//			File outputFile = new File (theDir, fileName);
			//			System.out.println(outputFile.toString());
			Path outputFilePath = theDir.toPath();
			Files.walk(outputFilePath)
			.filter(Files::isRegularFile)
			.map(path->path.toFile())
			.forEach(file->file.delete());
			System.out.println("I deleted directory: " + folderName + " and it's subdirectoryes");
		}
	}

	/**
	 * Recursively deletes all sub-folders and files under specified folder
	 * @param fileName
	 * @param folderName
	 * @throws IOException
	 */
	public void deleteAllFilesAndFolderInSpeceficFolderAndSubFolders(String fileName, String folderName) throws IOException {
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			File outputFile = new File (theDir, fileName);
			System.out.println(outputFile.toString());
			Path outputFilePath = outputFile.toPath();
			FileUtils.cleanDirectory(outputFile); 
		}
	}


}