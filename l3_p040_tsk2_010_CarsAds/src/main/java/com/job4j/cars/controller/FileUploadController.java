package com.job4j.cars.controller;

import com.job4j.cars.entity.CarsPhoto;
import com.job4j.cars.entity.FileMetaData;
import com.job4j.cars.exception.FileStorageException;
import com.job4j.cars.service.CarsPhotoService;
import com.job4j.cars.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/carsad")
public class FileUploadController {

    @Value("${upload.path}")
    private String uploadPath;

    private FileStorageService fileStorageService;
    private CarsPhotoService carsPhotoService;

    public FileUploadController(FileStorageService fileStorageService, CarsPhotoService carsPhotoService) {
        this.fileStorageService = fileStorageService;
        this.carsPhotoService = carsPhotoService;
    }

    /**
     * Controller to display the file upload form on the frontend.
     *
     * @param adid
     * @param photoid
     * @param model
     * @return carsads/uploadPhoto
     */
    @GetMapping("/photo-upload")
    public String uploadFile(@RequestParam("adid") int adid, @RequestParam("photoid") int photoid, final Model model) {
        try {
            List<CarsPhoto> carsPhotos = carsPhotoService.findByAdId(adid);
            model.addAttribute("carsPhotos", carsPhotos);
            model.addAttribute("adid", adid);
            model.addAttribute("photoid", photoid);
        } catch (Exception e) {
            model.addAttribute("error", "Unable to get ad_id. You can't load photo.");
            model.addAttribute("adid", 0);
             model.addAttribute("photoid", 0);
        }
        return "carsads/uploadPhoto";
    }


    /**
     * POST method to accept the incoming file in the application.This method is designed to accept
     * only 1 file at a time.
     *
     * @param file
     * @param redirectAttributes
     * @return succes page
     */
    @PostMapping("/photo-upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("adid") int adid,
                             RedirectAttributes redirectAttributes, Model model) {

        try {
            FileMetaData data = fileStorageService.store(file, adid);
            String vFileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(data.getFileName())
                    .path("/media/download/").toUriString();
            //data.setUrl(fileDownloadUrl(data.getFileName(),"/media/download/"));
            data.setUrl(vFileDownloadUrl);
            model.addAttribute("uploadedFile", data);

            List<CarsPhoto> carsPhotos = carsPhotoService.findByAdId(adid);
            model.addAttribute("carsPhotos", carsPhotos);
            model.addAttribute("adid", adid);
            model.addAttribute("photoid", carsPhotos.get(0).getID());

        } catch (FileStorageException e) {
            model.addAttribute("error", "Unable to store the file");
            model.addAttribute("adid", 0);
            return "carsads/uploadPhoto";
        }
        return "carsads/uploadPhoto";
    }

    @GetMapping("/photo-test")
    public String main(Model model) {

        model.addAttribute("messages", "Hello world");

        return "carsads/test-photo";
    }

    @PostMapping("/photo-test")
    public String add(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            //String uuidFile = UUID.randomUUID().toString();
            //String resultFilename = uuidFile + "." + file.getOriginalFilename();
            String resultFilename = "test.jpg";
            file.transferTo(new File(uploadPath + "/" + resultFilename));

        }

        return "carsads/test-photo";
    }


    /*
     * Controller to allow customer to download the file by passing the file name as the
     * request URL.
     * @param fileName
     * @param response
     * @return
     * @throws FileNotFoundException
     */
    /*@GetMapping("/media/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFIle(@PathVariable String fileName, final HttpServletResponse response) throws FileNotFoundException {
        FileMetaData fileData= fileStorageService.getFile(fileName);
        response.setContentType(fileData.getMime());
       return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(fileData.getResource());
    }*/

}
