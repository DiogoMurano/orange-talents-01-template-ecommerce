package br.com.zup.ecommerce.provider.file;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public interface UploadImageProvider {

    String uploadImage(@NotNull MultipartFile file);

}
