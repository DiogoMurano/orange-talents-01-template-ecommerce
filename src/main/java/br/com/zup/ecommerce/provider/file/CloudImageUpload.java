package br.com.zup.ecommerce.provider.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class CloudImageUpload implements UploadImageProvider {

    @Override
    public String uploadImage(MultipartFile file) {
        return "CloudDaVida.com.br/api/image/" + UUID.randomUUID() + file.getOriginalFilename().replace(" ", "-");
    }
}
