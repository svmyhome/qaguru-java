package files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DownloadUploadFilesTests {


    @Test
    @DisplayName("Скачивание и проверка файла при наличии в кнопке href")
    void downloadAndCompareFileWithHref() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']").download();
        System.out.println();

        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("This repository is the home of _JUnit 5_."));
        }
    }

//    @Test
//    @DisplayName("Скачивание и проверка файла без href hf,jnftn gkj[j kexit yt bcgjkmpjdfnm")
//    void downloadAndCompareFileWithoutHref() throws Exception {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//        Configuration.proxyEnabled = true;
//        open("https://github.com/junit-team/junit5/blob/main/README.md");
//        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']").download();
//        System.out.println();
//
//        try(InputStream is = new FileInputStream(downloaded)) {
//            byte[] data = is.readAllBytes();
//            String dataAsString = new String(data, StandardCharsets.UTF_8);
//            Assertions.assertTrue(dataAsString.contains("This repository is the home of _JUnit 5_."));
//        }
//    }

    @Test
    @DisplayName("Загрузка файла в input")
    void uploadFileToInput() {
        open("https://convert.io/ru/document-converter");
        $("input[type=file]").uploadFromClasspath("images/bender.jpg");
        $(".file-name").shouldHave(text("bender.jpg"));
    }

    @Test
    @DisplayName("Загрузка файла")
    void uploadFile() {
        open("https://cdkm.com/ru/");
        $("#filebutton").uploadFromClasspath("images/bender.jpg");
        $(".file-name").shouldHave(text("bender.jpg"));
    }
}
