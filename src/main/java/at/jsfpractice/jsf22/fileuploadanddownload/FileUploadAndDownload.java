package at.jsfpractice.jsf22.fileuploadanddownload;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import at.jsfpractice.jsf22.testDB.testDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.primefaces.model.DefaultStreamedContent;
import org.apache.commons.codec.binary.Base64;


@ManagedBean (name="fileUploadAndDownload")
public class FileUploadAndDownload 
{
	private UploadedFile ulFile;
	private StreamedContent SCFile;

	
    public UploadedFile getUlFile() {
		return ulFile;
	}

	public void setUlFile(UploadedFile ulFile) {
		this.ulFile = ulFile;
	}

	public StreamedContent getSCFile() {
		return SCFile;
	}

	public void setSCFile(StreamedContent sCFile) {
		SCFile = sCFile;
	}

	public void upload() 
	{
//        testDAO.testUpload();
        	
//        FacesMessage message = new FacesMessage("Succesful", ulFile.getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
        
	}

	public void test() 
	{
		testDAO.test();
	}
    
    public FileUploadAndDownload() {        
//        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
//        SCFile = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }
    
    
    
    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
    	
//    	Base64.en
    	
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
 
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
}
