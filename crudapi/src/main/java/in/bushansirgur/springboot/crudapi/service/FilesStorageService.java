package in.bushansirgur.springboot.crudapi.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import in.bushansirgur.springboot.crudapi.model.FileUpload;

public interface FilesStorageService {
  public void init();

  public FileUpload save(MultipartFile file);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
}