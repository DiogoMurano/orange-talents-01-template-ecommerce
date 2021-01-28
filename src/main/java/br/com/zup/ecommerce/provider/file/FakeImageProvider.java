package br.com.zup.ecommerce.provider.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FakeImageProvider implements UploadImageProvider {

    @Override
    public String uploadImage(MultipartFile file) {
        return "mercadolivre.com.br/api/image/" + UUID.randomUUID() + file.getOriginalFilename().replace(" ", "-");
    }
}
