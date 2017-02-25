package mx.wedevelop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by colorado on 24/02/17.
 */
public class StorageServiceImp implements StorageService {
    public static final String UPLOADED_FOLDER =
            System.getProperty("user.dir") + "/src/main/resources/static/upload/";


    @Override
    public String store(MultipartFile file) {
        String filePath = "";
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            filePath = "/upload/" + path.getFileName();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
