package mx.wedevelop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by colorado on 24/02/17.
 */
public interface StorageService {
    String store(MultipartFile file);
}
