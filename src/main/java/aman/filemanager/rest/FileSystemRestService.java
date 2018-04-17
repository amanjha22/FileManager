package aman.filemanager.rest;

import aman.filemanager.data.FileSystemRestResponse;
import aman.filemanager.data.constants.HttpResponseCodes;
import aman.filemanager.exceptions.SystemExcpetion;
import aman.filemanager.service.FileSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(path = "/fileSystem", produces = MediaType.APPLICATION_JSON)
public class FileSystemRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemRestService.class);

    @Autowired
    private FileSystemService fileSystemService;

    @RequestMapping(path = "/dir/open/{directoryId}", method = RequestMethod.GET)
    public FileSystemRestResponse openDirectory(@PathVariable("directoryId") String directoryId) {
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResult(fileSystemService.getAllChildren(directoryId));
        restResponse.setStatus(true);
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setMessage("Done");
        return restResponse;
    }

    @RequestMapping(path = "/dir/create", method = RequestMethod.PUT)
    public FileSystemRestResponse createDirectory(@RequestParam String directoryName, @RequestParam(defaultValue = "ROOT") String parentDirectoryId) throws SystemExcpetion {
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setStatus(true);
        restResponse.setMessage("Done!");
        restResponse.setResult(fileSystemService.addOrUpdateDirectory(directoryName, parentDirectoryId));

        return restResponse;
    }

    @RequestMapping(path = "/find/name/{name}", method = RequestMethod.GET)
    public FileSystemRestResponse findAllByName(@PathVariable("name") String name) {
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setStatus(true);
        restResponse.setResult(fileSystemService.find(name));
        restResponse.setMessage("Done");
        return restResponse;
    }

    @RequestMapping(path = "/dir/all", method = RequestMethod.GET)
    public FileSystemRestResponse getAllDirectories(){
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResult(fileSystemService.getAllDirectories());
        restResponse.setStatus(true);
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setMessage("Done!");
        return restResponse;
    }

    @ExceptionHandler(Exception.class)
    public FileSystemRestResponse handleException(Exception logException) {
        LOGGER.error("Error while processing rest call", logException);
        return FileSystemRestResponse.EXCEPTION_RESPONSE;
    }
}