package vacancy.rest;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vacancy.entity.Document;
import vacancy.exceptions.DocumentNotFoundException;
import vacancy.model.response.DocumentHeader;
import vacancy.model.response.UploadResponse;
import vacancy.repository.DocumentRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * <br>
 * <br>
 * User: felix <br>
 * Date: 28.04.15 <br>
 * Time: 16:00 <br>
 */

@RestController
@RequestMapping(value = "/api")
@Log4j
public class RestApiController {

    private final JAXBContext xmlContext;

    public RestApiController() throws JAXBException {
        xmlContext = JAXBContext.newInstance(Document.class);
    }

    @Autowired
    private DocumentRepository repository;

    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UploadResponse upload(MultipartFile file) {

        Document document = null;
        try {
            Unmarshaller unmarshaller = xmlContext.createUnmarshaller();
            document = (Document)unmarshaller.unmarshal(file.getInputStream());
            document.setUploadTime(LocalDateTime.now());
            document = repository.saveAndFlush(document);
        } catch (JAXBException e) {
            log.error(e, e);
            return UploadResponse.builder().fail("Не удалось прочитать документ").build();
        } catch (IOException e) {
            log.error(e, e);
            return UploadResponse.builder().fail("Не удалось прочитать документ").build();
        }

        return UploadResponse.builder().ok().document(new DocumentHeader(document)).build();
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public HttpEntity download(@RequestParam long documentId) {
        try {
            Document document = repository.findOne(documentId);
            if (document == null)
                throw new DocumentNotFoundException("Документ не найден");
            Marshaller marshaller = xmlContext.createMarshaller();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
            marshaller.marshal(document, writer);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setContentDispositionFormData("attachment", "document-" + documentId + ".xml");
            return new ResponseEntity(new InputStreamResource(new ByteArrayInputStream(stream.toByteArray())), headers, HttpStatus.OK);
        } catch (JAXBException e) {
            log.error(e, e);
            return null;
        }
    }

}
